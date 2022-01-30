package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Navigation;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

//@Autonomous(name = "red right")
public class RedRightAutoPD extends OpMode {
    private Base base;
    private Navigation navigation;
    private PizzaSpinner pizzaSpinner;

    private ElapsedTime time = new ElapsedTime();

    enum NAVIGATION_STATES {
        STOP,
        FORWARD_AHEAD_OF_ALLIANCE,
        FACE_CAROUSEL,
        MOVE_TOWARDS_CAROUSEL,
        SPIN_DUCKS,
        PARK,
    }

    private NAVIGATION_STATES currentState = NAVIGATION_STATES.FORWARD_AHEAD_OF_ALLIANCE;
    private final double MAX_POWER = 1;

    private final double FORWARD_AHEAD_OF_ALLIANCE_DIST = 19;
    private final double FORWARD_AHEAD_OF_ALLIANCE_ANG = 0;

    private final double MOVE_TOWARDS_CAROUSEL_DIST = 60;
    private final double MOVE_TOWARDS_CAROUSEL_ANG = 270;

    private double previousPosition = 0, integratedHeading = 0, previousHeading = 0;

    @Override
    public void init() {
        this.base = new Base(hardwareMap);
        this.navigation = new Navigation(hardwareMap);
        this.pizzaSpinner = new PizzaSpinner(hardwareMap);

        this.base.configureBase(false);
    }

    @Override
    public void loop() {
        double currentBaseCounts = 0, linearSpeedBase = (currentBaseCounts - previousPosition), currentAngleDeg = getIntegratedHeading();

        previousPosition = currentBaseCounts;

        switch (currentState) {
            case FORWARD_AHEAD_OF_ALLIANCE:
                boolean reachedDestination = navigation.driveToPos(currentBaseCounts + FORWARD_AHEAD_OF_ALLIANCE_DIST, FORWARD_AHEAD_OF_ALLIANCE_ANG, currentBaseCounts, currentAngleDeg, linearSpeedBase, MAX_POWER);

                if (reachedDestination) {
                    currentState = NAVIGATION_STATES.FACE_CAROUSEL;
                }
                break;

            case MOVE_TOWARDS_CAROUSEL:
                reachedDestination = navigation.driveToPos(currentBaseCounts + MOVE_TOWARDS_CAROUSEL_DIST, MOVE_TOWARDS_CAROUSEL_ANG, currentBaseCounts, currentAngleDeg, linearSpeedBase, MAX_POWER);

                if (reachedDestination) {
                    currentState = NAVIGATION_STATES.SPIN_DUCKS;
                }
                break;

            case SPIN_DUCKS:
                pizzaSpinner.autoSpin();
                break;

            case PARK:
                reachedDestination = navigation.driveToPos(currentBaseCounts + MOVE_TOWARDS_CAROUSEL_DIST, MOVE_TOWARDS_CAROUSEL_ANG, currentBaseCounts, currentAngleDeg, linearSpeedBase, MAX_POWER);

                if (reachedDestination) {
                    currentState = NAVIGATION_STATES.STOP;
                }
                break;

            case STOP:
                break;
        }
    }


    private double getIntegratedHeading() {
        double currentHeading = this.base.imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
        double deltaHeading = currentHeading - previousHeading;

        if (deltaHeading < -180) {
            deltaHeading += 360;
        } else if (deltaHeading >= 180) {
            deltaHeading -= 360;
        }

        integratedHeading += deltaHeading;
        previousHeading = currentHeading;

        return integratedHeading;
    }
}
