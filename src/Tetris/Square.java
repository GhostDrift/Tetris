package Tetris;

import java.awt.*;
import java.io.Serializable;

public class Square implements Serializable {
    private boolean colored = false;    // boolean value to check if the square is colored in
    private int x;  //holds the x coordinate of the colored square
    private int y;  //holds the y coordinate of the colored square
    private Color color;

    //constructor
    public Square(int x, int y){
        this.x = x;
        this.y = y;
//        this.color = c;
    }

    //getter and setter for colored
    public boolean getColored(){
        return this.colored;
    }
    public void setColored(boolean colored){
        this.colored = colored;
    }

    //getter for x
    public int getX(){
        return this.x;
    }

    //getter for y
    public int getY(){
        return this.y;
    }

    //set the value of x
    public void setX(int x){
        this.x = x;
    }
    //set the value of y
    public void setY(int y){
        this.y = y;
    }

    //set the value of color
    public void setColor(Color c){
        this.color = c;
    }
    //get the value of color
    public Color getColor(){
        return this.color;
    }

    @Override
    public String toString(){
        return ("X: " + this.x + " Y: " + this.y + " Colored: " + this.colored +"\n" );
    }
}
