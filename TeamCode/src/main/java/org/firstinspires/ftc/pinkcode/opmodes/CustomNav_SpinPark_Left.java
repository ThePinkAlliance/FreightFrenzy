package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.navigation.Navigation;
import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;

@Autonomous(name = "Auto Custom Nav", group = "auto")
public class CustomNav_SpinPark_Left extends LinearOpMode {
    Base Base;
    PizzaSpinner PizzaSpinner;
    Navigation Navigation;

    /*
        Spin spinner then park in storage thingy next to the spinner
        from the left side
     */

    @Override
    public void runOpMode() throws InterruptedException {
        this.Base = new Base(hardwareMap);

        this.Base.configureBase(true);

        this.PizzaSpinner = new PizzaSpinner(hardwareMap);

        this.Navigation = new Navigation(hardwareMap);

        // Wait for the game to begin
        waitForStart();

        //move forwards away from wall
        this.Navigation.driveToPos(17.25, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);
        //turn left
        this.Navigation.driveToPos(6, -90, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);
        //go forwards until near wall
        this.Navigation.driveToPos(20, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);
        //turn towards spinner
        this.Navigation.driveToPos(6, 90, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);
        //go forwards until spinner is touching spinny thing
        this.Navigation.driveToPos(-19, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);
        //spin spinny thing until duck is on floor
        this.PizzaSpinner.autoSpin();
        //back up to park area
        this.Navigation.driveToPos(19, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);
        //turn so back is to wall
        this.Navigation.driveToPos(6, 90, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);
        //go backwards into wall to park.
        this.Navigation.driveToPos(-6, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);


    }
}
