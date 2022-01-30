package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.subsystems.Navigation;
import org.firstinspires.ftc.pinkcode.subsystems.PD;

@Autonomous(name = "Base Auto", group = "auto")
public class BaseAuto extends LinearOpMode {
    private int location_one = 36;
    private Navigation nav = new Navigation(hardwareMap);

    private enum STAGES {
        START,
        SHIPPING,
        DUCKS
    }

    private STAGES stages = STAGES.START;

    private int speedCounts = 2048;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        while (opModeIsActive()) {
            double baseCounts = nav.getEncodersCounts();

            switch (stages) {
                case START:

                    break;

                case SHIPPING:
                    boolean arrived = nav.driveToPos(location_one, 0, baseCounts, nav.imu.getPosition().z, speedCounts, 1);
                    if (arrived) {
                        stages = STAGES.DUCKS;
                    }
                    break;
            }
        }
    }
}
