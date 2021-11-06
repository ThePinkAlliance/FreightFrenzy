package org.firstinspires.ftc.pinkcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.navigation.Navigation;
import org.firstinspires.ftc.pinkcode.roadrunner.roadrunner;
import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Collector;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;
import org.firstinspires.ftc.pinkcode.subsystems.Vision;
import org.firstinspires.ftc.pinkcode.utils.utils;

public abstract class RobotAsync extends LinearOpMode {
    public Base Base = new Base(hardwareMap);
    public Collector Collector = new Collector(hardwareMap);
    public Navigation Navigation = new Navigation(hardwareMap);
    public PizzaSpinner PizzaSpinner = new PizzaSpinner(hardwareMap);
    public Vision Vision = new Vision(hardwareMap);
}