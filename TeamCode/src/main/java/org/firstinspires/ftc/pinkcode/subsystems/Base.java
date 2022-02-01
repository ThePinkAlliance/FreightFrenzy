package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * Motor Type; Gobilda
 * Motor RPS: 7.25
 * Motor RPM: 435
 */

public class Base extends HardwareMap {
    private double retractTime = 4;
    private boolean inWarehouse = false;

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

    // possible issue with this
    // if the drivers drive up to the barrier but do not go over the pods will retract...
    // depending on the speed of the servos we can wait until the z axis on the imu bumps up...
    // and the distance to the barrier is very small we can retract and leave them retracted until exiting, for exiting the warehouse...
    // if imu z axis bumps up again and is close to the barrier we can lower the pods and reset roadrunners position
    public void needToRetract() {
//        double distance = this.frontLeftDistance.getDistance(DistanceUnit.INCH);

        Velocity vel = this.imu.getVelocity().toUnit(DistanceUnit.INCH);

//        int r = this.frontLeftColor.red();
//        int g = this.frontLeftColor.green();
//        int b = this.frontLeftColor.blue();

        // if the color sensor detects the barriers color return true. also the RGB values are placeholders for now
//        boolean isBarrierColor = r == 3 && g == 5 && b == 1;

        // if the yVelocity is greater then the xVelocity use the yVelocity
//        if (vel.yVeloc > vel.xVeloc && isBarrierColor) {
//            double secsUntilReached = vel.yVeloc / distance;

            // if seconds until reached barrier retract the odometry pods
//            if (secsUntilReached <= retractTime) {
//                centerPod.retract();
//            }
//        } else if (vel.xVeloc > vel.yVeloc && isBarrierColor) {
//            double secsUntilReached = vel.xVeloc / distance;

            // if seconds until reached barrier retract the odometry pods
//            if (secsUntilReached <= retractTime) {
//                centerPod.retract();
//            }
//        }

    }

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

    public void driveInches(double inches) {
        int ticks = (int) (inches * 2048);

        FL_Motor.setTargetPosition(ticks);
        FR_Motor.setTargetPosition(ticks);
        BR_Motor.setTargetPosition(ticks);
        BL_Motor.setTargetPosition(ticks);
    }

    public double getBaseCountRight() {
        return FR_Motor.getCurrentPosition();
    }

    public double getBaseCountLeft() {
        return FL_Motor.getCurrentPosition();
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
    public void tank(double leftRaw, double rightRaw) {
        double right = Range.clip(rightRaw, -0.7, 0.7);
        double left = Range.clip(leftRaw, -0.7, 0.7);

        this.FL_Motor.setPower(-right);
        this.FR_Motor.setPower(-left);
        this.BR_Motor.setPower(-left);
        this.BL_Motor.setPower(-right);
    }

    public void driveRaw(double front_left, double front_right, double back_left, double back_right) {
        this.FL_Motor.setPower(front_left);
        this.FR_Motor.setPower(front_right);
        this.BR_Motor.setPower(back_right);
        this.BL_Motor.setPower(back_left);
    }
}

