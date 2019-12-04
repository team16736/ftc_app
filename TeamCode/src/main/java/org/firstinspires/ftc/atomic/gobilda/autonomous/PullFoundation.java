package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose: Helper methods for pulling foundation
 */
public abstract class PullFoundation extends LinearOpMode {

    public void drive_ReverseAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void drive_ForwardAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void strafe_RightAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void strafe_LeftAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void turn_RightAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void turn_LeftAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

}