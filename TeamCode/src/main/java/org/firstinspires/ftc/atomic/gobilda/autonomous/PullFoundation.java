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

//    //Not tested #################################################
//    public void spin_RightAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
//        driveWheelActions.setMotorDirection_SpinRight();
//        driveWheelActions.forwardByTime(this, speed, drivingTime);
//        driveWheelActions.stop();
//    }

    //NEED TO TEST THIS
    //todo - Rahul
    public void turn_RightAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.left_back.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.right_back.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.left_front.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.right_front.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    //NEED TO TEST THIS
    //todo - Rahul
    public void turn_LeftAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.left_back.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.right_back.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.left_front.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.right_front.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

}