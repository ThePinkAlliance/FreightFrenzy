package org.firstinspires.ftc.pinkcode.subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class Shooter extends org.firstinspires.ftc.pinkcode.subsystems.HardwareMap {
    private final int shootSpeed = 1;
    private final double gateStartPos = 100;
    private final double dateOpenPos = 150;

    public Shooter(HardwareMap _map) {
        super(_map);
    }

    public void RevUp() {
        this.Shooter_Gate.setPosition(gateStartPos);
        this.Shooter_Motor.setPower(this.shootSpeed);
    }

    public void Stop() {
        this.Shooter_Motor.setPower(0);
    }

    public void Shoot() {
        this.Shooter_Gate.setPosition(dateOpenPos);
    }
}
