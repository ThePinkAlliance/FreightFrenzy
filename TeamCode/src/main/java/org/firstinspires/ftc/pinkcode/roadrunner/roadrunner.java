package org.firstinspires.ftc.pinkcode.roadrunner;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.pinkcode.roadrunner.drive.MecanumDrive;

public class roadrunner {
    public MecanumDrive mecanumDrive;

    public roadrunner(HardwareMap _map) {
        this.mecanumDrive = new MecanumDrive(_map);
    }
}
