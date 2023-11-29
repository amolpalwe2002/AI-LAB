import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    public static void main(String[] args) {
        char[][] board = new char[3][3];
        initializeBoard(board);

        char humanPlayer = 'X';
        char computerPlayer = 'O';

        int currentPlayer = 0; // 0 for human, 1 for computer
        boolean gameWon = false;
        boolean gameDraw = false;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        printBoard(board);

        while (!gameWon && !gameDraw) {
            if (currentPlayer == 0) {
                // Human player's turn
                int[] humanMove = getHumanMove(scanner);
                int row = humanMove[0];
                int col = humanMove[1];
                if (isValidMove(board, row, col)) {
                    board[row][col] = humanPlayer;
                    currentPlayer = 1; // Switch to the computer's turn
                } else {
                    System.out.println("Invalid move. Please try again.");
                    continue;
                }
            } else {
                // Computer player's turn
                int[] computerMove = getComputerMove(board, computerPlayer, humanPlayer);
                int row = computerMove[0];
                int col = computerMove[1];
                board[row][col] = computerPlayer;
                currentPlayer = 0; // Switch to the human's turn
            }

            printBoard(board);

            gameWon = checkWin(board, humanPlayer, computerPlayer);
            gameDraw = isBoardFull(board);
        }

        if (gameWon) {
            if (currentPlayer == 0) {
                System.out.println("Congratulations! You won.");
            } else {
                System.out.println("The computer won. Better luck next time!");
            }
        } else {
            System.out.println("It's a draw! The game is over.");
        }

        scanner.close();
    }

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static int[] getHumanMove(Scanner scanner) {
        System.out.print("Enter your move (row and column, e.g., 1 2): ");
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;
        return new int[] { row, col };
    }

    public static boolean isValidMove(char[][] board, int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            return true;
        }
        return false;
    }

    public static int[] getComputerMove(char[][] board, char computerPlayer, char humanPlayer) {
        Random random = new Random();
        int[] move = new int[2];

        // First, check if the computer can win on the next move
        move = findWinningMove(board, computerPlayer);
        if (move[0] != -1) {
            return move;
        }

        // Next, check if the computer needs to block the human player from winning
        move = findWinningMove(board, humanPlayer);
        if (move[0] != -1) {
            return move;
        }

        // If neither a winning move nor a blocking move is available, make a random move
        do {
            move[0] = random.nextInt(3);
            move[1] = random.nextInt(3);
        } while (!isValidMove(board, move[0], move[1]));

        return move;
    }

    public static int[] findWinningMove(char[][] board, char player) {
        int[] move = new int[] { -1, -1 };
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (isValidMove(board, row, col)) {
                    board[row][col] = player;
                    if (checkWin(board, player, player)) {
                        move[0] = row;
                        move[1] = col;
                    }
                    board[row][col] = ' '; // Undo the move
                }
            }
        }
        return move;
    }

    public static boolean checkWin(char[][] board, char player, char opponent) {
        // Check rows and columns for a win
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player)
                    || (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Check diagonals for a win
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player)
                || (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
