package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class SaveScore extends JFrame{
    private LinkedList<Score> highScores;
    private int newScore;
    private ControlPanel controlPanel;
    public SaveScore(int newScore){
        this.newScore = newScore;

    }
    private LinkedList<Score> getHighScores(){
        return new LinkedList<Score>();
    }
    private void addScore(Score s){

    }

    private class ControlPanel extends JPanel{
        private JButton save;
        private JTextField nameInput;
        private JTextArea newScoreDisplay;
        private Score s;
        public ControlPanel(){
            this.setBackground(Color.BLACK);
            save = new JButton("Save");
            save.setBackground(Color.BLACK);
            save.setForeground(Color.CYAN);
            save.setPreferredSize(new Dimension(75,20));
            save.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            System.out.println("Starting game");
                                s = new Score(newScore, nameInput.getText());

                                dispose();
                        }
                    }
            );

        }
    }
}

