// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BrakeSubsystem extends SubsystemBase {
  private final Servo brakeServo;
  private boolean isEngaged = true;

  /** Creates a new ExampleSubsystem. */
  public BrakeSubsystem(int servoPort) {
    brakeServo = new Servo(0);
  }

  public boolean isBrakeEngaged() {
    return isEngaged;
  }

  private void disengageBrake() {
    brakeServo.set(0.2);
    isEngaged = false;
  }

  private void engageBrake() {
    brakeServo.set(0);
    isEngaged = true;
  }

  public Command disengageCommand() {
    return Commands.runOnce(this::disengageBrake, this)
      .andThen(Commands.waitSeconds(0.5));
  }

  public Command engageCommand() {
    return Commands.runOnce(this::engageBrake, this)
      .andThen(Commands.waitSeconds(0.5));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Brake Engaged", isBrakeEngaged());
  }
}
