import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {


    private JPanel mainPanel;
    private JPanel headerPanel;
    private JLabel titleLabel;


    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JComboBox<String> SemesterComboBox;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton otherRadioButton;
    private ButtonGroup genderGroup;
    private JCheckBox termsCheckBox;
    private JButton registerButton;
    private JButton resetButton;


    private final Color BACKGROUND_COLOR = new Color(237, 234, 234);
    private final Color HEADER_COLOR = new Color(0x222222);
    private final Color TEXT_COLOR = new Color(255, 255, 255);
    private final Color SEMESTER_COLOR = Color.BLACK;
    private final Color BUTTON_COLOR = new Color(101, 78, 163);
    private final Font MAIN_FONT = new Font("Arial", Font.BOLD, 14);
    private final Font TITLE_FONT = new Font("Arial", Font.BOLD, 28);

    public Main() {
        setTitle("BaraK Valley Engineering College");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        setIconImage(new ImageIcon("bvec_logo.jpg").getImage());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);


        createHeaderPanel();


        createFormPanel();


        add(mainPanel);


        setVisible(true);
    }

    private void createHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(HEADER_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        headerPanel.setPreferredSize(new Dimension(600, 120));


        titleLabel = new JLabel("BVEC - ERP SYSTEM");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);



        headerPanel.add(titleLabel, BorderLayout.WEST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }

    private void createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(BACKGROUND_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        addFormLabel("Username:", formPanel, gbc, 0);
        usernameField = createStyledTextField();
        addFormField(usernameField, formPanel, gbc, 0);


        addFormLabel("Password:", formPanel, gbc, 1);
        passwordField = new JPasswordField();
        styleTextField(passwordField);
        addFormField(passwordField, formPanel, gbc, 1);


        addFormLabel("Confirm Password:", formPanel, gbc, 2);
        confirmPasswordField = new JPasswordField();
        styleTextField(confirmPasswordField);
        addFormField(confirmPasswordField, formPanel, gbc, 2);


        addFormLabel("Email:", formPanel, gbc, 3);
        emailField = createStyledTextField();
        addFormField(emailField, formPanel, gbc, 3);


        addFormLabel("Select Semester:", formPanel, gbc, 4);
        String[] semesters = {"Select one...", "1st semester", "2nd semester", "3rd semester",
                "4th semester", "5th semester", "6th semester", "7th semester","8th semester"};
        SemesterComboBox = new JComboBox<>(semesters);
        SemesterComboBox.setFont(MAIN_FONT);
        SemesterComboBox.setForeground(SEMESTER_COLOR);
        SemesterComboBox.setBackground(BUTTON_COLOR);
        addFormField(SemesterComboBox, formPanel, gbc, 4);


        addFormLabel("Gender:", formPanel, gbc, 5);

        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(BACKGROUND_COLOR);

        maleRadioButton = createStyledRadioButton("Male");
        femaleRadioButton = createStyledRadioButton("Female");
        otherRadioButton = createStyledRadioButton("Other");

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.add(otherRadioButton);

        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(genderPanel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        termsCheckBox = new JCheckBox("I agree to the terms and conditions");
        termsCheckBox.setFont(MAIN_FONT);
        termsCheckBox.setForeground(Color.BLACK);
        termsCheckBox.setBackground(BACKGROUND_COLOR);
        formPanel.add(termsCheckBox, gbc);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setForeground(TEXT_COLOR);

        registerButton = createStyledButton("Register");
        resetButton = createStyledButton("Reset");

        registerButton.setForeground(Color.BLACK);
        resetButton.setForeground(Color.BLACK);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateAndSubmit();
            }
        });


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        buttonPanel.add(registerButton);
        buttonPanel.add(resetButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        styleTextField(field);
        return field;
    }

    private void styleTextField(JTextField field) {
        field.setForeground(Color.BLACK);
        field.setBackground(Color.white);
        field.setFont(MAIN_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }

    private JRadioButton createStyledRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setForeground(Color.BLACK);
        radioButton.setFont(MAIN_FONT);
        radioButton.setBackground(BACKGROUND_COLOR);
        return radioButton;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.BLACK);
        button.setBackground(BUTTON_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));


        return button;
    }

    private void addFormLabel(String text, JPanel panel, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        label.setFont(MAIN_FONT);
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);
    }

    private void addFormField(Component component, JPanel panel, GridBagConstraints gbc, int row) {
        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(component, gbc);
    }

    private void validateAndSubmit() {

        if (usernameField.getText().isEmpty()) {
            showMessage("Please enter a username!");
            return;
        }

        if (passwordField.getPassword().length == 0) {
            showMessage("Please enter a password!");
            return;
        }

        if (!String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword()))) {
            showMessage("Passwords do not match!");
            return;
        }

        if (emailField.getText().isEmpty() || !emailField.getText().contains("@")) {
            showMessage("Please enter a valid email address!");
            return;
        }

        if (SemesterComboBox.getSelectedIndex() == 0) {
            showMessage("Please select your Semester");
            return;
        }

        if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected() && !otherRadioButton.isSelected()) {
            showMessage("Please select your gender!");
            return;
        }

        if (!termsCheckBox.isSelected()) {
            showMessage("You must agree to the terms and conditions!");
            return;
        }


        showMessage("Registration successful! Welcome to BVEC");
    }

    private void resetForm() {
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        emailField.setText("");
        SemesterComboBox.setSelectedIndex(0);
        genderGroup.clearSelection();
        termsCheckBox.setSelected(false);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Barak Valley Engineering College", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}