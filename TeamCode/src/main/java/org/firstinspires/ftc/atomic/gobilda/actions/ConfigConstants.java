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
    public static final String SERVO_LEFT = "servo_left";
    public static final String SERVO_RIGHT = "servo_right";

    //Trapper
    public static final String SERVO_TRAP = "servo_trap";

    public static final DcMotorSimple.Direction REVERSE = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction FORWARD = DcMotorSimple.Direction.FORWARD;
    public static final DcMotor.ZeroPowerBehavior BRAKE = DcMotor.ZeroPowerBehavior.BRAKE;

}
