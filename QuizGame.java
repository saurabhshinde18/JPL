import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizGame extends JFrame implements ActionListener {
    String question = "What is the capital of France?";
    String[] options = { "Berlin", "Paris", "Madrid", "Rome" };
    int correctAnswerIndex = 1;

    JLabel lblQuestion;
    JRadioButton[] choices;
    ButtonGroup group;
    JButton btnSubmit;
    JLabel lblResult;

    public QuizGame() {
        setTitle("Quiz Game");
        setLayout(new FlowLayout());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblQuestion = new JLabel(question);
        add(lblQuestion);

        group = new ButtonGroup();
        choices = new JRadioButton[options.length];

        for (int i = 0; i < options.length; i++) {
            choices[i] = new JRadioButton(options[i]);
            group.add(choices[i]);
            add(choices[i]);
        }

        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        lblResult = new JLabel("");
        add(lblResult);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < options.length; i++) {
            if (choices[i].isSelected()) {
                if (i == correctAnswerIndex) {
                    lblResult.setText("Correct!");
                } else {
                    lblResult.setText("Wrong! The correct answer is " + options[correctAnswerIndex]);
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        new QuizGame();
    }
}
