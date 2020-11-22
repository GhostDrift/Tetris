package Tetris;

public class Maps {
  private Square[][] piece0;
  private Square[][][] piece1;
  private Square[][][] piece2;
  private Square[][][] piece3;
  private Square[][] piece4;
  private Square[][][] piece5;
  private Square[][][] piece6;
  public Maps(){
      this.piece0 = populatePiece0();
      this.piece1 = populatePiece1();
      this.piece2 = populatePiece2();
//      this.piece3 = populatePiece3();
//      this.piece4 = populatePiece4();
//      this.piece5 = populatePiece5();
//      this.piece6 = populatePiece6();
  }

//    private Square[][][] populatePiece6() {
//    }
//
//    private Square[][][] populatePiece5() {
//    }
//
//    private Square[][] populatePiece4() {
//    }
//
//    private Square[][][] populatePiece3() {
//    }
//
    private Square[][][] populatePiece2() {
      Square[][][] piece2 = new Square[4][4][4];
        for (int k = 0; k<4; k++) {
            int x = 3;
            int y = 0;
            for(int i = 0; i < 4; i++){
                for(int j = 0; j< 4; j++){
                    piece2[k][i][j] = new Square(x,y);
                    //System.out.println(x + " " + y);
                    y++;
                }
                y=0;
                x++;
            }
            if (k == 0) {
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j< 4; j++){
                        if (i == 0) {
                            if (j== 0) {
                                piece2[k][i][j].setColored(true);
                                System.out.println(piece2[k][i][j]);
                            }
                        }
                        else if (i == 1) {
                            if (j== 1) {
                                piece2[k][i][j].setColored(true);
                                System.out.println(piece2[k][i][j]);
                            }
                        }
                        else if (i == 2) {
                            if (j== 1) {
                                piece2[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 3) {
                            if (j== 0) {
                                piece2[k][i][j].setColored(true);
                            }
                        }
                    }
                }
            }
            else if(k == 1) {
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j< 4; j++){
                        if (i == 0) {
                            if (j== 0 || j == 3) {
                                piece2[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 1) {
                            if (j== 1|| j== 2 ) {
                                piece2[k][i][j].setColored(true);
                            }
                        }
                    }
                }
            }
            else if(k == 2) {
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j< 4; j++){
                        if (i == 0) {
                            if (j== 3) {
                                piece2[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 1) {
                            if (j== 2 ) {
                                piece2[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 2) {
                            if (j== 2) {
                                piece2[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 3){
                            if(j == 3){
                                piece2[k][i][j].setColored(true);
                            }
                        }
                    }
                }
            }
            else if(k == 3) {
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j< 4; j++){
                        if (i == 2) {
                            if (j== 2 || j==1) {
                                piece2[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 3) {
                            if (j== 0 || j ==3 ) {
                                piece2[k][i][j].setColored(true);
                            }
                        }
                    }
                }
            }
        }
        return piece2;
    }

    private Square[][][] populatePiece1() {
      Square[][][] piece1 = new Square[4][4][4];
        for (int k = 0; k<4; k++) {
            int x = 3;
            int y = 0;
            for(int i = 0; i < 4; i++){
                for(int j = 0; j< 4; j++){
                    piece1[k][i][j] = new Square(x,y);
                    //System.out.println(x + " " + y);
                    y++;
                }
                y=0;
                x++;
              }
            if (k == 0) {
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j< 4; j++){
                        if (i == 0) {
                            if (j== 0) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 1) {
                            if (j== 1) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 2) {
                            if (j== 2) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 3) {
                            if (j== 1) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                    }
                }
            }
            else if(k == 1) {
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j< 4; j++){
                        if (i == 0) {
                            if (j== 3) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 1) {
                            if (j== 0 || j == 2 ) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 2) {
                            if (j== 1) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                    }
                }
            }
            else if(k == 2) {
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j< 4; j++){
                        if (i == 0) {
                            if (j== 2) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 1) {
                            if (j== 1 ) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 2) {
                            if (j== 2) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 3){
                            if(j == 3){
                                piece1[k][i][j].setColored(true);
                            }
                        }
                    }
                }
            }
            else if(k == 3) {
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j< 4; j++){
                        if (i == 1) {
                            if (j== 2) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 2) {
                            if (j== 1 || j ==3 ) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                        else if (i == 3) {
                            if (j== 0) {
                                piece1[k][i][j].setColored(true);
                            }
                        }
                    }
                }
            }
        }
        return piece1;
    }

    private static Square[][] populatePiece0() {
      Square[][] piece0 = new Square[4][4];
        int x = 3;
        int y = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j< 4; j++){
                piece0[i][j] = new Square(x,y);
                //System.out.println(x + " " + y);
                y++;
            }
            y=0;
            x++;
        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j< 4; j++){
                if (i == 1 || i ==2 ) {
                    if (j== 0 || j ==1) {
                        piece0[i][j].setColored(true);
                    }
                }
            }
        }
        return piece0;
    }

    @Override
    public String toString(){
      String s = "";
      s+= "piece0:\n";
      for (int i = 0; i < 4; i++) {
          for (int j = 0; j<4; j++) {
              s += this.piece0[i][j].toString();
          }
      }
      s+= "\npiece1:\n";
        for (int k = 0; k<4; k++) {
            s += "rotation " + k + "\n";
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j<4; j++) {
                    s += this.piece1[k][i][j].toString();
                }
            }
        }
        s+= "\npiece2:\n";
        for (int k = 0; k<4; k++) {
            s += "rotation " + k + "\n";
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j<4; j++) {
                    s += this.piece2[k][i][j].toString();
                }
            }
        }
        s += "\n";

        return s;
    }
}
