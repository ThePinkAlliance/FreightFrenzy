package org.firstinspires.ftc.pinkcode.navigation;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

public class Cache {
    public static double frontLeftMotorTicks = 0;
    public static double backLeftMotorTicks = 0;
    public static double frontRightMotorTicks = 0;
    public static double backRightMotorTicks = 0;
    public static Acceleration currentAcceleration;
    public static Orientation currentAngle;

    public static Pose2d currentPosition;
    public static Position currentImuPosition;
}
