package Tetris;

public class Piece {
    private Square[][] map;
    private boolean active = false;
    private int id;

    public Piece(int n){
        this.id = n;
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
            int x = 3;
            int y = 0;
            for(int i = 0; i < 4; i++){
                for(int j = 0; j< 4; j++){
                    map[i][j] = new Square(x,y);
                    //System.out.println(x + " " + y);
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

    //checks to see if the piece can be moved down
    public boolean moveableDown(Square[][] gameBoard){
        Square s;
        if(this.id == 1){
            return false;
        }
        else if(this.id ==2){
            return false;
        }
        else if(this.id ==3){
            return false;
        }
        else if(this.id ==4){
                    int i = 1;
                    int j = 3;
                    s = this.map[i][j];
                    if (s.getColored()) {
                        int yvalue = s.getY()+1;
                        System.out.println("x chordiante: " + s.getX() + " y cordinate: " + s.getY());
                        if(s.getY() == 29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yvalue].getColored()){
                            System.out.println("is block below colored?: "+ gameBoard[s.getX()][yvalue].getColored());
                            System.out.println("block y value: " + s.getY());

                            System.out.println("Block y value + 1: " + yvalue);
                            return false;
                        }
                    }
            return true;
        }
        else if(this.id ==5){
            return false;
        }
        else if(this.id ==6){
            return false;
        }
        else {
            return false;
        }

    }

    //checks to see if th piece can be moved left

    //checks to see if the piece can be moved right
}
