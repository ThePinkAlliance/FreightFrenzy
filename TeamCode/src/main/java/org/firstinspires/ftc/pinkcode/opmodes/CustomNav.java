package org.firstinspires.ftc.pinkcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.pinkcode.RobotAsync;

@Autonomous(name = "Auto Custom Nav", group = "auto")
public class CustomNav extends RobotAsync {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d pose = new Pose2d(2, 2);

        this.Navigation.moveTo(pose);
    }
}
