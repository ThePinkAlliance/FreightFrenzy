package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Spinner Test", group = "teleop")
public class spinnerTest extends OpMode {
    DcMotor motor;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "pizzaspinner");
    }

    @Override
    public void loop() {
        double speed = gamepad1.left_stick_y;

        motor.setPower(speed);

        telemetry.addData("speed", speed);
        telemetry.update();
    }
}
