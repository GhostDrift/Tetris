package Tetris;

public class Maps {
    private Square[][][][] pieces;
    public Maps(){

    }
    private static Square[][][][] initialize(){
        Square[][][][] maps = new Square[7][4][4][4];
        for(int i = 0; i< 7; i++){
            if(i == 0 || i==4){
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
    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i< 7; i++){
            if(i == 0 || i == 4){
                for(int k = 0; k<4; k++){
                    for(int l = 0; l<0; l++){
                        s += "Piece # " + i + "Map # " + 0 + " coordinates:\n" +pieces[i][0][k][l].toString() + " ";
                    }
                }
            }
            else{
                for(int j = 0; j< 4; j++){
                    for(int k = 0; k < 4; k++){
                        for(int l = 0; l< 4; l++){
                            s += "Piece # " + i + "Map # " + j + " coordinates:\n" +pieces[i][0][k][l].toString() + " ";
                        }
                    }
                }
            }
        }
        return s;
    }
}
