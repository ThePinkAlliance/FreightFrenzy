package org.firstinspires.ftc.pinkcode;

import com.acmerobotics.dashboard.config.Config;

@Config
public class Constants {
    // spinnerTargetRPM: 223

    public static String vuforiaKey = "AU5HdoL/////AAABmdflEYY1uEgKvLLnXhuUKQEiOh/Swf8w1NP3fjwJ0L5KhNZjEBmtqvcb1vRriuL7dxpTimmKsrPxVN0GSemDm1z0zZHiuEDJjN6is0gE5cC8eCf5/w4A9J9xygAQMiK4UOje3lWQjKpyMbqNeKgy1I6PZqyXBae1+6/gecIRmHuDjcqGFcEnRKmf8e6iPrFIdaC53DkmQUxJWRalVEqWsdmwmLm69AsaoG+aL7D0xkupVo7U23C2fdDkl66qsFO7v7jf0ONGEdmNjg1TTEKQmrip86/iMst+I7mdLA/pYsY00EjAjgPJ8YdXEqR5pKR2CK4DNmVU+c2A7T+w+KhGwxJ8us9j9FpYTd1yC0wRQD0R";
    public static double towerGearWidth = 1.543307;
    // inches
    public static double towerGearCircumference = 4.85;
    public static double towerGearLength = 4.23;

    public static double Width = 10;

    // this is for the amount of ticks per inch
    public static double Ticks = 2048;

    public interface CollectorPositions {
        int COLLECT = 0;
        int BOTTOM = 10;
        int MIDDLE = 50;
        int TOP = 100;
    }

    public interface CAMERA_POSITIONS {
        int FRONT = 0;
        int LEFT = -90;
        int RIGHT = 45;
    }

    public interface CollectorGrabberPositions {
        int REVERSE = -1;
        int OPEN = 1;
    }

    public static final double     COUNTS_PER_MOTOR_REV    = 2048 ;    // eg: Gobilda Motor Encoder
    public static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    public static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    public static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    // These constants define the desired driving/control characteristics
    // The can/should be tweaked to suite the specific robot drive train.
    public static final double     DRIVE_SPEED             = 0.7;     // Nominal speed for better accuracy.
    public static final double     TURN_SPEED              = 0.5;     // Nominal half speed for better accuracy.

    public static final double     HEADING_THRESHOLD       = 1 ;      // As tight as we can make it with an integer gyro
    public static final double     P_TURN_COEFF            = 0.1;     // Larger is more responsive, but also less stable
    public static final double     P_DRIVE_COEFF           = 0.15;     // Larger is more responsive, but also less stable

}
