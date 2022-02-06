package org.firstinspires.ftc.pinkcode.subsystems;

import com.arcrobotics.ftclib.controller.PDController;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.pinkcode.Constants;

public class Navigation extends HardwareMap {
    final double COUNTS_PER_INCH = Constants.DT_ENCODER_COUNTS; // Counts Previous 49.8
    final double POSITION_THRESHOLD = 1.0;   // Base travel
    final double ANGLE_THRESHOLD = 4.0;     // Degrees

    PDController anglePID = new PDController(Constants.dt_angle_kP, Constants.dt_angle_kD);

    double leftFMotorCmd, rightFMotorCmd, leftBMotorCmd, rightBMotorCmd, linearError;

    public Navigation(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    public int getEncodersCounts() {
        return (FL_Motor.getCurrentPosition() + FR_Motor.getCurrentPosition()) / 2;
    }

    public void resetEncoders() {
        configureMotorsPosition();
    }

    public boolean driveToPos (double targetPosInches, double targetAngleDeg, double currentBasePosCounts, double currentAngleDeg,
                                      double linearSpeedCounts, double maxPower)
    {
        double angleErrorDegrees = targetAngleDeg - currentAngleDeg;
        double currentPosInches = (currentBasePosCounts / COUNTS_PER_INCH);
        double linearSpeedInches = linearSpeedCounts / COUNTS_PER_INCH;
        double angleOffset;
        boolean max = false;

        linearError = targetPosInches - currentPosInches;
        double angularError = targetAngleDeg - currentAngleDeg;
        double motorCmd = PD.getMotorCmd(Constants.dt_kP, Constants.dt_kD, linearError, linearSpeedInches);

        // Determine the baseline motor speed command, but limit it to leave room for the turn offset
        if(maxPower == -999) {
            motorCmd = Range.clip(motorCmd, -0.6, 1);
        } else {
            motorCmd = Range.clip(motorCmd, -0.6, 1);
            motorCmd = Range.clip(motorCmd, -maxPower, maxPower);
        }

        if (max) {
            motorCmd = 1;
        }

        // Determine and add the angle offset
//        angleOffset = PD.getMotorCmd(Constants.dt_angle_kP, Constants.dt_angle_kD, angularError, 0);
        angleOffset = anglePID.calculate(currentAngleDeg, targetAngleDeg);

        System.out.println(angleOffset);
        System.out.println(currentAngleDeg);
        System.out.println(targetAngleDeg);

        leftFMotorCmd = motorCmd - angleOffset;
        rightFMotorCmd = motorCmd + angleOffset;
        leftFMotorCmd = Range.clip(leftFMotorCmd, -maxPower, maxPower);
        rightFMotorCmd = Range.clip(rightFMotorCmd, -maxPower, maxPower);

        // Limit the max motor command for gentle motion
//        if (maxPower == -999) {
//            leftFMotorCmd = Range.clip(leftFMotorCmd, -.25, .25);
//            rightFMotorCmd = Range.clip(rightFMotorCmd, -.25, .25);
//        }
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
