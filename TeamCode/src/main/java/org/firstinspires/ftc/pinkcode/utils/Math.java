package org.firstinspires.ftc.pinkcode.utils;

public class Math {

    /**
     * @from https://sciencing.com/use-trig-calculate-height-things-5948457.html
     * @param angle of the camera
     * @param height of the camera
     * @return distance
     */
    public double getDistanceFromAngle(double angle, double height) {
        return height / java.lang.Math.sin(angle);
    }
}
