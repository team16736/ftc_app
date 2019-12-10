package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Make sure to have the following:
 *
 * 1. Hardware config
 * 2. Setup direction of motors
 * 3. Action method to do something (hookUpDown, drive, etc.,)
 * 4. Helper methods (stop, brake, leftTurn, rightTurn, etc.,)
 *
 * Purpose: Drive the 4 wheels
 */
public class MecanumDriveWheelActions {

    public DcMotor leftFrontMotor;
    public DcMotor rightFrontMotor;
    public DcMotor leftBackMotor;
    public DcMotor rightBackMotor;

    //the amount to throttle the power of the motors
    private static final double THROTTLE = 0.8;

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    /**
     * Creates a mecanum motor using the 4 individual motors passed in as the arguments
     * @param tele : Telemetry to send messages to the Driver Control
     * @param hardware : Hardware Mappings
     */
    // Constructor
    public MecanumDriveWheelActions(Telemetry tele, HardwareMap hardware ) {

        this.telemetry = tele;
        this.hardwareMap = hardware;

        // 1. Hardware config
        leftFrontMotor = hardwareMap.get(DcMotor.class, ConfigConstants.FRONT_LEFT);
        rightFrontMotor = hardwareMap.get(DcMotor.class, ConfigConstants.FRONT_RIGHT);
        rightBackMotor = hardwareMap.get(DcMotor.class, ConfigConstants.BACK_RIGHT);
        leftBackMotor = hardwareMap.get(DcMotor.class, ConfigConstants.BACK_LEFT);

        // 2. Set direction
        setMotorDirection_Forward();
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
     * @param speedX - the x value of the joystick controlling strafe
     * @param speedY - the y value of the joystick controlling the forward/backwards motion
     * @param rotation - the x value of the joystick controlling the rotation
     */
    public void driveUsingJoyStick(double speedX, double speedY, double rotation) {

        telemetry.addData("Strafe Speed X: = ", speedX);
        telemetry.addData("Motion Speed Y: = ", speedY);
        telemetry.addData("Rotations: = ", rotation);
        telemetry.update();

        double backLeftValue = speedX + speedY + rotation;
        double frontLeftValue = -speedX + speedY + rotation;
        double backRightValue = speedX + speedY - rotation; //working well
        double frontRightValue = -speedX + speedY - rotation; //working well

        double max = getMaxPower(frontLeftValue, frontRightValue, backLeftValue, backRightValue);
        if (max > 1) {
            frontLeftValue = frontLeftValue / max;
            frontRightValue = frontRightValue / max;
            backLeftValue = backLeftValue / max;
            backRightValue = backRightValue / max;
        }

        rightFrontMotor.setPower(frontRightValue);
        leftFrontMotor.setPower(frontLeftValue);
        rightBackMotor.setPower(backRightValue);
        leftBackMotor.setPower(backLeftValue);

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
        leftBackMotor.setDirection(ConfigConstants.REVERSE);
        rightBackMotor.setDirection(ConfigConstants.REVERSE);
        leftFrontMotor.setDirection(ConfigConstants.REVERSE);
        rightFrontMotor.setDirection(ConfigConstants.FORWARD);
    }

    public void setMotorDirection_Reverse() {
        leftBackMotor.setDirection(ConfigConstants.FORWARD);
        rightBackMotor.setDirection(ConfigConstants.FORWARD);
        leftFrontMotor.setDirection(ConfigConstants.FORWARD);
        rightFrontMotor.setDirection(ConfigConstants.REVERSE);
    }

    public void setMotorDirection_StrafeLeft() {
        leftBackMotor.setDirection(ConfigConstants.FORWARD);
        rightBackMotor.setDirection(ConfigConstants.FORWARD);
        leftFrontMotor.setDirection(ConfigConstants.REVERSE);
        rightFrontMotor.setDirection(ConfigConstants.FORWARD);
    }

    public void setMotorDirection_StrafeRight() {
        leftBackMotor.setDirection(ConfigConstants.REVERSE);
        rightBackMotor.setDirection(ConfigConstants.REVERSE);
        leftFrontMotor.setDirection(ConfigConstants.FORWARD);
        rightFrontMotor.setDirection(ConfigConstants.REVERSE);
    }

    public void stop() {
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightBackMotor.setPower(0);
    }

    public void applyBrake() {
        leftBackMotor.setZeroPowerBehavior(ConfigConstants.BRAKE);
        rightBackMotor.setZeroPowerBehavior(ConfigConstants.BRAKE);
        leftFrontMotor.setZeroPowerBehavior(ConfigConstants.BRAKE);
        rightFrontMotor.setZeroPowerBehavior(ConfigConstants.BRAKE);
    }

    public void forwardByTime(LinearOpMode opMode, double speed, double drivingTime) {
        leftBackMotor.setPower(speed);
        rightBackMotor.setPower(speed);
        rightFrontMotor.setPower(speed);
        leftFrontMotor.setPower(speed);
        opMode.sleep((long)(1000 * drivingTime));
    }

    public void reverseByTime(LinearOpMode opMode, double speed, double drivingTime) {
        leftBackMotor.setPower(-speed);
        rightBackMotor.setPower(-speed);
        leftFrontMotor.setPower(-speed);
        rightFrontMotor.setPower(-speed);
        opMode.sleep((long)(1000 * drivingTime));
    }

    public void strafeRightByTime(LinearOpMode opMode, double speed, double drivingTime) {
        leftBackMotor.setPower(-speed);
        rightBackMotor.setPower(speed);
        leftFrontMotor.setPower(speed);
        rightFrontMotor.setPower(-speed);
        opMode.sleep((long)(1000 * drivingTime));
    }

    public void strafeLeftByTime(LinearOpMode opMode, double speed, double drivingTime) {
        leftFrontMotor.setPower(-speed);
        rightFrontMotor.setPower(speed);
        leftBackMotor.setPower(speed);
        rightBackMotor.setPower(-speed);
        opMode.sleep((long)(1000*drivingTime));
    }

    public void forwardByDistance(LinearOpMode opMode, double speed, double drivingTime) {
        leftBackMotor.setPower(speed);
        rightBackMotor.setPower(speed);
        rightFrontMotor.setPower(speed);
        leftFrontMotor.setPower(speed);
        opMode.sleep((long)(1000 * drivingTime));
    }

}