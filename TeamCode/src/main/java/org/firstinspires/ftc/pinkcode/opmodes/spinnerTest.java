package org.firstinspires.ftc.pinkcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.pinkcode.subsystems.Dashboard;

import java.util.HashMap;
import java.util.Map;

@TeleOp(name = "Spinner Test", group = "teleop")
@Disabled
public class spinnerTest extends OpMode {
    public static double velocity = 0;

    Dashboard dash;
    Map<String, Object> data = new HashMap<>();
    DcMotorEx motor;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "pizza");
        dash = new Dashboard(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad2.left_trigger > 0.1) {
            motor.setVelocity(-velocity);
        } else if (gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0) {
            motor.setVelocity(0);
        }

        if (gamepad2.right_trigger > 0.1) {
            motor.setVelocity(velocity);
        }

        data.put("Velocity", motor.getVelocity());

        dash.sendData(data);
    }
}
