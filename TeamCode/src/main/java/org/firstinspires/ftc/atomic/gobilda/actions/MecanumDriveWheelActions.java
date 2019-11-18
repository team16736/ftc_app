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

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    /**
     * Creates a mecanum motor using the 4 individual motors passed in as the arguments
     * @param telemetry : Telemetry to send messages to the Driver Control
     * @param hardwareMap : Hardware Mappings

     */
    public MecanumDriveWheelActions(Telemetry telemetry,
                                    HardwareMap hardwareMap ) {

        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        setupDriveWheelMotors(); //initialize 4 dc motors
        setMotorDirectionForward();
        setupDriveWheelBrakes();
    }

    /**
     * Use the DCMotor names provided in the config
     */
    public void setupDriveWheelMotors() {
        motorFrontLeft = hardwareMap.get(DcMotor.class, ConfigConstants.FRONT_LEFT);
        motorFrontRight = hardwareMap.get(DcMotor.class, ConfigConstants.FRONT_RIGHT);
        motorBackRight = hardwareMap.get(DcMotor.class, ConfigConstants.BACK_RIGHT);
        motorBackLeft = hardwareMap.get(DcMotor.class, ConfigConstants.BACK_LEFT);
    }

    public void setupDriveWheelBrakes() {
        motorBackLeft.setZeroPowerBehavior(ConfigConstants.BRAKE);
        motorBackRight.setZeroPowerBehavior(ConfigConstants.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(ConfigConstants.BRAKE);
        motorFrontRight.setZeroPowerBehavior(ConfigConstants.BRAKE);
    }

    public void setMotorDirectionForward() {
        motorBackLeft.setDirection(ConfigConstants.REVERSE);
        motorBackRight.setDirection(ConfigConstants.REVERSE);
        motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        motorFrontRight.setDirection(ConfigConstants.FORWARD);
    }

    public void setMotorDirectionReverse() {
        motorBackLeft.setDirection(ConfigConstants.REVERSE);
        motorBackRight.setDirection(ConfigConstants.FORWARD);
        motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        motorFrontRight.setDirection(ConfigConstants.FORWARD);
    }

    public void setMotorDirectionStrafeLeft() {
        motorBackLeft.setDirection(ConfigConstants.FORWARD);
        motorBackRight.setDirection(ConfigConstants.FORWARD);
        motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        motorFrontRight.setDirection(ConfigConstants.REVERSE);
    }

    public void setMotorDirectionStrafeRight() {
        motorBackLeft.setDirection(ConfigConstants.REVERSE);
        motorBackRight.setDirection(ConfigConstants.REVERSE);
        motorFrontLeft.setDirection(ConfigConstants.FORWARD);
        motorFrontRight.setDirection(ConfigConstants.FORWARD);
    }

    /**
     * Drive method to throttle the power
     * @param speedX - the x value of the joystick controlling strafe
     * @param speedY - the y value of the joystick controlling the forward/backward motion
     * @param rotation - the x value of the joystick controlling the rotation
     * @param throttle - the amount to throttle the power of the motors
     */
    public void drive(double speedX, double speedY, double rotation, double throttle){

        double throttledX = speedX * throttle;
        double throttledY = speedY * throttle;
        double throttledRotation = rotation * throttle;

        drive(throttledX, throttledY, throttledRotation);
    }

    /**
     * This function makes the mecanum motor drive using the joystick
     * @param speedX - the x value of the joystick controlling straf
     * @param speedY - the y value of the joystick controlling the forward/backwards motion
     * @param rotation - the x value of the joystick controlling the rotation
     */
    public void drive(double speedX, double speedY, double rotation) {
        telemetry.addData("Straf-speedX: ", speedX);
        telemetry.addData("Motion-speedY: ", speedY);
        telemetry.addData("rotation", rotation);
        telemetry.update();

        double frontLeftValue = speedX + speedY + rotation;
        double frontRightValue = -speedX + speedY - rotation;
        double backLeftValue= -speedX + speedY + rotation;
        double backRightValue = speedX + speedY - rotation;

        List<Double> valueList = new LinkedList<>();
        valueList.add(frontLeftValue);
        valueList.add(frontRightValue);
        valueList.add(backLeftValue);
        valueList.add(backRightValue);

        double max = Collections.max(valueList);
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
    }

    /**
     * Stop all the 4 mecanum wheels
     */
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