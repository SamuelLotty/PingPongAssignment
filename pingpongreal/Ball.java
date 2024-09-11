package com.example.pingpongreal;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class Ball {
    private double ballXPos,ballYPos,radius,speed;

    public Ball(double ballXPos, double ballYPos, double radius, double speed) {
        this.ballXPos = ballXPos;
        this.ballYPos = ballYPos;
        this.radius = radius;
        this.speed = speed;
    }

   public double getSpeed(){return speed;}
    public double getRadius(){return radius;}
    public double getBallXPos(){return ballXPos;}
    public double getBallYPos(){return ballYPos;}

    public void setBallXPos(double ballXPos) {
        this.ballXPos=ballXPos;
    }

    public void setBallYPos(double ballYPos) {
        this.ballYPos=ballYPos;
    }
}
