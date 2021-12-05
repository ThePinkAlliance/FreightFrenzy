package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Collector;
import org.firstinspires.ftc.pinkcode.subsystems.Vision;
import org.firstinspires.ftc.pinkcode.utils.Utils;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends OpMode {
    private Base Base;
    private Collector Collector;
    private Utils Utils;

    @Override
    public void init() {
        Base = new Base(hardwareMap);
        Collector = new Collector(hardwareMap);

        this.Base.configureBase(false);
    }

    // variables used in the following
    boolean checkedOnce = false;
    double focalLength = 0;
    double widthOfObject = 15; // I think it is 15 inches, the object is the shipping hub
    @Override
    public void loop() {
        // drives the left, right side's of the drive train from joystick positions
        this.Base.tank(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        telemetry.update();
    }

}