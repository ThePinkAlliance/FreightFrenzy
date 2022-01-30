package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.robotcore.util.Range;

public class Navigation extends HardwareMap {
    final double COUNTS_PER_INCH = 2048; // Counts Previous 49.8
    final double POSITION_THRESHOLD = 1.0;   // Base travel
    final double ANGLE_THRESHOLD = 4.0;     // Degrees

    double leftFMotorCmd, rightFMotorCmd, leftBMotorCmd, rightBMotorCmd, linearError;

    public Navigation(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    public int getEncodersCounts() {
        return (FL_Motor.getCurrentPosition() + FR_Motor.getCurrentPosition() + BR_Motor.getCurrentPosition() + BL_Motor.getCurrentPosition()) / 4;
    }

    public boolean driveToPos (double targetPosInches, double targetAngleDeg, double currentBasePosCounts, double currentAngleDeg,
                                      double linearSpeedCounts, double maxPower)
    {
        double angleErrorDegrees = targetAngleDeg - currentAngleDeg;
        double currentPosInches = (currentBasePosCounts / COUNTS_PER_INCH);
        double linearSpeedInches = linearSpeedCounts / COUNTS_PER_INCH;
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

        this.FR_Motor.setPower(rightBMotorCmd);
        this.BR_Motor.setPower(rightBMotorCmd);
        this.FL_Motor.setPower(leftBMotorCmd);
        this.BL_Motor.setPower(leftBMotorCmd);

        // True if navigated to position
        return (Math.abs(linearError) < POSITION_THRESHOLD) && (Math.abs(angleErrorDegrees) < ANGLE_THRESHOLD);
    }
}
