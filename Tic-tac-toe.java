
import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean playerX;
    private JLabel statusLabel;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 350);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        playerX = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                buttons[i][j].addActionListener(this);
                gamePanel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("Player X's turn");
        add(statusLabel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clickedButton && buttons[i][j].getText().equals("")) {
                    if (playerX) {
                        buttons[i][j].setText("X");
                        buttons[i][j].setForeground(Color.RED); // Set X's color to red
                        playerX = false;
                        statusLabel.setText("Player O's turn");
                    } else {
                        buttons[i][j].setText("O");
                        buttons[i][j].setForeground(Color.BLUE); // Set O's color to blue
                        playerX = true;
                        statusLabel.setText("Player X's turn");
                    }
                }
            }
        }

        if (checkForWin()) {
            if (!playerX) {
                statusLabel.setText("Player X wins!");
            } else {
                statusLabel.setText("Player O wins!");
            }
            disableButtons();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a tie!");
        }
    }

    private boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkThreeEquals(buttons[i][0].getText(), buttons[i][1].getText(), buttons[i][2].getText())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkThreeEquals(buttons[0][i].getText(), buttons[1][i].getText(), buttons[2][i].getText())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        return (checkThreeEquals(buttons[0][0].getText(), buttons[1][1].getText(), buttons[2][2].getText()) ||
                checkThreeEquals(buttons[0][2].getText(), buttons[1][1].getText(), buttons[2][0].getText()));
    }

    private boolean checkThreeEquals(String a, String b, String c) {
        return (!a.equals("") && a.equals(b) && b.equals(c));
    }

    private boolean isBoardFull() {
        // Check if the board is full
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        // Disable all buttons after the game ends
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        // Reset the game to its initial state
        playerX = true;
        statusLabel.setText("Player X's turn");

        // Clear button texts and enable buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setForeground(Color.BLACK); // Reset text color to black
                buttons[i][j].setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}


