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
	public static int CMTOPX = 77;
	public static int center = 450;
	
	//basic fields for each planet
	private String name;
	private int radius;//radius for in real cm and radius for normal display
	private Color color;//general color and colors for normal display(normal display do later)
	private int distFromSun;//in real cm (for scale) and normal display
	private int moonCount;
	private int ringCount;//overload method with ringcoutn with mooncount for jovains
	private Color ringColor;
	private Circle generalPlanet;//set in constructor
	private int inclination;
	private int axis;
	private int orbitPeriod;
	private int rotationalPeriod;
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
	public Planet(String name, int radius, Color color, int distFromSun, int moonCount, int ringCount, Color ringColor) {
		
		//initialize the four basic fields
		this.name = name;
		this.radius = radius;
		this.color = color;
		this.distFromSun = distFromSun;
		this.moonCount = moonCount;
		this.ringCount = ringCount;
		this.ringColor = ringColor;
		
		//create the and set the general planet circle for all displays
		this.generalPlanet = new Circle(this.distFromSun, center, this.radius, this.color);
		
		//set the moons and rings using the private setMoons and setRings method
		setMoons();
		setRings();
	}
	
	/**
	 * Sets the moons for the planet.
	 */
	private void setMoons() {
		
		//create an array to store the moons
		Circle[] moons = new Circle[this.moonCount];
		
		//find out what angle each moon should be separated by (in radians)
		double angle = Math.toRadians(360/this.moonCount);
		double currAngle = 0;
		double xPos = 0;
		double yPos = 0;
		
		//iterate through moons array
		for(int i = 0; i < moons.length; i++) {
				
			//set x and y values of each moon and create the circle for it
			xPos = this.distFromSun + this.radius * Math.cos(currAngle);
			yPos = center + this.radius * Math.sin(currAngle);
			moons[i] = new Circle(xPos, yPos, 2, Color.GHOSTWHITE);
			
			//animate each moon to travel in a circle around the planet
			Circle moonOrbit = new Circle(this.distFromSun, center, this.radius * 2, Color.TRANSPARENT);
			PathTransition moonTransition = new PathTransition(Duration.seconds(5), moonOrbit, moons[i]);
			moonTransition.setCycleCount(Animation.INDEFINITE);
			moonTransition.setInterpolator(Interpolator.LINEAR);
			moonTransition.setRate(-1);
			moonTransition.play();
			
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
	
	/**
	 * Returns the rings for the planet.
	 * @return the moons array for the planet.
	 */
	public Circle[] getRings() {
		return this.rings;
	}
	
	/**
	 * Sets the rings for the planet.
	 */
	private void setRings() {
		
		//create array to store the rings
		Circle[] rings = new Circle[this.ringCount];
		
		//create basic rings for this planet
		for(int i = 0; i < rings.length; i++) {
			
			rings[i] = new Circle(this.distFromSun, center, this.radius + 4*i + 20, Color.TRANSPARENT);
			rings[i].setStrokeWidth(2);
			rings[i].setStroke(this.ringColor);
			
		}//end for
		
		//set the rings array field
		this.rings = rings;
	}
	
	/**
	 * Returns a circle that is positioned to scale for the actual distance of the planet from the sun.
	 * @param scaleDist - the distance that the planet should be from the sun in cm to match the scale model.
	 * @param modifiedRadius - the modified radius in pixels for the scale distance display.
	 * @return a circle (the planet) that is positioned and sized to fit right on the scale distance model.
	 */
	public Circle scaleDistance(int scaleDist, int modifiedRadius) {
		
		//create and return the circle for the planet
		return new Circle(scaleDist * CMTOPX, center, modifiedRadius, this.color);
		
	}//end scaleDistance method
	
	/**
	 * Returns a circle that is positioned and sized to scale for the actual radius of the planet (in the scale model).
	 * @param scaleRad - the radius that the planet should have according to the scale radius model (in cm).
	 * @param modifiedDist - the modified distance in pixels for the scale radius model to display correctly.
	 * @return a circle (the planet) that is positioned and sized to be perfectly to scale for the scale radius model.
	 */
	public Circle scaleRadius(int scaleRad, int modifiedDist) {
		
		//create and return the circle for the planet
		return new Circle(modifiedDist, center, scaleRad * CMTOPX, this.color);
		
	}//end scaleRad method
	
	//create the method for the moons and rings
	
	
	
	//create orbiter sets the orbit, and rings
	public Circle createOrbiter(double sec, double otherRadius) {
		
		//create the planet
		Circle planet = new Circle(900 + this.distFromSun, 500, otherRadius, this.color);
		
		//create basic rings for this planet
		for(int i = 0; i < this.ringCount; i++) {
			
			rings[i] = new Circle(900 + this.distFromSun, 500, otherRadius + 4*i + 20, Color.TRANSPARENT);
			rings[i].setStrokeWidth(2);
			
			//if the planet is saturn, make the rings brown, else make them grey
			if(this.name.equalsIgnoreCase("Saturn")) rings[i].setStroke(Color.BROWN);
			else rings[i].setStroke(Color.GREY);
			
		}//end for
		
		//create the planet's orbit and set it equal to the orbitPath field
		Circle orbit = new Circle(900, 500, this.distFromSun, Color.TRANSPARENT);
		orbit.setStrokeWidth(2);
		orbit.setStroke(Color.BLACK);
		this.orbitPath = orbit;
		
		//make the planet orbit the sun
		PathTransition planetRevo = new PathTransition(Duration.seconds(sec), orbit, planet);
		planetRevo.setCycleCount(Animation.INDEFINITE);
		planetRevo.setInterpolator(Interpolator.LINEAR);
		planetRevo.setRate(-1);
		planetRevo.play();
		
		//make the rings orbit the sun with the planet
		for(int i = 0; i < rings.length; i++) {
			PathTransition ringRevo = new PathTransition(Duration.seconds(sec), orbit, rings[i]);
			ringRevo.setCycleCount(Animation.INDEFINITE);
			ringRevo.setInterpolator(Interpolator.LINEAR);
			ringRevo.setRate(-1);
			ringRevo.play();
		}
		
		return planet;
	}

}
