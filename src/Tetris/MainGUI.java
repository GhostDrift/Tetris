package Tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class MainGUI extends JFrame {
    // -- set the size of the JFrame. JPanels will adapt to this size
    private final int WIDTH = 300;
    private final int HEIGHT = 530;

    private Timer animationTimer = null;

    private GraphicPanelInner graphicsPanel;
    private ControlPanelInner controlPanel;

    public MainGUI ()
    {
        //construct the bast jFrame first
        // this is implied super();

        // -- set the application title
        setTitle("Tetris");

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
        setResizable(false);

        // -- set the layout manager and add items
        //    5, 5 is the border around the edges of the areas
        setLayout(new BorderLayout(10, 10));

        // -- construct a JPanel for graphics
        graphicsPanel = new GraphicPanelInner();
        this.add(graphicsPanel, BorderLayout.CENTER);

        // -- construct a JPanel for controls
        controlPanel = new ControlPanelInner();
        this.add(controlPanel, BorderLayout.WEST);

        // -- Timer will generate an event every 10mSec once it is started
        //    First parameter is the delay in mSec, second is the ActionListener
        //    that will handle the timer events
        animationTimer = new Timer(10,
                // -- ActionListener for the timer event
                // and example of real time programming
                // events occur at arbitrary times
                // and our program must be prepaired to deal with them
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("tic");
                    }
                }
        );

        // -- paint the graphics canvas before the initial display
        graphicsPanel.repaint();

        // -- show the frame on the screen
        this.setVisible(true);

        // -- set keyboard focus to the graphics panel
        graphicsPanel.setFocusable(true);
        graphicsPanel.requestFocus();

    }


    // -- Inner class for the graphics panel
    public class GraphicPanelInner extends JPanel {

        public GraphicPanelInner ()
        {
            super();
            this.setBackground(Color.white);
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
                    System.out.println("key typed: " + event.getKeyCode());
                }

                @Override
                public void keyPressed(KeyEvent event) {
                    System.out.println("key pressed: " + event.getKeyCode());
                    graphicsPanel.repaint();
                }

                @Override
                public void keyReleased(KeyEvent event) {
                    System.out.println("key released: " + event.getKeyCode());
                    graphicsPanel.repaint();
                }

            });
        }


        // -- this override sets the desired size of the JPanel which is
        //    used by some layout managers -- default desired size is 0,0
        //    which is, in general, not good -- will pull from layout manager
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
            graphicsContext.setColor(Color.BLACK);
            for (int i = 0; i < horzs; ++i) {
                graphicsContext.drawLine((int)x0, 0, (int)x0, height);
                x0 += horzspacing;
            }
            //draws horizontal grid Lines
            int verts = 30;
            double vertspacing = height / (double)verts;
            double y0 = 0.0;
            graphicsContext.setColor(Color.BLACK);
            for (int i = 0; i < verts; ++i) {
                graphicsContext.drawLine(0, (int)y0, width, (int)y0);
                y0 += vertspacing;
            }

        }

    }

    // -- Inner class for control panel
    public class ControlPanelInner extends JPanel {
        //buttons for control pannel
        private JButton saveButton;
        private JButton loadButton;
        private JButton pausePlay;

        //Labels for control pannel
        private JLabel scoreLabel;
        private JLabel nextPieceLabel;


        private JTextField score;
        private JTextArea textArea;
        private JScrollPane nextPiece;

        public ControlPanelInner ()
        {
            // -- set up buttons
            prepareButtonHandlers();

            // -- set the layout manager
            //    this will determine how items are added to the JPanel
            //setLayout(new GridLayout(10, 1, 2, 2));
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));


            // -- construct the JTextField, 5 characters wide
            score = new JTextField("0", 5);
            score.setHorizontalAlignment(JTextField.CENTER);    //centers the text in the text field.
            score.setEditable(false);

            //construct the labels for the control panel
            scoreLabel = new JLabel("Score");
            nextPieceLabel = new JLabel("Next Piece");

            // -- create a JTextArea with scroll bars, 7 rows, 5 columns
            //    scrollbar areas will show as soon as the JScrollPane
            //    is constructed. If you remove the calls to setHorizontalScrollBarPolicy
            //    and setVerticalScrollBarPolicy the scrollbars will only show
            //    when needed
            textArea = new JTextArea(6, 8);
            nextPiece = new JScrollPane(textArea);

            // -- add items to the JPanel in order (FlowLayout)

            this.add(pausePlay);
            this.add(scoreLabel);
            this.add(score);
            this.add(nextPieceLabel);
            this.add(nextPiece);
            this.add(loadButton);
            this.add(saveButton);





        }

        private void prepareButtonHandlers()
        {
            // -- Construct the JButtons and their associated event handlers

            pausePlay = new JButton("GO");
            pausePlay.setPreferredSize(new Dimension(75,20));
            pausePlay.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            if(pausePlay.getText().equals("GO")){
                                pausePlay.setText("PAUSE");
                            }
                            else if(pausePlay.getText().equals("PAUSE")){
                                pausePlay.setText("GO");
                            }
                            //send focus back to the graphics panel
                            graphicsPanel.requestFocus();
                        }
                    }
            );
            saveButton = new JButton("Save");
            saveButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            JFileChooser jfc = new JFileChooser();
                            if (jfc.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION) {
                                System.out.println(jfc.getSelectedFile().getName());
                            }
                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );

            loadButton = new JButton("Load");
            loadButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            JFileChooser jfc = new JFileChooser();
                            if (jfc.showDialog(null, "Load") == JFileChooser.APPROVE_OPTION) {
                                System.out.println(jfc.getSelectedFile().getName());
                            }
                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );

        }

        public Dimension getPreferredSize()
        {
            return new Dimension(100, 500);
        }

    }

    public static void main (String[] args)
    {

        // -- can run as an anonymous object since Swing
        //    is multi-threaded (the main function can terminate
        //    its thread while the Swing thread continues on)
        //-- the object we create is on stack but it has no stack refrence
        //  thus we call it "anonymous"
        new MainGUI();
        // this line of Code demonstrates the fact that the main function terminates and the main code still runs
        System.out.println("Main Thread Terminating");
    }
}
