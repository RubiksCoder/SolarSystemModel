package solar_system;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
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

public class JavaFXNotes extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
//		//for circle there is a specific object too (different from ellipse)
//		Circle circ = new Circle();
//		circ.setRadius(30);
//		circ.setLayoutX(50);
//		circ.setLayoutY(50);
//		circ.setFill(Color.AQUAMARINE);
//		
//		//create TranslateTransition object
//		TranslateTransition transition = new TranslateTransition();
//		transition.setDuration(Duration.seconds(3));
//		transition.setByX(500);
//		transition.setByY(300);
//		transition.setNode(circ);
//		transition.setAutoReverse(true);
//		transition.setCycleCount(Animation.INDEFINITE);
//		transition.play();
//		
//		//create ScaleTransition object
//		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(3), circ);
//		scaleTransition.setCycleCount(Animation.INDEFINITE);
//		scaleTransition.setAutoReverse(true);
//		scaleTransition.setToX(2);
//		scaleTransition.setToY(2);
//		scaleTransition.play();
//		
//		//create StrokeTransition object
//		
//		//create FillTransition object
//		FillTransition fillTransition = new FillTransition(Duration.seconds(3), circ, Color.CADETBLUE, Color.RED);
//		fillTransition.setCycleCount(Animation.INDEFINITE);
//		fillTransition.setAutoReverse(true);
//		fillTransition.play();
		
		//create rectangles
		Rectangle rect1 = new Rectangle(0,0,600,1);
		rect1.setFill(Color.BLUEVIOLET);
		
		Rectangle rect2 = new Rectangle(0, 599, 600, 1);
		rect2.setFill(Color.CORAL);
		
		//scale transition again
		ScaleTransition rect1T = new ScaleTransition(Duration.seconds(1), rect1);
		rect1T.setToY(600);
		rect1T.setCycleCount(1);
		rect1T.play();
		
		ScaleTransition rect2T = new ScaleTransition(Duration.seconds(1), rect2);
		rect2T.setToY(-600);
		rect2T.setCycleCount(1);
		rect2T.play();
		
		//make button
		Button button = new Button("Rotating");
		button.setLayoutX(-70);
		button.setLayoutY(300);
		
		RotateTransition btnRotate = new RotateTransition(Duration.seconds(2), button);
		btnRotate.setByAngle(360);
		btnRotate.setDelay(Duration.seconds(1));
		btnRotate.setRate(10);
		btnRotate.setCycleCount(10);
		btnRotate.play();
		
		TranslateTransition btnTransition = new TranslateTransition(Duration.seconds(2), button);
		btnTransition.setDelay(Duration.seconds(1));
		btnTransition.setToX(300);
		btnTransition.setCycleCount(1);
		btnTransition.play();
		
		//layout and scene init
		Pane root = new Pane();
		root.getChildren().addAll(rect1, rect2, button);
		Scene scene = new Scene(root, 600, 600);
		
		//can create a parallel transition and play multiple transitions for a node at the same time
		//can use sequential transition to play transitions in a sequence
		//path transition to make a shape follow a path
		
//		//create line
//		Line line = new Line(200, 30, 300, 400);
//		root.getChildren().add(line);
//		line.setStroke(Color.CORAL);
//		
//		//create quadratic curve
//		QuadCurve curve = new QuadCurve(180, 100, 240, 50, 10, 30);
//		root.getChildren().add(curve);
//		curve.setStroke(Color.BLACK);
//		curve.setFill(Color.CRIMSON);
//		
//		//draw ellipse
//		Ellipse circ = new Ellipse(250, 10, 45, 55);
//		circ.setStroke(Color.BLACK);
//		circ.setFill(Color.DARKGREEN);
//		root.getChildren().add(circ);
		
		primaryStage.setTitle("Java FX Shapes");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
