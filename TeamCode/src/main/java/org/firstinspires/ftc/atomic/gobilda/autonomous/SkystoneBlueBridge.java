package org.firstinspires.ftc.atomic.gobilda.autonomous;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.atomic.gobilda.actions.MecanumDriveWheelActions;

/**
 * Purpose:
 * 1. Identify Skystone on Blue Quarry
 * 2. Deliver to the other side of the Blue bridge
 * 3. Park by the BridgeBlue
 *
 * Sensors must be attached to one of the I2C ports
 */
//START AT THIRD HOLE FROM THE RIGHT FRAME
@Autonomous(name = "Skystone Blue BridgeBlue", group = "GoBilda")
public class SkystoneBlueBridge extends PullFoundation {

    ColorSensor colorSensor;
    boolean sensorLedOn = true;// Hardware Device Object
    boolean foundStone = false;
    float hsvValues[] = {0F,0F,0F};

    @Override
    public void runOpMode() {

        colorSensor = hardwareMap.get(ColorSensor.class, "right_color");
        colorSensor.enableLed(sensorLedOn);

        MecanumDriveWheelActions wheelActions = new MecanumDriveWheelActions(telemetry, hardwareMap);

        waitForStart();

        // Step 1: Move Forwards
        drive_ForwardAndStop(wheelActions, SPEED, 1.2);
        sleep(1000);

        // Step --> use sensor and find stone

        // Add code here to detect skystone using sensor
        //foundStone = true;
        foundStone = isThisSkystone(colorSensor, hsvValues);

        telemetry.update();

        // If stone is found, the collect it and deliver it
        if(foundStone){

            sleep(2020);
            telemetry.addData("Found black block","");
            collectStoneAndDeliver(wheelActions, 2.0);

        } else{

            strafe_RightAndStop(wheelActions,SPEED,0.4);
            sleep(1000);
            foundStone=isThisSkystone(colorSensor, hsvValues);

            if(foundStone){

                sleep(2020);
                telemetry.addData("Found black block","");
                collectStoneAndDeliver(wheelActions, 2.7);

            } else{

                strafe_RightAndStop(wheelActions,SPEED,0.4);
                sleep(1000);
                collectStoneAndDeliver(wheelActions, 3.0);
            }
        }

        foundStone = false;

        //Step8: Move backwards to park under bridge
        drive_ReverseAndStop(wheelActions, SPEED, 0.7);
        sleep(1000);

    }
    public boolean isThisSkystone(ColorSensor colorSensor, float hsvValues[]){

        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
        telemetry.addData("Hue", hsvValues[0]);

        if(hsvValues[0] < 50){

            return false;

        } else {

            return true;
        }
    }

    /**
     * This method will collect stone and deliver.
     * This method can be used again for 2nd stone.. collect and deliver
     */
    private void collectStoneAndDeliver(MecanumDriveWheelActions wheelActions, double distance) {

        //Step 2: if detect black block; Strafe RIGHT
        strafe_RightAndStop(wheelActions,SPEED,0.4);//changed
        sleep(1000);

        //Step 3: Move FORWARD
        drive_ForwardAndStop(wheelActions, SPEED, 0.6);
        sleep(2000);

        //Step 4: Spin LEFT
        set_Direction_SpinLeft(wheelActions);
        wheelActions.driveByTime(this, 0.3, 2.2);
        sleep(2000);

        //Step 5: Move FORWARD
        drive_ForwardAndStop(wheelActions, SPEED, 0.8);
        sleep(1000);

        //Step6: Spin RIGHT to face the BLUE bridge
        set_Direction_SpinRight(wheelActions);
        wheelActions.driveByTime(this, 0.3, 0.1);
        sleep(2000);

        // Step 7: Move FORWARD and deliver to the other side of the bridge
        drive_ForwardAndStop(wheelActions, SPEED, distance);
        sleep(1000);
    }

}