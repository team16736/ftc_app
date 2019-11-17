package org.firstinspires.ftc.atomic.gobilda.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public interface DrivetrainInterface {

    DcMotorSimple.Direction REVERSE = DcMotorSimple.Direction.REVERSE;
    DcMotorSimple.Direction FORWARD = DcMotorSimple.Direction.FORWARD;

    void drive(double speedX, double speedY, double rotation);

    void stop();
}
