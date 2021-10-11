package org.firstinspires.ftc.pinkcode.navigation;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.navigation.threads.ImuThread;
import org.firstinspires.ftc.pinkcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.pinkcode.subsystems.HardwareMap;

public class Navigation extends HardwareMap {
    private Thread imuThread;
    private boolean isPositionReached = false;
    private boolean isHeadingReached = false;


    public Navigation(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);

        this.imuThread = new Thread(new ImuThread(this.imu));
        this.imuThread.start();
    }

    // TODO: Make sure there is no measurement system mismatch
    public boolean moveTo(Pose2d pose) {
        double targetAngle = Math.toDegrees(Math.atan2(Cache.currentPosition.getX(), pose.getY()));
        double targetPosition = DriveConstants.inchesToTicks(pose.getX()) + this.FL_Motor.getCurrentPosition();
        double endTargetAngle = Math.toDegrees(pose.getHeading() - targetAngle);

        // Convert motor tick's to degrees for the final heading at the end
        int ticksToDegress = (int) Math.round((Constants.Ticks / 360) * Math.toDegrees(targetAngle));

        boolean isHeadingNegative = pose.getHeading() > 1;

        // Rotate the robot to the proper angle
        if (isHeadingNegative) {
            this.FL_Motor.setTargetPosition(-ticksToDegress);
            this.FR_Motor.setTargetPosition(ticksToDegress);
            this.BL_Motor.setTargetPosition(-ticksToDegress);
            this.BR_Motor.setTargetPosition(ticksToDegress);
        } else {
            this.FL_Motor.setTargetPosition(ticksToDegress);
            this.FR_Motor.setTargetPosition(-ticksToDegress);
            this.BL_Motor.setTargetPosition(ticksToDegress);
            this.BR_Motor.setTargetPosition(-ticksToDegress);
        }

        while (isHeadingReached) {
            if (Cache.currentAngle.thirdAngle > (targetAngle - 0.2)) {
                this.FL_Motor.setPower(0);
                this.FR_Motor.setPower(0);
                this.BL_Motor.setPower(0);
                this.BR_Motor.setPower(0);

                this.isHeadingReached = true;
            }
        }

        while (isPositionReached) {
            if (Cache.currentImuPosition.x > (targetPosition - 0.2)) {
                this.FL_Motor.setPower(0);
                this.FR_Motor.setPower(0);
                this.BL_Motor.setPower(0);
                this.BR_Motor.setPower(0);

                this.isPositionReached = true;
            }
        }

        Cache.currentPosition = pose;

        return isPositionReached;
    }
}
