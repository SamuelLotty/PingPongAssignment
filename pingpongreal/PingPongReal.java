package com.example.pingpongreal;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.EventListener;
import java.util.Scanner;

public class PingPongReal extends Application {
    String player1Name,player2Name;
    double ballSpeedValue;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Menu");

        Label welcome = new Label("Welcome to Ping Pong!");
        welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Button play = new Button("Play Game");
        Button exit = new Button("Exit");

        exit.setOnAction(e -> primaryStage.close());
        play.setOnAction(e -> login(primaryStage));

        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(15));
        vbox.getChildren().addAll(welcome, play, exit);

        GridPane grid = new GridPane();
        grid.add(vbox, 1, 1);
        grid.setHgap(1);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void login(Stage primaryStage) {
        Label title = new Label("Enter your specifications!");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        TextField p1 = new TextField();
        p1.setPromptText("Enter name for Player 1");

        TextField p2 = new TextField();
        p2.setPromptText("Enter name for Player 2");

        TextField ballSpeed = new TextField();
        ballSpeed.setPromptText("Enter the speed of the ball");

        TextField racketH = new TextField();
        racketH.setPromptText("Enter the height of the racket");

        TextField racketW = new TextField();
        racketW.setPromptText("Enter the Width of the racket");

        TextField scoreLimit = new TextField();
        scoreLimit.setPromptText("Enter the score limit");

        Button submit = new Button("Confirm");
        submit.setOnAction(e -> {
            if (p1.getText().isEmpty() || p2.getText().isEmpty() || ballSpeed.getText().isEmpty()
                    || racketW.getText().isEmpty() || racketH.getText().isEmpty() || scoreLimit.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all the fields");
            } else {
                player1Name = p1.getText();
                player2Name = p2.getText();
                double ballSpeedValue = Double.parseDouble(ballSpeed.getText());
                gameScreen(primaryStage);
            }
        });

        Button exit = new Button("Exit");
        exit.setOnAction(e -> primaryStage.close());

        VBox questions = new VBox(10);
        questions.getChildren().addAll(title, p1, p2, ballSpeed, racketH, racketW, scoreLimit, submit, exit);
        questions.setPadding(new Insets(50));

        Scene scene = new Scene(questions, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private void gameScreen(Stage primaryStage) {
        Group group = new Group();
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Racket player1 = new Racket(30, 260, 20, 70, 5, 0);
        Racket player2 = new Racket(530, 260, 20, 70, 5, 0);

        int play1Score = 0;
        int play2Score = 0;

        canvas.setFocusTraversable(true);
        ControlMove player = new ControlMove(player1,5);
        ControlMove player2R = new ControlMove(player2,5);

        Ball ball = new Ball(12, 5, 20, ballSpeedValue);

                // Check for collisions with rackets and walls
                if (Collision.checkCollision(ball, player1)) {
                    Collision.bounce(ball, player1);
                } else if (Collision.checkCollision(ball, player2)) {
                    Collision.bounce(ball, player2);
                }

                if (Collision.checkWallCollision(ball, canvas.getWidth())) {
                    Collision.bounceOffWallAndUpdateScores(ball, canvas.getWidth(),
                            player1, player2, new int[]{play1Score, play2Score});
                }

                //Render rackets and ball
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.BLACK);


                gc.fillRect(player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight());
                gc.fillRect(player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight());
                gc.fillOval(ball.getBallXPos() - ball.getRadius(), ball.getBallYPos() - ball.getRadius(), ball.getRadius() * 2, ball.getRadius() * 2);

                //Update the score
                gc.setTextAlign(TextAlignment.CENTER);
                gc.fillText(player1Name + ":" + play1Score + "\t\t" + player2Name + ":" + play2Score,
                        Math.round(canvas.getWidth() / 2),
                        Math.round(canvas.getHeight() / 6)
                );

        group.getChildren().add(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50),e -> gameScreen(primaryStage)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}