package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose: Go to the LEFT side and park under bridge
 *
 * 1. Strafes to the RIGHT
 * 2. Goes forward (which is the LEFT side of the bridge)
 */
@Autonomous(name = "Auto Left", group = "GoBilda")
@Disabled
public class GoLeft extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE

        // Step 1:  Strafe Right for 1 second
        //There are three reverses because our robot will only work like this
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        driveWheelActions.stop();

        // Step 2:  Drive Forwards for 1 Second
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        driveWheelActions.stop();
    }
}
