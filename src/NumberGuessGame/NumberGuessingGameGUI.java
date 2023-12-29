package NumberGuessGame;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class NumberGuessingGameGUI extends JFrame {
    
    private final int lowerBound = 1;
    private final int upperBound = 100;
    private int randomNumber;
    private final int maxAttempts = 10;
    private int attempts = 0;

    private final JLabel Label;
    private final JTextField guessTextField;
    private final JButton submitButton;
    private final JLabel resultLabel;

    public NumberGuessingGameGUI() {
        // Set up the frame
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialize components
        Label = new JLabel("Guess the number between " + lowerBound + " and " + upperBound + ":");
        guessTextField = new JTextField(10);
        submitButton = new JButton("Submit Guess");
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.WHITE);
        resultLabel = new JLabel();
        
       
        // Generate a random number
        generateRandomNumber();

        // Add components to the frame
        add(Label);
        add(Box.createRigidArea(new Dimension(0, 10))); // Add vertical spacing
        add(guessTextField);
        add(Box.createRigidArea(new Dimension(0, 100))); // Add vertical spacing
        add(submitButton);
        add(Box.createRigidArea(new Dimension(0, 10))); // Add vertical spacing
        add(resultLabel);

        // Add ActionListener to the Submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessTextField.getText());
            attempts++;

            if (userGuess == randomNumber) {
                resultLabel.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                submitButton.setEnabled(false);
            } else if (userGuess < randomNumber) {
                resultLabel.setText("Too low! Try again.");
            } else {
                resultLabel.setText("Too high! Try again.");
            }

            if (attempts >= maxAttempts) {
                resultLabel.setText("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
                submitButton.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}
