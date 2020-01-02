package org.firstinspires.ftc.atomic.gobilda.autonomous.unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.DriveWheelActions;

/**
 * Purpose: Go to the LEFT side and park under bridge
 *
 * 1. Goes forward
 * 2. Strafes to the RIGHT
 */
@Autonomous(name = "Auto Left", group = "GoBilda")
@Disabled
public class GoLeft extends LinearOpMode {

    @Override
    public void runOpMode() {

        DriveWheelActions driveWheelActions = new DriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1:  Drive Forwards for 1 Second
        driveWheelActions.setMotorDirection_Forward();
        driveWheelActions.driveByTime(this, 0.5, 0.5);//changed
        sleep(2000); //wait for 2 seconds


        // Step 2:  Strafe RIGHT side
        driveWheelActions.setMotorDirection_StrafeRight();
        driveWheelActions.driveByTime(this, 0.5, 0.5);//changed
        driveWheelActions.stop();
    }
}

