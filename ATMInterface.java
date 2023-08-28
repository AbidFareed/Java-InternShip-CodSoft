import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount bankAccount) {
        account = bankAccount;
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    public double checkBalance() {
        return account.getBalance();
    }
}

public class ATMInterface extends JFrame {
    private ATM atm;

    private JLabel balanceLabel;
    private JButton depositButton;
    private JButton withdrawButton;

    public ATMInterface(ATM atm) {
        super("ATM Interface");
        this.atm = atm;

        balanceLabel = new JLabel("Balance: $" + atm.checkBalance());
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter deposit amount:");
                try {
                    double amount = Double.parseDouble(input);
                    atm.deposit(amount);
                    updateBalanceLabel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter withdrawal amount:");
                try {
                    double amount = Double.parseDouble(input);
                    if (amount <= atm.checkBalance()) {
                        atm.withdraw(amount);
                        updateBalanceLabel();
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount.");
                }
            }
        });

        setLayout(new FlowLayout());
        add(balanceLabel);
        add(depositButton);
        add(withdrawButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + atm.checkBalance());
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000.0);
        ATM atm = new ATM(bankAccount);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMInterface(atm);
            }
        });
    }
}
