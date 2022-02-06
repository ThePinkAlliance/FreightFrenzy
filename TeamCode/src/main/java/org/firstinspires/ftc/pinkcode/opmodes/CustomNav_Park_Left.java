package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.navigation.Navigation;
import org.firstinspires.ftc.pinkcode.subsystems.Base;

@Autonomous(name = "Auto Custom Nav Left", group = "auto")
@Disabled
public class CustomNav_Park_Left extends LinearOpMode {
    Base Base;
    Navigation Navigation;

    enum states {
        START,
        TURN_RIGHT,
        PARK,
        END
    }
    states currentState = states.START;
    boolean reachedDes = false;

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

        while(opModeIsActive()){
            switch(currentState){
                case START:
                    //move forwards away from wall
                    reachedDes = this.Navigation.driveToPos(19, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if(reachedDes){
                        currentState = states.TURN_RIGHT;
                    }
                    break;
                case TURN_RIGHT:
                    //turn right
                    reachedDes = this.Navigation.driveToPos(6, 90, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if(reachedDes){
                        currentState = states.PARK;
                    }
                    break;
                case PARK:
                    //go forwards until past barrier
                    reachedDes = this.Navigation.driveToPos(80, Base.imu.getAngularOrientation().thirdAngle, Base.getBasePosCounts(), Base.imu.getAngularOrientation().thirdAngle, 5, Constants.DRIVE_SPEED);

                    if(reachedDes){
                        currentState = states.END;
                    }
                    break;
                case END:
                    break;
            }
        }

    }
}
