import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class LibraryManagementSystemGUI extends JFrame {
    private JButton addButton;
    private JButton showButton;
    private JButton issueButton;
    private JButton returnButton;

    public LibraryManagementSystemGUI() {
        initializeUI();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddBookFrame();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBooks();
            }
        });

        issueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIssueBookFrame();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReturnBookFrame();
            }
        });
    }

    private void initializeUI() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(2, 2));

        addButton = new JButton("Add Book");
        showButton = new JButton("Show Books");
        issueButton = new JButton("Issue Book");
        returnButton = new JButton("Return Book");
        Font buttonFont = new Font("Arial", Font.BOLD, 20);

        addButton.setFont(buttonFont);
        showButton.setFont(buttonFont);
        issueButton.setFont(buttonFont);
        returnButton.setFont(buttonFont);

        // Set background colors & matching text colors for contrast
        addButton.setBackground(new Color(46, 204, 113)); // Green
        addButton.setForeground(Color.WHITE);

        showButton.setBackground(new Color(52, 152, 219)); // Blue
        showButton.setForeground(Color.WHITE);

        issueButton.setBackground(new Color(230, 126, 34)); // Orange
        issueButton.setForeground(Color.WHITE);

        returnButton.setBackground(new Color(231, 76, 60)); // Red
        returnButton.setForeground(Color.WHITE);

        add(addButton);
        add(showButton);
        add(issueButton);
        add(returnButton);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showAddBookFrame() {
        JFrame addBookFrame = new JFrame("Add Book");
        addBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addBookFrame.setSize(400, 200);
        addBookFrame.setLayout(new GridLayout(5, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleTextField = new JTextField();

        JLabel authorLabel = new JLabel("Author:");
        JTextField authorTextField = new JTextField();

        JLabel yearLabel = new JLabel("Year:");
        JTextField yearTextField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceTextField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(52, 152, 219)); // Blue
        addButton.setForeground(Color.WHITE);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText();
                String author = authorTextField.getText();
                String year = yearTextField.getText();
                String price = priceTextField.getText();

                String filename = "OOP project.txt";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                    writer.write("Title: " + title + ", Author: " + author + ", Year: " + year + ", Price: " + price);
                    writer.newLine();
                    writer.flush();
                    JOptionPane.showMessageDialog(addBookFrame,
                            "Book details added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields(titleTextField, authorTextField, yearTextField, priceTextField);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addBookFrame,
                            "Error occurred while adding book details!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addBookFrame.add(titleLabel);
        addBookFrame.add(titleTextField);
        addBookFrame.add(authorLabel);
        addBookFrame.add(authorTextField);
        addBookFrame.add(yearLabel);
        addBookFrame.add(yearTextField);
        addBookFrame.add(priceLabel);
        addBookFrame.add(priceTextField);
        addBookFrame.add(new JLabel()); // Empty label for layout spacing
        addBookFrame.add(addButton);

        addBookFrame.setResizable(false);
        addBookFrame.setLocationRelativeTo(null);
        addBookFrame.setVisible(true);
    }

    private void showBooks() {
        String filename = "OOP project.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder booksText = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                booksText.append(line).append("\n");
            }
            JTextArea booksTextArea = new JTextArea(booksText.toString());
            JScrollPane scrollPane = new JScrollPane(booksTextArea);
            scrollPane.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(this, scrollPane, "Books", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred while reading book details!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showIssueBookFrame() {
        JFrame issueBookFrame = new JFrame("Issue Book");
        issueBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        issueBookFrame.setSize(400, 200);
        issueBookFrame.setLayout(new GridLayout(3, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleTextField = new JTextField();

        JLabel borrowerLabel = new JLabel("Borrower:");
        JTextField borrowerTextField = new JTextField();

        JButton issueButton = new JButton("Issue");
        issueButton.setBackground(new Color(52, 152, 219)); // Blue
        issueButton.setForeground(Color.WHITE);

        issueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText();
                String borrower = borrowerTextField.getText();

                String filename = "OOP project.txt";

                try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                    String line;
                    StringBuilder updatedText = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("Title: " + title)) {
                            line += ", Borrower: " + borrower;
                        }
                        updatedText.append(line).append("\n");
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                    writer.write(updatedText.toString());
                    writer.close();
                    JOptionPane.showMessageDialog(issueBookFrame,
                            "Book issued successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields(titleTextField, borrowerTextField);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(issueBookFrame,
                            "Error occurred while issuing the book!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        issueBookFrame.add(titleLabel);
        issueBookFrame.add(titleTextField);
        issueBookFrame.add(borrowerLabel);
        issueBookFrame.add(borrowerTextField);
        issueBookFrame.add(new JLabel()); // Empty label for layout spacing
        issueBookFrame.add(issueButton);

        issueBookFrame.setResizable(false);
        issueBookFrame.setLocationRelativeTo(null);
        issueBookFrame.setVisible(true);
    }

    private void showReturnBookFrame() {
        JFrame returnBookFrame = new JFrame("Return Book");
        returnBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        returnBookFrame.setSize(400, 200);
        returnBookFrame.setLayout(new GridLayout(2, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleTextField = new JTextField();

        JButton returnButton = new JButton("Return");
        returnButton.setBackground(new Color(52, 152, 219)); // Blue
        returnButton.setForeground(Color.WHITE);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText();

                String filename = "OOP project.txt";

                try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                    String line;
                    StringBuilder updatedText = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("Title: " + title)) {
                            continue; // Skip the line
                        }
                        updatedText.append(line).append("\n");
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                    writer.write(updatedText.toString());
                    writer.close();
                    JOptionPane.showMessageDialog(returnBookFrame,
                            "Book returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields(titleTextField);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(returnBookFrame,
                            "Error occurred while returning the book!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        returnBookFrame.add(titleLabel);
        returnBookFrame.add(titleTextField);
        returnBookFrame.add(new JLabel()); // Empty label for layout spacing
        returnBookFrame.add(returnButton);

        returnBookFrame.setResizable(false);
        returnBookFrame.setLocationRelativeTo(null);
        returnBookFrame.setVisible(true);
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryManagementSystemGUI();
            }
        });
    }
}
