package org.firstinspires.ftc.pinkcode;

public class Constants {
    // spinnerTargetRPM: 223

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
