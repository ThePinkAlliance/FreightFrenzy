package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.Robot;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends Robot {
    @Override
    public void init() {
    }

    @Override
    public void loop() {
        this.Base.Drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
}
