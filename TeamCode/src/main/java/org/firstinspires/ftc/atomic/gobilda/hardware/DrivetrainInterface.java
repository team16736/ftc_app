package org.firstinspires.ftc.atomic.gobilda.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public interface DrivetrainInterface {

    void drive(double speedX, double speedY, double rotation);

    void stop();
}
