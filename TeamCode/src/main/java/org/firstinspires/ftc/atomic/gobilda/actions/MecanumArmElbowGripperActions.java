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

    private double ARM_MIN_POSITION = -0.5;
    private double ARM_MAX_POSITION = 0.5;

    private double elbow_position = 0.0;
    private double ELBOW_MIN_POSITION = 0;
    private double ELBOX_MAX_POSITION = 0.5;

    private double grabber_position = 0.0;
    private double GRABBER_MIN_POSITION = 0;
    private double GRABBER_MAX_POSITION = 0.8;


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
    }


    public void armUpDown(double armVal) {

        double armPower = Range.clip(armVal, ARM_MIN_POSITION, ARM_MAX_POSITION);

        armMotor.setPower(armPower);

        telemetry.addData("Arm power: ", armPower);
    }


    //0.01 seems to be fast. Changed to 0.005 so it can open/fold elbow very slowly
    public void elbowOpenClose(boolean elbowOpen, boolean elbowClose) {

        if (elbowOpen) {

            elbow_position = elbow_position + 0.005;
            telemetry.addData("Elbow Position X: ", elbow_position);

        } else if (elbowClose) {

            elbow_position = elbow_position - 0.005;
            telemetry.addData("Elbow Position B: ", elbow_position);
        }

        elbowServo.setPosition(Range.clip(elbow_position, ELBOW_MIN_POSITION, ELBOX_MAX_POSITION));
    }


    public void grabberOpenClose(boolean grabberClose, boolean grabberOpen) {

        if (grabberClose) {

            grabber_position = grabber_position + 0.01;
            telemetry.addData("Grabber Position LB: ", grabber_position);

        } else if (grabberOpen) {

            grabber_position = grabber_position - 0.01;
            telemetry.addData("Grabber Position RB: ", grabber_position);
        }

        grabberServo.setPosition(Range.clip(grabber_position, GRABBER_MIN_POSITION, GRABBER_MAX_POSITION));
    }

}
