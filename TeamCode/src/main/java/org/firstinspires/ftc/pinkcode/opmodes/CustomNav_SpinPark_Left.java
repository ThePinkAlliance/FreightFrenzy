package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.navigation.Navigation;
import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;

@Autonomous(name = "Auto Custom Nav Spin Left", group = "auto")
public class CustomNav_SpinPark_Left extends LinearOpMode {
    Base Base;
    PizzaSpinner PizzaSpinner;
    Navigation Navigation;

    enum states {
        START,
        TURN_LEFT,
        FORWARDS_TO_WALL,
        TURN_RIGHT,
        BACKWARDS_INTO_SPINNER,
        SPIN_SPINNER,
        FORWARDS_TO_PARK,
        TURN_LEFT_AGAIN,
        PARK,
        END
    }
    states currentState = states.START;
    boolean reachedDes = false;

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

        while(opModeIsActive()) {
            switch (currentState) {
                case START:
                    //move forwards away from wall
                    reachedDes = this.Navigation.driveToPos(17.25, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if (reachedDes) {
                        currentState = states.TURN_LEFT;
                    }
                    break;
                case TURN_LEFT:
                    //turn left
                    reachedDes = this.Navigation.driveToPos(6, -90, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if (reachedDes) {
                        currentState = states.FORWARDS_TO_WALL;
                    }
                    break;
                case FORWARDS_TO_WALL:
                    //go forwards until near wall
                    reachedDes = this.Navigation.driveToPos(20, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if (reachedDes) {
                        currentState = states.TURN_RIGHT;
                    }
                    break;
                case TURN_RIGHT:
                    //turn towards spinner
                    reachedDes = this.Navigation.driveToPos(6, 90, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if (reachedDes) {
                        currentState = states.BACKWARDS_INTO_SPINNER;
                    }
                    break;
                case BACKWARDS_INTO_SPINNER:
                    //go forwards until spinner is touching spinny thing
                    reachedDes = this.Navigation.driveToPos(-19, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if (reachedDes) {
                        currentState = states.SPIN_SPINNER;
                    }
                    break;
                case SPIN_SPINNER:
                    //spin spinny thing until duck is on floor
                    this.PizzaSpinner.autoSpin();

                    if (reachedDes) {
                        currentState = states.FORWARDS_TO_PARK;
                    }
                    break;
                case FORWARDS_TO_PARK:
                    //back up to park area
                    reachedDes = this.Navigation.driveToPos(19, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if (reachedDes) {
                        currentState = states.TURN_LEFT_AGAIN;
                    }
                    break;
                case TURN_LEFT_AGAIN:
                    //turn so back is to wall
                    reachedDes = this.Navigation.driveToPos(6, 90, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if (reachedDes) {
                        currentState = states.PARK;
                    }
                    break;
                case PARK:
                    //go backwards into wall to park.
                    reachedDes = this.Navigation.driveToPos(-6, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if (reachedDes) {
                        currentState = states.TURN_LEFT;
                    }
                    break;
                case END:
                    break;
            }
        }
    }
}
