package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Wiggly Arm", group="Linear Opmode")

public class WigglyArm extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor armDrive = null;
    private Servo elbowServo = null;
    private Servo grabberServo = null;

    @Override
    public void runOpMode() {

        armDrive  = hardwareMap.get(DcMotor.class, "arm");
        elbowServo = hardwareMap.get(Servo.class, "elbow_servo");
        grabberServo = hardwareMap.get(Servo.class, "grabber_servo");
        
        armDrive.setDirection(DcMotor.Direction.REVERSE);
        elbowServo.setDirection(Servo.Direction.REVERSE);
        grabberServo.setDirection(Servo.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

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
        }
    }
}
