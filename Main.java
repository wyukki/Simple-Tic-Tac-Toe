package tictactoe;

import java.util.Scanner;

public class Main {
    private static Scanner sc;
    private static char[][] matrix;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int moveNum = 0;
        char sign;
        String s = "_________";
        matrix = createMatrix(s);
        printGrid(s);
        do {
            if (moveNum % 2 == 0) {
                sign = 'X';
            } else {
                sign = 'O';
            }
            moveNum++;
            s = checkNewMove(sign);
            printGrid(s);
        } while (!findResult(s));
        sc.close();
    }

    private static void printMatrix(char[][] matrix) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void printGrid(String s) {
        char[] arr = s.toCharArray();
        int index = 0;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (i == 0 || i == 8) {
                    System.out.print("-");
                } else if (i % 2 != 0) {
                    System.out.print(" ");
                } else if (j == 0 || j == 8) {
                    System.out.print("|");
                } else if (j % 2 != 0) {
                    System.out.print(" ");
                } else if (j % 2 == 0 && j != 0 && j != 8) {
                    System.out.print(arr[index]);
                    index++;
                }
            }
            System.out.println();
        }
    }

    private static char[][] createMatrix(String s) {
        char[][] mat = new char[3][3];
        char[] arr = s.toCharArray();
        int index = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                mat[i][j] = arr[index];
                index++;
            }
        }
        return mat;
    }

    private static boolean findResult(String s) {
        String result = "";
        char[] arr = s.toCharArray();
        boolean gameFinished = false;
        int countOfX = 0;
        int countOfO = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 'X') {
                countOfX++;
            } else if (arr[i] == 'O') {
                countOfO++;
            }
        }
        if (countOfX - countOfO > 1 || countOfO - countOfX > 1) {
            result = "Impossible";
        } else {
            for (int i = 0; i < 3; ++i) {
                if ((matrix[i][0] == 'X' || matrix[i][0] == 'O')
                        && matrix[i][0] == matrix[i][1]
                        && matrix[i][0] == matrix[i][2]) {
                    if (result.isBlank()) {
                        result = matrix[i][0] + " wins";
                        gameFinished = true;
                    }
                } else if ((matrix[i][i] == 'X' || matrix[i][i] == 'O')
                        && matrix[i][i] == matrix[(i + 1) % 3][i]
                        && matrix[i][i] == matrix[(i + 2) % 3][i]) {
                    if (result.isBlank()) {
                        result = matrix[i][i] + " wins";
                        gameFinished = true;
                    }
                } else if (i == 0
                        && (matrix[0][0] == 'X' || matrix[0][0] == 'O')
                        && matrix[0][0] == matrix[i + 1][1]
                        && matrix[i][0] == matrix[i + 2][2]) {
                    if (result.isBlank()) {
                        result = matrix[i][0] + " wins";
                        gameFinished = true;
                    }
                } else if (i == 2
                        && (matrix[0][2] == 'X' || matrix[0][2] == 'O')
                        && matrix[0][2] == matrix[1][1]
                        && matrix[0][2] == matrix[2][0]) {
                    if (result.isBlank()) {
                        result = matrix[0][2] + " wins";
                        gameFinished = true;
                    }
                }
            }
            if (result.isBlank()) {
                if ((countOfX == 5 && countOfO == 4)
                        || (countOfO == 5 && countOfX == 4)) {
                    result = "Draw";
                    gameFinished = true;
                } else {
                    result = "Game not finished";
                }
            }
        }
        System.out.println(result);
        return gameFinished;
    }

    private static int[] getNewMove() {
        int[] newMove = new int[2];
        int newMoveX = 0;
        int newMoveY = 0;
        while (true) {
            try {
                newMoveX = sc.nextInt();
                newMoveY = sc.nextInt();
                if (newMoveX > 3 || newMoveY > 3 || newMoveX < 1 || newMoveY < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }
        newMove[0] = newMoveX - 1;
        newMove[1] = newMoveY - 1;
        return newMove;
    }

    private static String checkNewMove(char sign) {
        int[] move = getNewMove();
        while (matrix[move[0]][move[1]] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            move = getNewMove();
        }
        matrix[move[0]][move[1]] = sign;
        int index = 0;
        char[] arr = new char[9];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                arr[index] = matrix[i][j];
                index++;
            }
        }
        return new String(arr);
    }
}
