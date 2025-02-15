package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.subsystems.endeffector.EndEffectorSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;

public class PutCoralCommand extends ParallelCommandGroup {
    public PutCoralCommand(CommandXboxController driverController, EndEffectorSubsystem endeffectorSubsystem,
                           ElevatorSubsystem elevatorSubsystem, IntakeSubsystem intakeSubsystem, double elevatorSetPoint) {
        addCommands(
                Commands.parallel(
                        new PreShootCommand(endeffectorSubsystem, intakeSubsystem, elevatorSubsystem, elevatorSetPoint),
                        Commands.sequence(
                                new WaitUntilCommand(() -> (driverController.leftTrigger().getAsBoolean() && endeffectorSubsystem.isShootReady()) && elevatorSubsystem.getIo().isNearExtension(elevatorSetPoint)),
                                new ShootCommand(endeffectorSubsystem)
                        )
                )
        );
    }
}
