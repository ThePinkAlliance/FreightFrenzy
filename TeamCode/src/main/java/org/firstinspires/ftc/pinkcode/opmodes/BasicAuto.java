package org.firstinspires.ftc.pinkcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.pinkcode.roadrunner.drive.MecanumDrive;
import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Dashboard;
import org.firstinspires.ftc.pinkcode.subsystems.Navigation;

import java.util.HashMap;
import java.util.Map;

@Autonomous(name = "Encoder Viewer", group = "debug")
@Disabled
public class BasicAuto extends LinearOpMode {
    Navigation nav;
    Dashboard dashboard;

    Map<String, Object> data = new HashMap<>();

    @Override
    public void runOpMode() throws InterruptedException {
        this.nav = new Navigation(hardwareMap);
        this.dashboard = new Dashboard(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            data.put("counts", this.nav.getEncodersCounts());

            dashboard.sendData(data);
        }
    }
}
