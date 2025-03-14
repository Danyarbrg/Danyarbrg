package org.openjfx;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;

public class switcher extends Application {

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(300, 300);


        return root;
    }

    private static class ToggleSwitch extends Parent {

        private BooleanProperty switchedOn = new SimpleBooleanProperty(false);

        private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));

        public BooleanProperty switchedOnProperty() {
            return switchedOn;
        }

        public ToggleSwitch() {
         Rectangle background = new Rectangle(100, 50);
         background.setArcWidth(50);
         background.setArcHeight(50);
         background.setFill(Color.WHITE);
         background.setStroke(Color.LIGHTGRAY);

         Circle trigger = new Circle(25);
         trigger.setCenterX(25);
         trigger.setCenterY(25);
         trigger.setFill(Color.WHITE);
         trigger.setStroke(Color.LIGHTGRAY);

         translateAnimation.setNode(trigger);

         getChildren().addAll(background, trigger);

         switchedOn.addListener((obs, oldState, newState) -> {
             boolean isOn = newState.booleanValue();
             translateAnimation.setToX(isOn ? 100 - 50 : 0);
             translateAnimation.play();
            });

         setOnMouseClicked(event -> {
             switchedOn.set(!switchedOn.get());
         });

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}