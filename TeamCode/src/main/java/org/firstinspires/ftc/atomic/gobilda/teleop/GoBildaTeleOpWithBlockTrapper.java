package org.firstinspires.ftc.atomic.gobilda.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumCSRServoActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.MecanumServoActions;

@TeleOp(name="MecannumTrapper 66", group="Linear Opmode")
public class GoBildaTeleOpWithBlockTrapper extends LinearOpMode {

    private MecanumServoActions servoActions = null;
    private MecanumCSRServoActions contiServoActions = null;
    private MecanumDriveWheelActions driveWheelActions = null;

    @Override
    public void runOpMode() throws InterruptedException {

        //Regular Servo
        servoActions = new MecanumServoActions(telemetry, hardwareMap);

        //Continuous Rotation Servo (CR Servo)
        //contiServoActions = new MecanumCSRServoActions(telemetry, hardwareMap);

        telemetry.addData("Mode: ", "Waiting...");
        telemetry.update();

        //wait for start button
        waitForStart();

        while(opModeIsActive()){

            /** Gamepad 1 **/
            driveWheelActions.drive(
                    gamepad1.left_stick_x,      //joystick controlling strafe
                    -gamepad1.left_stick_y,     //joystick controlling the forward/backward motion
                    gamepad1.right_stick_x);    //joystick controlling the rotation

            /** Gamepad 2 **/
            servoActions.hookMoveUpDownPosition(
                    gamepad2.dpad_left,
                    gamepad2.dpad_right);

           // servoActions.trapperUpDownPosition(
           //         gamepad2.dpad_up,
            //        gamepad2.dpad_down
            //);
        }
        telemetry.update();
        idle();
    }

}