package org.firstinspires.ftc.pinkcode.navigation;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.path.Path;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.navigation.threads.ImuThread;
import org.firstinspires.ftc.pinkcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.pinkcode.subsystems.HardwareMap;
import org.firstinspires.ftc.pinkcode.subsystems.PD;
import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * IMU: is for travel verification
 */
public class Navigation extends HardwareMap {
    private final Thread imuThread;
    private boolean isPositionReached = false;
    private boolean isHeadingReached = false;
    private ElapsedTime time;

    static final double POSITION_THRESHOLD = 1.0;   // Base travel
    static final double ANGLE_THRESHOLD = 4.0;     // Degrees

    static double leftFMotorCmd, rightFMotorCmd, leftBMotorCmd, rightBMotorCmd, linearError;

    public Navigation(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);

        this.imuThread = new Thread(new ImuThread(this.imu));
        this.time = new ElapsedTime();
        this.imuThread.start();
    }

    // TODO: Make sure there is no measurement system mismatch
    // TODO: this needs to be refactored and to add angle support
    @Deprecated
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

    public void moveImu(Pose2d pose2d) {
        int position = (int) (FL_Motor.getCurrentPosition() / Constants.Ticks);

        this.time.startTime();

        double margin = 2;
        double x = imu.getPosition().x;
        double y = imu.getPosition().y;

        while (!inBounds(x, pose2d.getX(), margin) || !inBounds(y, pose2d.getY(), margin)) {
            if (time.seconds() >= 5) {
                time.reset();

                break;
            }

            this.FL_Motor.setPower(1);
            this.FR_Motor.setPower(1);
            this.BL_Motor.setPower(1);
            this.BR_Motor.setPower(1);
        }

        this.FL_Motor.setPower(0);
        this.FR_Motor.setPower(0);
        this.BL_Motor.setPower(0);
        this.BR_Motor.setPower(0);
    }

    public boolean inBounds(double _val, double _target, double _margin) {
        return (_val <= (_target - _margin) && _val <= (_target + _margin));
    }

    // Tank drive two wheels to target positions in inches.
    // Returns true when both arrive at the target.
    public boolean driveToPos (double targetPosInches, double targetAngleDeg, double currentBasePosCounts, double currentAngleDeg,
                                      double linearSpeedCounts, double maxPower)
    {
        double angleErrorDegrees = targetAngleDeg - currentAngleDeg;
        double currentPosInches = (currentBasePosCounts / Constants.Ticks);
        double linearSpeedInches = linearSpeedCounts / Constants.Ticks;
        double angleOffset;
        linearError = targetPosInches - currentPosInches;
        double angularError = targetAngleDeg - currentAngleDeg;
        double motorCmd = PD.getMotorCmd(0.02, 0.07, linearError, linearSpeedInches);

        // Determine the baseline motor speed command, but limit it to leave room for the turn offset
        if(maxPower == -999) {
            motorCmd = Range.clip(motorCmd, -0.6, 0.6);
        } else {
            motorCmd = Range.clip(motorCmd, -0.6, 0.6);
            motorCmd = Range.clip(motorCmd, -maxPower, maxPower);
        }


        // Determine and add the angle offset
        angleOffset = PD.getMotorCmd(0.02, 0.001, angularError, 0);
        leftFMotorCmd = motorCmd - angleOffset;
        rightFMotorCmd = motorCmd + angleOffset;
        leftFMotorCmd = Range.clip(leftFMotorCmd, -1.0, 1.0);
        rightFMotorCmd = Range.clip(rightFMotorCmd, -1.0, 1.0);

        // Limit the max motor command for gentle motion
        if (maxPower == -999) {
            leftFMotorCmd = Range.clip(leftFMotorCmd, -.25, .25);
            rightFMotorCmd = Range.clip(rightFMotorCmd, -.25, .25);
        }
        leftBMotorCmd = leftFMotorCmd;
        rightBMotorCmd = rightFMotorCmd;

        this.FL_Motor.setPower(leftFMotorCmd);
        this.BL_Motor.setPower(leftBMotorCmd);
        this.FR_Motor.setPower(rightFMotorCmd);
        this.BR_Motor.setPower(rightBMotorCmd);

        // True if navigated to position
        return (Math.abs(linearError) < POSITION_THRESHOLD) && (Math.abs(angleErrorDegrees) < ANGLE_THRESHOLD);
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
