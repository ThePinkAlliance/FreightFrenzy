package org.firstinspires.ftc.pinkcode.navigation;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.path.Path;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.navigation.threads.ImuThread;
import org.firstinspires.ftc.pinkcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.pinkcode.subsystems.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Const;

/**
 * IMU: is for travel verification
 */
public class Navigation extends HardwareMap {
    private final Thread imuThread;
    private boolean isPositionReached = false;
    private boolean isHeadingReached = false;

    public Navigation(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);

        this.imuThread = new Thread(new ImuThread(this.imu));
        this.imuThread.start();
    }

    // TODO: Make sure there is no measurement system mismatch
    // TODO: this needs to be refactored and to add angle support
    public boolean moveTo(Pose2d pose) {
        double targetAngle = Math.toDegrees(Math.atan2(Cache.currentPosition.getX(), pose.getY()));
        double targetPosition = DriveConstants.inchesToTicks(pose.getX()) + this.FL_Motor.getCurrentPosition();
        double endTargetAngle = Math.toDegrees(pose.getHeading() - targetAngle);

        // Convert motor tick's to degrees for the final heading at the end
        int ticksToDegress = (int) Math.round((Constants.Ticks / 360) * Math.toDegrees(targetAngle));
        int rotTicks = (int) Math.round((Constants.Width * pose.getHeading() * Math.PI / 180) * Constants.Ticks);

        boolean isHeadingNegative = pose.getHeading() > 1;

        // Rotate the robot to the proper angle
        this.FL_Motor.setTargetPosition(-rotTicks);
        this.FR_Motor.setTargetPosition(rotTicks);
        this.BL_Motor.setTargetPosition(-rotTicks);
        this.BR_Motor.setTargetPosition(rotTicks);

        Cache.currentPosition = pose;

        return isPositionReached;
    }
}
