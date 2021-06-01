package Tetris;

import java.awt.*;

public class Piece {
    private Square[][] map;
    private boolean active = false;
    private int id;
    private int rotation;
    private int gameID;
    private int color;


    public Piece(int n, Maps maps, int gameID){
        this.gameID = gameID;
        this.id = n;
        setColor();
        this.rotation =0;
        Square[][] template = maps.getMap(n,rotation);
        this.map = new Square[4][4];
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                this.map[i][j] = new Square(template[i][j].getX(),template[i][j].getY());
                this.map[i][j].setColored(template[i][j].getColored());
                this.map[i][j].setColor(this.color);
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
    public int getGameID(){
        return this.gameID;
    }
    public int getRotation(){
        return this.rotation;
    }
    public void setRotation(int r){
        this.rotation = r;
    }
    public void setColor(){
        if (this.gameID == 0){
            this.color = 1;
        }
        else {
            if (id == 0){
                this.color = 7;
            }
            else if (id == 1){
                this.color = 2;
            }
            else if (id == 3){
                this.color = 1;
            }
            else if (id == 4){
                this.color = 5;
            }
            else if (id == 5){
                this.color = 6;
            }
            else if (id == 6){
                this.color = 4;
            }
            else if (id == 2){
                this.color = 3;
            }
        }
    }

    //checks to see if the piece can be moved down
    public boolean moveableDown(Square[][] gameBoard){
        Square s;
        int r = this.getRotation();
        int id = this.getId();
        int game = this.getGameID();
        if(id == 0){
    //      System.out.println("piece 0");
            int j = 1;
            for (int i = 1; i<3; i++) {
                s = this.map[i][j];
                if (s.getColored()) {
                    int yvalue = s.getY()+1;
        //          System.out.println("x chordiante: " + s.getX() + " y cordinate: " + s.getY());
                    if(s.getY() == 29){
    //                  System.out.println("at the bottom of the board");
                        return false;
                    }
                    else if(gameBoard[s.getX()][yvalue].getColored()){
        //                  System.out.println("is block below colored?: "+ gameBoard[s.getX()][yvalue].getColored());
        //                  System.out.println("block y value: " + s.getY());
        //                  System.out.println("Block y value + 1: " + yvalue);
        //                  System.out.println("Blocked by another piece");
                        return false;
                    }
                }
            }
                return true;
        }
        else if(id ==3){
            //            System.out.println("piece 3");
            if (r== 0) {
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
            }
            else if(r ==1){
                int y = 2;
                for(int i = 0; i<4; i++){
                    s = this.map[i][y];
                    if(s.getColored()){
                        int yValue = s.getY()+1;
                        if(s.getY()==29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yValue].getColored()){
                            return false;
                        }
                    }
                }
            }
            else if(r == 2){
                int x = 2;
                int y = 3;
                s= this.map[x][y];
                if(s.getColored()){
                    int yValue = s.getY()+1;
                    if(s.getY()==29){
                        return false;
                    }
                    else if(gameBoard[s.getX()][yValue].getColored()){
                        return false;
                    }
                }
            }
            else if(r == 3){
                int y = 1;
                for(int i = 0; i<4; i++){
                    s = this.map[i][y];
                    if(s.getColored()){
                        int yValue = s.getY()+1;
                        if(s.getY()==29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yValue].getColored()){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        if (game == 0) {
            if(id ==1 || id == 5){
    //            System.out.println("piece 1");
    //            if(r==0 ){
    //                System.out.println("rotation 0");
                    for(int i = 0; i<4; i++){
                        for(int j = 0; j< 4; j++){
                            s = this.map[i][j];
                            if (s.getColored()) {
                                int yvalue = s.getY()+1;
    //                        System.out.println("x chordiante: " + s.getX() + " y cordinate: " + s.getY());
                                if(s.getY() == 29){
    //                                System.out.println("at the bottom of the board");
                                    return false;
                                }
                                else if(gameBoard[s.getX()][yvalue].getColored()){
    //                            System.out.println("is block below colored?: "+ gameBoard[s.getX()][yvalue].getColored());
    //                            System.out.println("block y value: " + s.getY());
    //                            System.out.println("Block y value + 1: " + yvalue);
    //                                System.out.println("Blocked by another piece");
                                    return false;
                                }
                            }
                        }
                    }
                return true;
            }
            else if(id ==2){
    //            System.out.println("piece 2");
                if (r == 0 || r == 2){
                    for(int i = 0; i<4; i++){
                        for(int j = 0; j< 4; j++){
                            s = this.map[i][j];
                            if (s.getColored()) {
                                int yvalue = s.getY()+1;
    //                        System.out.println("x chordiante: " + s.getX() + " y cordinate: " + s.getY());
                                if(s.getY() == 29){
    //                                System.out.println("at the bottom of the board");
                                    return false;
                                }
                                else if(gameBoard[s.getX()][yvalue].getColored()){
    //                            System.out.println("is block below colored?: "+ gameBoard[s.getX()][yvalue].getColored());
    //                            System.out.println("block y value: " + s.getY());
    //                            System.out.println("Block y value + 1: " + yvalue);
    //                                System.out.println("Blocked by another piece");
                                    return false;
                                }
                            }
                        }
                    }
                }
                else if(r == 1){
                    int j = 3;
                    for(int i = 0; i<2; i++){
                        s = this.map[i][j];
                        if (s.getColored()) {
                            int yvalue = s.getY()+1;
    //                        System.out.println("x chordiante: " + s.getX() + " y cordinate: " + s.getY());
                            if(s.getY() == 29){
    //                            System.out.println("at the bottom of the board");
                                return false;
                            }
                            else if(gameBoard[s.getX()][yvalue].getColored()){
    //                            System.out.println("is block below colored?: "+ gameBoard[s.getX()][yvalue].getColored());
    //                            System.out.println("block y value: " + s.getY());
    //                            System.out.println("Block y value + 1: " + yvalue);
    //                                System.out.println("Blocked by another piece");
                                return false;
                            }
                            j--;
                        }
                    }
                }
                else if(r == 3){
                    int j = 2;
                    for(int i = 2; i<4; i++){
                        s = this.map[i][j];
                        if (s.getColored()) {
                            int yvalue = s.getY()+1;
    //                        System.out.println("x chordiante: " + s.getX() + " y cordinate: " + s.getY());
                            if(s.getY() == 29){
    //                            System.out.println("at the bottom of the board");
                                return false;
                            }
                            else if(gameBoard[s.getX()][yvalue].getColored()){
    //                            System.out.println("is block below colored?: "+ gameBoard[s.getX()][yvalue].getColored());
    //                            System.out.println("block y value: " + s.getY());
    //                            System.out.println("Block y value + 1: " + yvalue);
    //                                System.out.println("Blocked by another piece");
                                return false;
                            }
                            j++;
                        }
                    }
                }
                return true;
            }
            else if(id ==4){
    //            System.out.println("piece 4");
                s = this.map[1][0];
                int yValue = s.getY()+1;
                if(s.getY()==29){
                    return false;
                }
                else if(gameBoard[s.getX()][yValue].getColored()){
                    return false;
                }
                return true;
            }
            else {
    //            System.out.println("piece 6");
                if(r == 0){
                    int i = 1;
                    int j = 1;
                        s = this.map[i][j];
                        int yValue = s.getY()+1;
                        if(s.getY()==29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yValue].getColored()){
                            return false;
                        }
                }
                else if(r == 1){
                    int j = 1;
                    for(int i =0; i<2; i++){
                        s = this.map[i][j];
                        int yValue = s.getY()+1;
                        if(s.getY()==29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yValue].getColored()){
                            return false;
                        }
                    }
                }
                else if(r == 2){
                    int i = 1;
                    int j = 2;
                    s = this.map[i][j];
                    int yValue = s.getY()+1;
                    if(s.getY()==29){
                        return false;
                    }
                    else if(gameBoard[s.getX()][yValue].getColored()){
                        return false;
                    }
                }
                else if(r == 3){
                    int j = 1;
                    for(int i =1; i<3; i++){
                        s = this.map[i][j];
                        int yValue = s.getY()+1;
                        if(s.getY()==29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yValue].getColored()){
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        else if (game == 1){
            if(id == 1){
                if(r == 0){
                    int j = 1;
                    for(int i = 1; i < 4; i++){
                        s = this.map[i][j];
                        int yValue = s.getY()+1;
                        if(s.getY() == 29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yValue].getColored()){
                            return false;
                        }
                    }
                }
                else if(r == 1){
                    int j = 2;
                    for(int i = 1; i < 3; i++){
                        s = this.map[i][j];
                        int yValue = s.getY()+1;
                        if(s.getY() == 29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yValue].getColored()){
                            return false;
                        }
                    }
                }
                else if (r == 2){
                    for (int j = 1; j <3; j ++) {
                        for(int i = 1; i < 4; i++){
                            if ((j == 1) &&((i == 1)|| (i == 2))) {
                                s = this.map[i][j];
                                int yValue = s.getY()+1;
                                if(s.getY() == 29){
                                    return false;
                                }
                                else if(gameBoard[s.getX()][yValue].getColored()){
                                    return false;
                                }
                            }
                            else if((j == 2)&& (i == 3)){
                                s = this.map[i][j];
                                int yValue = s.getY()+1;
                                if(s.getY() == 29){
                                    return false;
                                }
                                else if(gameBoard[s.getX()][yValue].getColored()){
                                    return false;
                                }
                            }
                        }
                    }
                }
                else if ( r == 3){
                    for(int i = 2; i <4; i ++){
                        if (i == 2){
                            int j = 2;
                            s = this.map[i][j];
                            int yValue = s.getY()+1;
                            if(s.getY() == 29){
                                return false;
                            }
                            else if(gameBoard[s.getX()][yValue].getColored()){
                                return false;
                            }
                        }
                        else {
                            int j = 0;
                            s = this.map[i][j];
                            int yValue = s.getY()+1;
                            if(s.getY() == 29){
                                return false;
                            }
                            else if(gameBoard[s.getX()][yValue].getColored()){
                                return false;
                            }
                        }
                    }
                }
            }
            else if (id == 2){
                if (r == 0){
                    for(int i = 1; i < 4; i++){
                        int j = 1;
                        s = this.map[i][j];
                        int yValue = s.getY()+1;
                        if(s.getY() == 29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yValue].getColored()){
                            return false;
                        }
                    }
                }
                else if (r == 1){
                    for (int i = 1; i < 3; i ++){
                        int j = 2;
                        s = this.map[i][j];
                        int yValue = s.getY()+1;
                        if(s.getY() == 29){
                            return false;
                        }
                        else if(gameBoard[s.getX()][yValue].getColored()){
                            return false;
                        }
                    }
                }
                else if (r == 2){
                    for (int i = 1; i <4; i++){
                        if ((i == 1 ) || (i == 2)){
                            int j = 1;
                            s = this.map[i][j];
                            int yValue = s.getY()+1;
                            if(s.getY() == 29){
                                return false;
                            }
                            else if(gameBoard[s.getX()][yValue].getColored()){
                                return false;
                            }
                        }
                        else{
                            int j = 2;
                            s = this.map[i][j];
                            int yValue = s.getY()+1;
                            if(s.getY() == 29){
                                return false;
                            }
                            else if(gameBoard[s.getX()][yValue].getColored()){
                                return false;
                            }
                        }
                    }
                }
                else {
                    for (int i = 2; i <4; i++){
                        if(i == 2){
                            int j = 2;
                            s = this.map[i][j];
                            int yValue = s.getY()+1;
                            if(s.getY() == 29){
                                return false;
                            }
                            else if(gameBoard[s.getX()][yValue].getColored()){
                                return false;
                            }
                        }
                        else{
                            int j = 0;
                            s = this.map[i][j];
                            int yValue = s.getY()+1;
                            if(s.getY() == 29){
                                return false;
                            }
                            else if(gameBoard[s.getX()][yValue].getColored()){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;

    }

    //checks to see if the piece can be moved left
    public boolean movableLeft(Square[][] gameBoard){
        Square s;
        int r = this.getRotation();
        if(this.id == 0){
            int i = 1;
            for(int j = 0; j<2; j++){
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
        else if(this.id ==1 || this.id == 5){
            for(int i =0; i<4; i++){
                for(int j = 0; j<4; j++){
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
            }
            return true;
        }
        else if(this.id ==2){
            if(r == 0){
                int j = 0;
                for(int i = 0; i<2; i++){
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
                    j++;
                }
            }
            else if(r == 1|| r == 3){
                for(int i =0; i<4; i++){
                    for(int j = 0; j<4; j++){
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
                }
            }
            else if(r == 2){
                int j =3;
                for(int i = 0; i<2; i++){
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
                    j--;
                }
            }
            return true;
        }
        else if(this.id ==3){
            if (this.getRotation() == 0) {
                int i = 1;
//              int j = 3;
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
            }
            else if(this.getRotation() == 1){
                int i = 0;
                int j = 2;
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
            else if(this.getRotation() == 2){
                int i = 2;
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
            }
            else{
                int i = 0;
                int j = 1;
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
            int i = 1;
            int j = 0;
            s = this.map[i][j];
            int xValue = s.getX()-1;
            if(s.getX() <= 0){
                return false;
            }
            else if(gameBoard[xValue][s.getY()].getColored()){
                return false;
            }
            return true;
        }
        else {
            if (r == 0) {
                int i = 1;
                for(int j =0; j<2; j++){
                    s = this.map[i][j];
                    int xValue = s.getX()-1;
                    if(s.getX() <= 0){
                        return false;
                    }
                    else if(gameBoard[xValue][s.getY()].getColored()){
                        return false;
                    }
                }
            }
            else if(r == 1){
                int i = 0;
                int j = 1;
                s = this.map[i][j];
                int xValue = s.getX()-1;
                if(s.getX() <= 0){
                    return false;
                }
                else if(gameBoard[xValue][s.getY()].getColored()){
                    return false;
                }
            }
            else if(r == 2){
                int i = 1;
                for(int j =1; j<3; j++){
                    s = this.map[i][j];
                    int xValue = s.getX()-1;
                    if(s.getX() <= 0){
                        return false;
                    }
                    else if(gameBoard[xValue][s.getY()].getColored()){
                        return false;
                    }
                }
            }
            else if(r == 3){
                int i = 1;
                int j = 1;
                s = this.map[i][j];
                int xValue = s.getX()-1;
                if(s.getX() <= 0){
                    return false;
                }
                else if(gameBoard[xValue][s.getY()].getColored()){
                    return false;
                }
            }
            return true;
        }
    }

    //checks to see if the piece can be moved right
    public boolean movableRight(Square[][] gameBoard){
        Square s;
        int r = this.getRotation();
        if(this.id == 0){
            int i = 2;
            for(int j = 0; j<2; j++){
                s = this.map[i][j];
                if (s.getColored()) {
                    int xValue = s.getX()+1;
                    //System.out.println("x chordiante: " + s.getX() + " y cordinate: " + s.getY());
                    if(s.getX() >= 9){
//                        System.out.println("The block is at the right edge of the board");
                        return false;
                    }
                    else if(gameBoard[xValue][s.getY()].getColored()){
//                        System.out.println("the block is on the left of another block");
                        return false;
                    }
                }
            }
            return true;
        }
        else if(this.id ==1 || this.id == 5){
            for(int i =0; i<4; i++){
                for(int j = 0; j<4; j++){
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
            }
            return true;
        }
        else if(this.id ==2){
            if(r == 0){
                int j = 1;
                for(int i = 2; i<4; i++){
                    s = this.map[i][j];
                    if (s.getColored()) {
                        int xValue = s.getX()+1;
                        if(s.getX() <= 0){
                            return false;
                        }
                        else if(xValue == 10){
                            return false;
                        }
                        else if(gameBoard[xValue][s.getY()].getColored()){
                            return false;
                        }
                    }
                    j--;
                }
            }
            else if(r == 1|| r == 3){
                for(int i =0; i<4; i++){
                    for(int j = 0; j<4; j++){
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
                }
            }
            else if(r == 2){
                int j =2;
                for(int i = 2; i<4; i++){
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
                    j++;
                }
            }
            return true;
        }
        else if(this.id ==3){
            if (this.getRotation() == 0) {
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
            }
            else if(this.getRotation() ==1){
                int i = 3;
                int j = 2;
                s = this.map[i][j];
                if (s.getColored()) {
                    int xValue = s.getX()+1;
                    if(s.getX() >= 9){
                        return false;
                    }
                    else if(gameBoard[xValue][s.getY()].getColored()){
                        return false;
                    }
                }
            }
            else if(this.getRotation() == 2){
                int i = 2;
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
            }
            else if(this.getRotation() == 3){
                int i = 3;
                int j = 1;
                s = this.map[i][j];
                if (s.getColored()) {
                    int xValue = s.getX()+1;
                    if(s.getX() >= 9){
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
            int i = 1;
            int j = 0;
            s = this.map[i][j];
            int xValue = s.getX()+1;
            if(s.getX() >= 9){
                return false;
            }
            else if(gameBoard[xValue][s.getY()].getColored()){
                return false;
            }
            return true;
        }
        else {
            if(r == 0){
                int i = 1;
                for(int j = 0; j< 2; j++){
                    s = this.map[i][j];
                    int xValue = s.getX()+1;
                    if(s.getX() >= 9){
                        return false;
                    }
                    else if(gameBoard[xValue][s.getY()].getColored()){
                        return false;
                    }
                }
            }
            else if(r == 1){
                int i = 1;
                int j = 1;
                s = this.map[i][j];
                int xValue = s.getX()+1;
                if(s.getX() >= 9){
                    return false;
                }
                else if(gameBoard[xValue][s.getY()].getColored()){
                    return false;
                }
            }
            else if(r == 2){
                int i = 1;
                for(int j = 1; j< 3; j++){
                    s = this.map[i][j];
                    int xValue = s.getX()+1;
                    if(s.getX() >= 9){
                        return false;
                    }
                    else if(gameBoard[xValue][s.getY()].getColored()){
                        return false;
                    }
                }
            }
            else if(r == 3){
                int i = 2;
                int j = 1;
                s = this.map[i][j];
                int xValue = s.getX()+1;
                if(s.getX() >= 9){
                    return false;
                }
                else if(gameBoard[xValue][s.getY()].getColored()){
                    return false;
                }
            }
            return true;
        }
    }
    //checks to see if the piece can be rotated
    public boolean canRotate(Square[][] gameBoard, Maps maps){
        int id = this.getId();
        int r = this.rotation;
        int nextR;
        if(!this.moveableDown(gameBoard)){
            return false;
        }
        try {
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
//            else if(id == 1){
//                return false;
//            }
//            else if(id == 2){
//                return false;
//            }
//            else if(id == 3){
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
//            }
//            else if(id == 5){
//                return false;
//            }
//            else {
//                return false;
//            }
        } catch (Exception e) {
            return false;
        }
    }
    //rotates the piece
    public void rotate(Maps maps,Square[][] gameBoard){
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
        Square s;
        for(int i = 0; i< 4; i++){
            for(int j = 0; j< 4; j++){
                s = map[i][j];
                if (s.getColored()) {
                    gameBoard[s.getX()][s.getY()].setColored(!s.getColored());
                }
                s.setColored(nextMap[i][j].getColored());
            }
        }

        this.setRotation(nextR);
    }
}
