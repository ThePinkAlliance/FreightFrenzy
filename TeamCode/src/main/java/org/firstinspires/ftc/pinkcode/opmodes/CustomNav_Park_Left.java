package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.navigation.Navigation;
import org.firstinspires.ftc.pinkcode.subsystems.Base;

@Autonomous(name = "Auto Custom Nav Left", group = "auto")
public class CustomNav_Park_Left extends LinearOpMode {
    Base Base;
    Navigation Navigation;

    /*
        Park in warehouse from left start position
     */

    @Override
    public void runOpMode() throws InterruptedException {
        this.Base = new Base(hardwareMap);

        this.Base.configureBase(true);

        this.Navigation = new Navigation(hardwareMap);

        // Wait for the game to begin
        waitForStart();

        //move forwards away from wall
        this.Navigation.driveToPos(19, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);
        //turn right
        this.Navigation.driveToPos(6, 90, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);
        //go forwards until past barrier
        this.Navigation.driveToPos(80, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

    }
}
