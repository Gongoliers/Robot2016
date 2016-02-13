package org.usfirst.frc5112.Robot2016.commands;

import org.usfirst.frc5112.Robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevateBoulderToFlywheel extends Command {

	public ElevateBoulderToFlywheel() {
		requires(Robot.kicker);
	}

	@Override
	protected void initialize() {
		Robot.kicker.up();
		setTimeout(0.75);
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		return isTimedOut(); // Robot.kicker.isAtElevatedPosition();
	}

	@Override
	protected void end() {
		Robot.kicker.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}