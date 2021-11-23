package org.firstinspires.ftc.pinkcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.roadrunner.drive.MecanumDrive;
import org.firstinspires.ftc.pinkcode.subsystems.Base;

public class BasicAuto extends LinearOpMode {
    Base base;
    MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        this.base = new Base(hardwareMap);
        this.drive = new MecanumDrive(hardwareMap);

        waitForStart();

        Trajectory one = drive.trajectoryBuilder(new Pose2d(0, 4)).build();

        drive.followTrajectory(one);
    }
}
