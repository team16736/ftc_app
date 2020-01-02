package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose:
 * Start from Blue Quarry side - place on the 2nd tile
 * Go forward toward Quarry
 * Strafe left under bridge
 */
@Autonomous(name = "Bridge Blue", group = "GoBilda")
@Disabled
//ROBOT FACING FORWARD at BLUE QUARRY. START FROM 2nd TILE BY BLUE BRIDGE
public class BlueQuarryToBridge extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1:  Drive Forward
        driveWheelActions.setMotorDirection_Forward();
        driveWheelActions.driveByTime(this, 0.3, 2.0);
        driveWheelActions.stop();

        // Step 2:  Strafe left
        driveWheelActions.setMotorDirection_StrafeLeft();
        driveWheelActions.driveByTime(this, 0.35, 2.75);
        driveWheelActions.stop();

    }
}

