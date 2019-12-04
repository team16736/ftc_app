package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumHookServoActions {

    public Servo left_hook = null;
    public Servo right_hook = null;

    private double hookServoPosition;
    private double MIN_POSITION  = 0;
    private double MAX_POSITION  = 0.8;

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public MecanumHookServoActions(Telemetry telemetry, HardwareMap hardwareMap ) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        initializeHardware();

        setServoDirection_Forward();
    }

    public void initializeHardware() {

        left_hook = hardwareMap.get(Servo.class, ConfigConstants.SERVO_LEFT);
        right_hook = hardwareMap.get(Servo.class, ConfigConstants.SERVO_RIGHT);

        //left_grip.setPosition(MIN_POSITION);
        //right_grip.setPosition(MAX_POSITION);
    }

    public void hookMove(boolean leftButtonPressed, boolean rightButtonPressed) {

        if (leftButtonPressed) {

            hookServoPosition = hookServoPosition + 0.01;
            telemetry.addData("Position x: ", hookServoPosition);

        } else if (rightButtonPressed) {

            hookServoPosition = hookServoPosition - 0.01;
            telemetry.addData("Position y: ", hookServoPosition);
        }

        left_hook.setPosition(Range.clip(hookServoPosition * - 1.2, MIN_POSITION, MAX_POSITION));
        right_hook.setPosition(Range.clip(hookServoPosition, MIN_POSITION, MAX_POSITION));

        telemetry.update();
    }

    public void setServoDirection_Forward() {
        left_hook.setDirection(Servo.Direction.FORWARD);
        right_hook.setDirection(Servo.Direction.FORWARD);
    }

    public void setServoDirection_Reverse() {
        left_hook.setDirection(Servo.Direction.REVERSE);
        right_hook.setDirection(Servo.Direction.REVERSE);
    }

//    public void stop() {
//        left_grip.setPosition(0.0);
//        right_grip.setPosition(0.0);
//    }
}