package org.firstinspires.ftc.pinkcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.List;

public class Vision extends HardwareMap {
    private com.qualcomm.robotcore.hardware.HardwareMap _map;

    public Vision(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);

        this._map = _map;

        initVuforia();
        initTfod();
    }

    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String TFOD_MODEL_ASSET_CUSTOM = "model.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };
    private static final String[] LABELS_CUSTOM = {
            "red_shipping",
    };
    //License Key
    private static final String VUFORIA_KEY =
            Constants.vuforiaKey;
    //Vision tool objects
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;


    /*
        RAWR PUT PROPER LABEL IN PROPER FORMAT
            Ball
            Cube
            Duck
            Marker
     */
    public boolean CheckForLabel(String label){
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                // step through the list of recognitions
                for (Recognition recognition : updatedRecognitions) {
                    // check label to see if the camera now sees said object
                    if (recognition.getLabel().equals(label)) {
                        //found it
                        return true;
                    }
                }
            }
        }

        //If never found object in all recognitions, then it isnt there, so false
        return false;
    }

    //can make methods for more specific outputs?
    //return array of specific label
    public List<Recognition> GetAllRecOfLabel(String label) {
        List<Recognition> labelRecs = null;
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {

                labelRecs = new ArrayList<>();

                // step through the list of recognitions
                for (Recognition recognition : updatedRecognitions) {
                    // check label to see if the camera now sees object
                    if (recognition.getLabel().equals(label)) {
                        //if rec is object we want, add to our output
                        labelRecs.add(recognition);
                    }
                }
            }
        }
        return labelRecs;
    }

    public void activate() {
        this.tfod.activate();
    }

    //Use thread for this?
    public void LoopVision(){

    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = this.Webcam;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }
    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = this._map.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", this._map.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET_CUSTOM, LABELS_CUSTOM);
    }
}
