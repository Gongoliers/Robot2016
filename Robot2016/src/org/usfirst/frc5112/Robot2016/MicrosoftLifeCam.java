package org.usfirst.frc5112.Robot2016;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.GetImageSizeResult;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class MicrosoftLifeCam {

	private Image frame;
	private boolean cameraStarted;
	private USBCamera camera;

	public MicrosoftLifeCam(String cameraName) {
		camera.openCamera();
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		cameraStarted = false;
		camera = new USBCamera(cameraName);
	}

	public static enum Axis {
		X, Y
	}

	public void setBrightness(int brightness) {
		camera.setBrightness(brightness);
	}

	public int getBrightness() {
		return camera.getBrightness();
	}

	public void setExposureManual(int exposure) {
		camera.setExposureManual(exposure);
	}

	public void setExposureAuto() {
		camera.setExposureAuto();
	}

	public Image getImage() {
		return frame;
	}

	public void start() {
		camera.startCapture();
		cameraStarted = true;
	}

	public void stop() {
		camera.stopCapture();
		cameraStarted = false;
	}

	public Image getCurrentFrame() {
		if (!cameraStarted)
			start();
		camera.getImage(frame);
		return frame;
	}

	public void setFPS(int fps) {
		camera.setFPS(fps);
	}

	public void displayOnCameraServer() {
		CameraServer.getInstance().setImage(getCurrentFrame());
	}

	public int getResolution(Axis axis) {
		Image currentImage = getCurrentFrame();
		GetImageSizeResult size = NIVision.imaqGetImageSize(currentImage);
		if (axis.equals(Axis.X))
			return size.width;
		else
			return size.height;
	}

	public double[] convertPixelSystemToAimingSystem(int[] pixel, int resolutionX, int resolutionY) {
		double[] aimingPoint = new double[2];
		aimingPoint[0] = (pixel[0] - resolutionX / 2.0) / (resolutionX / 2.0);
		aimingPoint[1] = -(pixel[1] - resolutionY / 2.0) / (resolutionY / 2.0);
		return aimingPoint;
	}

	public double getDistanceToTarget(double targetFeet, int targetPixels, double fieldOfViewAngle,
			int fieldOfViewPixels) {
		return targetFeet * fieldOfViewPixels / (2 * targetPixels * Math.tan(Math.toDegrees(fieldOfViewAngle)));
	}

	public double getFieldOfViewAngle(double targetFeet, int targetPixels, int fieldOfViewPixels,
			double distanceToTarget) {
		return Math.toDegrees(Math.atan(targetFeet * fieldOfViewPixels / (targetPixels * distanceToTarget)));
	}
}
