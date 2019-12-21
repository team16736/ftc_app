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

    public DcMotor left_front;
    public DcMotor right_front;
    public DcMotor left_back;
    public DcMotor right_back;

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
        left_front = hardwareMap.get(DcMotor.class, ConfigConstants.FRONT_LEFT);
        right_front = hardwareMap.get(DcMotor.class, ConfigConstants.FRONT_RIGHT);

        right_back = hardwareMap.get(DcMotor.class, ConfigConstants.BACK_RIGHT);
        left_back = hardwareMap.get(DcMotor.class, ConfigConstants.BACK_LEFT);

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

        double backLeftValue = -speedX + speedY + rotation;
        double frontLeftValue = speedX + speedY + rotation;

        double backRightValue = speedX + speedY - rotation;
        double frontRightValue = -speedX + speedY - rotation;

        double max = getMaxPower(frontLeftValue, frontRightValue, backLeftValue, backRightValue);
        if (max > 1) {
            frontLeftValue = frontLeftValue / max;
            frontRightValue = frontRightValue / max;
            backLeftValue = backLeftValue / max;
            backRightValue = backRightValue / max;
        }

        right_front.setPower(frontRightValue);
        left_front.setPower(frontLeftValue);
        right_back.setPower(backRightValue);
        left_back.setPower(backLeftValue);
    }

    private double getMaxPower(double frontLeftValue, double frontRightValue, double backLeftValue, double backRightValue) {
        List<Double> valueList = new LinkedList<>();
        valueList.add(frontLeftValue);
        valueList.add(frontRightValue);
        valueList.add(backLeftValue);
        valueList.add(backRightValue);

        return Collections.max(valueList);
    }

    //Working - 12/21
    public void setMotorDirection_Forward() {
        left_back.setDirection(ConfigConstants.REVERSE);
        left_front.setDirection(ConfigConstants.REVERSE);

        right_back.setDirection(ConfigConstants.REVERSE);
        right_front.setDirection(ConfigConstants.FORWARD);
    }

    //Working - 12/21
    public void setMotorDirection_Reverse() {
        left_back.setDirection(ConfigConstants.FORWARD);
        left_front.setDirection(ConfigConstants.FORWARD);

        right_back.setDirection(ConfigConstants.FORWARD);
        right_front.setDirection(ConfigConstants.REVERSE);
    }

    //Working - 12/21
    public void setMotorDirection_StrafeLeft() {
        left_back.setDirection(ConfigConstants.REVERSE);
        left_front.setDirection(ConfigConstants.FORWARD);

        right_back.setDirection(ConfigConstants.FORWARD);
        right_front.setDirection(ConfigConstants.FORWARD);
    }

    //Working - 12/21
    public void setMotorDirection_StrafeRight() {
        left_back.setDirection(ConfigConstants.FORWARD);
        left_front.setDirection(ConfigConstants.REVERSE);

        right_back.setDirection(ConfigConstants.REVERSE);
        right_front.setDirection(ConfigConstants.REVERSE);

        telemetry.addData("Strafe Right: ", "RIGHT");
        telemetry.update();
    }

    public void stop() {
        left_front.setPower(0);
        right_front.setPower(0);
        left_back.setPower(0);
        right_back.setPower(0);
    }

    public void applyBrake() {
        left_back.setZeroPowerBehavior(ConfigConstants.BRAKE);
        right_back.setZeroPowerBehavior(ConfigConstants.BRAKE);
        left_front.setZeroPowerBehavior(ConfigConstants.BRAKE);
        right_front.setZeroPowerBehavior(ConfigConstants.BRAKE);
    }

    public void forwardByTime(LinearOpMode opMode, double speed, double drivingTime) {
        left_back.setPower(speed);
        right_back.setPower(speed);
        right_front.setPower(speed);
        left_front.setPower(speed);
        opMode.sleep((long)(1000 * drivingTime));
    }

    public void reverseByTime(LinearOpMode opMode, double speed, double drivingTime) {
        left_back.setPower(-speed);
        right_back.setPower(-speed);
        left_front.setPower(-speed);
        right_front.setPower(-speed);
        opMode.sleep((long)(1000 * drivingTime));
    }

    public void strafeRightByTime(LinearOpMode opMode, double speed, double drivingTime) {
        left_back.setPower(-speed);
        right_back.setPower(speed);
        left_front.setPower(speed);
        right_front.setPower(-speed);
        opMode.sleep((long)(1000 * drivingTime));
    }

    public void strafeLeftByTime(LinearOpMode opMode, double speed, double drivingTime) {
        left_front.setPower(-speed);
        right_front.setPower(speed);
        left_back.setPower(speed);
        right_back.setPower(-speed);
        opMode.sleep((long)(1000*drivingTime));
    }

    public void forwardByDistance(LinearOpMode opMode, double speed, double drivingTime) {
        left_back.setPower(speed);
        right_back.setPower(speed);
        right_front.setPower(speed);
        left_front.setPower(speed);
        opMode.sleep((long)(1000 * drivingTime));
    }

}