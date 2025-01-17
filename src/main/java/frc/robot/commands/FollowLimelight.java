package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.SwerveDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class FollowLimelight extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private SwerveDrive m_SwerveDrive;
  private Limelight m_limelight;
  private double kP_rotation = -0.1;
  private double kP_forwardDistance = 0.1;
  private double kP_sideDistance = 0.1;

  private double rotation;
  private double sideDistance;
  private double forwardDistance;

  private double xVel = 0;
  private double yVel = 0;

  private double rotationCutOff = 0.1;
  private double sideDistanceCutOff = 0.1;
  private double CutOff = 0.1;


  public FollowLimelight(SwerveDrive sd, Limelight limelight) {
    m_SwerveDrive = sd;
    m_limelight = limelight;
    SmartDashboard.putNumber("Limelight Rotation kP", kP_rotation);
    SmartDashboard.putNumber("Limelight Side Distance kP", kP_sideDistance);
    SmartDashboard.putNumber("Limelight Forward Distance kP", kP_forwardDistance);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sd, limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double[] target = m_limelight.getVisionTarget();
    kP_rotation = SmartDashboard.getNumber("Limelight Rotation kP", kP_rotation);
    kP_forwardDistance = SmartDashboard.getNumber("Limelight Side Distance kP", kP_sideDistance);
    kP_sideDistance = SmartDashboard.getNumber("Limelight Forward Distance kP", kP_forwardDistance);

    rotation = target[1]*kP_rotation;
    forwardDistance = (3-target[3])*kP_forwardDistance;
    sideDistance = target[4]*kP_sideDistance;
    
    //FIELD CENTRIC MOTION - MOVING FORWARD TOWARD APRILTAG
    xVel = Math.cos(m_SwerveDrive.heading)*forwardDistance;//FIELD FORWARD
    yVel = Math.sin(m_SwerveDrive.heading)*forwardDistance;//FIELD LEFT & RIGHT
    
    if (target[0] == 1){
          m_SwerveDrive.setMotors(xVel, yVel, rotation);
    }
      
    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
//    m_SwerveDrive.allStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    /*if (rotation <= rotationCutOff){
      return true;
    }*/
    return false;
  }
}
