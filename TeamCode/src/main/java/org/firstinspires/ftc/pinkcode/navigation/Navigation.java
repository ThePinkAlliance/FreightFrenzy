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
    public int move(double inches) {
        int position = (int) (FL_Motor.getCurrentPosition() + (inches * Constants.Ticks));

        // Rotate the robot to the proper angle
        this.FL_Motor.setTargetPosition(position);
        this.FR_Motor.setTargetPosition(position);
        this.BL_Motor.setTargetPosition(position);
        this.BR_Motor.setTargetPosition(position);

        // this is to pause the code until the motors stop
        while (FL_Motor.isBusy()) {}

        return position;
    }

    // the angle is in 180 degree format
    public int rotate(double angle) {
        int angleTicks = (int) ((Constants.Width * angle * Math.PI / 180) * Constants.Ticks) + FL_Motor.getCurrentPosition();

        this.FL_Motor.setTargetPosition(-angleTicks);
        this.FR_Motor.setTargetPosition(angleTicks);
        this.BL_Motor.setTargetPosition(-angleTicks);
        this.BR_Motor.setTargetPosition(angleTicks);

        // this is to pause the code until the motors stop
        while (FL_Motor.isBusy()) {}

        return angleTicks;
    }
}
