package org.firstinspires.ftc.atomic.gobilda.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.atomic.gobilda.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.atomic.gobilda.util.MecanumConfigConstants;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Mecanum 0", group="Linear Opmode")
public class MecanumDrivetrainTeleOp extends LinearOpMode {

    private static final double THROTTLE = 0.8;
    private MecanumDrivetrain mecanumDrivetrain = null;

    @Override
    public void runOpMode() {

        DcMotor frontLeft = hardwareMap.get(DcMotor.class, MecanumConfigConstants.FRONT_LEFT);
        DcMotor frontRight = hardwareMap.get(DcMotor.class, MecanumConfigConstants.FRONT_RIGHT);
        DcMotor backRight = hardwareMap.get(DcMotor.class, MecanumConfigConstants.BACK_RIGHT);
        DcMotor backLeft = hardwareMap.get(DcMotor.class, MecanumConfigConstants.BACK_LEFT);

        mecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            /* Gamepad 1 */
            mecanumDrivetrain.drive(gamepad1.left_stick_x, //speed x
                                    -gamepad1.left_stick_y, //speed y
                                    gamepad1.right_stick_x,  //rotation
                                    THROTTLE); //throttle
        }

        telemetry.addData("MecanumDrivetrainTeleOp", "Stopping");
        //mecanumDrivetrain.stop();
    }
}
