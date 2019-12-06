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

    //Hooks
    public static final String LEFT_GRIP = "left_grip";
    public static final String RIGHT_GRIP = "right_grip";

    //Trapper
    public static final String SERVO_TRAP = "servo_trap";

    public static final DcMotorSimple.Direction REVERSE = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction FORWARD = DcMotorSimple.Direction.FORWARD;
    public static final DcMotor.ZeroPowerBehavior BRAKE = DcMotor.ZeroPowerBehavior.BRAKE;

}
