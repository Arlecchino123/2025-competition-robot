package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.endeffectorarm.EndEffectorArmSubsystem;
import frc.robot.subsystems.indicator.IndicatorSubsystem;

public class ShootCommand extends Command {
    private final EndEffectorArmSubsystem endEffectorArmSubsystem;
    private final IndicatorSubsystem indicatorSubsystem;
    private final Timer timer = new Timer();

    public ShootCommand(IndicatorSubsystem indicatorSubsystem, EndEffectorArmSubsystem endEffectorArmSubsystem) {
        this.endEffectorArmSubsystem = endEffectorArmSubsystem;
        this.indicatorSubsystem = indicatorSubsystem;
    }

    @Override
    public void execute() {
        endEffectorArmSubsystem.setWantedState(EndEffectorArmSubsystem.WantedState.CORAL_SHOOT);
    }

    @Override
    public boolean isFinished() {
        if (endEffectorArmSubsystem.isShootFinished() && !timer.isRunning()) {
            timer.start();
        }
        return Robot.isSimulation() || timer.hasElapsed(0.3);
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        timer.reset();
    }
}
