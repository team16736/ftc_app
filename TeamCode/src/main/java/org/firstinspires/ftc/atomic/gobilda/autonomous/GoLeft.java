package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose: Go to the LEFT side and park under bridge
 *
 * 1. Goes forward
 * 2. Strafes to the RIGHT
 */
@Autonomous(name = "Auto Left", group = "GoBilda")
public class GoLeft extends LinearOpMode {
    @Override
    public void runOpMode() {

        MecanumDriveWheelActions driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1:  Drive Forwards for 1 Second
        driveWheelActions.left_back.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.right_back.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.left_front.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.right_front.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        //driveWheelActions.stop();
        sleep(2000); //wait for 2 seconds


        // Step 2:  Strafe RIGHT side
        driveWheelActions.left_back.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.right_back.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.left_front.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.right_front.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        driveWheelActions.stop();
    }
}

