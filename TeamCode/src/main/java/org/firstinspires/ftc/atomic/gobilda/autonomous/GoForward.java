package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelEncoderActions;

@Autonomous(name = "Auto Forward Encoder", group = "GoBilda")
public class GoForward extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDriveWheelEncoderActions driveWheelEncoderActions = new MecanumDriveWheelEncoderActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        driveWheelEncoderActions.driveByDistance(5, ConfigConstants.DIRECTION_REVERSE, 1.0);
        sleep(2000); //wait for 2 seconds

        driveWheelEncoderActions.driveByDistance(5, ConfigConstants.DIRECTION_FORWARD, 1.0);
        sleep(2000); //wait for 2 seconds

        driveWheelEncoderActions.driveByDistance(5, ConfigConstants.DIRECTION_REVERSE, 1.0);
        sleep(2000); //wait for 2 seconds
    }
}

