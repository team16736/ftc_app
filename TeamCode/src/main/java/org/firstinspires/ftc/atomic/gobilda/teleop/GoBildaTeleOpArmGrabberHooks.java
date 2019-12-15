package org.firstinspires.ftc.atomic.gobilda.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumArmElbowGripperActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumHookServoActions;

@TeleOp(name="Mecannum-Arm", group="Linear Opmode")
@Disabled
public class GoBildaTeleOpArmGrabberHooks extends LinearOpMode {

    private MecanumHookServoActions hookActions = null;
    private MecanumDriveWheelActions driveWheelActions = null;
    //private MecanumArmElbowGripperActions armElbowGripActions = null;

    private DcMotor armDrive = null;
    private Servo elbowServo = null;
    private Servo grabberServo = null;


    @Override
    public void runOpMode() {

        hookActions = new MecanumHookServoActions(telemetry, hardwareMap);
        driveWheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);
        //armElbowGripActions = new MecanumArmElbowGripperActions(telemetry, hardwareMap);

        armDrive  = hardwareMap.get(DcMotor.class, "arm");
        elbowServo = hardwareMap.get(Servo.class, "elbow_servo");
        grabberServo = hardwareMap.get(Servo.class, "grabber_servo");

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

//            armElbowGripActions.armUpDown(
//                    gamepad2.left_stick_y);     //arm move up/down
//            armElbowGripActions.elbowOperClose();
//            armElbowGripActions.grabberOpenClose();


            //#################################################
            double armPower;
            double armVal = gamepad2.left_stick_y;
            double maxUp = 0.5;
            double maxDown = -0.3;

            armPower    = Range.clip(armVal, maxDown, maxUp) ;
            armDrive.setPower(armPower);

            boolean elbowServoOn = gamepad2.left_bumper;
            boolean servoGrabberOn = gamepad2.right_bumper;
            double grabberServoPosition = 0.0;

            if (elbowServoOn){
                double elbowServoPosition = 0.5;
                elbowServo.setPosition(elbowServoPosition);
            }
            if (servoGrabberOn){
                grabberServoPosition = 1.0;
            }
            grabberServo.setPosition(grabberServoPosition);
            telemetry.update();
            //#################################################
        }

        telemetry.addData("Mecannum-Wiggly Arm", "Stopping");
        idle();
    }
}
