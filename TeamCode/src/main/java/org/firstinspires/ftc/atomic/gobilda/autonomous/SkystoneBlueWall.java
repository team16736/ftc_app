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

        //1. Move forward

        //2. Sense the color

        //3. Armbot or Pushbot

        sleep(2000);
    }


}