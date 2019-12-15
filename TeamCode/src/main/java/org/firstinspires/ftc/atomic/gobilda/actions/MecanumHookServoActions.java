package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Make sure to have the following:
 *
 * 1. Hardware config
 * 2. Setup direction of servos
 * 3. Action method to do something (hookUpDown, drive, etc.,)
 * 4. Helper methods (stop, brake, leftTurn, rightTurn, etc.,)
 *
 * Purpose: Hooks move up and down
 */
public class MecanumHookServoActions {

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public Servo left_hook;
    public Servo right_hook;

    private double left_hook_position = 0.0;
    private double right_hook_position = 0.0;

    private double MIN_POSITION  = 0;
    private double MAX_POSITION  = 0.8;

    // Constructor
    public MecanumHookServoActions(Telemetry tele, HardwareMap hardware) {

        this.telemetry = tele;
        this.hardwareMap = hardware;

        // 1. Hardware config
        left_hook = hardwareMap.get(Servo.class, ConfigConstants.SERVO_LEFT);
        right_hook = hardwareMap.get(Servo.class, ConfigConstants.SERVO_RIGHT);

        // 2. Set direction
        setServoDirection_Forward();
    }

    public void hookUpDown(boolean leftButtonPressed, boolean rightButtonPressed) {

        if (leftButtonPressed) {

            left_hook_position = left_hook_position +0.01;
            right_hook_position = right_hook_position -0.01;
            telemetry.addData("Left Hook - Position x: ", left_hook_position);

        } else if (rightButtonPressed) {

            left_hook_position = left_hook_position -0.01;
            right_hook_position = right_hook_position +0.01;
            telemetry.addData("Right Hook - Position y: ", right_hook_position);
        }

        left_hook.setPosition(Range.clip(left_hook_position, MIN_POSITION, MAX_POSITION));
        right_hook.setPosition(Range.clip(right_hook_position, MIN_POSITION, MAX_POSITION));
        telemetry.update();
    }

    public void setServoDirection_Forward() {
        left_hook.setDirection(Servo.Direction.FORWARD);
        right_hook.setDirection(Servo.Direction.FORWARD);
    }

}