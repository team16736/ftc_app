package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose: Methods for pulling foundation
 */
public abstract class PullFoundation extends LinearOpMode {

    final double SPEED = 0.5;

    public void drive_ReverseAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_Reverse();
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void drive_ForwardAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_Forward();
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void strafe_RightAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_StrafeRight();
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void strafe_LeftAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_StrafeLeft();
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    //NEED TO TEST THIS
    //todo - Rahul
    public void turn_RightAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.leftBackMotor.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.rightBackMotor.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.leftFrontMotor.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.rightFrontMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    //NEED TO TEST THIS
    //todo - Rahul
    public void turn_LeftAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.leftBackMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.rightBackMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.leftFrontMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.rightFrontMotor.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

}