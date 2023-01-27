package frc.robot.subsystems;
// Author - Richard

// import the network modules neccessary for the april tag
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Apriltag{

    public Apriltag(){}
    // instance for setting up Network entries
    NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
    NetworkTable vision_nt = ntinst.getTable("Vision");
    // get fps, tag id from vision_nt
    NetworkTableEntry fps = vision_nt.getEntry("fps");
    NetworkTableEntry tag_id = vision_nt.getEntry("tag_id");

    /*Get pose estimations from vision_nt */
    // get translation estimations
    NetworkTableEntry translation_x = vision_nt.getEntry("translation_x");
    NetworkTableEntry translation_y = vision_nt.getEntry("translation_y");
    NetworkTableEntry translation_z = vision_nt.getEntry("translation_z");
    
    // get rotation estimations
    NetworkTableEntry rotation_x = vision_nt.getEntry("rotation_x");
    NetworkTableEntry rotation_y = vision_nt.getEntry("rotation_y");
    NetworkTableEntry rotation_z = vision_nt.getEntry("rotation_z");

    public double[] getEstimations () {
        double[] estimations = new double[8];

        
        estimations[0] = fps.getDouble((0)); // FPS
        estimations[1] = tag_id.getDouble((0)); // TAG ID, if not seen == 0
        
        estimations[2] = translation_x.getDouble((0)); // X
        estimations[3] = translation_y.getDouble((0)); // Y
        estimations[4] = translation_z.getDouble((0)); // Distance

        // rx = rotation_x.getDouble((0)); // FLIP X
        estimations[5] = rotation_y.getDouble((0)); // FLIP Y
        // rz = rotation_z.getDouble((0)); // Rotate


        estimations[6] = Math.atan((estimations[2])/(estimations[4])); // FIND ANGLE

        // distance in actual feet is calculated from the apriltag to the camera
        estimations[7] = estimations[4]/3.768; // GET ACTUAL DISTANCE IN FT
        return estimations; // returns the estimations

    }

}




