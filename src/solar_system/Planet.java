package solar_system;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Planet {
	
	//distance from the earth to the sun
	//eccentricity of the orbits
	//number of moons + rings
	
	//second model
	//inclinaiton of the orbiwts
	//mass
	//size
	//tilt of the rotational axis
	
	//planet class, store dist, eccentricity, general color, 
	
	//orbital time and rotation time <--- separate scene
	//variable to convert cm values to pixel values
	public static double CMTOPX = 55.44;
	public static int center = 450;
	
	//basic fields for each planet
	private String name;
	private int radius;//radius for in real cm and radius for normal display
	private Color color;//general color and colors for normal display(normal display do later)
	private int distFromSun;//in real cm (for scale) and normal display
	private int distFromEcliptic;
	private int moonCount;
	private int ringCount;//overload method with ringcoutn with mooncount for jovains
	private Color ringColor;
	private boolean jovian;
	private Circle generalPlanet;//set in constructor
	public Circle orbitPath;//additional for homescreen
	private Circle[] moons;//what is returned; include getters and setters
	private Circle[] rings;//what is returned; include getters and setters
	
	/**
	 * This is the constructor to specify values for the 7 basic fields of each planet; this information will be used for general
	 * display of the planets.
	 * @param name - name of planet
	 * @param radius - radius of planet in pixels for general display
	 * @param color - general color of the planet
	 * @param distFromSun - radius of the orbit of the planet in pixels for general display
	 */
	public Planet(String name, int radius, Color color, int distFromSun, int distFromEcliptic, int moonCount, int ringCount, Color ringColor) {
		
		//initialize the four basic fields
		this.name = name;
		this.radius = radius;
		this.color = color;
		this.distFromSun = distFromSun;
		this.distFromEcliptic = center - distFromEcliptic;
		this.moonCount = moonCount;
		this.ringCount = ringCount;
		this.ringColor = ringColor;
		
		//check whether the planet is jovian or not, and if it is, set the field to true
		if(ringCount > 0) this.jovian = true;
		
		//create the and set the general planet circle for all displays
		this.generalPlanet = new Circle(this.distFromSun, this.distFromEcliptic, this.radius, this.color);
		
		//set the moons and rings using the private setMoons and setRings method, if moons/rings are given
		if(this.jovian) setRings();
		if(moonCount > 0) setMoons();
	}
	
	//get name
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the rings for the planet.
	 */
	private void setRings() {
		
		//create array to store the rings
		Circle[] rings = new Circle[this.ringCount];
		
		//create basic rings for this planet
		for(int i = 0; i < rings.length; i++) {
			
			rings[i] = new Circle(this.distFromSun, this.distFromEcliptic, this.radius + 5*i + 20, Color.TRANSPARENT);
			rings[i].setStrokeWidth(2);
			rings[i].setStroke(this.ringColor);
			if(this.name.equalsIgnoreCase("Saturn")) {
				rings[i].setRadius(this.radius + 8*i + 30);
				rings[i].setStrokeWidth(6);
			}
			
		}//end for
		
		//set the rings array field
		this.rings = rings;
	}

	/**
	 * Returns the rings for the planet.
	 * @return the moons array for the planet.
	 */
	public Circle[] getRings() {
		return this.rings;
	}

	/**
	 * Sets the moons for the planet.
	 */
	private void setMoons() {
		
		//create an array to store the moons
		Circle[] moons = new Circle[this.moonCount];
		
		//find out what angle each moon should be separated by (in radians)
		//double angle = Math.toRadians(360/this.moonCount);
		//double currAngle = 0;
		double xPos = 0;
		double yPos = 0;
		double delay = 0;
		//iterate through moons array
		for(int i = 0; i < moons.length; i++) {
				
			//set x and y values of each moon and create the circle for it
			//for jovian planets, go out from ring, otherwise for rocky planets go out from surface
			Circle moonOrbit;
			if(this.jovian) {
				xPos = this.distFromSun + this.rings[this.rings.length-1].getRadius() * 1.2; // * Math.cos(currAngle); //add for stationary moons
				yPos = this.distFromEcliptic;// * Math.sin(currAngle); //add for stationary moons
				moonOrbit = new Circle(this.distFromSun, this.distFromEcliptic, this.rings[this.rings.length-1].getRadius() * 1.2, Color.TRANSPARENT);
			}
			else {
				xPos = this.distFromSun +  this.radius * 1.2; // * Math.cos(currAngle); //add for stationary moons
				yPos = this.distFromEcliptic;
				moonOrbit = new Circle(this.distFromSun, this.distFromEcliptic, this.radius * 1.2, Color.TRANSPARENT);
			}
			moons[i] = new Circle(xPos, yPos, 4, Color.LIGHTGREY);
			
			//update angle
			//currAngle += angle;
			
			//animate each moon to travel in a circle around the planet
			PathTransition moonTransition = new PathTransition(Duration.seconds(10), moonOrbit, moons[i]);
			moonTransition.setDelay(Duration.millis(delay));
			moonTransition.setCycleCount(Animation.INDEFINITE);
			moonTransition.setInterpolator(Interpolator.LINEAR);
			moonTransition.setRate(-1);
			moonTransition.play();
			delay+=10000/this.moonCount;
			
		}//end for
		
		//set the moons array field
		this.moons = moons;
		
	}//end setMoons method
	
	/**
	 * Returns the moons for the planet.
	 * @return the moons array for the planet.
	 */
	public Circle[] getMoons() {
		return this.moons;
	}
	
	//method to get general planet
	public Circle getGeneralPlanet() {
		return this.generalPlanet;
	}

	/**
	 * Returns a circle that is positioned to scale for the actual distance of the planet from the sun.
	 * @param scaleDist - the distance that the planet should be from the sun in cm to match the scale model.
	 * @param modifiedRadius - the modified radius in pixels for the scale distance display.
	 * @return a circle (the planet) that is positioned and sized to fit right on the scale distance model.
	 */
	public Circle scaleDistance(double scaleDist, int modifiedRadius) {
		
		//create and return the circle for the planet
		return new Circle(100 + scaleDist * CMTOPX, center, modifiedRadius, this.color);
		
	}//end scaleDistance method
	
	/**
	 * Returns a circle that is positioned and sized to scale for the actual radius of the planet (in the scale model).
	 * @param scaleRad - the radius that the planet should have according to the scale radius model (in cm).
	 * @param modifiedDist - the modified distance in pixels for the scale radius model to display correctly.
	 * @return a circle (the planet) that is positioned and sized to be perfectly to scale for the scale radius model.
	 */
	public Circle scaleRadius(double scaleRad, int modifiedDist) {
		
		//create and return the circle for the planet
		return new Circle(100 + modifiedDist, center, scaleRad * CMTOPX, this.color);
		
	}//end scaleRad method
	
	//create method to show inclination
	public Shape[] scaleInclination(double angleOfInc) {
		
		//make the height of the planet above the ecliptic (in cm) be same as double the angleOfInc as a scale
		double heightOverEcliptic = CMTOPX * 1 * angleOfInc;//this.distFromSun * Math.tan(Math.toRadians(angleOfInc)); <-- gives right angle
		
		//create a line from the edge of the screen (at the beginning of the ecliptic) to the center of the planet
		Line incLine = new Line(0, center, this.distFromSun, center - heightOverEcliptic);
		incLine.setStroke(Color.WHITE);
		incLine.setStrokeWidth(2);
		
		//create a line from ecliptic to center of planet
		Line centerLine = new Line(this.distFromSun, center, this.distFromSun, center-heightOverEcliptic);
		centerLine.setStroke(Color.WHITE);
		centerLine.setStrokeWidth(2);
		
		//create circle for the planet at the correct height
		Circle planet = new Circle(this.distFromSun, center - heightOverEcliptic, this.radius, this.color);
		
		//return shapes array
		Shape[] arr = {planet, incLine, centerLine};
		return arr;
	}
	
	//create method to show axis of orbit
	public Shape[] scaleAxis(double axisAngle) {
		
//		//reduce the heights of the planets above/below the ecliptic for asthetic purposes
//		int reducedHeight = center - (center-this.distFromEcliptic)/4;
		
		//create dotted line across center of planet
		Line dottedLine = new Line(this.distFromSun, center + radius*1.5, this.distFromSun, center - radius*1.5);
		dottedLine.setStrokeWidth(1);
		dottedLine.setStroke(Color.AQUAMARINE);
		dottedLine.getStrokeDashArray().addAll(5d, 5d);
		
		//create line for the axis
		double xOffset = this.radius*1.5*Math.sin(Math.toRadians(axisAngle));
		double yOffset = this.radius*1.5*Math.cos(Math.toRadians(axisAngle));
		Line axis = new Line(this.distFromSun - xOffset, center - yOffset, this.distFromSun + xOffset, center + yOffset);
		axis.setStrokeWidth(2);
		axis.setStroke(Color.WHITE);
		
		//create dot to point toward counter-clockwise direction of planet if looked at from that side
		Circle north = new Circle(this.distFromSun - xOffset, center - yOffset, 6, Color.RED);
		
		//create planet
		Circle planet = new Circle(this.distFromSun, center, this.radius, this.color);
		
		//create new shapes array to return
		Shape[] shapes = {planet, axis, dottedLine, north};
		return shapes;
	}
	
	public Rectangle scaleGravity(double surfGravity) {
		
		return new Rectangle(surfGravity * CMTOPX, 30, this.color);
		
	}
	
	public Rectangle scaleDensity(double density) {
		
		return new Rectangle(density * 3 * CMTOPX, 30, this.color);
		
	}
//	//create orbiter sets the orbit, and rings
//	public Circle createOrbiter(double sec, double otherRadius) {
//		
//		//create the planet
//		Circle planet = new Circle(900 + this.distFromSun, 500, otherRadius, this.color);
//		
//		//create basic rings for this planet
//		for(int i = 0; i < this.ringCount; i++) {
//			
//			rings[i] = new Circle(900 + this.distFromSun, 500, otherRadius + 4*i + 20, Color.TRANSPARENT);
//			rings[i].setStrokeWidth(2);
//			
//			//if the planet is saturn, make the rings brown, else make them grey
//			if(this.name.equalsIgnoreCase("Saturn")) rings[i].setStroke(Color.BROWN);
//			else rings[i].setStroke(Color.GREY);
//			
//		}//end for
//		
//		//create the planet's orbit and set it equal to the orbitPath field
//		Circle orbit = new Circle(900, 500, this.distFromSun, Color.TRANSPARENT);
//		orbit.setStrokeWidth(2);
//		orbit.setStroke(Color.BLACK);
//		this.orbitPath = orbit;
//		
//		//make the planet orbit the sun
//		PathTransition planetRevo = new PathTransition(Duration.seconds(sec), orbit, planet);
//		planetRevo.setCycleCount(Animation.INDEFINITE);
//		planetRevo.setInterpolator(Interpolator.LINEAR);
//		planetRevo.setRate(-1);
//		planetRevo.play();
//		
//		//make the rings orbit the sun with the planet
//		for(int i = 0; i < rings.length; i++) {
//			PathTransition ringRevo = new PathTransition(Duration.seconds(sec), orbit, rings[i]);
//			ringRevo.setCycleCount(Animation.INDEFINITE);
//			ringRevo.setInterpolator(Interpolator.LINEAR);
//			ringRevo.setRate(-1);
//			ringRevo.play();
//		}
//		
//		return planet;
//	}

}
