package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumGripperActions {

    public Servo left_grip = null;
    public Servo right_grip = null;

    private double gripperServoPosition;
    private double MIN_POSITION  = 0;
    private double MAX_POSITION  = 0.8;

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public MecanumGripperActions(Telemetry telemetry, HardwareMap hardwareMap ) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        initializeHardware();

        setGripperDirection_Forward();
    }

    public void initializeHardware() {

        left_grip = hardwareMap.get(Servo.class, ConfigConstants.LEFT_GRIP);
        right_grip = hardwareMap.get(Servo.class, ConfigConstants.RIGHT_GRIP);
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

    public void setGripperDirection_Reverse() {
        left_grip.setDirection(Servo.Direction.REVERSE);
        right_grip.setDirection(Servo.Direction.REVERSE);
    }

}