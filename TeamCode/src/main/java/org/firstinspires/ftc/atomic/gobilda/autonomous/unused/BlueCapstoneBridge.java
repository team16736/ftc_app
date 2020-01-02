package org.firstinspires.ftc.atomic.gobilda.autonomous.unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumArmElbowGripperActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumHookServoActions;
import org.firstinspires.ftc.atomic.gobilda.autonomous.PullFoundation;

/**
 * Purpose: Pull BLUE foundation to the building site
 */
@Autonomous(name = "Blue Capstone BridgeBlue ", group = "GoBilda")
@Disabled
public class BlueCapstoneBridge extends PullFoundation {

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions wheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);
        MecanumHookServoActions hookActions = new MecanumHookServoActions(telemetry, hardwareMap);
        MecanumArmElbowGripperActions armActions = new MecanumArmElbowGripperActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        //Step 1: Drive backwards
        drive_ReverseAndStop(wheelActions, SPEED, 0.75);
        sleep(1500);


        //Step 2: Strafe right
       strafe_RightAndStop(wheelActions, SPEED, 2.0);
        sleep(1500);


        //Step 3: Drive Backwards
        drive_ReverseAndStop(wheelActions, SPEED, 0.9);
        sleep(1500);


        //Step 4: Strafe Right to be behind foundation
        strafe_RightAndStop(wheelActions, SPEED, 1.25);
        sleep(1500);


        //Step 5: move forwards to push foundation to the wall
        drive_ForwardAndStop(wheelActions, SPEED , 1.0);
        sleep(2000);


        //Step 6: open elbow
        armActions.elbowOpenCompletely();
        sleep(5000);


        //Remove 2 lines below ###########
        drive_ReverseAndStop(wheelActions, SPEED, 0.75);
        sleep(1500);


        //Step 7: drop Capstone

        //Step 8: turn left 90 degrees


        //Step 9: move forwards to have arm under bridge



    }

}