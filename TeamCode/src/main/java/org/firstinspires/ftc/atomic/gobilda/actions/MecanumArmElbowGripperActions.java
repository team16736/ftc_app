package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Make sure to have the following:
 *
 * 1. Hardware config
 * 2. Setup direction of motors
 * 3. Action method to do something (hookUpDown, drive, etc.,)
 * 4. Helper methods (stop, brake, leftTurn, rightTurn, etc.,)
 *
 * Purpose:
 * 1. Arm move up and down
 * 2. Elbow open and close
 * 3. Grabber open and close
 */
public class MecanumArmElbowGripperActions {

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    private DcMotor armDrive;
    private Servo elbowServo;
    private Servo grabberServo;

    double armPower;


    // Constructor
    public MecanumArmElbowGripperActions(Telemetry tele, HardwareMap hardware) {

        this.telemetry = tele;
        this.hardwareMap = hardware;

        // 1. Hardware config
        armDrive  = hardwareMap.get(DcMotor.class, "arm");
        elbowServo = hardwareMap.get(Servo.class, "elbow_servo");
        grabberServo = hardwareMap.get(Servo.class, "grabber_servo");

        // 2. Set direction
        armDrive.setDirection(DcMotor.Direction.REVERSE);
        elbowServo.setDirection(Servo.Direction.REVERSE);
        grabberServo.setDirection(Servo.Direction.FORWARD);
    }

    public void armUpDown(double armVal) {

        double maxUp = 0.5;
        double maxDown = -0.3;

        armPower = Range.clip(armVal, maxDown, maxUp);
        armDrive.setPower(armPower);
    }

    public void elbowOperClose() {
    }

    public void grabberOpenClose() {

    }
}