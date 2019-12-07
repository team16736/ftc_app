package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose: Go to the RIGHT side and park under bridge
 *
 * 1. Strafes to the LEFT
 * 2. Goes forward (which is the RIGHT side of the bridge)
 */
@Autonomous(name = "Auto Right", group = "GoBilda")
public class GoRight extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE


        // Step 1:  Drive Forwards for 1 Second
        driveWheelActions.leftBackMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.rightBackMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.leftFrontMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.rightFrontMotor.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        driveWheelActions.stop();


        // Step 2:  Strafe Right for 1 second
        //There are three reverses because our robot will only work like this
        driveWheelActions.leftBackMotor.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.rightBackMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.leftFrontMotor.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.rightFrontMotor.setDirection(ConfigConstants.REVERSE);//
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        driveWheelActions.stop();


    }
}

