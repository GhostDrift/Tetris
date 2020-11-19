package Tetris;

public class Piece {
    private boolean[][] map;
    private boolean active = false;
    public Piece(int n){
        if (n == 1){

        }
        else if( n == 2){

        }
        else if( n == 3){

        }
        else if(n == 4){
            map = new boolean[1][4];
            for (int i = 0; i < 4; i++){
                map[0][i] = true;
            }
        }
        else if(n == 5){

        }
        else if(n == 6){

        }
        else if(n == 7) {

        }
    }
}
