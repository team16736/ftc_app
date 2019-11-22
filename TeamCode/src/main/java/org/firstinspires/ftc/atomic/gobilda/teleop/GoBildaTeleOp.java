package org.firstinspires.ftc.atomic.gobilda.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumServoActions;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Mecanum 0", group="Linear Opmode")
public class GoBildaTeleOp extends LinearOpMode {

    private MecanumDriveWheelActions mecanumDriveWheelActions = null;

    @Override
    public void runOpMode() {

        mecanumDriveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            /** Gamepad 1 **/
            mecanumDriveWheelActions.drive(
                    gamepad1.left_stick_x,      //joystick controlling strafe
                    -gamepad1.left_stick_y,     //joystick controlling the forward/backward motion
                    gamepad1.right_stick_x);    //joystick controlling the rotation
        }

        telemetry.addData("MecanumDrivetrainTeleOp", "Stopping");

        //mecanumDrivetrain.stop();
    }
}
