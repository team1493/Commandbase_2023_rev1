// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.SwerveDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Apriltag;

/** An example command that uses an example subsystem. */
public class FollowApriltag extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private SwerveDrive swerveDrive;
  private Apriltag aprilTag;

  private double Velx = 0;
  private double Vely = 0;
  public FollowApriltag(SwerveDrive swerve, Apriltag apriltag) {
    SmartDashboard.putNumber("Following April Tag", 1);
    swerveDrive = swerve;
    aprilTag = apriltag;

    addRequirements(swerve);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get the estimations from the april tag class
    SmartDashboard.putNumber("Executing", 1);
    double[] estimations = aprilTag.getEstimations();

    double new_angle = -(estimations[6] * 100); // the angle
    // System.out.println(new_angle);
    double angle = new_angle * (Math.PI/180);  // the angle in radian

    if (estimations[1] != 0) { // see if the tag is detected
        SmartDashboard.putNumber("Executing", 2);
        SmartDashboard.putNumber("Angle to move", angle);
        swerveDrive.setMotors(Velx, Vely, angle); // rotate the robot to a certain angle
    }

 }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

