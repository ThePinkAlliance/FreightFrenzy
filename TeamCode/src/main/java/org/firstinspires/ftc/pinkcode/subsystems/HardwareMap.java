package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.pinkcode.roadrunner.util.Encoder;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class HardwareMap {
    protected DcMotor Spinner_Motor;
    protected DcMotor FL_Motor;
    protected DcMotor FR_Motor;
    protected DcMotor BL_Motor;
    protected DcMotor BR_Motor;
    protected DcMotor Collector_Motor_L;
    protected DcMotor Collector_Motor_R;
    protected DcMotor Turret_Rotate_Motor_L;
    protected DcMotor Turret_Rotate_Motor_R;

    protected Encoder leftEncoder, rightEncoder, frontEncoder;

    protected DcMotor Turret_Motor;

    protected Servo Grabber_Rotate_L, Grabber_Rotate_R, Grabber_Motion;

    protected Servo Left_Pod_Servo, Right_Pod_Servo, Center_Pod_Servo;

    @Deprecated
    protected ColorSensor frontLeftColor;

    @Deprecated
    protected Rev2mDistanceSensor frontLeftDistance;

    protected WebcamName Webcam;

    protected DcMotor Collector_Intake_Motor;

    public BNO055IMU imu;

    public HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        FR_Motor = _map.get(DcMotorEx.class, "rightF_drive");
        BR_Motor = _map.get(DcMotorEx.class, "rightB_drive");
        FL_Motor = _map.get(DcMotorEx.class, "leftF_drive");
        BL_Motor = _map.get(DcMotorEx.class, "leftB_drive");

        Turret_Rotate_Motor_L = _map.get(DcMotor.class, "shoot1");
        Turret_Rotate_Motor_R = _map.get(DcMotor.class, "shoot2");

//        frontLeftDistance = (Rev2mDistanceSensor) _map.get(DistanceSensor.class, "distance-sensor");
//        frontLeftColor = _map.get(ColorSensor.class, "color-sensor");

        imu = _map.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

        Webcam = _map.get(WebcamName.class, "webcam");

        configureMotorsDefault();

        BL_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        FL_Motor.setDirection(DcMotorSimple.Direction.REVERSE);

        FL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Turret_Rotate_Motor_L.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Turret_Rotate_Motor_R.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Turret_Rotate_Motor_L.setDirection(DcMotorSimple.Direction.REVERSE);

//        Center_Pod_Servo = _map.get(Servo.class, "centerP-servo");
//        Right_Pod_Servo = _map.get(Servo.class, "rightP-servo");
//        Left_Pod_Servo = _map.get(Servo.class, "leftP-servo");
//
//        leftEncoder = new Encoder(_map.get(DcMotorEx.class, "leftF_drive"));
//        rightEncoder = new Encoder(_map.get(DcMotorEx.class, "rightB_drive"));
//        frontEncoder = new Encoder(_map.get(DcMotorEx.class, "rightF_drive"));
    }

    protected void configureMotorsDefault() {
        BR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Turret_Rotate_Motor_L.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Turret_Rotate_Motor_R.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    protected void configureMotorsPosition() {
        FL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
