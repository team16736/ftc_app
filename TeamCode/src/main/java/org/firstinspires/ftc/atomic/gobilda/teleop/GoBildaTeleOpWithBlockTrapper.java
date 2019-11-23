package org.firstinspires.ftc.atomic.gobilda.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumServoActions;

@TeleOp(name="MecannumHooks 713", group="Linear Opmode")
public class GoBildaTeleOpWithBlockTrapper extends LinearOpMode {

    private MecanumServoActions servoActions = null;
    private MecanumDriveWheelActions mecanumDriveWheelActions = null;


    @Override
    public void runOpMode() {

        servoActions = new MecanumServoActions(telemetry, hardwareMap);
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
            servoActions.hookMoveUpDownPosition(
                    gamepad2.dpad_left,
                    gamepad2.dpad_right);
        }

        telemetry.addData("MecanumDrivetrainTeleOp", "Stopping");
        idle();
    }
}
