package org.firstinspires.ftc.atomic.gobilda.teleop;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "Sensor: MR Color", group = "Sensor")
public class SensorMRColorClean extends LinearOpMode {

  ColorSensor colorSensor;

  @Override
  public void runOpMode() {

    //During Active mode; where LED is ON (default mode);
    //During Passive mode; where LED is OFF

    colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color");
    colorSensor.enableLed(true);

    // Stores the Hue, Saturation and Value information.
    float hsvValues[] = {0F,0F,0F};

    final float referenceValues[] = hsvValues;
    final View relativeLayout = beforeSensing();

    waitForStart();

    while (opModeIsActive()) {

      Color.RGBToHSV(colorSensor.red() * 8,
              colorSensor.green() * 8,
              colorSensor.blue() * 8, hsvValues);

      int alpha = colorSensor.alpha();
      int red = colorSensor.red();
      int green = colorSensor.green();
      int blue = colorSensor.blue();
      float hue = hsvValues[0];

      telemetry.addData("Clear: ", alpha);
      telemetry.addData("Red: ", red);
      telemetry.addData("Green: ", green);
      telemetry.addData("Blue: ", blue);
      telemetry.addData("Hue: ", hue);

      //Add logic here to print "Yellow" or "NOT Yellow"
      //...
      //...
      //...

      telemetry.update();

      afterSensing(referenceValues, relativeLayout);
    }

    afterSensingSetWhite(relativeLayout, Color.WHITE);
  }




  //Standard code - no need to touch ******************
  private View beforeSensing() {
    // get a reference to the RelativeLayout so we can change the background
    // color of the Robot Controller app to match the hue detected by the RGB sensor.
    int relativeLayoutId = hardwareMap.appContext
            .getResources()
            .getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
    return ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
  }

  //Standard code - no need to touch ******************
  private void afterSensing(final float[] referenceValues, final View relativeLayout) {
    // change the background color to match the color detected by the RGB sensor.
    // pass a reference to hue, saturation and value array as an argument to HSVToColor method.
    relativeLayout.post(new Runnable() {
      public void run() {
        relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, referenceValues));
      }
    });
  }

  //Standard code - no need to touch ******************
  private void afterSensingSetWhite(final View relativeLayout, final int white) {
    // Set the panel back to the default color
    relativeLayout.post(new Runnable() {
      public void run() {
        relativeLayout.setBackgroundColor(white);
      }
    });
  }

}
