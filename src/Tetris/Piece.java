package Tetris;

public class Piece {
    private Square[][] map;
    private boolean active = false;

    public Piece(int n){
        if (n == 1){
            map = new Square[4][4];
        }
        else if( n == 2){
            map = new Square[4][4];
        }
        else if( n == 3){
            map = new Square[4][4];
        }
        else if(n == 4){
            map = new Square[4][4];
            int x = 0;
            int y = 0;
            for(int i = 0; i < 4; i++){
                for(int j = 0; j< 4; j++){
                    map[i][j] = new Square(x,y);
                            y++;
                }
                y=0;
                x++;
            }
            for(int i = 0; i < 4; i++){
                map[1][i].setColored(true);
            }
        }
        else if(n == 5){
            map = new Square[4][4];
        }
        else if(n == 6){
            map = new Square[4][4];
        }
        else if(n == 7) {
            map = new Square[4][4];
        }
    }
    //getters and setters for private variables
    public Square[][] getMap(){
        return this.map;
    }
    public void setActive(boolean a){
        this.active = a;
    }
    public boolean getActive(){
        return this.active;
    }
}
