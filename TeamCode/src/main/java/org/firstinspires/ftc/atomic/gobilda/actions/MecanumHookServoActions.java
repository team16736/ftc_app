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
    private double right_hook_position = 1.0;

    private double HOOK_MIN_POSITION = 0;
    private double HOOK_MAX_POSITION = 1.0;

    // Constructor
    public MecanumHookServoActions(Telemetry opModeTelemetry, HardwareMap opModeHardware) {

        this.telemetry = opModeTelemetry;
        this.hardwareMap = opModeHardware;

        // 1. Hardware config
        left_hook = hardwareMap.get(Servo.class, ConfigConstants.SERVO_LEFT);
        right_hook = hardwareMap.get(Servo.class, ConfigConstants.SERVO_RIGHT);

        // 2. Set direction
        left_hook.setDirection(Servo.Direction.REVERSE);
        right_hook.setDirection(Servo.Direction.REVERSE);

        // 3. Set beginning position
        left_hook.setPosition(left_hook_position);
        right_hook.setPosition(right_hook_position);
    }

    public void hookUpDown(boolean leftPadPressed, boolean rightPadPressed) {

        if (leftPadPressed) {

            left_hook_position = left_hook_position + 0.5;
            right_hook_position = right_hook_position - 0.5;
            telemetry.addData("Left Hook - Position x: ", left_hook_position);

        } else if (rightPadPressed) {

            left_hook_position = left_hook_position - 0.5;
            right_hook_position = right_hook_position + 0.5;
            telemetry.addData("Right Hook - Position y: ", right_hook_position);
        }

        left_hook.setPosition(Range.clip(left_hook_position, HOOK_MIN_POSITION, HOOK_MAX_POSITION));
        right_hook.setPosition(Range.clip(right_hook_position, HOOK_MIN_POSITION, HOOK_MAX_POSITION));
        telemetry.update();
    }

}