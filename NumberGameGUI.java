import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGameGUI extends JFrame {
    private int targetNumber;
    private int attempts;
    private int maxAttempts = 5;

    private JLabel infoLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JButton newGameButton;

    public NumberGameGUI() {
        super("Number Guessing Game");
        setLayout(new FlowLayout());

        targetNumber = generateRandomNumber(1, 100);
        attempts = 0;

        infoLabel = new JLabel("Guess a number between 1 and 100:");
        add(infoLabel);

        guessField = new JTextField(10);
        add(guessField);

        guessButton = new JButton("Guess");
        add(guessButton);

        newGameButton = new JButton("New Game");
        add(newGameButton);
        newGameButton.setVisible(false);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                targetNumber = generateRandomNumber(1, 100);
                attempts = 0;
                infoLabel.setText("Guess a number between 1 and 100:");
                guessField.setText("");
                guessButton.setEnabled(true);
                newGameButton.setVisible(false);
            }
        });
    }

    private int generateRandomNumber(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }

    private void processGuess() {
        try{
        int userGuess = Integer.parseInt(guessField.getText());
        attempts++;

        if (userGuess == targetNumber) {
            infoLabel.setText("Congratulations! You guessed it in " + attempts + " attempts.");
            guessButton.setEnabled(false);
            newGameButton.setVisible(true);
        } else if (attempts >= maxAttempts) {
            infoLabel.setText("Out of attempts. The number was " + targetNumber + ".");
            guessButton.setEnabled(false);
            newGameButton.setVisible(true);
        } else if (userGuess < targetNumber) {
            infoLabel.setText("Try higher. Attempts: " + attempts);
        } else {
            infoLabel.setText("Try lower. Attempts: " + attempts);
        }

        guessField.setText("");
    }
    catch(Exception E){
        JOptionPane.showMessageDialog(this, "Please ensure Enter Number only not Character!",
        "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    public static void main(String[] args) {
        NumberGameGUI game = new NumberGameGUI();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(300, 150);
        game.setVisible(true);
    }
}
