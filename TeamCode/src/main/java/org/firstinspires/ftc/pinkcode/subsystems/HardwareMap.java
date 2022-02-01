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

public class HardwareMap {
    protected DcMotor Spinner_Motor;
    protected DcMotor FL_Motor;
    protected DcMotor FR_Motor;
    protected DcMotor BL_Motor;
    protected DcMotor BR_Motor;
    protected DcMotor Collector_Motor_L;
    protected DcMotor Collector_Motor_R;

    protected Encoder leftEncoder, rightEncoder, frontEncoder;

    protected Servo Grabber_Rotate_L, Grabber_Rotate_R, Grabber_Motion;

    protected Servo Collector_rotate;

    protected Servo Left_Pod_Servo, Right_Pod_Servo, Center_Pod_Servo;

    protected DcMotor Collector_Intake_Motor;

    public BNO055IMU imu;

    public HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        FR_Motor = _map.get(DcMotorEx.class, "rightF");
        BR_Motor = _map.get(DcMotorEx.class, "rightB");
        FL_Motor = _map.get(DcMotorEx.class, "leftF");
        BL_Motor = _map.get(DcMotorEx.class, "leftB");

        Collector_Motor_L = _map.get(DcMotor.class, "tower-l");
        Collector_Motor_R = _map.get(DcMotor.class, "tower-r");

        Collector_Intake_Motor = _map.get(DcMotor.class, "intake");
        Collector_rotate = _map.get(Servo.class, "col-rot");
        Spinner_Motor = _map.get(DcMotor.class, "pizza");

        imu = _map.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

        configureMotorsDefault();

        BL_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        FL_Motor.setDirection(DcMotorSimple.Direction.REVERSE);

        FL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Collector_Motor_R.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Collector_Motor_L.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Collector_Intake_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        Spinner_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Collector_Motor_L.setDirection(DcMotorSimple.Direction.FORWARD);
        Collector_Motor_L.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    protected void configureMotorsDefault() {
        BR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    protected void configureMotorsPosition() {
        FL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        BR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Collector_Motor_R.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Collector_Motor_L.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Collector_Motor_R.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Collector_Motor_L.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
