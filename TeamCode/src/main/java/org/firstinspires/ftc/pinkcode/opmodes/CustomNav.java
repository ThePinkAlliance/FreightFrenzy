package org.firstinspires.ftc.pinkcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.navigation.Navigation;
import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

@Autonomous(name = "Auto Custom Nav", group = "auto")
@Disabled
public class CustomNav extends LinearOpMode {
    Base Base;
    Navigation Navigation;

    enum states {
        PARKED,
        MOVE_TO_BARCODE,
    }

    states currentState = states.PARKED;

    @Override
    public void runOpMode() throws InterruptedException {
        this.Base = new Base(hardwareMap);

        this.Base.configureBase(true);

        this.Navigation = new Navigation(hardwareMap);

        // Wait for the game to begin
        waitForStart();

        while (opModeIsActive()) {
            // Note that we be using DRIVE_SPEED
            switch (currentState) {
                case PARKED:
                    boolean reachedDest = this.Navigation.driveToPos(6, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(),
                            Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if (reachedDest) {
                        currentState = states.MOVE_TO_BARCODE;
                    }

                    break;

                case MOVE_TO_BARCODE:
                    this.Navigation.driveToPos(6, 90, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);
                    break;
            }
        }


    }
}
