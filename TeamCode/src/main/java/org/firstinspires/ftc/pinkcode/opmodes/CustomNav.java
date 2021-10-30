package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.pinkcode.RobotAsync;

@Autonomous(name = "Auto Custom Nav", group = "auto")
public class CustomNav extends RobotAsync {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        this.Navigation.move(36);
        this.Navigation.rotate(50);
    }
}
