package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumHookServoActions;

/**
 * Purpose: Pull BLUE foundation to the building site
 */
@Autonomous(name = "Blue Foundation Pull", group = "GoBilda")
public class PullBlueFoundation extends PullFoundation {

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
        strafe_RightAndStop(wheelActions, SPEED, 0.9);
        sleep(2000); //wait for 2 seconds


         // Step 2: Drive REVERSE towards the building zone
        drive_ReverseAndStop(wheelActions, SPEED, 1.3);


        // Step 3: Move rear Hooks DOWN to grab the foundation
        servoHookOn=true;
        moveHooksUpOrDown(hookActions);
        sleep(2000);


        // Step 4: Drive FORWARD towards building site
        drive_ForwardAndStop(wheelActions, SPEED + 0.3, 1.1);
        sleep(2000);


        // Step 5: Hook move UP to release the foundation
        servoHookOn=false;
        moveHooksUpOrDown(hookActions);
        sleep(2000);


        // Step 6: Strafe LEFT and park under bridge
        strafe_LeftAndStop(wheelActions, SPEED, 1.8);
        sleep(2000);
    }

    private void moveHooksUpOrDown(MecanumHookServoActions hookActions) {

        if (servoHookOn) {
            // Move the hooks down
            lefthookPosition = 0.0;
            righthookPosition = 1.0;
        } else {
            // Move the hooks up
            lefthookPosition = 1.0;
            righthookPosition = 0.0;
        }
        hookActions.left_hook.setPosition(lefthookPosition);
        hookActions.right_hook.setPosition(righthookPosition);
    }

}