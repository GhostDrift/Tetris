package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    //    private final int HEIGHT = 550;
//    private final int WIDTH = 900;
    private final int WIDTH = 296;
    private final int HEIGHT = 325;
    private final Color background = new Color(50,0,100);
    private controlPanel control;
    private MainGUI mainGame;
    private JButton start;
    public Menu(){
        setTitle("Block Buster");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(0,0));
        setBackground(background);
        this.start = new JButton("Start");
        this.start.setPreferredSize(new Dimension(75,20));
        this.start.setBackground(Color.black);
        this.start.setForeground(Color.CYAN);
        this.start.setActionCommand("open");
        this.start.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("Starting game");
                        String cmd = arg0.getActionCommand();
                        if(cmd.equals("open")){
                            dispose();
                            mainGame = new MainGUI();
                        }
                    }
                }
        );
        this.control = new controlPanel(start);
        this.add(control);
        setVisible(true);

    }
    private class controlPanel extends JPanel{
        private JButton start;
        public controlPanel(JButton start){
            this.start = start;
            setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
            this.add(start);

        }
    }
    public static void main(String[] args) {
        Menu startUP = new Menu();
    }
}


