package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

public abstract class HardwareMap {
    protected DcMotor FL_Motor;
    protected DcMotor FR_Motor;
    protected DcMotor BL_Motor;
    protected DcMotor BR_Motor;
    public BNO055IMU imu;

    public HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        FL_Motor = _map.get(DcMotor.class, "fl-motor");
        FR_Motor = _map.get(DcMotor.class, "fr-motor");
        BL_Motor = _map.get(DcMotor.class, "bl-motor");
        BR_Motor = _map.get(DcMotor.class, "br-motor");

        imu = _map.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        FL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
