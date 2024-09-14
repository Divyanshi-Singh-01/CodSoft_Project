import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {
    private BankAccount account;
    private JFrame frame;
    private JTextField amountField;
    private JTextArea resultArea;

    public ATM(BankAccount account) {
        this.account = account;
        createUI();
    }

    private void createUI() {
        frame = new JFrame("ATM Machine");
        JPanel panel = new JPanel();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel instructionLabel = new JLabel("Enter amount:");
        instructionLabel.setBounds(10, 20, 150, 25);
        panel.add(instructionLabel);

        amountField = new JTextField(20);
        amountField.setBounds(10, 50, 165, 25);
        panel.add(amountField);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(180, 50, 100, 25);
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(180, 80, 100, 25);
        panel.add(withdrawButton);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(10, 80, 150, 25);
        panel.add(checkBalanceButton);

        resultArea = new JTextArea();
        resultArea.setBounds(10, 110, 270, 50);
        resultArea.setEditable(false);
        panel.add(resultArea);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
    }

    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                account.deposit(amount);
                resultArea.setText("Deposited: $" + amount + "\nNew Balance: $" + account.getBalance());
            } else {
                resultArea.setText("Invalid amount. Please enter a positive number.");
            }
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter a numeric value.");
        }
    }

    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                if (account.withdraw(amount)) {
                    resultArea.setText("Withdrew: $" + amount + "\nNew Balance: $" + account.getBalance());
                } else {
                    resultArea.setText("Insufficient balance. Please try a lower amount.");
                }
            } else {
                resultArea.setText("Invalid amount. Please enter a positive number.");
            }
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter a numeric value.");
        }
    }

    private void checkBalance() {
        resultArea.setText("Current Balance: $" + account.getBalance());
    }

    public static void main(String[] args) {
        // Creating a BankAccount with an initial balance
        BankAccount account = new BankAccount(1000.0);
        new ATM(account);
    }
}
