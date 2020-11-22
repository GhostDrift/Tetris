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
      s += "\n";
        return s;
    }
}
