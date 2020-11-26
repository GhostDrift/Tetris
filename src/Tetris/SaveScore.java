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
    private final int WIDTH = 296;
    private final int HEIGHT = 325;
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
        public ControlPanel(){
            this.setBackground(Color.BLACK);
            nameInput = new JTextField(10);
            nameInput.setHorizontalAlignment(JTextField.LEFT);
            nameInput.setBackground(Color.BLACK);
            nameInput.setForeground(Color.CYAN);
            newScoreDisplay = new JTextField("" + newScore, 10);
            scoreLable = new JLabel("Your Score");
            nameLable = new JLabel("Enter your first name or ininitials");
            save = new JButton("Save");
            save.setBackground(Color.BLACK);
            save.setForeground(Color.CYAN);
            save.setPreferredSize(new Dimension(WIDTH,HEIGHT));
            save.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            System.out.println("Starting game");
                                s = new Score(newScore, nameInput.getText());

                                dispose();
                        }
                    }
            );
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

