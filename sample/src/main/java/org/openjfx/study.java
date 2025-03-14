package org.openjfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class study extends Application {

    private List<String> colors = new ArrayList<>(); // список цветов
    private int currentColorIndex = 0; // индекс текущего цвета

    @Override
    public void start(Stage stage) { // start - точка входа графического интерфейса

        // добавление текстового поля
        TextField textField = new TextField();
        textField.setPromptText("Write the name here"); // серая подсказка

        //


        // инициализация цветов списка
        colors.add("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 18px;");
        colors.add("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 14px;");
        colors.add("-fx-background-color: green; -fx-text-fill: black; -fx-font-size: 22px;");
        colors.add("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 10px;");

        // создание кнопки с изменяемым текстом и цветом и просто кнопок
        Button btn1 = new Button("Наведи на меня");
        btn1.getStyleClass().add("my-button");
        btn1.setOnMouseEntered(event -> {
           currentColorIndex = (currentColorIndex + 1) % colors.size(); // переход к новому цвету
            btn1.setStyle(colors.get(currentColorIndex)); // устанавливаем новый цвет
        });
        Button btn2 = new Button("Наведи на меня");
        btn2.setOnMouseEntered(event -> {
            currentColorIndex = (currentColorIndex + 1) % colors.size(); // переход к новому цвету
            btn2.setStyle(colors.get(currentColorIndex)); // устанавливаем новый цвет
        });
        Button btn3 = new Button("Наведи на меня");
        btn3.setOnMouseEntered(event -> {
            currentColorIndex = (currentColorIndex + 1) % colors.size(); // переход к новому цвету
            btn3.setStyle(colors.get(currentColorIndex)); // устанавливаем новый цвет
        });
        Button button = new Button("Click me!");
        button.setStyle("-fx-background-color: rgba(150, 0, 100, 0.9); -fx-text-fill: yellow;");
        button.setOnAction(event -> {
                    String name = textField.getText(); // получаем текст из поля getText работает и с другими данными(кнопка)
                    if (name != "") {
                        System.out.println("Wazzap, " + name + ".");
                    }
                    else {
                        System.out.println("Write the name");
                    }
                    if (button.getText().equals("Click me!")) {
                        button.setText("One more");
                        button.setStyle("-fx-background-color: #FF6035; -fx-text-fill: white;");
                    }
                    else if (button.getText().equals("One more")) {
                        button.setText("Click click");
                        button.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
                    }
                    else if (button.getText().equals("Click click")) {
                        button.setText("Click me!");
                        button.setStyle("-fx-background-color: rgba(150, 0, 100, 0.9); -fx-text-fill: yellow;");
                        // поддерживает цвета CSS, rgba(0, 0, 0, 0.5(степень прозрачности))
                    }
        });

        // обработка изменения текста в button
        button.textProperty().addListener((observable, oldValue, newValue) -> { // addListener отслеживает изменение св-ств, textProperty - свойство текстового поля
            System.out.println("Text was changed on: " + newValue);
        });

        // Используем VBox для вертикального расположеения элементов - является таким же контейнером как StackPane
        HBox rootH = new HBox(10); // вертиакальный отступ
        rootH.setPadding(new Insets(20)); // отступы вокруг контейнеров
        rootH.setAlignment(Pos.CENTER); // Варавнивание
        VBox.setMargin(textField, new Insets (10, 0, 10, 0)); // отступ n пикселей для конкретной кнопки
        rootH.getChildren().addAll(textField, button);

        // используем HBox для горизонтального расположения элементов
        VBox rootV = new VBox(10);
        rootV.setPadding(new Insets(20));
        rootV.setAlignment(Pos.BOTTOM_LEFT); // Варавнивание
        rootV.getChildren().addAll(rootH, btn1, btn2, btn3); // вкладывание VBox в Hbox для сложного интерфейса

        // настройка сцены окна
        Scene scene = new Scene(rootV, 700, 700); // scene - содержимое окна
        stage.setScene(scene); // stage - окно
        stage.setTitle("some App");
        stage.show(); // отображение окна

        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
