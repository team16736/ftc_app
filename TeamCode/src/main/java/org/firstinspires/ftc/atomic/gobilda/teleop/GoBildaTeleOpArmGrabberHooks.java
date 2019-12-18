package org.firstinspires.ftc.atomic.gobilda.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.atomic.gobilda.actions.ConfigConstants;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumArmElbowGripperActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumHookServoActions;

@TeleOp(name="Mecannum-Arm", group="Linear Opmode")
public class GoBildaTeleOpArmGrabberHooks extends LinearOpMode {

    private MecanumHookServoActions hookActions = null;
    private MecanumDriveWheelActions driveWheelActions = null;
    private MecanumArmElbowGripperActions armElbowGripActions = null;

    private DcMotor armDrive = null;
    private Servo elbowServo = null;
    private Servo grabberServo = null;


    @Override
    public void runOpMode() {

        hookActions = new MecanumHookServoActions(telemetry, hardwareMap);
        driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);
        armElbowGripActions = new MecanumArmElbowGripperActions(telemetry, hardwareMap);

        armDrive  = hardwareMap.get(DcMotor.class, ConfigConstants.ARM);
        elbowServo = hardwareMap.get(Servo.class, ConfigConstants.ELBOW_SERVO);
        grabberServo = hardwareMap.get(Servo.class, ConfigConstants.GRABBER_SERVO);

        armDrive.setDirection(DcMotor.Direction.REVERSE);
        elbowServo.setDirection(Servo.Direction.REVERSE);
        grabberServo.setDirection(Servo.Direction.FORWARD);

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

            armElbowGripActions.armUpDown(gamepad2.left_stick_y);     //arm up/down

            armElbowGripActions.elbowOpenClose(gamepad2.dpad_up,    //elbow up
                                            gamepad2.dpad_down);    //elbow down

            armElbowGripActions.grabberOpenClose(gamepad2.left_bumper,  //open grabber
                                                gamepad2.right_bumper); //close grabber

            telemetry.update();
        }

        telemetry.addData("AtomicBot", "Stopping");
        telemetry.update();

        idle();
    }
}
