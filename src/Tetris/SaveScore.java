package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class SaveScore extends JFrame{
    private ArrayList<Score> highScoresList;
    private int newScore;
    private ControlPanel controlPanel;
    private File highScores = new File("HighScores.ser");
    private final int WIDTH = 400;
    private final int HEIGHT = 250;
    private final Color purple = new Color(50,0,100);
    public SaveScore(int newScore){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Block Buster");
        this.highScoresList = getHighScoresList();
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new BorderLayout());
        this.newScore = newScore;
        controlPanel = new ControlPanel();
        this.add(controlPanel, BorderLayout.CENTER);
        setForeground(Color.BLACK);
        this.setVisible(true);
    }
    private ArrayList<Score> getHighScoresList(){
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
    private void addScore(Score s){

    }

    private class ControlPanel extends JPanel{
        private JButton save;
        private JTextField nameInput;
        private JTextField newScoreDisplay;
        private Score s;
        private JLabel scoreLable;
        private JLabel nameLable;
        private JLabel gameOverLable;
        public ControlPanel(){
            this.setBackground(purple);
            gameOverLable = new JLabel("GAME OVER");
            gameOverLable.setForeground(Color.RED);
            gameOverLable.setBackground(Color.BLACK);
            gameOverLable.setFont(new Font("TimesRoman", Font.BOLD, 50));
            nameInput = new JTextField("Enter Name Here",20);
            nameInput.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            nameInput.setHorizontalAlignment(JTextField.LEFT);
            nameInput.setBackground(Color.BLACK);
            nameInput.setForeground(Color.CYAN);
            nameInput.setHorizontalAlignment(JLabel.CENTER);
            newScoreDisplay = new JTextField("" + newScore, 5);
            newScoreDisplay.setEditable(false);
            newScoreDisplay.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            newScoreDisplay.setForeground(Color.CYAN);
            newScoreDisplay.setBackground(Color.BLACK);
            newScoreDisplay.setHorizontalAlignment(JTextField.CENTER);
            scoreLable = new JLabel("Your Score");
            scoreLable.setForeground(Color.CYAN);
            scoreLable.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            nameLable = new JLabel("Enter your first name or initials");
            nameLable.setForeground(Color.CYAN);
            nameLable.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            save = new JButton("Save");
            save.setBackground(Color.black);
            save.setForeground(Color.CYAN);
            save.setPreferredSize(new Dimension(200,25));
            save.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            save.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            System.out.println("Starting game");
                                s = new Score(newScore, nameInput.getText());
                                addScoreToList(s);
//                                System.out.println(highScoresList);
                                new Menu();
                                dispose();
                        }
                    }
            );
            this.add(gameOverLable);
            this.add(scoreLable);
            this.add(newScoreDisplay);
            this.add(nameLable);
            this.add(nameInput);
            this.add(save);

        }
        public Dimension getPreferredSize()
        {
            return new Dimension(100, 500);
        }
    }
    private void addScoreToList(Score s){
        this.highScoresList.add(s);
//        System.out.println(highScoresList);
        highScoresList.sort(new Comparator<Score>() {
            @Override
            public int compare(Score score, Score t1) {
               return score.compareTo(t1);
            }
        });
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(highScores));
            out.writeObject(this.highScoresList);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new SaveScore(5);
    }
}

