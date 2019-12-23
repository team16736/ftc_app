package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Purpose:
 * 1. Arm move up and down
 * 2. Elbow open and close
 * 3. Grabber open and close
 */
public class MecanumArmElbowGripperActions {

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    private DcMotor armMotor;
    private Servo elbowServo;
    private Servo grabberServo;

    private int arm_current_position = 50;
    private int ARM_MIN_POSITION = -45;
    private int ARM_MAX_POSITION = 75;

    private double grabber_position = 0.0;
    private double GRABBER_MIN_POSITION = 0;
    private double GRABBER_MAX_POSITION = 0.8;

    private double elbow_position = 0.0;
    private double ELBOW_MIN_POSITION = -0.5;
    private double ELBOW_MAX_POSITION = 0.5;

    // Constructor
    public MecanumArmElbowGripperActions(Telemetry tele, HardwareMap hardware) {

        this.telemetry = tele;
        this.hardwareMap = hardware;

        // 1. Hardware config
        armMotor = hardwareMap.get(DcMotor.class, ConfigConstants.ARM);
        elbowServo = hardwareMap.get(Servo.class, ConfigConstants.ELBOW_SERVO);
        grabberServo = hardwareMap.get(Servo.class, ConfigConstants.GRABBER_SERVO);

        // 2. Set direction
        armMotor.setDirection(DcMotor.Direction.REVERSE);
        elbowServo.setDirection(Servo.Direction.REVERSE);
        grabberServo.setDirection(Servo.Direction.FORWARD);

        //Not tested
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void armUpDown(boolean armUp, boolean armDown) {

        arm_current_position = armMotor.getCurrentPosition();
        telemetry.addData("Arm CURRENT: ", armMotor.getCurrentPosition() + " Timestamp: " + System.currentTimeMillis());

        if(armUp) {

            arm_current_position = arm_current_position + 10;

            armMotor.setTargetPosition(arm_current_position);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armMotor.setPower(0.2);
            telemetry.addData("Arm: ", "UP");

        } else if(armDown) {

            arm_current_position = arm_current_position - 10;

            armMotor.setTargetPosition(arm_current_position);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armMotor.setPower(0.2);
            telemetry.addData("Arm: ", "DOWN");
        }

        telemetry.addData("Arm TARGET: ", arm_current_position + " Timestamp: " + System.currentTimeMillis());
    }


    public void armUpDown(double armVal) {

        double armPower = Range.clip(armVal, ARM_MIN_POSITION, ARM_MAX_POSITION);

        armMotor.setPower(armPower);

        telemetry.addData("Arm power: ", armPower);
    }

    public void grabberOpenClose(boolean grabberClose, boolean grabberOpen) {

        if (grabberOpen) {

            grabber_position = grabber_position + 0.2;
            grabberServo.setPosition(Range.clip(grabber_position, GRABBER_MIN_POSITION, GRABBER_MAX_POSITION));

        } else if (grabberClose) {

            grabber_position = grabber_position - 0.2;
            grabberServo.setPosition(Range.clip(grabber_position, GRABBER_MIN_POSITION, GRABBER_MAX_POSITION));
        }

        telemetry.addData("Grabber position: ", grabber_position);
    }


    public void elbowOpenClose(boolean elbowOpen, boolean elbowClose) {

        if (elbowClose) {

            elbow_position = elbow_position + 0.1;
            elbowServo.setPosition(Range.clip(elbow_position, ELBOW_MIN_POSITION, ELBOW_MAX_POSITION));

        } else if (elbowOpen) {

            elbow_position = elbow_position - 0.1;
            elbowServo.setPosition(Range.clip(elbow_position, ELBOW_MIN_POSITION, ELBOW_MAX_POSITION));
        }

        telemetry.addData("Elbow position: ", elbow_position);
//        telemetry.update();
    }
}
