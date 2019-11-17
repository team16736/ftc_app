package org.firstinspires.ftc.atomic.gobilda.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.atomic.gobilda.hardware.TwoWheelDrivetrain;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 *
 **/
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="2WD OpMode", group="Linear Opmode")
@Disabled
public class TwoWheelDrivetrainTeleOp extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private TwoWheelDrivetrain twoWheelDrivetrain = null;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Starting ");
        telemetry.update();

        DcMotor left = hardwareMap.get(DcMotor.class, "LF");
        DcMotor right = hardwareMap.get(DcMotor.class, "LR");
        twoWheelDrivetrain = new TwoWheelDrivetrain(left, right);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            twoWheelDrivetrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            // Show the elapsed game time.
            telemetry.addData("Status:",gamepad2.left_stick_y);
            telemetry.update();
        }

        telemetry.addData("TwoWheelDrivetrainTeleOp", "Stopping");
        twoWheelDrivetrain.stop();
    }
}
