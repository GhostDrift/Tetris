package Tetris;

import java.io.Serializable;

public class Square implements Serializable {
    private boolean colored = false;    // boolean value to check if the square is colored in
    private int x;  //holds the x coordinate of the colored square
    private int y;  //holds the y coordinate of the colored square

    //constructor
    public Square(int x, int y){
        this.x = x;
        this.y = y;
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
}