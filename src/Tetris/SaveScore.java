package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

public class SaveScore extends JFrame{
    private LinkedList<Score> highScoresList;
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
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new BorderLayout());
        this.newScore = newScore;
        controlPanel = new ControlPanel();
        this.add(controlPanel, BorderLayout.CENTER);
        setForeground(Color.BLACK);
        this.setVisible(true);
    }
    private LinkedList<Score> getHighScoresList(){

        return new LinkedList<Score>();
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


    public static void main(String[] args) {
        new SaveScore(5);
    }
}

