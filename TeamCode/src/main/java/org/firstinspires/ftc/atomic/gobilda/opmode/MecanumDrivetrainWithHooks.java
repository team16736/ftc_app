package org.firstinspires.ftc.atomic.gobilda.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.atomic.gobilda.hardware.DraggerServo;
import org.firstinspires.ftc.atomic.gobilda.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.atomic.gobilda.util.MecanumConfigConstants;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Mecanum 1", group="Linear Opmode")
public class MecanumDrivetrainWithHooks extends LinearOpMode {

    private static final double THROTTLE = .35;


    private ElapsedTime runtime = new ElapsedTime();

    private MecanumDrivetrain mMecanumDrivetrain = null;

    @Override
    public void runOpMode() {

        DcMotor frontLeft = hardwareMap.get(DcMotor.class, MecanumConfigConstants.FRONT_LEFT);
        DcMotor frontRight = hardwareMap.get(DcMotor.class, MecanumConfigConstants.FRONT_RIGHT);
        DcMotor backRight = hardwareMap.get(DcMotor.class, MecanumConfigConstants.BACK_RIGHT);
        DcMotor backLeft = hardwareMap.get(DcMotor.class, MecanumConfigConstants.BACK_LEFT);
        
        mMecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);

        CRServo hook1  = hardwareMap.get(CRServo.class, MecanumConfigConstants.SERVO_LEFT);
        CRServo hook2  = hardwareMap.get(CRServo.class, MecanumConfigConstants.SERVO_RIGHT);

        DraggerServo hookServo1 = new DraggerServo(hook1);
        DraggerServo hookServo2 = new DraggerServo(hook2);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            /* Gamepad 1 */
            mMecanumDrivetrain.drive(gamepad1.left_stick_x,
                                    -gamepad1.left_stick_y, 
                                    gamepad1.right_stick_x, 
                                    THROTTLE);

            if (gamepad1.x) {
                hookServo1.moveDown();
                hookServo2.moveDown();
            }
            else if (gamepad1.y) {
                hookServo1.moveUp();
                hookServo2.moveUp();
            }
            else {
                hookServo1.stop ();
                hookServo2.stop ();
            }
        }

        telemetry.addData("MecanumDrivetrainTeleOp", "Stopping");
        mMecanumDrivetrain.stop();
    }
}
