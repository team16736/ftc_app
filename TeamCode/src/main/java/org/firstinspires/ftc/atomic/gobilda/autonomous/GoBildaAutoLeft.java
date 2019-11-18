package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;

@Autonomous(name = "Mecanum Auto Left 55", group = "GoBilda")
public class GoBildaAutoLeft extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE

        // Step 1:  Strafe Right for 1 second
        //There are three reverses because our robot will only work like this
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        driveWheelActions.stop();

        // Step 2:  Drive Forwards for 1 Second
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, 0.5, 1.0);
        driveWheelActions.stop();
    }
}

