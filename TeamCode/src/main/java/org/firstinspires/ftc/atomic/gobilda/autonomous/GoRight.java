package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose: Go to the RIGHT side and park under bridge
 *
 * 1. Goes forward
 * 2. Strafes to the LEFT
 */
@Autonomous(name = "Auto Right", group = "GoBilda")
public class GoRight extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        // Step 1:  Drive Forwards for 1 Second
        driveWheelActions.setMotorDirection_Forward();
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        //driveWheelActions.stop();
        sleep(2000); //wait for 2 seconds


        // Step 2:  Strafe LEFT side
        driveWheelActions.setMotorDirection_StrafeLeft();
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        driveWheelActions.stop();
    }
}

