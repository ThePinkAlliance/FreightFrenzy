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
    public interface CollectorGrabberPositions {
        double CLOSE = 0;
        double OPEN = 100;
    }
}
