package Tetris;

public class Piece {
    private Square[][] map;
    private boolean active = false;
    private int id;
    private int rotation;

    public Piece(int n, Maps maps){
        this.id = n;
        this.rotation =0;
        Square[][] template = maps.getMap(n,rotation);
        this.map = new Square[4][4];
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                this.map[i][j] = new Square(template[i][j].getX(),template[i][j].getY());
                this.map[i][j].setColored(template[i][j].getColored());
            }
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
    public int getId(){
        return this.id;
    }
    public int getRotation(){
        return this.rotation;
    }
    public void setRotation(int r){
        this.rotation = r;
    }

    //checks to see if the piece can be moved down
    public boolean moveableDown(Square[][] gameBoard){
        Square s;
        if(this.id == 0){
            return false;
        }
        else if(this.id ==1){
            return false;
        }
        else if(this.id ==2){
            return false;
        }
        else if(this.id ==3){
                    int i = 1;
                    int j = 3;
                    s = this.map[i][j];
                    if (s.getColored()) {
                        int yvalue = s.getY()+1;
//                        System.out.println("x chordiante: " + s.getX() + " y cordinate: " + s.getY());
                        if(s.getY() == 29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yvalue].getColored()){
//                            System.out.println("is block below colored?: "+ gameBoard[s.getX()][yvalue].getColored());
//                            System.out.println("block y value: " + s.getY());
//                            System.out.println("Block y value + 1: " + yvalue);
                            return false;
                        }
                    }
            return true;
        }
        else if(this.id ==4){
            return false;
        }
        else if(this.id ==5){
            return false;
        }
        else {
            return false;
        }

    }

    //checks to see if the piece can be moved left
    public boolean movableLeft(Square[][] gameBoard){
        Square s;
        if(this.id == 0){
            return false;
        }
        else if(this.id ==1){
            return false;
        }
        else if(this.id ==2){
            return false;
        }
        else if(this.id ==3){
            int i = 1;
//            int j = 3;
            for (int j = 0; j< 4; j++) {
                s = this.map[i][j];
                if (s.getColored()) {
                    int xValue = s.getX()-1;
                    if(s.getX() <= 0){
                        return false;
                    }
                    else if(gameBoard[xValue][s.getY()].getColored()){
                        return false;
                    }
                }
            }
            return true;
        }
        else if(this.id ==4){
            return false;
        }
        else if(this.id ==6){
            return false;
        }
        else {
            return false;
        }
    }

    //checks to see if the piece can be moved right
    public boolean movableRight(Square[][] gameBoard){
        Square s;
        if(this.id == 0){
            return false;
        }
        else if(this.id ==1){
            return false;
        }
        else if(this.id ==2){
            return false;
        }
        else if(this.id ==3){
            int i = 1;
//            int j = 3;
            for (int j = 0; j< 4; j++) {
                s = this.map[i][j];
                if (s.getColored()) {
                    int xValue = s.getX()+1;
                    //System.out.println("x chordiante: " + s.getX() + " y cordinate: " + s.getY());
                    if(s.getX() >= 9){
                        //System.out.println("The block is at the right edge of the board");
                        return false;
                    }
                    else if(gameBoard[xValue][s.getY()].getColored()){
                        //System.out.println("the block is on the left of another block");
                        return false;
                    }
                }
            }
            return true;
        }
        else if(this.id ==4){
            return false;
        }
        else if(this.id ==5){
            return false;
        }
        else {
            return false;
        }
    }
    //checks to see if the piece can be rotated
    public boolean canRotate(Square[][] gameBoard, Maps maps){
        int id = this.getId();
        int r = this.rotation;
        int nextR;
        if(r == 3){
            nextR = 0;
        }
        else{
            nextR = r +1;
        }
        Square[][] nextMap = maps.getMap(id,nextR);
        Square[][] map = this.getMap();
        if(id == 0 || id == 4){
            return true;
        }
        else if(id == 1){
            return false;
        }
        else if(id == 2){
            return false;
        }
        else if(id == 3){
            for(int i = 0; i< 4; i++){
                for(int j = 0; j<4; j++){
                    Square s = map[i][j];
                    if(nextMap[i][j].getColored()){
                        if (!s.getColored()) {
                            if(gameBoard[s.getX()][s.getY()].getColored()){
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        else if(id == 5){
            return false;
        }
        else {
            return false;
        }
    }
    //rotates the piece
    public void rotate(Maps maps){
        Square[][] map = this.getMap();
        int r = this.getRotation();
        int nextR;
        if(r == 3){
            nextR = 0;
        }
        else{
            nextR = r +1;
        }
        Square[][] nextMap = maps.getMap(id,nextR);
        for(int i = 0; i< 4; i++){
            for(int j = 0; j< 4; j++){
                map[i][j].setColored(nextMap[i][j].getColored());
            }
        }
    }
}
