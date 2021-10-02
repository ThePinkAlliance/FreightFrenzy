package org.firstinspires.ftc.pinkcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.navigation.Navigation;
import org.firstinspires.ftc.pinkcode.roadrunner.roadrunner;
import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.utils.utils;

public abstract class RobotAsync extends LinearOpMode {
    public utils Utils;
    public roadrunner Roadrunner = new roadrunner(hardwareMap);
    public Base Base = new Base(hardwareMap);
    public Navigation Navigation = new Navigation(hardwareMap);
}

