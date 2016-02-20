package org.usfirst.frc5112.Robot2016.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5112.Robot2016.Robot;

public class IntakeBoulder extends Command {

	private boolean start;
	private boolean done;

	public IntakeBoulder() {
		requires(Robot.intake);
	}

	protected void initialize() {
		Robot.intake.setBarSpeed(-1);
		start = false;
		done = false;
		setTimeout(0.4);
	}

	protected void execute() {
		SmartDashboard.putNumber("Intake Current", Robot.pdp.getCurrent(4));
		if (isTimedOut() && !start && Robot.pdp.getCurrent(4) > 8.5) {
			start = true;
		}
		if (start && Robot.pdp.getCurrent(4) < 7.6) {
			done = true;
		}
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
		Robot.intake.setBarSpeed(0);
	}

	protected void interrupted() {
		end();
	}
}
