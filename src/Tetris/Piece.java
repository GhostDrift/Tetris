package Tetris;

public class Piece {
    private boolean[][] map;
    private boolean active = false;
    public Piece(int n){
        if (n == 1){
            map = new boolean[4][4];
        }
        else if( n == 2){
            map = new boolean[4][4];
        }
        else if( n == 3){
            map = new boolean[4][4];
        }
        else if(n == 4){
            map = new boolean[4][4];
            for(int i = 0; i < 4; i++){
                map[1][i] = true;
            }
        }
        else if(n == 5){
            map = new boolean[4][4];
        }
        else if(n == 6){
            map = new boolean[4][4];
        }
        else if(n == 7) {
            map = new boolean[4][4];
        }
    }
    public boolean[][] getMap(){
        return this.map;
    }
}
