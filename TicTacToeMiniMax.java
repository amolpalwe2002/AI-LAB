import java.util.Scanner;

public class TicTacToeMiniMax {

    // This function is used to draw the board's current state every time the user turn arrives.
    public static void constBoard(int[] board) {
        System.out.println("Current State Of Board : \n");
        for (int i = 0; i < 9; i++) {
            if (i > 0 && i % 3 == 0) {
                System.out.println();
            }
            if (board[i] == 0) {
                System.out.print("- ");
            } else if (board[i] == 1) {
                System.out.print("O ");
            } else if (board[i] == -1) {
                System.out.print("X ");
            }
        }
        System.out.println("\n");
    }

    // This function takes the user move as input and make the required changes on the board.
    public static void user1Turn(int[] board) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter X's position from [1...9]: ");
        int pos = sc.nextInt();
        if (board[pos - 1] != 0) {
            System.out.println("Wrong Move!!!");
            System.exit(0);
        }
        board[pos - 1] = -1;
    }

    public static void user2Turn(int[] board) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter O's position from [1...9]: ");
        int pos = sc.nextInt();
        if (board[pos - 1] != 0) {
            System.out.println("Wrong Move!!!");
            System.exit(0);
        }
        board[pos - 1] = 1;
    }

    // MinMax function.
    public static int minimax(int[] board, int player) {
        int x = analyzeBoard(board);
        if (x != 0) {
            return x * player;
        }
        int pos = -1;
        int value = -2;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = player;
                int score = -minimax(board, player * -1);
                if (score > value) {
                    value = score;
                    pos = i;
                }
                board[i] = 0;
            }
        }

        if (pos == -1) {
            return 0;
        }
        return value;
    }

    // This function makes the computer's move using minmax algorithm.
    public static void compTurn(int[] board) {
        int pos = -1;
        int value = -2;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = 1;
                int score = -minimax(board, -1);
                board[i] = 0;
                if (score > value) {
                    value = score;
                    pos = i;
                }
            }
        }
        board[pos] = 1;
    }

    // This function is used to analyze a game.
    public static int analyzeBoard(int[] board) {
        int[][] cb = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        for (int i = 0; i < 8; i++) {
            if (board[cb[i][0]] != 0 && board[cb[i][0]] == board[cb[i][1]] && board[cb[i][0]] == board[cb[i][2]]) {
                return board[cb[i][2]];
            }
        }
        return 0;
    }

    // Main Function.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        // The board is considered in the form of a single-dimensional array.
        // One player moves 1 and the other move -1.
        int[] board = new int[9];

        System.out.print("Enter 1 for single player, 2 for multiplayer: ");
        choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("Computer : O Vs. You : X");
            System.out.print("Enter to play 1(st) or 2(nd): ");
            int player = sc.nextInt();
            for (int i = 0; i < 9; i++) {
                if (analyzeBoard(board) != 0) {
                    break;
                }
                if ((i + player) % 2 == 0) {
                    compTurn(board);
                } else {
                    constBoard(board);
                    user1Turn(board);
                }
            }
        } else {
            for (int i = 0; i < 9; i++) {
                if (analyzeBoard(board) != 0) {
                    break;
                }
                if (i % 2 == 0) {
                    constBoard(board);
                    user1Turn(board);
                } else {
                    constBoard(board);
                    user2Turn(board);
                }
            }
        }

        int x = analyzeBoard(board);
        constBoard(board);

        if (x == 0) {
            System.out.println("Draw!!!");
        } else if (x == -1) {
            System.out.println("X Wins!!! Y Lose !!!");
        } else if (x == 1) {
            System.out.println("X Lose!!! O Wins !!!!");
        }
    }
}
