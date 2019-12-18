package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class ConfigConstants {

    //Drive wheels
    public static final String FRONT_LEFT = "FL";
    public static final String FRONT_RIGHT = "FR";
    public static final String BACK_RIGHT = "BR";
    public static final String BACK_LEFT = "BL";

    //Hooks
    public static final String SERVO_LEFT = "left_hook";
    public static final String SERVO_RIGHT = "right_hook";

    //Arm
    public static final String ARM = "arm";

    //Elbow
    public static final String ELBOW_SERVO = "elbow_servo";

    //Grabber
    public static final String GRABBER_SERVO = "grabber_servo";

    //Added for simplicity
    public static final DcMotorSimple.Direction REVERSE = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction FORWARD = DcMotorSimple.Direction.FORWARD;
    public static final DcMotor.ZeroPowerBehavior BRAKE = DcMotor.ZeroPowerBehavior.BRAKE;


    //Encoder FORWARD 1 inch
    public static final float ENCODER_CLICKS_FORWARD_1_INCH = 18.75487911f;

    //Encoder STRAFE 1 inch
    public static final float ENCODER_CLICKS_STRAFE_1_INCH = 25.8944908f;

    public static final int DIRECTION_FORWARD = 0;
    public static final int DIRECTION_REVERSE = 1;
    public static final int DIRECTION_STRAFE_RIGHT = 2;
    public static final int DIRECTION_STRAFE_LEFT = 3;

}
