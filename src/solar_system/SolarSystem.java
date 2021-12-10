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

public class SolarSystem extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		//create circles for the Sun and the Earth
		Circle sun = new Circle(300, 300, 50, Color.YELLOW);
		Circle earth = new Circle(300, 100, 10, Color.BLUE);
		
		//make earth's orbit
		Circle orbit = new Circle(300, 300, 200);
		
		//make the earth complete revolution around the sun
		PathTransition revo = new PathTransition(Duration.seconds(5), orbit, earth);
		revo.setCycleCount(Animation.INDEFINITE);
		revo.setInterpolator(Interpolator.LINEAR);
		revo.play();
		
		//create pane layout and scene
		Pane root = new Pane();
		root.getChildren().addAll(sun, earth);
		Scene scene = new Scene(root, 600, 600);
		
		//set title of stage and display
		primaryStage.setTitle("Java FX Shapes");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		
		//launch program
		launch(args);

	}

}
