package com.example.pingpongreal;

import javafx.scene.shape.Rectangle;

public class Racket {
    private double x, y, width, height, speedX, speedY;

    public Racket(double x, double y, double width, double height, double speedX, double speedY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speedX = speedX;
        this.speedY = speedY;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void moveUp() {
        setY(getY()-speedY);
    }

    public void moveDown() {
        setY(getY()+speedY);

    }
}
