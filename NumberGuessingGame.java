import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class NumberGuessingGame extends JFrame {
    private int randomNumber;
    private JTextField guessField;
    private JTextArea resultArea;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(500, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        // Initialize UI components
        JLabel guessLabel = new JLabel("Enter your guess (1-100):");
        guessField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        // Layout UI components
        JPanel panel = new JPanel();
        panel.add(guessLabel);
        panel.add(guessField);
        panel.add(submitButton);

        getContentPane().add(panel, "North");
        getContentPane().add(new JScrollPane(resultArea), "Center");
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess == randomNumber) {
                resultArea.setText("Congratulations! You guessed the correct number.");
            } else if (guess < randomNumber) {
                resultArea.setText("Too low! Try a higher number.");
            } else {
                resultArea.setText("Too high! Try a lower number.");
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid input! Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
