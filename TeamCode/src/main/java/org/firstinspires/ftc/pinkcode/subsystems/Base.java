package org.firstinspires.ftc.pinkcode.subsystems;

import org.firstinspires.ftc.pinkcode.Constants;

/**
 * Motor Type; Gobilda
 * Motor RPS: 5.33
 * Motor RPM: 320
 */

public class Base extends HardwareMap {
    public Base(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    public void configureBase(boolean auto) {
        if (auto) {
            this.configureMotorsPosition();
        } else {
            this.configureMotorsDefault();
        }
    }

    // get drive differance
    public double getDriveDiff() {
        double bl = this.BL_Motor.getCurrentPosition();
        double fl = this.FL_Motor.getCurrentPosition();

        double fr = this.FR_Motor.getCurrentPosition();
        double br = this.BR_Motor.getCurrentPosition();

        double l = fl / fr;

        return l * fr;
    }

    public void clutchBase(double power, double rot, double clutch) {
        double rPower = power / clutch;

        if (rot >= .1 || rot <= -.1) {
            driveRaw(-rPower, rPower, -rPower, rPower);
        } else {
            driveRaw(rPower, rPower, rPower, rPower);
        }
    }

    public double getBasePosCounts(){
        return this.BL_Motor.getCurrentPosition() / Constants.Ticks;
    }

    public double getBasePos(){
        return this.BL_Motor.getCurrentPosition();
    }

    // drive controls for mechanium drive
    public void drive(double left_stick_x, double left_stick_y, double right_stick_x) {
        // r is the hypotenuse of (x,y) coordinate of left stick, robotAngle = angleTheta of (x,y) coordinate of left stick. rightX = turning speed
        double r = Math.hypot(left_stick_x, left_stick_y);
        double robotAngle = Math.atan2(left_stick_y, left_stick_x) - Math.PI / 4;
        double rightX = right_stick_x;

        // Equations below is motor speed for each wheel
        double v1 = r * Math.cos(robotAngle) + rightX; // v1: Front Right
        double v2 = r * Math.sin(robotAngle) + rightX; // v2: Back Right
        double v3 = r * Math.sin(robotAngle) - rightX; // v3: Front Left
        double v4 = r * Math.cos(robotAngle) - rightX; // v4: Back Left

        // If not turning give each wheel full power
        if (right_stick_x == 0) {
            v1 += v1 / 3;
            v2 += v2 / 3;
            v3 += v3 / 3;
            v4 += v4 / 3;
        }

        this.FL_Motor.setPower(v3);
        this.FR_Motor.setPower(v1);
        this.BL_Motor.setPower(v4);
        this.BR_Motor.setPower(v2);
    }

    // drive controls for mechanium drive
    public void tank(double left_stick_x, double left_stick_y, double right_stick_x) {
        // r is the hypotenuse of (x,y) coordinate of left stick, robotAngle = angleTheta of (x,y) coordinate of left stick. rightX = turning speed
        double r = Math.hypot(left_stick_x, left_stick_y);
        double robotAngle = Math.atan2(left_stick_y, left_stick_x) - Math.PI / 4;
        double rightX = right_stick_x;

        // Equations below is motor speed for each wheel
        double v1 = r * Math.cos(robotAngle) + rightX; // v1: Front Right
        double v2 = r * Math.cos(robotAngle) + rightX; // v2: Back Right
        double v3 = r * Math.cos(robotAngle) - rightX; // v3: Front Left
        double v4 = r * Math.cos(robotAngle) - rightX; // v4: Back Left

        // If not turning give each wheel full power
        if (right_stick_x == 0) {
            v1 += v1 / 3;
            v2 += v2 / 3;
            v3 += v3 / 3;
            v4 += v4 / 3;
        }

        this.FL_Motor.setPower(v3);
        this.FR_Motor.setPower(v1);
        this.BL_Motor.setPower(v4);
        this.BR_Motor.setPower(v2);
    }


    // drive controls for tank drive
    public void tank(double left, double right) {
        this.FL_Motor.setPower(left);
        this.FR_Motor.setPower(right);
        this.BR_Motor.setPower(right);
        this.BL_Motor.setPower(left);
    }

    public void driveRaw(double front_left, double front_right, double back_left, double back_right) {
        this.FL_Motor.setPower(front_left);
        this.FR_Motor.setPower(front_right);
        this.BR_Motor.setPower(back_right);
        this.BL_Motor.setPower(back_left);
    }
}

