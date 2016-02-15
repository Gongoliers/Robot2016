package org.usfirst.frc5112.Robot2016;

import org.usfirst.frc5112.Robot2016.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public Joystick driveStick;
	public Joystick shootStick;
	public JoystickButton rb;
	public JoystickButton lb;
	public JoystickButton start;
	public JoystickButton select;

	public OI() {
		shootStick = new Joystick(1);

		driveStick = new Joystick(0);

		rb = new JoystickButton(shootStick, 5);
		lb = new JoystickButton(shootStick, 4);
		start = new JoystickButton(shootStick, 8);
		select = new JoystickButton(shootStick, 7);

		rb.whenPressed(new ShootBoulderIntoHighGoal());
		lb.whenPressed(new IntakeBoulder());
		start.whenPressed(new DisplayNormalCameraImage());
		select.whenPressed(new AlignWithTarget());

		// SmartDashboard Button
		SmartDashboard.putData("Shoot boulder into high goal", new ShootBoulderIntoHighGoal());
		SmartDashboard.putData("Intake Boulder", new IntakeBoulder());
		SmartDashboard.putData("Display Normal Camera Image", new DisplayNormalCameraImage());
		SmartDashboard.putData("Align With Target", new AlignWithTarget());

	}

	public Joystick getDriveStick() {
		return driveStick;
	}

	public Joystick getShootStick() {
		return shootStick;
	}

}
