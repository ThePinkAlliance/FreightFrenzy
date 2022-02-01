package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.subsystems.Base;

@TeleOp(name = "Drive Config", group = "teleop")
@Disabled
public class Config extends LinearOpMode {
    Base base;

    @Override
    public void runOpMode() throws InterruptedException {
        base = new Base(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            base.tank(gamepad1.left_stick_y, gamepad1.right_stick_y);

            telemetry.addData("drive diff", base.getDriveDiff());
            telemetry.update();
        }
    }
}
