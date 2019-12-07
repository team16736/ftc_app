package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumHookServoActions;

/**
 * Purpose: Pull RED foundation to the building site
 */
@Autonomous(name = "Red Foundation Bridge Pull", group = "GoBilda")
public class PullRedFoundationBridge extends PullFoundation {

    boolean servoHookOn = false;
    double lefthookPosition = 0.0;
    double righthookPosition = 0.0;

    @Override
    public void runOpMode() {

        MecanumDriveWheelActions wheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);
        MecanumHookServoActions hookActions = new MecanumHookServoActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1:  Strafe LEFT
        strafe_LeftAndStop(wheelActions, SPEED, 0.9);
        sleep(2000); //wait for 2 seconds


        // Step 2: Drive REVERSE towards the building zone
        drive_ReverseAndStop(wheelActions, SPEED, 1.3);


        // Step 3: Move rear Hooks DOWN to grab the foundation
        servoHookOn=true;
        moveHooksUpOrDown(hookActions);
        sleep(2000);


        // Step4: Drive FORWARD towards building site
        drive_ForwardAndStop(wheelActions, SPEED -0.2, 4.0); //SPEED-0.5, added 2.5 driving time
        sleep(2000);


        // Step5: Hook move UP to release the foundation
        servoHookOn=false;
        moveHooksUpOrDown(hookActions);
        sleep(2000);


        // Step 6: Strafe RIGHT
        strafe_RightAndStop(wheelActions, SPEED, 1.6);
        sleep(2000);

        // Step 7: Move Backwards
        drive_ReverseAndStop(wheelActions,SPEED,1.0);
        sleep(2000);

        //Step 8: Strafe RIGHT and park under bridge
        strafe_RightAndStop(wheelActions, SPEED, 1.0);
        sleep(2000);

    }

    private void moveHooksUpOrDown(MecanumHookServoActions hookActions) {

        if (servoHookOn) {
            //Move the hooks down
            lefthookPosition = 0.0;
            righthookPosition = 1.0;
        } else {
            //Move the hooks up
            lefthookPosition = 1.0;
            righthookPosition = 0.0;
        }
        hookActions.left_hook.setPosition(lefthookPosition);
        hookActions.right_hook.setPosition(righthookPosition);
    }

}

