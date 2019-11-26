package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumServoActions;

/**
 * Purpose: Pull blue foundation to the building site
 */
@Autonomous(name = "Blue Foundation Pull", group = "GoBilda")
public class PullBlueFoundation extends LinearOpMode {

    private final double SPEED = 0.5;

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions wheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap); //created a object
        MecanumServoActions servoActions = new MecanumServoActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1:  Strafe RIGHT for 1 second
        strafe_RightAndStop(wheelActions, SPEED, 0.2);
        sleep(2000); //2 second wait

        // Step 2:  Drive REVERSE for 1 Second
        drive_ReverseAndStop(wheelActions, SPEED, 0.2);
        sleep(2000);

        //Step 3: Move rear Hooks DOWN to grab the foundation
        servoActions.servo_left.setPosition(0.3); //hook move down
        servoActions.servo_right.setPosition(-0.3); //hook move down
        servoActions.stop();
        sleep(2000);

        //Step4: Drive FORWARD towards the building zone
        drive_ForwardAndStop(wheelActions, SPEED, 0.2);
        sleep(2000);

        //Step5: Hook move UP to release the foundation
        //servoActions.servo_left.setPosition(0.0);  //hook move up
        //servoActions.servo_right.setPosition(0.0); //hook move up
        servoActions.stop();
        sleep(2000);

        // Step 6: Strafe LEFT and park under bridge
        strafe_LeftAndStop(wheelActions, SPEED, 0.2);
        sleep(2000);
    }

    private void drive_ReverseAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    private void drive_ForwardAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    private void strafe_RightAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    private void strafe_LeftAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }
}