package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.DriveWheelActions;

/**
 * Purpose:
 * Start from Red Quarry side - place on the 2nd tile
 * Go FORWARD toward Quarry
 * Strafe RIGHT under bridge
 */
@Autonomous(name = "Quarry To RED Bridge", group = "GoBilda")
//PLACE ROBOT FORWARD FACING RED QUARRY. ALIGHT WITH 2nd TILE ON BRIDGE SIDE.
public class QuarryToRedBridge extends LinearOpMode {

    @Override
    public void runOpMode() {

        DriveWheelActions driveWheelActions = new DriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1:  Drive Forward
        driveWheelActions.applySensorSpeed = true;// we have altered the speed for the forwards movement
        driveWheelActions.setMotorDirection_Forward();
        driveWheelActions.driveByTime(this, 0.3, 2.0);
        driveWheelActions.stop();

        // Step 2:  Strafe left
        driveWheelActions.setMotorDirection_StrafeRight();
        driveWheelActions.driveByTime(this, 0.35, 2.75);
        driveWheelActions.stop();

        driveWheelActions.applySensorSpeed = false;// we have altered the speed for the forwards movement
    }
}

