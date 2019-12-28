package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumHookServoActions;

/**
 * Purpose:
 * 1. Identify Skystone on Blue Quarry
 * 2. Get the Skystone (Armbot or Pushbot)
 * 2. Deliver to the other side of the Blue bridge
 * 3. Park by the Wall
 */
@Autonomous(name = "Skystone Blue Wall", group = "GoBilda")
public class SkystoneBlueWall extends PullFoundation {

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions wheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Move forward

        boolean foundBlack = false;

        // Sense color
        // if(yellow) -- then Strafe RIGHT 8 inches

        // Sense color
        // if(yellow) -- then Strafe RIGHT 8 inches

        // Sense color
        // if(black) -- then FORWARD, collect the block


        // Collect block using Pushbot
        // move FORWARD
        // spin LEFT
        // move FORWARD
        // spin RIGHT
        // move FORWARD
        // move REVERSE
        // park under bridge

        sleep(2000);
    }

//hello//hello//
}