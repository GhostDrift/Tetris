package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;


public class SaveScore extends JFrame{
    private final ArrayList<Score> highScoresList;
    private final int newScore;
    private final File highScores = new File("HighScores.ser");
    private final Color purple = new Color(50,0,100);
    public SaveScore(int newScore){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int WIDTH = 400;
        int HEIGHT = 250;
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        setTitle("Block Buster");
        this.highScoresList = getHighScoresList();

        this.setLayout(new BorderLayout());
        this.newScore = newScore;
        ControlPanel controlPanel = new ControlPanel();
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
            return new ArrayList<>();
        } catch (ClassNotFoundException c) {
            // -- in case the Rectangle.class file cannot be found after
            //    reading the file
            System.out.println("Score class not found");
        }
        return new ArrayList<>();
    }
    private void addScore(Score s){

    }

    private class ControlPanel extends JPanel{
        private final JButton save;
        private final JTextField nameInput;
        private Score s;

        public ControlPanel(){
            this.setBackground(purple);
            this.setFocusable(true);
            JLabel gameOverLable = new JLabel("GAME OVER");
            gameOverLable.setForeground(Color.RED);
            gameOverLable.setBackground(Color.BLACK);
            gameOverLable.setFont(new Font("TimesRoman", Font.BOLD, 50));
            nameInput = new JTextField("Enter Initials here",20);
            nameInput.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            nameInput.setHorizontalAlignment(JTextField.LEFT);
            nameInput.setBackground(Color.BLACK);
            nameInput.setForeground(Color.CYAN);
            nameInput.setHorizontalAlignment(JLabel.CENTER);
            JTextField newScoreDisplay = new JTextField("" + newScore, 5);
            newScoreDisplay.setEditable(false);
            newScoreDisplay.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            newScoreDisplay.setForeground(Color.CYAN);
            newScoreDisplay.setBackground(Color.BLACK);
            newScoreDisplay.setHorizontalAlignment(JTextField.CENTER);
            JLabel scoreLable = new JLabel("Your Score");
            scoreLable.setForeground(Color.CYAN);
            scoreLable.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            JLabel nameLable = new JLabel("Enter your initials");
            nameLable.setForeground(Color.CYAN);
            nameLable.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            save = new JButton("Save");
            save.setBackground(Color.black);
            save.setForeground(Color.CYAN);
            save.setPreferredSize(new Dimension(200,25));
            save.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            save.setDefaultCapable(true);
            save.addActionListener(
                    arg0 -> {
                        System.out.println("Starting game");
                        if(nameInput.getText().equals("Enter Initials here")){
                            nameInput.setText("You must input your initials here");
                        }
                        else {

                            s = new Score(newScore, nameInput.getText());
                            addScoreToList(s);
//                                System.out.println(highScoresList);
                            new Menu();
                            dispose();
                        }
                    }
            );
            this.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent event) {
                    System.out.println("key typed: " + event.getKeyCode());
                    if(event.getKeyCode() == 0){
                        clickSave();
                    }
                }

                @Override
                public void keyPressed(KeyEvent event) {

                }

                @Override
                public void keyReleased(KeyEvent event) {

                }

            });
            this.add(gameOverLable);
            this.add(scoreLable);
            this.add(newScoreDisplay);
            this.add(nameLable);
            this.add(nameInput);
            this.add(save);
            this.requestFocus();
            System.out.println(save.isDefaultButton());

        }

        private void clickSave() {
            save.doClick();
        }

        public Dimension getPreferredSize()
        {
            return new Dimension(100, 500);
        }
    }
    private void addScoreToList(Score s){
        this.highScoresList.add(s);
//        System.out.println(highScoresList);
        highScoresList.sort((score, t1) -> Integer.compare(t1.getScore(), score.getScore()));
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(highScores));
            out.writeObject(this.highScoresList);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new SaveScore(0);
    }
}

