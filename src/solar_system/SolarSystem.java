package solar_system;

import java.io.FileInputStream;

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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SolarSystem extends Application {
	
	//create all the planet objects
	//Planet mercury = new Planet("Mercury", 10, Color.GREY, 60, 0);
	
	public static void main(String[] args) {
		
		//launch program
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//create all the panes for all scenes
		
		//create menu options for each characteristic
		MenuItem homeOption = new MenuItem("Home");
		MenuItem distOption = new MenuItem("Distance");
		MenuItem sizeOption = new MenuItem("Size");
		MenuItem satelliteOption = new MenuItem("Moons and Rings");
		MenuItem inclinationOption = new MenuItem("Inclination");
		MenuItem axisOption = new MenuItem("Axis of Rotation");
		MenuItem timeOption = new MenuItem("Time Periods");
		
		//create menu and add all menu options
		Menu menu = new Menu("Menu");
		menu.getItems().add(homeOption);
		menu.getItems().add(distOption);
		menu.getItems().add(sizeOption);	
		menu.getItems().add(satelliteOption);
		menu.getItems().add(inclinationOption);
		menu.getItems().add(axisOption);
		menu.getItems().add(timeOption);
		
		//add an icon to the menu and create menu bar
		FileInputStream file = new FileInputStream("C:\\Users\\yashj\\Desktop\\Random Pictures\\SolarSystemIcon.png");
		menu.setGraphic(new ImageView(new Image(file)));
		MenuBar menuBar = new MenuBar(menu);
		
		//create pane layout (set it to the home pane initially), vbox layout (storing menu and pane), and scene
		Pane pane = homePane();
		VBox root = new VBox(menuBar, pane);
		Scene scene = new Scene(root, 1800, 935);
		
		//set title of stage and display
		primaryStage.setTitle("Solar System Model");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	//create method to create planet scene with rings and distance; returns pane; will be home scene
	public static Pane homePane() {
		//create circles for the Sun
		Circle sun = new Circle(900, 500, 50, Color.YELLOW);
		
		//create saturn object
		Planet saturn = new Planet("Saturn", 30, Color.GOLDENROD, 300, 82, 8, Color.BROWN);
		Circle saturnCircle = saturn.createOrbiter(10, 30);
		Circle[] rings = saturn.getRings();
		Circle orbit = saturn.orbitPath;
		
		//create pane layout and scene
		Pane pane = new Pane();
		pane.getChildren().addAll(sun, orbit, saturnCircle);
		for(Circle ring: rings) pane.getChildren().add(ring);
		return pane;
	}
	
	//create method for distance
	//create method for size
	//create method for moons and rings
	//create method for inclination of planes
	//create method for axis of rotations
	//create method to display both times (rotational and orbital)
	//create method for mass, or something else
	

}
