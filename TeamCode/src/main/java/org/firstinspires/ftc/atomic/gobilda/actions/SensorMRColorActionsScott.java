/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.atomic.gobilda.actions;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
 * The op mode assumes that the color sensor Modern Robotics Color Sensor
 * is configured with a name of "sensor_color".
 *
 * You can use the X button on gamepad1 to toggle the LED on and off.
 */
public class SensorMRColorActionsScott {

    Telemetry telemetry;
    HardwareMap hardwareMap;
    LinearOpMode linearOpMode;

    ColorSensor colorSensor;
    boolean led_light_ON;

    public SensorMRColorActionsScott(Telemetry tele,
                                     HardwareMap hardware,
                                     LinearOpMode linearOpModeFromCallingProgram) {

        this.telemetry = tele;
        this.hardwareMap = hardware;
        this.linearOpMode = linearOpModeFromCallingProgram;

        //1. get a reference to our ColorSensor object.
        colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color");

        //2. in the beginning turn OFF the LED
        led_light_ON = false;

    }

    public void isYellow() {

        // sensor_HSV_Values is an array that will hold the hue, saturation and value.
        float sensor_HSV_Values[] = {0F, 0F, 0F};
        final float reference_HSV_values[] = sensor_HSV_Values;

        final View view = beforeSensingColor();

        // Set sensor LED on
        colorSensor.enableLed(true);

        // convert the RGB to HSV  (this is like converting inches to feet - unit conversion)
        Color.RGBToHSV(colorSensor.red() * 8,
                    colorSensor.green() * 8,
                    colorSensor.blue() * 8,
                        sensor_HSV_Values);


        float hueValue = sensor_HSV_Values[0];
        float saturationValue = sensor_HSV_Values[1];
        float valueValue = sensor_HSV_Values[2];

        telemetry.addData("Red: ", colorSensor.red());
        telemetry.addData("Green: ", colorSensor.green());
        telemetry.addData("Blue: ", colorSensor.blue());
        telemetry.addData("ALPHA: ", colorSensor.alpha());

        telemetry.addData("Hue: ", hueValue);
        telemetry.addData("Saturation: ", saturationValue);
        telemetry.addData("Value: ", valueValue);
        
        if (hueValue < 50) {
            telemetry.addData("Yellow: ", hueValue);
        } else {
            telemetry.addData("NOT Yellow: ", hueValue);
        }
        telemetry.update();


        afterSensingColor(reference_HSV_values, view);

    }

    //NO NEED TO TOUCH THIS METHOD ****************
    private View beforeSensingColor() {

        int relativeLayoutId = hardwareMap.appContext
                .getResources()
                .getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());

        return ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
    }

    //NO NEED TO TOUCH THIS METHOD ****************
    private void afterSensingColor(final float[] reference_HSV_values, final View view) {

        view.post(new Runnable() {
            public void run() {
                view.setBackgroundColor(Color.HSVToColor(0xff, reference_HSV_values));
            }
        });

        view.post(new Runnable() {
            public void run() {
                view.setBackgroundColor(Color.WHITE);
            }
        });
    }

}
