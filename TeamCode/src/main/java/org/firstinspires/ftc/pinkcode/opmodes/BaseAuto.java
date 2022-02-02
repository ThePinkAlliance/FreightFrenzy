package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.subsystems.Dashboard;
import org.firstinspires.ftc.pinkcode.subsystems.Navigation;
import org.firstinspires.ftc.pinkcode.subsystems.PD;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

import java.util.HashMap;
import java.util.Map;

@Autonomous(name = "Base Auto", group = "auto")
public class BaseAuto extends LinearOpMode {
    private double location_one = 10;
    private int location_two = 40;
    private Navigation nav;
    private Dashboard dashboard;

    private enum STAGES {
        START,
        SHIPPING,
        DUCKS,
        STOP
    }

    private STAGES stages = STAGES.START;

    private double previousCounts = 0;
    private double linearSpeed = 0;
    private Map<String, Object> dashboardData = new HashMap<>();

    @Override
    public void runOpMode() throws InterruptedException {
        this.nav = new Navigation(hardwareMap);
        this.dashboard = new Dashboard(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            double baseCounts = nav.getEncodersCounts();
            Position imuPosition = nav.imu.getPosition();

            linearSpeed = baseCounts - previousCounts;
            previousCounts = baseCounts;

            dashboardData.put("linearSpeed", linearSpeed);
            dashboardData.put("current inches", baseCounts / Constants.DT_ENCODER_COUNTS);
            dashboardData.put("baseCounts", baseCounts);
            dashboardData.put("imu position", imuPosition);

            switch (stages) {
                case START:
                    dashboardData.put("target inches", location_one);

                    if (nav.driveToPos(location_one, 0, baseCounts, nav.imu.getPosition().z, linearSpeed, .25)) {
                        stages = STAGES.STOP;
                    }
                    break;

                case SHIPPING:
                    if (nav.driveToPos(location_one, 0, baseCounts, nav.imu.getPosition().z, linearSpeed, 1)) {
                        stages = STAGES.DUCKS;
                    }
                    break;

                case DUCKS:
                    if (nav.driveToPos(location_two, 90, baseCounts, nav.imu.getPosition().z, linearSpeed, 1)) {
                        stages = STAGES.DUCKS;
                    }
                    break;

                case STOP:
                    break;
            }

            dashboard.sendData(dashboardData);
        }
    }
}
