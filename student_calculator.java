import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame {

    private JTextField nameField, m1Field, m2Field, m3Field;
    private String[] studentNames = new String[4];
    private int[] studentTotals = new int[4];
    private double[] studentAverages = new double[4];
    private String[] studentGrades = new String[4];
    private int studentCount = 0;

    public StudentGradeCalculator() {
        setTitle("Student Grades Calculator");
        setLayout(new GridLayout(6, 2, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);

        nameField = new JTextField();
        m1Field = new JTextField();
        m2Field = new JTextField();
        m3Field = new JTextField();

        add(new JLabel("Name:")); add(nameField);
        add(new JLabel("Marks 1:")); add(m1Field);
        add(new JLabel("Marks 2:")); add(m2Field);
        add(new JLabel("Marks 3:")); add(m3Field);
        JButton calculateButton = new JButton("Add Student");
        add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (studentCount < 4) {
                    addStudent();
                } else {
                    showResults();
                }
            }
        });
    }

    private void addStudent() {
        String name = nameField.getText();
        int m1 = Integer.parseInt(m1Field.getText());
        int m2 = Integer.parseInt(m2Field.getText());
        int m3 = Integer.parseInt(m3Field.getText());
        int total = m1 + m2 + m3;
        double average = total / 3.0;
        String grade = (average >= 90) ? "A+" :
                       (average >= 80) ? "A" :
                       (average >= 70) ? "B" :
                       (average >= 60) ? "C" :
                       (average >= 50) ? "D" : "F";

        studentNames[studentCount] = name;
        studentTotals[studentCount] = total;
        studentAverages[studentCount] = average;
        studentGrades[studentCount] = grade;
        studentCount++;

        nameField.setText("");
        m1Field.setText("");
        m2Field.setText("");
        m3Field.setText("");

        if (studentCount == 4) {
            showResults();
        }
    }

    private void showResults() {
        JFrame resultsFrame = new JFrame("Student Results");
        resultsFrame.setSize(400, 300);
        resultsFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < studentCount; i++) {
            result.append(String.format("Name: %s, Total: %d, Average: %.2f, Grade: %s\n",
                    studentNames[i], studentTotals[i], studentAverages[i], studentGrades[i]));
        }

        resultArea.setText(result.toString());
        resultsFrame.add(new JScrollPane(resultArea));
        resultsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGradeCalculator().setVisible(true);
        });
    }
}


