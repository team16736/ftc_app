package org.firstinspires.ftc.atomic.gobilda.actions;

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
 * Purpose: Gripper open/close
 */
public class MecanumGripperActions {

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public Servo left_grip = null;
    public Servo right_grip = null;

    private double gripperServoPosition;

    private double MIN_POSITION  = 0;
    private double MAX_POSITION  = 0.8;

    // Constructor
    public MecanumGripperActions(Telemetry tele, HardwareMap hardware) {

        this.telemetry = tele;
        this.hardwareMap = hardware;

        // 1. Hardware config
        left_grip = hardwareMap.get(Servo.class, ConfigConstants.LEFT_GRIP);
        right_grip = hardwareMap.get(Servo.class, ConfigConstants.RIGHT_GRIP);

        // 2. Set direction
        setGripperDirection_Forward();
    }

    public void gripperMove(boolean leftButtonPressed, boolean rightButtonPressed) {

        if (leftButtonPressed) {

            gripperServoPosition = gripperServoPosition + 0.01;
            telemetry.addData("Position x: ", gripperServoPosition);

        } else if (rightButtonPressed) {

            gripperServoPosition = gripperServoPosition - 0.01;
            telemetry.addData("Position y: ", gripperServoPosition);
        }

        left_grip.setPosition(Range.clip(gripperServoPosition * - 1.2, MIN_POSITION, MAX_POSITION));
        right_grip.setPosition(Range.clip(gripperServoPosition, MIN_POSITION, MAX_POSITION));

        telemetry.update();
    }

    public void setGripperDirection_Forward() {
        left_grip.setDirection(Servo.Direction.FORWARD);
        right_grip.setDirection(Servo.Direction.FORWARD);
    }

}