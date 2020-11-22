package Tetris;

public class Maps {
    private Square[][][][] pieces;  //4D array to store the maps

    //default constructor
    public Maps(){
        pieces = initialize();
        pieces =populate(pieces);
    }
    //initializes the array to store the piece maps
    private static Square[][][][] initialize(){
        Square[][][][] maps = new Square[7][4][4][4];
        for(int i = 0; i< 7; i++){
            if((i == 0) || (i==4)){
                int j =0;
                int x = 3;
                int y = 0;
                for(int k = 0; k < 4; k++){
                    for(int l = 0; l< 4; l++){
                        maps[i][j][k][l] = new Square(x,y);
                        //System.out.println(x + " " + y);
                        y++;
                    }
                    y=0;
                    x++;
                }
            }
            else{
                for(int j = 0; j< 4; j++){
                    int x = 3;
                    int y = 0;
                    for(int k = 0; k < 4; k++){
                        for(int l = 0; l< 4; l++){
                            maps[i][j][k][l] = new Square(x,y);
                            //System.out.println(x + " " + y);
                            y++;
                        }
                        y=0;
                        x++;
                    }
                }
            }

        }
        return maps;
    }

    //populates the maps
    private static Square[][][][] populate(Square[][][][] pieces){
        for(int i = 0; i< 7; i++){
            if(i == 0){
                int j =0;
                    for(int k = 0; k < 4; k++){
                        for(int l = 0; l< 4; l++){
                            if((k == 1)||(k==2)){
                                if((l==0)||(l==1)){
                                    pieces[i][j][k][l].setColored(true);
                                }
                            }
                        }
                    }

            }
            else if(i == 4){
                int j =0;
                for(int k = 0; k < 4; k++){
                    for(int l = 0; l< 4; l++){
                        if((k == 1)){
                            if((l==0)){
                                pieces[i][j][k][l].setColored(true);
                            }
                        }
                    }
                }
            }
//            else if(i == 1){
//                for(int j = 0; j< 4; j++){
//                    int x = 3;
//                    int y = 0;
//                    for(int k = 0; k < 4; k++){
//                        for(int l = 0; l< 4; l++){
//
//                        }
//                    }
//                }
//            }
//            else if(i == 2){
//                for(int j = 0; j< 4; j++){
//                    int x = 3;
//                    int y = 0;
//                    for(int k = 0; k < 4; k++){
//                        for(int l = 0; l< 4; l++){
//
//                        }
//                    }
//                }
//            }
//            else if(i == 3){
//                for(int j = 0; j< 4; j++){
//                    int x = 3;
//                    int y = 0;
//                    for(int k = 0; k < 4; k++){
//                        for(int l = 0; l< 4; l++){
//
//                        }
//                    }
//                }
//            }
//            else if(i == 5){
//                for(int j = 0; j< 4; j++){
//                    int x = 3;
//                    int y = 0;
//                    for(int k = 0; k < 4; k++){
//                        for(int l = 0; l< 4; l++){
//
//                        }
//                    }
//                }
//            }
//            else if(i == 6){
//                for(int j = 0; j< 4; j++){
//                    int x = 3;
//                    int y = 0;
//                    for(int k = 0; k < 4; k++){
//                        for(int l = 0; l< 4; l++){
//
//                        }
//                    }
//                }
//            }
        }
        return pieces;
    }

    //toString method
    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i< 7; i++){
                s +=" Piece #" + i + "\n";
            if(i == 0){
                s += "Map #" + 0 + " coordinates:\n";
                for(int k = 0; k<4; k++){
                    for(int l = 0; l<0; l++){
                        s += pieces[i][0][k][l].toString() + " ";
                    }
                }
            }
            else if(i == 4){
                s += "Map #" + 0 + " coordinates:\n";
                for(int k = 0; k<4; k++){
                    for(int l = 0; l<0; l++){
                        s += pieces[i][0][k][l].toString() + " ";
                    }
                }
            }
            else{
                for(int j = 0; j< 4; j++){
                    s += "Map #" + j + " coordinates:\n";
                    for(int k = 0; k < 4; k++){
                        for(int l = 0; l< 4; l++){
                            s += pieces[i][0][k][l].toString() + " ";
                        }
                    }
                }
            }
            s += "\n";
        }
        return s;
    }
}
