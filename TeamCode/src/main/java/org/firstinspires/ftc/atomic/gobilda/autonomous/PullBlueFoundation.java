package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumHookServoActions;

/**
 * Purpose: Pull blue foundation to the building site
 */
@Autonomous(name = "Blue Foundation Pull", group = "GoBilda")
public class PullBlueFoundation extends LinearOpMode {

    private final double SPEED = 0.5;
    double lefthookPosition = 0.0;
    double righthookPosition = 0.0;
    boolean servoHookOn = false;

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions wheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);
        MecanumHookServoActions hookActions = new MecanumHookServoActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1:  Strafe RIGHT
        strafe_RightAndStop(wheelActions, SPEED, 0.75);
        sleep(2000); //wait for 2 seconds

         // Step 2:  Drive REVERSE towards the building zone
        drive_ReverseAndStop(wheelActions, SPEED, 1.0);
        sleep(2000);

        //Step 3: Move rear Hooks DOWN to grab the foundation
        servoHookOn=true;
        if (servoHookOn){
         lefthookPosition = 0.0;
           righthookPosition = 1.0;
        } else {
            lefthookPosition = 1.0;
            righthookPosition = 0.0;
        }

        hookActions.servo_left.setPosition(lefthookPosition);
        hookActions.servo_right.setPosition(righthookPosition);

   // hookActions.servo_left.setPosition(0.5); //hook move down
        // hookActions.servo_right.setPosition(-0.5); //hook move down
        sleep(2000);

        //Step4: Drive FORWARD towards building site
        drive_ForwardAndStop(wheelActions, SPEED, 2.0);
        sleep(2000);

//        Step5: Hook move UP to release the foundation

        servoHookOn=false;
        if (servoHookOn){
            lefthookPosition = 0.0;
            righthookPosition = 1.0;
        } else {
            lefthookPosition = 1.0;
            righthookPosition = 0.0;
        }

        hookActions.servo_left.setPosition(lefthookPosition);
       hookActions.servo_right.setPosition(righthookPosition);

       // hookActions.servo_left.setPosition(0.0);  //hook move up
       // hookActions.servo_right.setPosition(0.0); //hook move up
        sleep(2000);

//         Step 6: Strafe LEFT and park under bridge
        strafe_LeftAndStop(wheelActions, SPEED, 0.5);
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
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }

    private void strafe_LeftAndStop(MecanumDriveWheelActions driveWheelActions, double speed, double drivingTime) {
        driveWheelActions.motorBackLeft.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorBackRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.motorFrontLeft.setDirection(ConfigConstants.REVERSE);
        driveWheelActions.motorFrontRight.setDirection(ConfigConstants.FORWARD);
        driveWheelActions.forwardByTime(this, speed, drivingTime);
        driveWheelActions.stop();
    }
}