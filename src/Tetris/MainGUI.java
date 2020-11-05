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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;


public class MainGUI extends JFrame {
    // -- set the size of the JFrame. JPanels will adapt to this size
    private final int WIDTH = 512;
    private final int HEIGHT = 450;

    private Timer animationTimer = null;

    private GraphicPanelInner graphicsPanel;
    private ControlPanelInner controlPanel;

    public MainGUI ()
    {
        //construct the bast jFrame first
        // this is implied super();

        // -- set the application title
        setTitle("Java Swing Application");

        // -- size of the frame: width, height
        setSize(WIDTH, HEIGHT);

        // -- center the frame on the screen
        setLocationRelativeTo(null);

        // -- shut down the entire application when the frame is closed
        //    if you don't include this the application will continue to
        //    run in the background and you'll have to kill it by pressing
        //    the red square in eclipse
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -- set the layout manager and add items
        //    5, 5 is the border around the edges of the areas
        setLayout(new BorderLayout(5, 5));

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
        this.setVisible(false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setVisible(true);

        // -- set keyboard focus to the graphics panel
        graphicsPanel.setFocusable(true);
        graphicsPanel.requestFocus();

    }


    // -- Inner class for the graphics panel
    public class GraphicPanelInner extends JPanel implements MouseMotionListener {

        public GraphicPanelInner ()
        {
            super();
            this.setBackground(Color.white);
            this.prepareActionHandlers();

            this.addMouseMotionListener(this);
        }

        // -- prepare the controls and their associated action listeners
        private void prepareActionHandlers()
        {
            // -- The JPanel can have a mouse listener if desired
            this.addMouseListener(new MouseListener() {

                                      @Override
                                      public void mouseClicked(MouseEvent event) {
                                          System.out.println("Mouse Clicked at (" + event.getX() + ", " + event.getY() + ")");
                                      }

                                      @Override
                                      public void mouseEntered(MouseEvent event) {
                                          System.out.println("Mouse Entered at (" + event.getX() + ", " + event.getY() + ")");
                                      }

                                      @Override
                                      public void mouseExited(MouseEvent event) {
                                          System.out.println("Mouse Exited at (" + event.getX() + ", " + event.getY() + ")");
                                      }

                                      @Override
                                      public void mousePressed(MouseEvent event) {
                                          // -- BUTTON1 is the left, BUTTON3 is the right
                                          if (event.getButton() == MouseEvent.BUTTON1) {
                                              System.out.println("Left button pressed");
                                          }
                                          else if (event.getButton() == MouseEvent.BUTTON3) {
                                              System.out.println("Right button pressed");
                                          }
                                          graphicsPanel.requestFocus();
                                      }

                                      @Override
                                      public void mouseReleased(MouseEvent event) {
                                          // -- BUTTON1 is the left, BUTTON3 is the right
                                          if (event.getButton() == MouseEvent.BUTTON1) {
                                              System.out.println("Left button released");
                                          }
                                          else if (event.getButton() == MouseEvent.BUTTON3) {
                                              System.out.println("Right button released");
                                          }
                                          graphicsPanel.requestFocus();
                                          repaint();
                                      }
                                  }
            );

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

        // -- Mouse motion event handlers
        @Override
        public void mouseDragged(MouseEvent event) {
            System.out.println("Mouse dragged to (" + event.getX() + ", " + event.getY() + ")");
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            System.out.println("Mouse moved to (" + event.getX() + ", " + event.getY() + ")");
        }

        // -- this override sets the desired size of the JPanel which is
        //    used by some layout managers -- default desired size is 0,0
        //    which is, in general, not good -- will pull from layout manager
        public Dimension getPreferredSize()
        {
            return new Dimension(50, 50);
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

            // -- draw an image of random color on the panel
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    int pixel =	((int)(Math.random() * 255) << 16) | ((int)(Math.random() * 255) << 8) | ((int)(Math.random() * 255));
                    bi.setRGB(j, i, pixel);
                }
            }
            graphicsContext.drawImage(bi, 0, 0, this.getWidth(), this.getHeight(), null);

            // -- overlay a grid to fill the entire space evenly
            int horzs = 10;
            double horzspacing = width / (double)horzs;
            double x0 = 0.0;
            graphicsContext.setColor(Color.GREEN);
            for (int i = 0; i < horzs; ++i) {
                graphicsContext.drawLine((int)x0, 0, (int)x0, height);
                x0 += horzspacing;
            }

            int verts = 10;
            double vertspacing = height / (double)verts;
            double y0 = 0.0;
            graphicsContext.setColor(Color.BLUE);
            for (int i = 0; i < verts; ++i) {
                graphicsContext.drawLine(0, (int)y0, width, (int)y0);
                y0 += vertspacing;
            }

            // -- draw a red ellipse in the center of the graphics area
            graphicsContext.setColor(Color.RED);
            graphicsContext.fillOval(width / 2 - 25, height / 2 - 25, 50, 50);
        }

    }

    // -- Inner class for control panel
    public class ControlPanelInner extends JPanel {

        private JButton readtextfieldButton;
        private JButton saveButton;
        private JButton loadButton;
        private JButton timerOnButton;
        private JButton timerOffButton;
        private JButton textareaButton;

        private JTextField textField;
        private JTextArea textArea;
        private JScrollPane scrollableTextArea;

        public ControlPanelInner ()
        {
            // -- set up buttons
            prepareButtonHandlers();

            // -- set the layout manager
            //    this will determine how items are added to the JPanel
            //setLayout(new GridLayout(10, 1, 2, 2));
            setLayout(new FlowLayout());


            // -- construct the JTextField, 5 characters wide
            textField = new JTextField("Default", 5);

            // -- add items to the JPanel in order (FlowLayout)
            this.add(readtextfieldButton);
            this.add(textField);
            this.add(textareaButton);
            this.add(loadButton);
            this.add(saveButton);
            this.add(timerOnButton);
            this.add(timerOffButton);

            // -- add a JTextArea with scroll bars, 7 rows, 5 columns
            //    scrollbar areas will show as soon as the JScrollPane
            //    is constructed. If you remove the calls to setHorizontalScrollBarPolicy
            //    and setVerticalScrollBarPolicy the scrollbars will only show
            //    when needed
            textArea = new JTextArea(7, 5);
            scrollableTextArea = new JScrollPane(textArea);
            scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            this.add(scrollableTextArea);

        }

        private void prepareButtonHandlers()
        {
            // -- Construct the JButtons and their associated event handlers
            readtextfieldButton = new JButton("Read Text");
            readtextfieldButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            System.out.println(textField.getText());
                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );

            textareaButton = new JButton("Set Text");
            textareaButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            for (int i = 0; i < 10; ++i) {
                                textArea.append("String " + i + "\n");
                                // -- send focus back to the graphicsPanel
                                graphicsPanel.requestFocus();
                            }
                        }
                    }
            );

            timerOnButton = new JButton("Timer on");
            timerOnButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            animationTimer.start();
                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );
            timerOffButton = new JButton("Timer off");
            timerOffButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            animationTimer.stop();
                            // -- send focus back to the graphicsPanel
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
