package org.firstinspires.ftc.atomic.gobilda.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumArmElbowGripperActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumHookServoActions;

@TeleOp(name="Mecannum-Wiggly Arm", group="Linear Opmode")
public class GoBildaTeleOpArmGrabberHooks extends LinearOpMode {

    private MecanumHookServoActions hookActions = null;
    private MecanumDriveWheelActions driveWheelActions = null;
    private MecanumArmElbowGripperActions armElbowGripActions = null;

    @Override
    public void runOpMode() {

        hookActions = new MecanumHookServoActions(telemetry, hardwareMap);
        driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);
        armElbowGripActions = new MecanumArmElbowGripperActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            /** Gamepad 1 **/
            driveWheelActions.drive(
                    gamepad1.left_stick_x,      //joystick controlling strafe
                    -gamepad1.left_stick_y,     //joystick controlling forward/backward
                    gamepad1.right_stick_x);    //joystick controlling rotation

            /** Gamepad 2 **/
            hookActions.hookUpDown(
                    gamepad2.dpad_left,         //key to move up hookUpDown
                    gamepad2.dpad_right);       //key to move down hookUpDown


//            armElbowGripActions.arm - goes up and down
//            armElbowGripActions.elbow - open and close
//            armElbowGripActions.gripper - open and close


//            double armVal = gamepad2.left_stick_y;
//            boolean elbowServoOn = gamepad2.left_bumper;
//            boolean servoGrabberOn = gamepad2.right_bumper;
        }

        telemetry.addData("Mecannum-Wiggly Arm", "Stopping");
        idle();
    }
}
