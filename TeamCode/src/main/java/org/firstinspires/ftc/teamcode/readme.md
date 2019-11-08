## TeamCode - ATOMIC NARWAHLS

Welcome!

This module, TeamCode, is the place where you will write/paste the code for your team's
robot controller App.

## Creating your own OpModes

* Basic:    This is a minimally functional OpMode used to illustrate the skeleton/structure
            of a particular style of OpMode.  These are bare bones examples.
* Sensor:   This is a Sample OpMode that shows how to use a specific sensor.
            It is not intended as a functioning robot, it is simply showing the minimal code
            required to read and display the sensor values.
* Hardware: This is not an actual OpMode, but a helper class that is used to describe
            one particular robot's hardware devices: eg: for a Pushbot.  Look at any
            Pushbot sample to see how this can be used in an OpMode.
            Teams can copy one of these to create their own robot definition.
* Pushbot:  This is a Sample OpMode that uses the Pushbot robot structure as a base.
* Concept:	This is a sample OpMode that illustrates performing a specific function or concept.
            These may be complex, but their operation should be explained clearly in the comments,
            or the header should reference an external doc, guide or tutorial.
* Library:  This is a class, or set of classes used to implement some strategy.
            These will typically NOT implement a full OpMode.  Instead they will be included
            by an OpMode to provide some stand-alone capability.


2)  In the new Team0417 folder, delete the TeamCode.iml file.

3)  the new Team0417 folder, rename the "src/main/java/org/firstinspires/ftc/teamcode" folder
    to a matching name with a lowercase 'team' eg:  "team0417".

4)  In the new Team0417/src/main folder, edit the "AndroidManifest.xml" file, change the line that contains
         package="org.firstinspires.ftc.teamcode"
    to be
         package="org.firstinspires.ftc.team0417"

5)  Add:    include ':Team0417' to the "/settings.gradle" file.
    
6)  Open up Android Studios and clean out any old files by using the menu to "Build/Clean Project""
