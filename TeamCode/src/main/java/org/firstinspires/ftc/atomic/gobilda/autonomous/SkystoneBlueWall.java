package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose:
 * 1. Identify Skystone on Blue Quarry
 * 2. Deliver to the other side of the Blue bridge
 * 3. Park by the Bridge
 */
@Autonomous(name = "Skystone Blue Bridge", group = "GoBilda")
public class SkystoneBlueWall extends PullFoundation {

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions wheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);
        boolean foundStone = false;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1: Move Forwards
        drive_ForwardAndStop(wheelActions, SPEED, 1.2);
        sleep(1000);

        // Step --> use sensor and find stone

        // ##########################

            // Add code here to detect skystone using sensor
            foundStone = true;

        //##########################

        // If stone is found, the collect it and deliver it
        if(foundStone){

            collectStoneAndDeliver(wheelActions);
        }

        foundStone = false;

//        // If stone is found again, the collect it and deliver it
//        if(foundStone){
//
//            collectStoneAndDeliver(wheelActions);
//        }


        //Step8: Move backwards to park under bridge
        drive_ReverseAndStop(wheelActions, SPEED, 0.5);
        sleep(1000);

    }

    /**
     * This method will collect stone and deliver.
     * This method can be used again for 2nd stone.. collect and deliver
     */
    private void collectStoneAndDeliver(MecanumDriveWheelActions wheelActions) {

        //Step 2: if detect black block; Strafe RIGHT
        strafe_RightAndStop(wheelActions,SPEED,0.5);
        sleep(1000);


        //Step 3: Move FORWARD
        drive_ForwardAndStop(wheelActions, SPEED, 0.6);
        sleep(2000);


        //Step 4: Spin LEFT
        set_Direction_SpinLeft(wheelActions);
        wheelActions.driveByTime(this, 0.3, 2.2);
        sleep(2000);


        //Step 5: Move FORWARD
        drive_ForwardAndStop(wheelActions, SPEED, 0.8);
        sleep(1000);


        //Step6: Spin RIGHT to face the BLUE bridge
        set_Direction_SpinRight(wheelActions);
        wheelActions.driveByTime(this, 0.3, 0.3);
        sleep(2000);


        // Step 7: Move FORWARD and deliver to the other side of the bridge
        drive_ForwardAndStop(wheelActions, SPEED, 1.7);
        sleep(1000);
    }

}