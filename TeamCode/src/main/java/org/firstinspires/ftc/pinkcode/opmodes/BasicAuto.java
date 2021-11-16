package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.subsystems.Base;

public class BasicAuto extends LinearOpMode {
    Base base;

    @Override
    public void runOpMode() throws InterruptedException {
        this.base = new Base(hardwareMap);

        waitForStart();

        base.tank(1, 1);
        sleep(5000);
    }
}
