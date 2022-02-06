package org.firstinspires.ftc.pinkcode;

public class AutoPath {
    public double distance;
    public double angle;
    public double maxPower = Constants.PATH_MAX_POWER;

    public AutoPath(double distance, double angle) {
        this.angle = angle;
        this.distance = distance;
    }

    public AutoPath(double distance, double angle, double maxPower) {
        this.distance = distance;
        this.angle = angle;
        this.maxPower = maxPower;
    }
}
