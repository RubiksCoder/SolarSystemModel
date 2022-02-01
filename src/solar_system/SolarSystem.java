package solar_system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SolarSystem extends Application {
	
	//create the sun
	public static Circle the_sun() {
		return new Circle(-500, Planet.center, 600, Color.YELLOW);
	}
	
	//create all the planet objects
	static Planet mercury = new Planet("Mercury", 30, Color.GREY, 140, 10, 0, 0, null);
	static Planet venus = new Planet("Venus", 50, Color.SANDYBROWN, 250, -20, 0, 0, null);
	static Planet earth = new Planet("Earth", 55, Color.BLUE, 400, 30, 1, 0, null);
	static Planet mars = new Planet("Mars", 40, Color.RED, 520, -50, 2, 0, null);
	static Planet jupiter = new Planet("Jupiter", 130, Color.ORANGE, 780, 170, 79, 4, Color.RED);
	static Planet saturn = new Planet("Saturn", 120, Color.GOLDENROD, 1090, -170, 82, 7, Color.BROWN);
	static Planet uranus = new Planet("Uranus", 80, Color.LIGHTBLUE, 1400, 150, 27, 13, Color.LIGHTGREY);
	static Planet neptune = new Planet("Neptune", 80, Color.MEDIUMSLATEBLUE, 1650, -90, 14, 5, Color.LIGHTGREY);
	
	static //create an array to store all the planets
	Planet[] planets = {mercury, venus, earth, mars, jupiter, saturn, uranus, neptune};

	//first create all the border panes to use in the application
	static BorderPane homePane = new BorderPane(createMoonRingPane(), createMenuBar(), null, null, null);
	static BorderPane distPane = new BorderPane(createScaleDistPane(), createMenuBar(), null, null, null);
	static BorderPane sizePane = new BorderPane(createScaleRadiusPane(), createMenuBar(), null, null, null);
	static BorderPane satellitePane = homePane;
	static BorderPane incPane = new BorderPane(createScaleIncPane(), createMenuBar(), null, null, null);
	static BorderPane axisPane = new BorderPane(createScaleAxisPane(), createMenuBar(), null, null, null);
	static BorderPane gravPane = new BorderPane(createScaleGravPane(), createMenuBar(), null, null, null);
	static BorderPane densityPane = new BorderPane(createScaleDensityPane(), createMenuBar(), null, null, null);
	
	//create the scene
	static Scene scene = new Scene(homePane, 1830, 930);
	
	public static void main(String[] args) {
		launch(args);
	}//end main
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//set title of stage and display the scene
		primaryStage.setTitle("Solar System Model");
		primaryStage.setScene(scene);
		primaryStage.show();

	}//end start
	
	/**
	 * Creates a menu bar options to move to any of the other scenes.
	 * @return a menu bar object.
	 */
	public static MenuBar createMenuBar() {

			//create menu options for each characteristic
			MenuItem homeOption = new MenuItem("Home");
			homeOption.setOnAction(e -> scene.setRoot(homePane));
			
			MenuItem distOption = new MenuItem("Distance");
			distOption.setOnAction(e -> scene.setRoot(distPane));
			
			MenuItem sizeOption = new MenuItem("Size");
			sizeOption.setOnAction(e -> scene.setRoot(sizePane));
			
			MenuItem satelliteOption = new MenuItem("Moons and Rings");
			satelliteOption.setOnAction(e -> scene.setRoot(satellitePane));
			
			MenuItem inclinationOption = new MenuItem("Inclination");
			inclinationOption.setOnAction(e -> scene.setRoot(incPane));
			
			MenuItem axisOption = new MenuItem("Axis of Rotation");
			axisOption.setOnAction(e -> scene.setRoot(axisPane));
			
			MenuItem gravOption = new MenuItem("Surface Gravity");
			gravOption.setOnAction(e -> scene.setRoot(gravPane));
			
			MenuItem densityOption = new MenuItem("Density");
			densityOption.setOnAction(e -> scene.setRoot(densityPane));
			
			//create menu and add all menu options
			Menu mainMenu = new Menu("Menu");
			mainMenu.getItems().addAll(homeOption, distOption, sizeOption, satelliteOption, inclinationOption, axisOption, gravOption, densityOption);
			
			//add an icon to the menu
			FileInputStream file = null;
			try {
				file = new FileInputStream("C:\\Users\\yashj\\Desktop\\Random Pictures\\SolarSystemIcon.png");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			mainMenu.setGraphic(new ImageView(new Image(file)));
			
			//create menubar with menu and return
			return new MenuBar(mainMenu);
			
		}//end createMenu method
	
	public static Pane createMoonRingPane() {
		
		//create title
		Label title = createLabel("Moons & Rings of The Planets", 30, 740, 20);
		
		//create the pane to return
		Pane pane = new Pane(the_sun());
		
		//add the moons for each planet (with moons) to the pane
		for(int i = 0; i < planets.length; i++) {
			pane.getChildren().add(planets[i].getGeneralPlanet());
			if(i > 1) pane.getChildren().addAll(planets[i].getMoons());
			if(i > 3) pane.getChildren().addAll(planets[i].getRings());
		}	

		//add scale
		Circle scaleMoon = new Circle(260, 769, 4, Color.LIGHTGREY);
		VBox legend = createLegend("     = 1 Moon\n1 Band = 1 Ring", 240, 700);
		pane.getChildren().addAll(scaleMoon, legend, title);
		
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		return pane;
		
	}//end createMoonRingPane method
	
	public static Pane createScaleDistPane() {
		
		//create title
		Label title = createLabel("Distance From Sun", 30, 810, 30);
		
		//create a line for the center line
		Line centerLine = new Line(0, 450, 1800, 450);
		centerLine.setStrokeWidth(1);
		centerLine.setStroke(Color.WHITE);
		
		//create a line for the scale
		Line scaleLine = new Line(0, 450, 1800, 450);
		scaleLine.setStrokeWidth(1);
		scaleLine.setStroke(Color.WHITE);
		
		VBox legend = createLegend("1 cm* = 1 AU = 149,600,000 km\n*distances are measured from the surface of the sun to centers of the planets", 240, 700);
		
		//create the pane to return
		Pane pane = new Pane(scaleLine, the_sun(), mercury.scaleDistance(0.387, 4), venus.scaleDistance(0.72, 7), earth.scaleDistance(1, 8), 
				mars.scaleDistance(1.52, 6), jupiter.scaleDistance(5.2, 88), saturn.scaleDistance(9.5, 80), 
				uranus.scaleDistance(19.2, 40), neptune.scaleDistance(30.1, 40), title, legend);
		
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		return pane;
	}
	
	public static Pane createScaleRadiusPane() {
		
		//create title
		Label title = createLabel("Size (Radius) of Planets", 30, 810, 30);		
		
		//create a line for the center line
		Line centerLine = new Line(0, 450, 1800, 450);
		centerLine.setStrokeWidth(1);
		centerLine.setStroke(Color.WHITE);
		
		//create a line for the scale
		Line scaleLine = new Line(0, 450, 1800, 450);
		scaleLine.setStrokeWidth(1);
		scaleLine.setStroke(Color.WHITE);
		
		//create legend
		VBox legend = createLegend("1 cm = 1 Earth Diameter = 12,742 km", 1440, 700);
		
		//create the pane to return
		Pane pane = new Pane(the_sun());
		pane.getChildren().addAll(mercury.scaleRadius(0.38/2, 12), venus.scaleRadius(0.95/2, 50), earth.scaleRadius(1.0/2, 105), 
				mars.scaleRadius(0.53/2, 148), jupiter.scaleRadius(10.97/2, 468), saturn.scaleRadius(9.14/2, 1028), 
				uranus.scaleRadius(3.98/2, 1393), neptune.scaleRadius(3.86/2, 1613), title, legend);
		
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		return pane;
	}
	
	public static Pane createScaleIncPane() {
		
		//create title and legend
		Label title = createLabel("Inclination of Orbital Plane of Planets", 30, 700, 30);
		VBox legend = createLegend("Every cm that the planet's center is directly above/below ecliptic = 1° of inclination of orbital plane above ecliptic", 240, 765);
		
		//creat pane to return
		Pane pane = new Pane(the_sun());
		
		//add all the scaled inclination lines and planets to the pane
		pane.getChildren().addAll(mercury.scaleInclination(7));
		pane.getChildren().addAll(venus.scaleInclination(-3.4));
		pane.getChildren().addAll(earth.scaleInclination(0));
		pane.getChildren().addAll(mars.scaleInclination(-1.85));
		pane.getChildren().addAll(jupiter.scaleInclination(1.3));
		pane.getChildren().addAll(saturn.scaleInclination(-3.4));
		pane.getChildren().addAll(uranus.scaleInclination(0.77));
		pane.getChildren().addAll(neptune.scaleInclination(-1.77));
		
		//line for ecliptic
		Line ecliptic = new Line(0, 450, 1800, 450);
		ecliptic.setStrokeWidth(1);
		ecliptic.setStroke(Color.AQUAMARINE);
		ecliptic.getStrokeDashArray().addAll(25d, 40d);
		Label eclipticLabel = new Label("ecliptic");
		eclipticLabel.setTextFill(Color.web("#ffffff"));
		eclipticLabel.setLayoutX(1780);
		eclipticLabel.setLayoutY(Planet.center-12);
		
		pane.getChildren().addAll(ecliptic, eclipticLabel, title, legend);
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		return pane;
	}
	
	//create method to display the axes to scale
	public static Pane createScaleAxisPane() {
		
		//create title and legend
		Label title = createLabel("Axis of Rotation of Planets", 30, 750, 30);
		VBox legend = createLegend("Angle between vertical dashed line and slanted line = angle of axis of rotation of planet in reference to "
				+ "its respective orbital plane\n*the red dot indicates the pole of the planet that if seen directly (from a bird's eye view), "
				+ "would spin counter-clockwise.", 240, 750);
		
		//create pane to return
		Pane pane = new Pane(the_sun());
		
		//add all the scaled axes planets and lines to the pane
		pane.getChildren().addAll(mercury.scaleAxis(0));
		pane.getChildren().addAll(venus.scaleAxis(177.4));
		pane.getChildren().addAll(earth.scaleAxis(23.45));
		pane.getChildren().addAll(mars.scaleAxis(23.98));
		pane.getChildren().addAll(jupiter.scaleAxis(3.08));
		pane.getChildren().addAll(saturn.scaleAxis(26.73));
		pane.getChildren().addAll(uranus.scaleAxis(97.92));
		pane.getChildren().addAll(neptune.scaleAxis(29.6));
		
		//line for ecliptic
		Line ecliptic = new Line(0, 450, 1800, 450);
		ecliptic.setStrokeWidth(1);
		ecliptic.setStroke(Color.AQUAMARINE);
		ecliptic.getStrokeDashArray().addAll(25d, 40d);
		pane.getChildren().addAll(ecliptic, title, legend);
		
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		return pane;
	}
	
	private static Label createLabel(String text, int size) {
		Label label = new Label(text);
		label.setFont(new Font(size));
		label.setTextFill(Color.web("#ffffff"));
		return label;
	}
	
	private static Label createLabel(String text, int size, double xPos, double yPos) {
		Label label = new Label(text);
		label.setFont(new Font(size));
		label.setTextFill(Color.web("#ffffff"));
		label.setLayoutX(xPos);
		label.setLayoutY(yPos);
		return label;
	}
	//create legend
	public static VBox createLegend(String text) {
		Label title = createLabel("Legend:", 25);
		Label legendText = createLabel(text, 20);
		VBox legend = new VBox(title, legendText);
		//legend.setAlignment(Pos.CENTER);
		legend.setMargin(title, new Insets(50, 0, 0, 30));
		legend.setMargin(legendText, new Insets(20, 0, 0, 30));
		//legend.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
		return legend;
	}
	
	//create legend
	public static VBox createLegend(String text, double xPos, double yPos) {
		Label title = createLabel("Legend:", 25);
		Label legendText = createLabel(text, 20);
		VBox legend = new VBox(title, legendText);
		//legend.setAlignment(Pos.CENTER);
		legend.setMargin(title, new Insets(5, 10, 0, 10));
		legend.setMargin(legendText, new Insets(10, 5, 5, 5));
		legend.setLayoutX(xPos);
		legend.setLayoutY(yPos);
		legend.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));

		return legend;
	}
	
	//create method to create scale surface gravity pane
	public static VBox createScaleGravPane() {
		
		//create title
		Label title = createLabel("Surface Gravity of The Planets", 30);
		
		//add all names of planets to a vertical box
		VBox names = new VBox();
		for(Planet planet: planets) {
			names.getChildren().add(createLabel(planet.getName() + ": ", 20));
		}
		names.setSpacing(30);
		
		//create another vbox and add all surface gravity bars to it
		VBox gravityBars = new VBox(30, mercury.scaleGravity(3.7), venus.scaleGravity(8.87), earth.scaleGravity(9.8), 
						mars.scaleGravity(3.72), jupiter.scaleGravity(24.79), saturn.scaleGravity(10.44), 
						uranus.scaleGravity(8.87), neptune.scaleGravity(11.15));
		
		//add both vboxes to a hbox
		HBox hbox = new HBox(20, names, gravityBars);
		
		//create label for legend
		VBox legend = createLegend("1 cm = 1 m/s² of Surface Gravity");
		//Label legend = createLabel("Legend: 1 cm = 1 m/s²", 20);
		
		//add above four, independent nodes/layouts to a final vbox to return
		VBox finalBox = new VBox(title, hbox, legend);
		finalBox.setMargin(title, new Insets(20, 0, 0, 770));
		finalBox.setMargin(hbox, new Insets(20, 0, 0, 30));
		//finalBox.setMargin(legend, new Insets(50, 0, 0, 30));
		finalBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		return finalBox;
		
	}
	
	//create method to create clocks
	public static VBox createScaleDensityPane() {
		
		//create title
		Label title = createLabel("Density of The Planets", 30);
		
		//add all names of planets to a vertical box
		VBox names = new VBox();
		for(Planet planet: planets) {
			names.getChildren().add(createLabel(planet.getName() + ": ", 20));
		}
		names.setSpacing(30);
		
		//create another vbox and add all density bars to it
		VBox densityBars = new VBox(30, mercury.scaleDensity(5.43), venus.scaleDensity(5.24), earth.scaleDensity(5.51), 
						mars.scaleDensity(3.39), jupiter.scaleDensity(1.33), saturn.scaleDensity(0.687), 
						uranus.scaleDensity(1.27), neptune.scaleDensity(1.64));
		
		//add both vboxes to a hbox
		HBox hbox = new HBox(20, names, densityBars);
		
		//create label for legend
		VBox legend = createLegend("3 cm = 1 g/m³ of Density");
		
		//add above four, independent nodes/layouts to a final vbox to return
		VBox finalBox = new VBox(title, hbox, legend);
		finalBox.setMargin(title, new Insets(20, 0, 0, 800));
		finalBox.setMargin(hbox, new Insets(20, 0, 0, 30));
		//finalBox.setMargin(legend, new Insets(50, 0, 0, 30));
		finalBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		return finalBox;
		
	}
	//create method 
	
	//create method to create planet scene with rings and distance; returns pane; will be home scene
//	public static Pane homePane() {
//		//create circles for the Sun
//		Circle sun = new Circle(900, 500, 50, Color.YELLOW);
//		
//		//create saturn object
//		Planet saturn = new Planet("Saturn", 30, Color.GOLDENROD, 300, 82, 8, Color.BROWN);
//		Circle saturnCircle = saturn.createOrbiter(10, 30);
//		Circle[] rings = saturn.getRings();
//		Circle orbit = saturn.orbitPath;
//		
//		//create pane layout and scene
//		Pane pane = new Pane();
//		pane.getChildren().addAll(sun, orbit, saturnCircle);
//		for(Circle ring: rings) pane.getChildren().add(ring);
//		return pane;
//	}
	
	//create method for distance
	//create method for size
	//create method for moons and rings
	//create method for inclination of planes
	//create method for axis of rotations
	//create method to display both times (rotational and orbital)
	//create method for mass, or something else
	//
	//
}
