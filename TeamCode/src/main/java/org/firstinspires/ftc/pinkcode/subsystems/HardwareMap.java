package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public abstract class HardwareMap {
    protected DcMotor Spinner_Motor;
    protected DcMotor FL_Motor;
    protected DcMotor FR_Motor;
    protected DcMotor BL_Motor;
    protected DcMotor BR_Motor;
    protected DcMotor Collector_Motor;
    protected WebcamName Webcam;

    protected Servo Collector_Servo;

    public BNO055IMU imu;

    public HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        FL_Motor = _map.get(DcMotor.class, "fl-motor");
        FR_Motor = _map.get(DcMotor.class, "fr-motor");
        BL_Motor = _map.get(DcMotor.class, "bl-motor");
        BR_Motor = _map.get(DcMotor.class, "br-motor");

        Collector_Motor = _map.get(DcMotor.class, "collect-motor");
        Collector_Servo = _map.get(Servo.class, "collector-servo");

        Spinner_Motor = _map.get(DcMotor.class, "spinner-motor");

        imu = _map.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        configureMotorsDefault();

        FL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Spinner_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Webcam = _map.get(WebcamName.class, "Webcam 1");

    }

    protected void configureMotorsDefault() {
        FL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Collector_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    protected void configureMotorsPosition() {
        FL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
