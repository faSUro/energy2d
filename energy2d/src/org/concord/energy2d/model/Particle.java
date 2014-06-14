package org.concord.energy2d.model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

/**
 * A particle in the Lagrangian frame.
 * 
 * @author Charles Xie
 * 
 */
public class Particle extends Manipulable implements Discrete {

	float mass = 1.0f;
	float radius = 0.04f;
	float rx, ry;
	float vx, vy;
	float ax, ay;
	float fx, fy;
	private Color color = Color.WHITE;

	public Particle() {
		super(new Ellipse2D.Float());
	}

	public Particle(float rx, float ry) {
		this();
		setLocation(rx, ry);
	}

	private void updateShape() {
		Ellipse2D.Float e = (Ellipse2D.Float) getShape();
		e.x = rx - radius;
		e.y = ry - radius;
		e.width = e.height = radius * 2;
	}

	public Particle duplicate(float x, float y) {
		Particle p = new Particle(x, y);
		p.color = color;
		p.mass = mass;
		p.radius = radius;
		p.vx = vx;
		p.vy = vy;
		return p;
	}

	public void setLocation(float rx, float ry) {
		this.rx = rx;
		this.ry = ry;
		updateShape();
	}

	public void translateBy(float deltaX, float deltaY) {
		rx += deltaX;
		ry += deltaY;
		updateShape();
	}

	// predict this particle's new position using second order Taylor expansion.
	void predict(float dt) {
		float dt2 = 0.5f * dt * dt;
		rx += vx * dt + ax * dt2;
		ry += vy * dt + ay * dt2;
		vx += ax * dt;
		vy += ay * dt;
	}

	// correct this particle's position predicted by the predict method.
	// fx and fy were used in the force calculation routine to store the new acceleration data.
	// ax and ay were used to hold the old acceleration data before calling this method.
	// After calling this method, new acceleration data will be assigned to ax and ay, whereas the forces to fx and fy.
	void correct(float dt) {
		vx += 0.5f * dt * (fx - ax);
		vy += 0.5f * dt * (fy - ay);
		ax = fx;
		ay = fy;
		fx *= mass;
		fy *= mass;
		updateShape();
	}

	public float getSpeed() {
		return (float) Math.hypot(vx, vy);
	}

	public void setAngle(float angle) {
		float c = getSpeed();
		vx = (float) (Math.cos(angle) * c);
		vy = (float) (Math.sin(angle) * c);
	}

	public void setRx(float rx) {
		this.rx = rx;
		updateShape();
	}

	public float getRx() {
		return rx;
	}

	public void setRy(float ry) {
		this.ry = ry;
		updateShape();
	}

	public float getRy() {
		return ry;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVx() {
		return vx;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public float getVy() {
		return vy;
	}

	public float getAx() {
		return ax;
	}

	public float getAy() {
		return ay;
	}

	public void setRadius(float radius) {
		this.radius = radius;
		updateShape();
	}

	public float getRadius() {
		return radius;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getMass() {
		return mass;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public String toXml() {
		String xml = "<particle>\n";
		xml += "<rx>" + rx + "</rx>\n";
		xml += "<ry>" + ry + "</ry>\n";
		if (vx != 0)
			xml += "<vx>" + vx + "</vx>\n";
		if (vy != 0)
			xml += "<vy>" + vy + "</vy>\n";
		xml += "<radius>" + radius + "</radius>\n";
		xml += "<mass>" + mass + "</mass>\n";
		String uid = getUid();
		if (uid != null && !uid.trim().equals(""))
			xml += "<uid>" + uid + "</uid>\n";
		String label = getLabel();
		if (label != null && !label.trim().equals(""))
			xml += "<label>" + label + "</label>\n";
		if (!Color.WHITE.equals(color))
			xml += "<color>" + Integer.toHexString(0x00ffffff & color.getRGB()) + "</color>\n";
		xml += "</particle>\n";
		return xml;
	}

}