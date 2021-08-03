package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Menu extends JFrame {
    //    private final int HEIGHT = 550;
//    private final int WIDTH = 900;
    private final int WIDTH = 296;
    private final int HEIGHT = 450;
    private final Color background = new Color(50,0,100);
    private controlPanel control;
    private GameGUI mainGame;
    private JButton startClassic;
    private JButton startBlockBuster;
    private ArrayList<Score> highScoresList;
    private File highScores = new File("HighScores0.ser");
    public Menu(){
        setTitle("Block Buster");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(0,0));
        setBackground(background);
        this.startBlockBuster = new JButton("BlockBuster");
        this.startBlockBuster.setPreferredSize(new Dimension(200,20));
        this.startBlockBuster.setBackground(Color.black);
        this.startBlockBuster.setForeground(Color.CYAN);
        this.startBlockBuster.setActionCommand("open");
        this.startBlockBuster.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("Starting game");
                        String cmd = arg0.getActionCommand();
                        if(cmd.equals("open")){
                            dispose();
                            mainGame = new GameGUI(0);
                        }
                    }
                }
        );
        this.startClassic = new JButton("Classic Tetris");
        this.startClassic.setPreferredSize(new Dimension(200,20));
        this.startClassic.setBackground(Color.black);
        this.startClassic.setForeground(Color.CYAN);
        this.startClassic.setActionCommand("open");
        this.startClassic.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("Starting game");
                        String cmd = arg0.getActionCommand();
                        if(cmd.equals("open")){
                            dispose();
                            mainGame = new GameGUI(1);
                        }
                    }
                }
        );
        this.startBlockBuster.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        this.startClassic.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        this.highScoresList = getHighScoresList();
        this.control = new controlPanel(startBlockBuster, startClassic);
        this.add(control, BorderLayout.CENTER);
        setVisible(true);

    }private ArrayList<Score> getHighScoresList(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(highScores));
            Object ob = in.readObject();
            in.close();
            if(ob instanceof ArrayList){
                return (ArrayList<Score>) ob;
            }


        } catch (IOException i) {
            // -- in case the file cannot be opened
            System.out.println("can't open file");
            return new ArrayList<Score>();
        } catch (ClassNotFoundException c) {
            // -- in case the Rectangle.class file cannot be found after
            //    reading the file
            System.out.println("Score class not found");
        }
        return new ArrayList<Score>();
    }
    private class controlPanel extends JPanel{
        private JButton startBlockBuster;
        private JButton startClassic;
        private JTextArea highScores;
        private JLabel scoresLable;
        private JLabel gameTitle;
        public controlPanel(JButton startBlockBuster, JButton startClassic){
            this.startBlockBuster = startBlockBuster;
            this.startClassic = startClassic;
            highScores = new JTextArea(5,5);
            highScores.setBackground(Color.BLACK);
            highScores.setForeground(Color.RED);
            highScores.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            highScores.setText(getHighScores());
            highScores.setEditable(false);
            scoresLable = new JLabel("High Scores");
            scoresLable.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            scoresLable.setForeground(Color.CYAN);
            setBackground(background);
            setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
            gameTitle = new JLabel("Block Buster");
            gameTitle.setForeground(Color.red);
            gameTitle.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            this.add(gameTitle);
            this.add(scoresLable);
            this.add(highScores);
            this.add(startBlockBuster);
            this.add(startClassic);

        }
        private String getHighScores(){
            String s = "";
            if(highScoresList.size() >= 5){
                for(int i = 0; i < 5; i++){
                    s += highScoresList.get(i).toString() + "\n";
                }
            }
            else {
                for (int i = 0; i<highScoresList.size(); i++) {
                    s += highScoresList.get(i).toString() + "\n";
                }
            }

            return s;
        }
        public Dimension getPreferredSize()
        {
            return new Dimension(296,325 );
        }

    }
    public static void main(String[] args) {
        Menu startUP = new Menu();
    }
}


