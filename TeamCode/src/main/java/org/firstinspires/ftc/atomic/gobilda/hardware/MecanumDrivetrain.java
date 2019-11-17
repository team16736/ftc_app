package org.firstinspires.ftc.atomic.gobilda.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.atomic.gobilda.util.NumberUtility;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDrivetrain implements DrivetrainInterface {

    private static final float ENCODER_CLICKS_FORWARD_1_INCH = 18.75487911f;
    private static final float ENCODER_CLICKS_STRAFE_1_INCH = 25.8944908f;

    public static final int DIRECTION_FORWARD = 0;
    public static final int DIRECTION_REVERSE = 1;
    public static final int DIRECTION_STRAFE_RIGHT = 2;
    public static final int DIRECTION_STRAFE_LEFT = 3;

    private DcMotor motorFrontLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackLeft;
    private DcMotor motorBackRight;

    private Telemetry mTelemetry;

    /**
     * Creates a mecanum motor using the 4 individual motors passed in as the arguments
     * @param telemetry : Telemetry to send messages to the Driver Control
     * @param frontLeft : Front left motor
     * @param frontRight : Front right motor
     * @param backLeft : Back left motor
     * @param backRight : Back right motor
     */
    public MecanumDrivetrain(Telemetry telemetry,
                             DcMotor frontLeft,
                             DcMotor frontRight,
                             DcMotor backLeft,
                             DcMotor backRight ) {
        mTelemetry = telemetry;
        motorFrontLeft = frontLeft;
        motorFrontRight = frontRight;
        motorBackLeft = backLeft;
        motorBackRight = backRight;

        setMotorDirection(DIRECTION_FORWARD);

        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void setMotorDirection(int direction){

        if (DIRECTION_REVERSE == direction){
            motorBackLeft.setDirection(REVERSE);
            motorBackRight.setDirection(FORWARD);
            motorFrontLeft.setDirection(REVERSE);
            motorFrontRight.setDirection(FORWARD);

        } else if (DIRECTION_STRAFE_LEFT == direction){
            motorBackLeft.setDirection(FORWARD);
            motorBackRight.setDirection(FORWARD);
            motorFrontLeft.setDirection(REVERSE);
            motorFrontRight.setDirection(REVERSE);

        } else if (DIRECTION_STRAFE_RIGHT == direction){
            motorBackLeft.setDirection(REVERSE);
            motorBackRight.setDirection(REVERSE);
            motorFrontLeft.setDirection(FORWARD);
            motorFrontRight.setDirection(FORWARD);

        } else {  //DIRECTION_FORWARD
            motorBackLeft.setDirection(REVERSE);
            motorBackRight.setDirection(REVERSE);
            motorFrontLeft.setDirection(REVERSE);
            motorFrontRight.setDirection(FORWARD);
        }
    }

    /**
     * Overload of drive method that allows for throttling of power
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
        mTelemetry.addData("speedX", speedX);
        mTelemetry.addData("speedY", speedY);
        mTelemetry.addData("rotation", rotation);
        mTelemetry.update();

        double fl = speedX + speedY + rotation;
        double fr = -speedX + speedY - rotation;
        double bl= -speedX + speedY + rotation;
        double br = speedX + speedY - rotation;

        double max = NumberUtility.findMax(fl, fr, bl, br);
        if (max > 1) {
            fl = fl / max;
            fr = fr / max;
            bl = bl / max;
            br = br / max;
        }

        motorFrontRight.setPower(fr);
        motorFrontLeft.setPower(fl);
        motorBackRight.setPower(br);
        motorBackLeft.setPower(bl);
    }

    /**
     * This function stops the mecanum motor
     */
    public void stop() {
        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
    }

    /**
     * Method to drive a specified distance using motor encoder functionality
     *
     * @param inches - The Number Of Inches to Move
     * @param direction - The Direction to Move
     *                  - Valid Directions:
     *                  - MecanumDrivetrain.DIRECTION_FORWARD
     *                  - MecanumDrivetrain.DIRECTION_REVERSE
     *                  - MecanumDrivetrain.DIRECTION_STRAFE_LEFT
     *                  - MecanumDrivetrain.DIRECTION_STRAFE_RIGHT
     * @param speed - The desired motor power (most accurate at low powers < 0.25)
     */
    public void driveByDistance(int inches, int direction, double speed){
        setMotorDirection(direction);
        driveByRevolution(convertDistanceToTarget(inches, direction), speed);
    }

    private int convertDistanceToTarget(int inches, int direction){
        float target;

        if (DIRECTION_FORWARD == direction || DIRECTION_REVERSE == direction){
            target = inches * ENCODER_CLICKS_FORWARD_1_INCH;
        }
        else{
            target = inches * ENCODER_CLICKS_STRAFE_1_INCH;
        }

        return Math.round(target);
    }

    /**
     * Returns if atleast one of the wheels is moving
     * @return true if the robot is moving
     */
    public boolean isMoving() {
        return motorFrontLeft.isBusy() || motorFrontRight.isBusy() || motorBackRight.isBusy() || motorBackLeft.isBusy();
    }

    /**
     * Method will motors a specified number of revolutions at the desired power
     * agnostic of direction.
     *
     * @param revolutions - the number of motor encoder ticks to move
     * @param power - the speed at which to move
     */
    private void driveByRevolution(int revolutions, double power){
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setTargetPosition(revolutions);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setTargetPosition(revolutions);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setTargetPosition(revolutions);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setTargetPosition(revolutions);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFrontLeft.setPower(power);
        motorBackRight.setPower(power);
        motorBackLeft.setPower(power);
        motorFrontRight.setPower(power);
    }

    public void forwardByTime(LinearOpMode opMode, double speed, double time) {
        motorBackLeft.setPower(speed);
        motorBackRight.setPower(speed);
        motorFrontRight.setPower(speed);
        motorFrontLeft.setPower(speed);
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

    public void backwardByTime(LinearOpMode opMode, double speed, double time) {
        motorBackLeft.setPower(-speed);
        motorBackRight.setPower(-speed);
        motorFrontLeft.setPower(-speed);
        motorFrontRight.setPower(-speed);
        opMode.sleep((long)(1000 * time));
    }
}