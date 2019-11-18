package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.atomic.gobilda.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.atomic.gobilda.util.MecanumConfigConstants;

//@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Mecanum Auto Left 55", group = "Linear Opmode")
@Autonomous(name="Mecanum Auto Left 55", group="GoBilda")

public class MecanumAutoLeft extends LinearOpMode {

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


        //DO NOT CHANGE ANYTHING WITH FORWARD AND REVERSE

            // Step 1:  Strafe Right for 1 second
        //There are three reverses because our robot will only work like this
        mecanumDrivetrain.motorBackLeft.setDirection(MecanumConfigConstants.FORWARD);
            mecanumDrivetrain.motorBackRight.setDirection(MecanumConfigConstants.REVERSE);
            mecanumDrivetrain.motorFrontLeft.setDirection(MecanumConfigConstants.REVERSE);
            mecanumDrivetrain.motorFrontRight.setDirection(MecanumConfigConstants.REVERSE);
            mecanumDrivetrain.forwardByTime(this, 0.5, 1.0);
            mecanumDrivetrain.stop();

//            // Step 3:  Drive Forwards for 1 Second
            mecanumDrivetrain.motorBackLeft.setDirection(MecanumConfigConstants.REVERSE);
            mecanumDrivetrain.motorBackRight.setDirection(MecanumConfigConstants.REVERSE);
            mecanumDrivetrain.motorFrontLeft.setDirection(MecanumConfigConstants.REVERSE);
            mecanumDrivetrain.motorFrontRight.setDirection(MecanumConfigConstants.FORWARD);
            mecanumDrivetrain.forwardByTime(this, 0.5, 1.0);
            mecanumDrivetrain.stop();


            // Step 4:  Stop
            //mecanumDrivetrain.stop();


    }
}

