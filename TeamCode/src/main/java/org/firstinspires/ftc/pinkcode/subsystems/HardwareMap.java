package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class HardwareMap {
    protected DcMotor Spinner_Motor;
    protected DcMotor FL_Motor;
    protected DcMotor FR_Motor;
    protected DcMotor BL_Motor;
    protected DcMotor BR_Motor;
    protected DcMotor Collector_Motor_L;
    protected DcMotor Collector_Motor_R;
    protected DcMotor Turret_Rotate_Motor;

    protected WebcamName Webcam;

    protected DcMotor Collector_Intake_Motor;

    public BNO055IMU imu;

    public HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap _map) {
//        FL_Motor = _map.get(DcMotor.class, "fl-motor");
//        FR_Motor = _map.get(DcMotor.class, "fr-motor");
//        BL_Motor = _map.get(DcMotor.class, "bl-motor");
//        BR_Motor = _map.get(DcMotor.class, "br-motor");

        FR_Motor = _map.get(DcMotorEx.class, "rightF_drive");
        BR_Motor = _map.get(DcMotorEx.class, "rightB_drive");
        FL_Motor = _map.get(DcMotorEx.class, "leftF_drive");
        BL_Motor = _map.get(DcMotorEx.class, "leftB_drive");

        Turret_Rotate_Motor = _map.get(DcMotor.class, "turret_rot");

//        Collector_Motor_L = _map.get(DcMotor.class, "collect-motor-l");
//        Collector_Motor_R = _map.get(DcMotor.class, "collect-motor-r");
//        Collector_Intake_Motor = _map.get(DcMotor.class, "collector-intake-motor");

//        Spinner_Motor = _map.get(DcMotor.class, "spinner-motor");

        imu = _map.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

        Webcam = _map.get(WebcamName.class, "webcam");

        configureMotorsDefault();

        BL_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        FL_Motor.setDirection(DcMotorSimple.Direction.REVERSE);

//        Collector_Intake_Motor.setDirection(DcMotorSimple.Direction.REVERSE);

        FL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Turret_Rotate_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        Spinner_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        Collector_Motor_L.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        Collector_Motor_R.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        Collector_Motor_L.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        Collector_Motor_R.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        Collector_Motor_L.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    protected void configureMotorsDefault() {
        BR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Turret_Rotate_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    protected void configureMotorsPosition() {
        FL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
