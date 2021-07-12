package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class HardwareMap {
    public DcMotor FL_Motor;
    public DcMotor FR_Motor;
    public DcMotor BL_Motor;
    public DcMotor BR_Motor;
    public DcMotor Shooter_Motor;

    public Servo Shooter_Gate;

    public HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        FL_Motor = _map.get(DcMotor.class, "fl-motor");
        FR_Motor = _map.get(DcMotor.class, "fr-motor");
        BL_Motor = _map.get(DcMotor.class, "bl-motor");
        BR_Motor = _map.get(DcMotor.class, "br-motor");
        Shooter_Motor = _map.get(DcMotor.class, "shooter-motor");

        Shooter_Gate = _map.get(Servo.class, "shooter-gate");

        FL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Shooter_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Shooter_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Shooter_Gate.setDirection(Servo.Direction.FORWARD);
    }
}
