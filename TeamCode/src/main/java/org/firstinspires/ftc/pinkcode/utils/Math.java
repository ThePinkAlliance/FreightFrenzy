package org.firstinspires.ftc.pinkcode.utils;

import org.firstinspires.ftc.pinkcode.Constants;

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

    /**
     *
     * @param objectAngle
     * @param cameraPosition
     * @return angle from the front of the robot
     */
    public double calculateFromObject(double objectAngle, int cameraPosition) {
        return objectAngle + cameraPosition;
    }
}
