package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose: Go to the LEFT side and park under bridge
 *
 * 1. Goes forward
 * 2. Strafes to the LEFT
 */
@Autonomous(name = "Auto Left", group = "GoBilda")
public class GoLeft extends LinearOpMode {
    @Override
    public void runOpMode() {

        MecanumDriveWheelActions driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1:  Drive Forwards for 1 Second
        driveWheelActions.leftBackMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.rightBackMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.leftFrontMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.rightFrontMotor.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        driveWheelActions.stop();



        // Step 2:  Strafe RIGHT side
        driveWheelActions.leftBackMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.rightBackMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.leftFrontMotor.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.rightFrontMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        driveWheelActions.stop();




    }
}

