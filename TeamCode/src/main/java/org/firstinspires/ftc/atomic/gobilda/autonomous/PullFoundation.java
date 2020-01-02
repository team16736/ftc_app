package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumHookServoActions;

/**
 * Purpose: Methods for pulling foundation
 */
public abstract class PullFoundation extends LinearOpMode {

    public final double SPEED = 0.5;

   public boolean servoHookOn = false;

    private double left_hook_position = 0.0;
    private double right_hook_position = 1.0;

    private double HOOK_MIN_POSITION = 0;
    private double HOOK_MAX_POSITION = 1.0;


    public void drive_ReverseAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_Reverse();
        driveWheelActions.driveByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void drive_ForwardAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_Forward();
        driveWheelActions.driveByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void strafe_RightAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_StrafeRight();
        driveWheelActions.driveByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void strafe_LeftAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_StrafeLeft();
        driveWheelActions.driveByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void set_Direction_SpinLeft(MecanumDriveWheelActions driveWheelActions) {
        driveWheelActions.left_back.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.left_front.setDirection(ConfigConstants.FORWARD);

        driveWheelActions.right_back.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.right_front.setDirection(ConfigConstants.FORWARD);
    }

    public void set_Direction_SpinRight(MecanumDriveWheelActions driveWheelActions) {
        driveWheelActions.left_back.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.left_front.setDirection(ConfigConstants.REVERSE);

        driveWheelActions.right_back.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.right_front.setDirection(ConfigConstants.REVERSE);
    }

    public void moveHooksUpOrDown(MecanumHookServoActions hookActions) {



        if (servoHookOn) {  //down

            left_hook_position = 1.0;
            right_hook_position = 0;
            telemetry.addData("Left Hook - DOWN Position x: ", left_hook_position);

        } else {  //up

            left_hook_position = 0;
            right_hook_position = 1.0;
            telemetry.addData("Right Hook - UP Position y: ", right_hook_position);
        }

        hookActions.left_hook.setPosition(left_hook_position);
        hookActions.right_hook.setPosition(right_hook_position);
        telemetry.update();
    }


}