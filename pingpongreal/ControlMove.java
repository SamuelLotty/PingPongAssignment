package com.example.pingpongreal;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class ControlMove implements EventHandler<KeyEvent>{
    public Racket racket;
    public double speed;
//Constructor
    public ControlMove(Racket racket,double speed){
        this.racket=racket;
        this.speed=speed;
    }
    //Handler
    @Override
    public void handle(KeyEvent event) {
        switch(event.getCode()) {
            case UP:
                racket.moveUp();
                break;
            case DOWN:
                racket.moveDown();
                break;
        }
    }
}

