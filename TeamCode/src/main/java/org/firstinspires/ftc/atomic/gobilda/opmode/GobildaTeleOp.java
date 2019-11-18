package org.firstinspires.ftc.atomic.gobilda.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Mecanum 0", group="Linear Opmode")
public class GobildaTeleOp extends LinearOpMode {

    private static final double THROTTLE = 0.8;
    private MecanumDriveWheelActions mecanumDriveWheelActions = null;

    @Override
    public void runOpMode() {

        mecanumDriveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            /* Gamepad 1 */
            mecanumDriveWheelActions.drive(gamepad1.left_stick_x, //speed x
                                    -gamepad1.left_stick_y, //speed y
                                    gamepad1.right_stick_x,  //rotation
                                    THROTTLE); //throttle
        }

        telemetry.addData("MecanumDrivetrainTeleOp", "Stopping");
        //mecanumDrivetrain.stop();
    }
}
