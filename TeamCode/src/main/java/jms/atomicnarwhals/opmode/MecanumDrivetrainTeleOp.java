package jms.atomicnarwhals.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import jms.atomicnarwhals.hardware.MecanumDrivetrain;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Mecanum 0", group="Linear Opmode")
public class MecanumDrivetrainTeleOp extends LinearOpMode {

    private static final double THROTTLE = 0.8;
    private MecanumDrivetrain MecanumDrivetrain = null;

    @Override
    public void runOpMode() {

        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "LF");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "RF");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "RB");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "LB");

        MecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            /* Gamepad 1 */
            MecanumDrivetrain.drive(gamepad1.left_stick_x,
                                    -gamepad1.left_stick_y, 
                                    gamepad1.right_stick_x, 
                                    THROTTLE);
        }

        telemetry.addData("MecanumDrivetrainTeleOp", "Stopping");

        //MecanumDrivetrain.stop();
    }
}
