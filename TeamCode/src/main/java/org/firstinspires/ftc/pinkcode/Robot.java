package org.firstinspires.ftc.pinkcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Collector;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;
import org.firstinspires.ftc.pinkcode.subsystems.Vision;
import org.firstinspires.ftc.pinkcode.utils.utils;

public abstract class Robot extends OpMode {
    public utils Utils;
    public Collector Collector = new Collector(hardwareMap);
    public Base Base = new Base(hardwareMap);
    public PizzaSpinner PizzaSpinner = new PizzaSpinner(hardwareMap);
    public Vision Vision = new Vision(hardwareMap);
}
