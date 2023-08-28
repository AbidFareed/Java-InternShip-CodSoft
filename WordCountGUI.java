import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCountGUI extends JFrame {
    private JTextArea textArea;
    private JButton countButton;

    public WordCountGUI() {
        super("Word Count");

        textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        countButton = new JButton("Count Words");
        add(countButton);

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textArea.getText();
                int wordCount = countWords(inputText);
                if(inputText.isEmpty()){
                    wordCount=0;
                }
                JOptionPane.showMessageDialog(countButton, "Total words : "+wordCount,
                "Result", JOptionPane.DEFAULT_OPTION);
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static int countWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WordCountGUI();
            }
        });
    }
}
