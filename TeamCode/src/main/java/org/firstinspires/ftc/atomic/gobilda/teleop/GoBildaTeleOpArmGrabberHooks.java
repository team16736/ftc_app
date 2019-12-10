package org.firstinspires.ftc.atomic.gobilda.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumArmActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumHookServoActions;

@TeleOp(name="Mecannum-Wiggly Arm", group="Linear Opmode")
public class GoBildaTeleOpArmGrabberHooks extends LinearOpMode {

    private MecanumArmActions armActions = null;
    private MecanumHookServoActions servoActions = null;
    private MecanumDriveWheelActions mecanumDriveWheelActions = null;

    @Override
    public void runOpMode() {

        armActions = new MecanumArmActions(telemetry, hardwareMap);
        servoActions = new MecanumHookServoActions(telemetry, hardwareMap);
        mecanumDriveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            /** Gamepad 1 **/
            mecanumDriveWheelActions.drive(
                    gamepad1.left_stick_x,      //joystick controlling strafe
                    -gamepad1.left_stick_y,     //joystick controlling the forward/backward motion
                    gamepad1.right_stick_x);    //joystick controlling the rotation

            /** Gamepad 2 **/
            servoActions.hookUpDown(
                    gamepad2.dpad_left,         //key to move up hookUpDown
                    gamepad2.dpad_right);       //key to move down hookUpDown

            double arm_up = gamepad2.right_stick_y;
            double arm_down = gamepad2.right_stick_x;

//            armActions.arm - goes up and down
//            armActions.elbow - open and close
//            armActions.gripper - open and close
        }

        telemetry.addData("MecanumDrivetrainTeleOp", "Stopping");
        idle();
    }
}
