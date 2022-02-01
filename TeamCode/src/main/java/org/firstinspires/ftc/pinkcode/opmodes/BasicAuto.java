package org.firstinspires.ftc.pinkcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.pinkcode.roadrunner.drive.MecanumDrive;
import org.firstinspires.ftc.pinkcode.subsystems.Base;

@Autonomous(name = "Basic Auto", group = "auto")
@Disabled
public class BasicAuto extends LinearOpMode {
    Base base;
    MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        this.base = new Base(hardwareMap);
        this.drive = new MecanumDrive(hardwareMap);

        waitForStart();

        Trajectory one = drive.trajectoryBuilder(new Pose2d(0, 0, Math.toRadians(0))).forward(12).build();

        drive.followTrajectory(one);
    }
}
