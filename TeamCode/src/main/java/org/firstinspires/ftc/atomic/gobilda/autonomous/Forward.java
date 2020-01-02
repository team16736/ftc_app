package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose: Go forward and park under bridge
 */
@Autonomous(name = "Forward", group = "GoBilda")
public class Forward extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        // Step 1:  Drive Forward for 1 Second
        driveWheelActions.setMotorDirection_Reverse();
        driveWheelActions.driveByTime(this, 0.35, 0.25);//changed

        sleep(2000); //wait for 2 seconds
    }
}

