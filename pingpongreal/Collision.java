package com.example.pingpongreal;

public class Collision {
    public static boolean checkCollision(Ball ball, Racket racket) {
        double ballX = ball.getBallXPos();
        double ballY = ball.getBallYPos();
        double ballRadius = ball.getRadius();

        double racketX = racket.getX();
        double racketY = racket.getY();
        double racketWidth = racket.getWidth();
        double racketHeight = racket.getHeight();

        if (ballX + ballRadius >= racketX &&
                ballX - ballRadius <= racketX + racketWidth &&
                ballY + ballRadius >= racketY &&
                ballY - ballRadius <= racketY + racketHeight) {
            return true; //Collision
        }
        return false;
    }

    public static void bounce(Ball ball, Racket racket) {
        // Example: Reverse the ball's horizontal direction
        double newSpeedX = -ball.getSpeed();
        double newSpeedY = ball.getSpeed();
    }
    public static boolean checkWallCollision(Ball ball,double screenWidth){
        double ballX = ball.getBallXPos();
        double ballRadius = ball.getRadius();

        return ballX - ballRadius <= 0 || ballX + ballRadius >= screenWidth;
    }
    public static void bounceOffWallAndUpdateScores(Ball ball, double screenWidth, Racket player1Racket, Racket player2Racket, int[] scores) {
        if (ball.getBallXPos() <= 0) {
            // Goal for Player 2
            scores[1]++;
        } else if (ball.getBallXPos() >= screenWidth) {
            // Goal for Player 1
            scores[0]++;
        }
        ball.setBallXPos(screenWidth / 2);
        ball.setBallYPos(screenWidth / 2);

    }
}