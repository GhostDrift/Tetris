package Tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

//***************************** Testing Mode *****************************//

public class GameGUI extends JFrame {
    // -- set the size of the JFrame. JPanels will adapt to this size
//    private final int WIDTH = 296;
//    private final int HEIGHT = 550;
    private final int WIDTH = 293;
    private final int HEIGHT = 545;
    private final static int gameId = 1;
    private final static int test = 3;

    private Timer gameTimer = null;
    protected static int n;
    protected static int m;
    protected static boolean playing = true;

    //graphics display areas
    private PlayArea playArea;
    private NextPieceDisplay nextPieceDisplay;

    //control panel
    private ControlPanelInner controlPanel;

    //2D array for mapping game board
    private Square[][] gameBoard = new Square[10][30];
    private Square[][] nextPieceMap = new Square[4][4];

    //piece color variables
    private Color backgroundColor = Color.black;
    private Color nextColor = Color.CYAN;
    private Color[] colors = new Color[8]; //and array to hold all of the colors for the squares on the board.
    //    private int colorIndex = 0;
//    private final Color purple = new Color(100,0,150);
    // piece variable for game play
    private final Maps maps = new Maps(gameId);
    private Piece np;
    private Piece p;

    //variable to hold the score
    private int score =0;

    //variable to hold the size of the screen
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenHeight = screenSize.height;
    private int screenWidth = screenSize.width;
    public GameGUI()
    {
        System.out.println("Screen height: " + screenHeight + "\nScreen width: " + screenWidth);
        //construct the bast jFrame first
        // this is implied super();

        // -- set the application title
        populateColors();
        setTitle("Block Buster");

        // -- size of the frame: width, height
        setSize(WIDTH, HEIGHT);

        // -- center the frame on the screen
        setLocationRelativeTo(null);

        // -- shut down the entire application when the frame is closed
        //    if you don't include this the application will continue to
        //    run in the background and you'll have to kill it by pressing
        //    the red square in eclipse
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the window a fixed size
//        setResizable(false);



        // -- set the layout manager and add items
        //    5, 5 is the border around the edges of the areas
        //setLayout(new BorderLayout(10, 10));
        setLayout(new BorderLayout(0,0));

        this.setResizable(false);

        //populate the 2D array that will be used to map the game board
        int xValue = 1;
        int yValue = 0;
//        int xIncrease = 18;
//        int yIncrease = 17;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 30; j++){
                gameBoard[i][j] = new Square(xValue, yValue);
                yValue += 17;
            }
            yValue = 0;
            xValue += 18;
        }
        //populate the 2D array that will be used to map the next piece display
        xValue = 14;
        yValue =17;
        for(int i = 0; i<4; i++){
            for(int j = 0; j< 4; j++){
                nextPieceMap[i][j] = new Square(xValue, yValue);
                yValue += 17;
            }
            yValue = 17;
            xValue += 19;
        }



        // -- construct a JPanel for graphics
        playArea = new PlayArea();
        this.add(playArea, BorderLayout.CENTER);

        // -- construct a JPanel for controls
        controlPanel = new ControlPanelInner();
        this.add(controlPanel, BorderLayout.WEST);

        // -- Timer will generate an event every 10mSec once it is started
        //    First parameter is the delay in mSec, second is the ActionListener
        //    that will handle the timer events
        final Random rn = new Random();
//        p = new Piece(rn.nextInt(7),maps,gameId);
//        np = new Piece(rn.nextInt(7),maps,gameId);
        p = new Piece(test,maps,gameId);
        np = new Piece(test,maps,gameId);
        gameTimer = new Timer(400,
                // -- ActionListener for the timer event
                // and example of real time programming
                // events occur at arbitrary times
                // and our program must be prepaired to deal with them
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
//                        System.out.println(p.getActive());
                        if(!p.getActive()){
                            score +=checkLines(gameBoard);
                            controlPanel.upDateScore(score);
                            p = addPiece(gameBoard, np, gameTimer, score);
                            p.setActive(true);
                            np = getNextPiece(nextPieceMap, maps);
                        }
                        else {
                            moveDown(gameBoard,p);
                        }
                        playArea.repaint();
                        nextPieceDisplay.repaint();
                    }
                }
        );

        // -- paint the graphics canvas before the initial display
        playArea.repaint();


        // -- show the frame on the screen
        this.setVisible(true);

        // -- set keyboard focus to the graphics panel
        playArea.setFocusable(true);
        playArea.requestFocus();

    }

    //clears the next piece panel and retrieves a new Piece and paints it to the next piece panel
    //also returns the new piece
    private static Piece getNextPiece(Square[][] nextPieceMap, Maps maps){
        Random rn = new Random();
        int n = rn.nextInt(7);
//        System.out.println("n = " + n);
        Piece np = new Piece(test,maps,gameId);
//        Piece np = new Piece(n,maps,gameId);
        //clear the next piece panel
        for(int i = 0; i< 4; i++){
            for(int j = 0; j< 4; j++){
                nextPieceMap[i][j].setColored(false);
            }
        }
        //adds a piece to the next piece display
        Square[][] pieceMap = np.getMap();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j< 4; j++){
                nextPieceMap[i][j].setColored(pieceMap[i][j].getColored());
                nextPieceMap[i][j].setColor(pieceMap[i][j].getColor());
            }
        }
        //make sure to call the repaint function after calling this method.
        return np;
    }

    //adds a new piece to the game board
    private Piece addPiece(Square[][] gameBoard, Piece p, Timer gameTimer, int score){
        //add the piece to the game board
        Square[][] pieceMap = p.getMap();
        p.setActive(true);
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Square s = pieceMap[i][j];
//                    System.out.println("X: " + s.getX() + " Y: " + s.getY());
                    if (s.getColored()) {
                        if(gameBoard[s.getX()][s.getY()].getColored()){
                            throw new Exception();
                        }
                        gameBoard[s.getX()][s.getY()].setColored(pieceMap[i][j].getColored());
                        gameBoard[s.getX()][s.getY()].setColor(pieceMap[i][j].getColor());
                    }
                }
            }
        } catch (Exception e) {
            gameTimer.stop();
            dispose();
            new SaveScore(score);
//            gameOver(score);
        }
        return p;
    }
    //stops the timer and plays the end animation
    //not working yet
//    private static void gameOver(int score){
//        System.out.println("ending the game");
//
//    }
    //moves the piece down one square
    private static void moveDown(Square[][] gameBoard, Piece p){
        Square[][] pieceMap = p.getMap();
//        System.out.println(p.moveableDown(gameBoard));
        if(p.moveableDown(gameBoard)){
            for(int i = 0; i< 4; i++){
                for (int j = 0; j<4; j++){
                    Square s = pieceMap[i][j];
                    if(s.getColored()){
                        gameBoard[s.getX()][s.getY()].setColored(false);
                    }
                }
            }
            for(int i =0; i< 4; i++){
                for(int j = 0; j<4; j++){
                    Square s = pieceMap[i][j];
                    s.setY(s.getY()+1);
                    if(s.getColored()){
                        gameBoard[s.getX()][s.getY()].setColored(true);
                        gameBoard[s.getX()][s.getY()].setColor(s.getColor());
                    }
                }
            }
        }
        else {
            p.setActive(false);
        }
    }

    //moves piece one to the left
    private static void moveLeft(Square[][] gameBoard, Piece p){
        if(p.movableLeft(gameBoard)){
            Square[][] pieceMap = p.getMap();
            for(int i = 0; i< 4; i++){
                for (int j = 0; j<4; j++){
                    Square s = pieceMap[i][j];
                    if(s.getColored()){
                        gameBoard[s.getX()][s.getY()].setColored(false);
                    }
                }
            }
            for(int i =0; i< 4; i++){
                for(int j = 0; j<4; j++){
                    Square s = pieceMap[i][j];
                    s.setX(s.getX()-1);
                    if(s.getColored()){
                        gameBoard[s.getX()][s.getY()].setColored(true);
                        gameBoard[s.getX()][s.getY()].setColor(s.getColor());
                    }
                }
            }
        }
        if (p.moveableDown(gameBoard)){
            p.setActive(true);
        }
//        moveDown(gameBoard,p);
    }
    //moves the piece one to the right
    private static void moveRight(Square[][] gameBoard, Piece p){
        if(p.movableRight(gameBoard)){
            Square[][] pieceMap = p.getMap();
            for(int i = 0; i< 4; i++){
                for (int j = 0; j<4; j++){
                    Square s = pieceMap[i][j];
                    if(s.getColored()){
                        gameBoard[s.getX()][s.getY()].setColored(false);
                    }
                }
            }
            for(int i =0; i< 4; i++){
                for(int j = 0; j<4; j++){
                    Square s = pieceMap[i][j];
                    s.setX(s.getX()+1);
                    if(s.getColored()){
                        gameBoard[s.getX()][s.getY()].setColored(true);
                        gameBoard[s.getX()][s.getY()].setColor(s.getColor());
                    }
                }
            }
        }
        if (p.moveableDown(gameBoard)){
            p.setActive(true);
        }
//        moveDown(gameBoard,p);
    }
    //checks to see if a line was cleared
    private static int checkLines(Square[][] gameBoard){
//        System.out.println("Checking to see if line needs to be cleared");
        int linesCleared = 0;
        int columnsChecked;
        boolean clearLine;
        for(int j = 29; j >=0; j--){
            columnsChecked = 0;
            clearLine = true;
            while(columnsChecked < 10 && clearLine){
                if(!gameBoard[columnsChecked][j].getColored()){
                    clearLine = false;
                }
                columnsChecked++;
            }
            if(clearLine){
                clearLine(gameBoard, j);
                linesCleared++;
                if(j != 0){
                    linesCleared += shiftDown(gameBoard,j);
                }
            }
        }
        return linesCleared;
    }
    //clears the line that needs to be cleared
    private static void clearLine(Square[][] gameBoard, int rowCleared){
//        System.out.println("clear row " + rowCleared);
        for(int i = 0; i<10; i++){
            gameBoard[i][rowCleared].setColored(false);
        }
    }
    //shifts the board down one
    private static int shiftDown(Square[][] gameBoard, int rowCleared){
        Square s;
        for(int j =rowCleared; j >0; j-- ){
            for(int i = 0; i<10; i++){
                s = gameBoard[i][j];
                s.setColored(gameBoard[i][j-1].getColored());
                s.setColor((gameBoard[i][j-1].getColor()));
            }
        }

        return checkLines(gameBoard);
    }
    //populates the array with colors
    private void populateColors(){
        this.colors[0] = Color.BLACK;
        this.colors[1] = Color.CYAN;
        this.colors[2] = new Color(0,50,160);
        this.colors[3] = new Color(200,100,25);
        this.colors[4] = new Color(0,150,25);
        this.colors[5] = new Color(100,0,150);
        this.colors[6] = new Color(200,0,40);
        this.colors[7] = new Color(237,245,14);

    }

    // -- Inner class for the graphics panel
    public class PlayArea extends JPanel {
        private Color color;
        public PlayArea()
        {
            super();
            this.color = colors[1];
            this.setBackground(colors[0]);
            this.prepareActionHandlers();

        }


        // -- prepare the controls and their associated action listeners
        private void prepareActionHandlers()
        {
            // -- keyboard listener
            //    note that the JPanel must have focus for these to
            //    generate events. You can click the mouse in the JPanel
            //    or call graphicsPanel.requestFocus();
            this.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent event) {

                }

                @Override
                public void keyPressed(KeyEvent event) {
                    if(event.getKeyCode()== 37){
                        //moves the piece left if it is not blocked
                        moveLeft(gameBoard, p);
                        playArea.repaint();
                    }
                    else if(event.getKeyCode()== 38){
                        if(p.canRotate(gameBoard,maps)){
                            p.rotate(maps, gameBoard);
                        }
                    }
                    else if(event.getKeyCode()== 39){
                        //moves the piece right if it is not blocked
                        moveRight(gameBoard,p);
                    }
                    else if(event.getKeyCode()== 40){
                        //moves the piece down if it is not blocked
                        while (p.getActive()){
                            moveDown(gameBoard,p);
                        }
                        playArea.repaint();
                    }
                }

                @Override
                public void keyReleased(KeyEvent event) {

                }

            });

        }


        // -- this override sets the desired size of the JPanel which is
        //    used by some layout managers -- default desired size is 0,0
        //    which is, in general, not good -- will pull from layout manager
        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(25, 60);
        }

        // -- this override is where all the painting should be done.
        //    DO NOT call it directly. Rather, call repaint() and let the
        //    event handling system decide when to call it
        //    DO NOT put graphics function calls elsewhere in the code, although
        //    legal, it's bad practice and could destroy the integrity of the
        //    display
        //    This function is used for all "permanent" painting
        @Override
        public void paint(Graphics g)
        {
            // -- the base class paintComponent(g) method ensures
            //    the drawing area will be cleared properly. Do not
            //    modify any attributes of g prior to sending it to
            //    the base class
            super.paintComponent(g);

            // -- for legacy reasons the parameter comes in as type Graphics
            //    but it is really a Graphics2D object. Cast it up since the
            //    Graphics2D class is more capable
            Graphics2D graphicsContext = (Graphics2D)g;
            int height = this.getHeight();
            int width = this.getWidth();


            // -- overlay a grid to fill the entire space evenly
            //draws vertical grid lines
            int horzs = 10;
            double horzspacing = width / (double)horzs;
            double x0 = 0.0;
            graphicsContext.setColor(Color.darkGray);
            for (int i = 0; i < horzs; ++i) {
                graphicsContext.drawLine((int)x0, 0, (int)x0, height);
                x0 += horzspacing;
            }
            //draws horizontal grid Lines
            int verts = 30;
            double vertspacing = height / (double)verts;
            double y0 = 0.0;
            graphicsContext.setColor(Color.darkGray);
            for (int i = 0; i < verts; ++i) {
                graphicsContext.drawLine(0, (int)y0, width, (int)y0);
                y0 += vertspacing;
            }

//
//
            graphicsContext.setColor(color);
            //test
//            Square s;
//            for(int i = 0; i < 8; i++){
//                graphicsContext.setColor(colors[i]);
//                for (int j = 0; j < 30; j++){
//                        s = gameBoard[i][j];
//                        graphicsContext.fillRect(s.getX(), s.getY(), 16, 16);
//                    }
//                }

//            graphicsContext.fillRect(73,119,17,17);
//            graphicsContext.fillRect(1, 34, 16, 16);
//            graphicsContext.fillRect(1, 17, 16, 16);
//            graphicsContext.fillRect(19, 0, 16, 16);
            Square s;
            for(int i = 0; i < 10; i++){
                for (int j = 0; j < 30; j++){
                    if(gameBoard[i][j].getColored()){
                        s = gameBoard[i][j];
                        graphicsContext.setColor(colors[s.getColor()]);
                        graphicsContext.fillRect(s.getX(), s.getY(), 16, 16);
                    }
                }
            }

        }

    }

    // -- Inner class for control panel
    public class ControlPanelInner extends JPanel {
        //buttons for control pannel
//        private JButton saveButton;
//        private JButton loadButton;
        private JButton pausePlay;

        //Labels for control pannel
        private JLabel scoreLabel;
        private JLabel nextPieceLabel;


        private JTextField score;
        private String currentScore = "0";



        public ControlPanelInner ()
        {
            // -- set up buttons
            prepareButtonHandlers();

            // -- set the layout manager
            //    this will determine how items are added to the JPanel
            //setLayout(new GridLayout(10, 1, 2, 2));
            setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));

            setBackground(Color.BLACK);

            // -- construct the JTextField, 5 characters wide
            score = new JTextField(currentScore, 5);
            score.setHorizontalAlignment(JTextField.CENTER);    //centers the text in the text field.
            score.setEditable(false);
            score.setBackground(colors[0]);
            score.setForeground(colors[1]);


            //construct the labels for the control panel
            scoreLabel = new JLabel("Score");
            scoreLabel.setForeground(colors[5]);
            nextPieceLabel = new JLabel("Next Piece");
            nextPieceLabel.setForeground(colors[5]);

            //constructs a graphics panel to display the next piece
            nextPieceDisplay = new NextPieceDisplay();
            nextPieceDisplay.repaint();

            // -- add items to the JPanel in order (FlowLayout)

            this.add(pausePlay);
            this.add(scoreLabel);
            this.add(score);
            this.add(nextPieceLabel);
            this.add(nextPieceDisplay);
//            this.add(loadButton);
//            this.add(saveButton);

            setBorder( new EmptyBorder(0,20,0,5));





        }
        public void upDateScore(int currentScore){
            this.currentScore = "" +currentScore;
            score.setText(this.currentScore);
        }

        private void prepareButtonHandlers()
        {
            // -- Construct the JButtons and their associated event handlers

            pausePlay = new JButton("GO");
            pausePlay.setPreferredSize(new Dimension(75,20));
            pausePlay.setBackground(colors[0]);
            pausePlay.setForeground(colors[1]);
            pausePlay.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            if(pausePlay.getText().equals("GO")){
                                pausePlay.setText("PAUSE");
                                gameTimer.start();
                                playArea.requestFocus();
                            }
                            else if(pausePlay.getText().equals("PAUSE")){
                                pausePlay.setText("GO");
                                gameTimer.stop();

                            }
                            //send focus back to the graphics panel

                        }
                    }
            );
//            saveButton = new JButton("Save");
//            saveButton.setBackground(Color.black);
//            saveButton.setForeground(Color.CYAN);
//            saveButton.addActionListener(
//                    new ActionListener() {
//                        public void actionPerformed(ActionEvent arg0) {
//                            JFileChooser jfc = new JFileChooser();
//                            if (jfc.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION) {
//                                System.out.println(jfc.getSelectedFile().getName());
//                            }
//                            // -- send focus back to the graphicsPanel
//                            playArea.requestFocus();
//                        }
//                    }
//            );
//
//            loadButton = new JButton("Load");
//            loadButton.setBackground(Color.black);
//            loadButton.setForeground(Color.CYAN);
//            loadButton.addActionListener(
//                    new ActionListener() {
//                        public void actionPerformed(ActionEvent arg0) {
//                            JFileChooser jfc = new JFileChooser();
//                            if (jfc.showDialog(null, "Load") == JFileChooser.APPROVE_OPTION) {
//                                System.out.println(jfc.getSelectedFile().getName());
//                            }
//                            // -- send focus back to the graphicsPanel
//                            playArea.requestFocus();
//                        }
//                    }
//            );

        }

        public Dimension getPreferredSize()
        {
            return new Dimension(100, 500);
        }

    }

    //Inner class for next piece display
    private class NextPieceDisplay extends JPanel {
        public NextPieceDisplay() {
            super();
            this.setBackground(Color.BLACK);
        }

        //sets the size of the window
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 100);
        }

        @Override
        public void paint(Graphics g) {
            // -- the base class paintComponent(g) method ensures
            //    the drawing area will be cleared properly. Do not
            //    modify any attributes of g prior to sending it to
            //    the base class
            super.paintComponent(g);

            // -- for legacy reasons the parameter comes in as type Graphics
            //    but it is really a Graphics2D object. Cast it up since the
            //    Graphics2D class is more capable
            Graphics2D graphicsContext = (Graphics2D) g;
            int height = this.getHeight();
            int width = this.getWidth();


            //creates a colored square in the middle of the screen
//           graphicsContext.setColor(colors[1]);
//            graphicsContext.fillRect(37, 39, 16, 16);
            //fill in the display
            Square s;
            for(int i = 0; i<4; i++){
                for (int j = 0; j<4; j++){
                    if(nextPieceMap[i][j].getColored()){
                        s = nextPieceMap[i][j];
                        graphicsContext.setColor(colors[s.getColor()]);
                        graphicsContext.fillRect(s.getX(), s.getY(), 17, 16);
                    }
                }
            }

        }
    }

    public static void main (String[] args)
    {

        // -- can run as an anonymous object since Swing
        //    is multi-threaded (the main function can terminate
        //    its thread while the Swing thread continues on)
        //-- the object we create is on stack but it has no stack refrence
        //  thus we call it "anonymous"
        new GameGUI();
        // this line of Code demonstrates the fact that the main function terminates and the main code still runs
        System.out.println("Main Thread Terminating");
    }

}