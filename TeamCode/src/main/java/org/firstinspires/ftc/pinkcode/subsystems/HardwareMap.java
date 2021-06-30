package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class HardwareMap {
    public DcMotor FL_Motor;
    public DcMotor FR_Motor;
    public DcMotor BL_Motor;
    public DcMotor BR_Motor;

    public HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        FL_Motor = _map.get(DcMotor.class, "fl-motor");
        FR_Motor = _map.get(DcMotor.class, "fr-motor");
        BL_Motor = _map.get(DcMotor.class, "bl-motor");
        BR_Motor = _map.get(DcMotor.class, "br-motor");

        FL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
