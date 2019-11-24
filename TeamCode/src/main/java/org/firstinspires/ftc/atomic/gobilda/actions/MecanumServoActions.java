package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumServoActions {

    private Servo servo_left = null;
    private Servo servo_right = null;

    private double hookServoPosition;
    private double MIN_POSITION  = 0;
    private double MAX_POSITION  = 0.8;

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public MecanumServoActions(Telemetry telemetry, HardwareMap hardwareMap ) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        initializeHardware();

        setServoDirection_Forward();
    }

    public void initializeHardware() {

        servo_left = hardwareMap.get(Servo.class, ConfigConstants.SERVO_LEFT);
        servo_right= hardwareMap.get(Servo.class, ConfigConstants.SERVO_RIGHT);

        //servo_left.setPosition(MIN_POSITION);
        //servo_right.setPosition(MAX_POSITION);
    }

    public void hookMove(boolean leftButtonPressed, boolean rightButtonPressed) {

        if (leftButtonPressed) {

            hookServoPosition = hookServoPosition + 0.01;
            telemetry.addData("Position x: ", hookServoPosition);

        } else if (rightButtonPressed) {

            hookServoPosition = hookServoPosition - 0.01;
            telemetry.addData("Position y: ", hookServoPosition);
        }

        servo_left.setPosition(Range.clip(hookServoPosition * - 1.2, MIN_POSITION, MAX_POSITION));
        servo_right.setPosition(Range.clip(hookServoPosition, MIN_POSITION, MAX_POSITION));

        telemetry.update();
    }

    public void setServoDirection_Forward() {
        servo_left.setDirection(Servo.Direction.FORWARD);
        servo_right.setDirection(Servo.Direction.FORWARD);
    }

    public void setServoDirection_Reverse() {
        servo_left.setDirection(Servo.Direction.REVERSE);
        servo_right.setDirection(Servo.Direction.REVERSE);
    }

}