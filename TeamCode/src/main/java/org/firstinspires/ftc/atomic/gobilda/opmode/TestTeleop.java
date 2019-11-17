package org.firstinspires.ftc.atomic.gobilda.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.atomic.gobilda.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.atomic.gobilda.util.MecanumConfigConstants;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TestMecanum OpMode", group="Linear Opmode")
public class TestTeleop extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrivetrain mMecanumDrivetrain = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        DcMotor frontLeft  = hardwareMap.get(DcMotor.class, MecanumConfigConstants.FRONT_LEFT);
        DcMotor frontRight = hardwareMap.get(DcMotor.class, MecanumConfigConstants.FRONT_RIGHT);
        DcMotor backRight = hardwareMap.get(DcMotor.class, MecanumConfigConstants.BACK_RIGHT);
        DcMotor backLeft = hardwareMap.get(DcMotor.class, MecanumConfigConstants.BACK_LEFT);
        mMecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        // TODO : how are our motors connected ? Are any reversed ?
        //leftDrive.setDirection(DcMotor.Direction.FORWARD);
        //rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if(gamepad1.a){
                mMecanumDrivetrain.driveByDistance(35, MecanumDrivetrain.DIRECTION_STRAFE_RIGHT, 0.5);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {

                }
                mMecanumDrivetrain.stop();
            }
            if(gamepad1.b){
                mMecanumDrivetrain.driveByDistance(35, MecanumDrivetrain.DIRECTION_STRAFE_RIGHT, 0.25);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {

                }
                mMecanumDrivetrain.stop();
            }

            if(gamepad1.x){
                mMecanumDrivetrain.driveByDistance(35, MecanumDrivetrain.DIRECTION_STRAFE_RIGHT, 1);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {

                }
                mMecanumDrivetrain.stop();
            }
            if(gamepad1.y){
                mMecanumDrivetrain.driveByDistance(35, MecanumDrivetrain.DIRECTION_STRAFE_RIGHT, 0.1);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {

                }
                mMecanumDrivetrain.stop();
            }
            if(gamepad2.a){
                mMecanumDrivetrain.driveByDistance(2,MecanumDrivetrain.DIRECTION_FORWARD, .5);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }
            if(gamepad2.b){
                mMecanumDrivetrain.driveByDistance(4,MecanumDrivetrain.DIRECTION_FORWARD, .5);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }

            if(gamepad2.x){
                mMecanumDrivetrain.driveByDistance(5,MecanumDrivetrain.DIRECTION_FORWARD, .5);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }
            if(gamepad2.y){
                mMecanumDrivetrain.driveByDistance(6,MecanumDrivetrain.DIRECTION_FORWARD, .5);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }
        }

        telemetry.addData("MecanumDrivetrainTeleOp" , "Stopping");
        mMecanumDrivetrain.stop();
    }
}
