package org.openjfx;  // Пакет, в котором находится класс

import static java.lang.Math.random;

import javafx.scene.layout.HBox;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;  // Базовый класс для JavaFX-приложений
import javafx.event.ActionEvent;        // Класс события (нажатие кнопки)
import javafx.event.EventHandler;       // Интерфейс для обработки событий
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.CycleMethod;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.Scene;              // Контейнер для содержимого окна
import javafx.scene.control.Button;     // Класс кнопки
import javafx.scene.layout.StackPane;   // Контейнер для размещения элементов
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle; // Добавил импорт для Rectangle
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;              // Класс окна приложения
import javafx.util.Duration;

import java.awt.*;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);

        HBox hbox = new HBox(10);
        Button btn1 = new Button("1");
        Button btn2 = new Button("2");
        Button btn3 = new Button("3");
        Button btn4 = new Button("4");

        hbox.getChildren().addAll(btn1, btn2, btn3, btn4); // добавление кнопок в hbox
        root.getChildren().add(hbox); // добавление hbox в корневой Group

        Group circles = new Group();
        for (int i = 0; i < 30; i++) {
            Circle circle = new Circle(150, Color.web("white", 0.05));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
            circle.setEffect(new BoxBlur(10, 10, 3));
        }

        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
                new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE,
                        new Stop[]{
                                new Stop(0, Color.web("#f8bd55")),
                                new Stop(0.14, Color.web("#c0fe56")),
                                new Stop(0.28, Color.web("#5dfbc1")),
                                new Stop(0.43, Color.web("#64c2f8")),
                                new Stop(0.57, Color.web("#be4af7")),
                                new Stop(0.71, Color.web("#ed5fc2")),
                                new Stop(0.85, Color.web("#ef504c")),
                                new Stop(1, Color.web("#f2660f"))}));

        colors.widthProperty().bind(scene.widthProperty());
        colors.heightProperty().bind(scene.heightProperty());

        Group blendModelGroup =
                new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
                        Color.BLACK), circles), colors);
        colors.setBlendMode(BlendMode.OVERLAY);
        root.getChildren().add(blendModelGroup);

        Timeline timeline = new Timeline();
        for (Node circle: circles.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(circle.translateXProperty(), random() * 800),
                            new KeyValue(circle.translateYProperty(), random() * 600)
                    ),
                    new KeyFrame(new Duration(40000),
                            new KeyValue(circle.translateXProperty(), random() * 800),
                            new KeyValue(circle.translateYProperty(), random() * 600)
                    )
            );
        }



        timeline.play();
        primaryStage.setTitle("Circles");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}