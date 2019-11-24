package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MecanumDriveWheelActions {

    public DcMotor motorFrontLeft;
    public DcMotor motorFrontRight;
    public DcMotor motorBackLeft;
    public DcMotor motorBackRight;

    //the amount to throttle the power of the motors
    private static final double THROTTLE = 0.8;

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    /**
     * Creates a mecanum motor using the 4 individual motors passed in as the arguments
     * @param telemetry : Telemetry to send messages to the Driver Control
     * @param hardwareMap : Hardware Mappings
     */
    public MecanumDriveWheelActions(Telemetry telemetry, HardwareMap hardwareMap ) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        initializeHardware(); //initialize 4 dc motors

        setMotorDirection_Forward();
    }

    /**
     * Use the DCMotor names provided in the config.
     */
    public void initializeHardware() {
        motorFrontLeft = hardwareMap.get(DcMotor.class, ConfigConstants.FRONT_LEFT);
        motorFrontRight = hardwareMap.get(DcMotor.class, ConfigConstants.FRONT_RIGHT);
        motorBackRight = hardwareMap.get(DcMotor.class, ConfigConstants.BACK_RIGHT);
        motorBackLeft = hardwareMap.get(DcMotor.class, ConfigConstants.BACK_LEFT);
    }


    /**
     * Drive method to throttle the power
     * @param speedX - the x value of the joystick controlling strafe
     * @param speedY - the y value of the joystick controlling the forward/backward motion
     * @param rotation - the x value of the joystick controlling the rotation
     */
    public void drive(double speedX, double speedY, double rotation){

        double throttledX = speedX * THROTTLE;
        double throttledY = speedY * THROTTLE;
        double throttledRotation = rotation * THROTTLE;

        driveUsingJoyStick(throttledX, throttledY, throttledRotation);
    }

    /**
     * This function makes the mecanum motor drive using the joystick
     * @param speedX - the x value of the joystick controlling straf
     * @param speedY - the y value of the joystick controlling the forward/backwards motion
     * @param rotation - the x value of the joystick controlling the rotation
     */
    public void driveUsingJoyStick(double speedX, double speedY, double rotation) {

        telemetry.addData("Straf Speed X: = ", speedX);
        telemetry.addData("Motion Speed Y: = ", speedY);
        telemetry.addData("Rotations: = ", rotation);
        telemetry.update();

        double frontLeftValue = speedX + speedY + rotation;
        double frontRightValue = -speedX + speedY - rotation;
        double backLeftValue= -speedX + speedY + rotation;
        double backRightValue = speedX + speedY - rotation;

        double max = getMaxPower(frontLeftValue, frontRightValue, backLeftValue, backRightValue);
        if (max > 1) {
            frontLeftValue = frontLeftValue / max;
            frontRightValue = frontRightValue / max;
            backLeftValue = backLeftValue / max;
            backRightValue = backRightValue / max;
        }

        motorFrontRight.setPower(frontRightValue);
        motorFrontLeft.setPower(frontLeftValue);
        motorBackRight.setPower(backRightValue);
        motorBackLeft.setPower(backLeftValue);

        telemetry.addData("frontRightValue: = ", frontRightValue);
        telemetry.addData("frontLeftValue: = ", frontLeftValue);
        telemetry.addData("backRightValue: = ", backRightValue);
        telemetry.addData("backLeftValue: = ", backLeftValue);

    }

    private double getMaxPower(double frontLeftValue, double frontRightValue, double backLeftValue, double backRightValue) {
        List<Double> valueList = new LinkedList<>();
        valueList.add(frontLeftValue);
        valueList.add(frontRightValue);
        valueList.add(backLeftValue);
        valueList.add(backRightValue);

        return Collections.max(valueList);
    }

    public void setMotorDirection_Forward() {
        motorBackLeft.setDirection(ConfigConstants.REVERSE);
        motorBackRight.setDirection(ConfigConstants.REVERSE);
        motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        motorFrontRight.setDirection(ConfigConstants.FORWARD);
    }

    public void setMotorDirection_Reverse() {
        motorBackLeft.setDirection(ConfigConstants.REVERSE);
        motorBackRight.setDirection(ConfigConstants.FORWARD);
        motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        motorFrontRight.setDirection(ConfigConstants.FORWARD);
    }

    public void setMotorDirection_StrafeLeft() {
        motorBackLeft.setDirection(ConfigConstants.FORWARD);
        motorBackRight.setDirection(ConfigConstants.FORWARD);
        motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        motorFrontRight.setDirection(ConfigConstants.REVERSE);
    }

    public void setMotorDirection_StrafeRight() {
        motorBackLeft.setDirection(ConfigConstants.REVERSE);
        motorBackRight.setDirection(ConfigConstants.REVERSE);
        motorFrontLeft.setDirection(ConfigConstants.FORWARD);
        motorFrontRight.setDirection(ConfigConstants.FORWARD);
    }

    public void applyBrake() {
        motorBackLeft.setZeroPowerBehavior(ConfigConstants.BRAKE);
        motorBackRight.setZeroPowerBehavior(ConfigConstants.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(ConfigConstants.BRAKE);
        motorFrontRight.setZeroPowerBehavior(ConfigConstants.BRAKE);
    }

    public void stop() {
        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
    }

    public void forwardByTime(LinearOpMode opMode, double speed, double time) {
        motorBackLeft.setPower(speed);
        motorBackRight.setPower(speed);
        motorFrontRight.setPower(speed);
        motorFrontLeft.setPower(speed);
        opMode.sleep((long)(1000 * time));
    }

    public void reverseByTime(LinearOpMode opMode, double speed, double time) {
        motorBackLeft.setPower(-speed);
        motorBackRight.setPower(-speed);
        motorFrontLeft.setPower(-speed);
        motorFrontRight.setPower(-speed);
        opMode.sleep((long)(1000 * time));
    }

    public void strafeRightByTime(LinearOpMode opMode, double speed, double time) {
        motorBackLeft.setPower(-speed);
        motorBackRight.setPower(speed);
        motorFrontLeft.setPower(speed);
        motorFrontRight.setPower(-speed);
        opMode.sleep((long)(1000 * time));
    }

    public void strafeLeftByTime(LinearOpMode opMode, double speed, double time) {
        motorFrontLeft.setPower(-speed);
        motorFrontRight.setPower(speed);
        motorBackLeft.setPower(speed);
        motorBackRight.setPower(-speed);
        opMode.sleep((long)(1000*time));
    }

}