package org.firstinspires.ftc.atomic.gobilda.autonomous;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.atomic.gobilda.actions.DriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.HookServoActions;
import org.firstinspires.ftc.atomic.gobilda.utilities.ConfigConstants;

/**
 * Purpose: Methods for pulling foundation
 */
public abstract class HelperAction extends LinearOpMode {

    protected ColorSensor right_sensor;
    protected ColorSensor left_sensor;
    protected boolean foundStone = false;
    protected float hsvValues[] = {0F,0F,0F};

    public final double SPEED = 0.5;
    public boolean servoHookOn = false;
    public double left_hook_position = 0.0;
    public double right_hook_position = 1.0;


    public void drive_ReverseAndStop(DriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_Reverse();
        driveWheelActions.driveByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void drive_ForwardAndStop(DriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_Forward();
        driveWheelActions.driveByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void strafe_RightAndStop(DriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_StrafeRight();
        driveWheelActions.driveByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void strafe_LeftAndStop(DriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.setMotorDirection_StrafeLeft();
        driveWheelActions.driveByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    public void set_Direction_SpinLeft(DriveWheelActions driveWheelActions) {
        driveWheelActions.left_back.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.left_front.setDirection(ConfigConstants.FORWARD);

        driveWheelActions.right_back.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.right_front.setDirection(ConfigConstants.FORWARD);
    }

    public void set_Direction_SpinRight(DriveWheelActions driveWheelActions) {
        driveWheelActions.left_back.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.left_front.setDirection(ConfigConstants.REVERSE);

        driveWheelActions.right_back.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.right_front.setDirection(ConfigConstants.REVERSE);
    }

    public void moveHooksUpOrDown(HookServoActions hookActions) {

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


    public boolean isThisSkystone(ColorSensor colorSensor, float hsvValues[]){

        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
        telemetry.addData("Hue", hsvValues[0]);

        if(hsvValues[0] < 50){

            telemetry.addData("YELLOW block : ", System.currentTimeMillis());
            telemetry.update();
            return false;

        } else {

            telemetry.addData("BLACK block : ", System.currentTimeMillis());
            telemetry.update();
            return true;
        }
    }
}