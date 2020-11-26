package Tetris;

import java.io.Serializable;

public class Score implements Serializable {
    private String initials;
    private int score;
    public Score(int score, String initials){
        this.initials = initials;
        this.score = score;
    }
    public String getInitials(){
        return this.initials;
    }
    public int getScore(){
        return this.score;
    }
    @Override
    public String toString(){
        return "" + this.score + " - " + this.initials;
    }
}
