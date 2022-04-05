import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SeaChessTable {
    String[][] playTable;
    boolean isPlaying = true;

    public void StartGame() {
        CreateMatrix();

        int winState = WhoWin();
        boolean turn = true;
        short gameTimer = 0;

        GameLoop(turn, gameTimer, winState);
    }

    public void GameLoop(boolean turn, short gameTimer, int winState) {
        while (isPlaying) {

            PrintMatrix();
            PrintPlayerComment(turn);
            int playerPosition = ReadPlayerInput();
            Coordinate playerCoordinate = InputToCoordinate(playerPosition);
            boolean verifyClearSquare = VerifyCoordinates(playTable, playerCoordinate);
            playerCoordinate = VerifyLoop(verifyClearSquare, playerCoordinate);
            EditMatrix(playerCoordinate, turn);
            turn = !turn;
            gameTimer++;
            winState = WhoWin();
            if (gameTimer == 9 || winState != 0) {
                break;
            }
        }
        EndGame(gameTimer, winState);
    }

    public boolean VerifyCoordinates(String[][] matrix, Coordinate playedCoordinates) {
        boolean verify;
        verify = matrix[playedCoordinates.x][playedCoordinates.y].equals(" ");
        return verify;
    }

    public Coordinate VerifyLoop(boolean verifyClearSquare, Coordinate playerCoordinate) {
        while (!verifyClearSquare) {
            System.out.println("Invalid input");
            int playerPosition = ReadPlayerInput();
            playerCoordinate = InputToCoordinate(playerPosition);
            verifyClearSquare = VerifyCoordinates(playTable, playerCoordinate);

        }
        return playerCoordinate;
    }

    public int ReadPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        int playerInput;
        System.out.println("Choose square:");
        while (true) {
            playerInput = scanner.nextInt();
            if (PositionIsValid(playerInput)) {
                break;
            } else {
                System.out.println("Invalid input:");
            }
        }

        return playerInput;
    }

    public boolean PositionIsValid(int currPosition) {
        ArrayList<Integer> validPlayerInputPositions = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        return validPlayerInputPositions.contains(currPosition);
    }

    public Coordinate InputToCoordinate(int playerInput) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < playerInput - 1; i++) {

            y += 2;
            if (y == 6) {
                x += 2;
                y = 0;
            }
        }
        return new Coordinate(x, y);
    }

    public void CreateMatrix() {
        playTable = new String[5][5];

        for (int line = 0; line < playTable.length; line++)
            for (int col = 0; col < playTable.length; col++) {

                if (line % 2 == 0 && col % 2 == 1)
                    playTable[line][col] = "│";

                else if (line % 2 == 1 && col % 2 == 0)
                    playTable[line][col] = "─";

                else if (col == 1 || col == 3)
                    playTable[line][col] = "┼";

                else
                    playTable[line][col] = " ";
            }

    }

    public void EditMatrix(Coordinate playerPositioning, boolean turn) {
        if (turn) {
            playTable[playerPositioning.x][playerPositioning.y] = "X";
        } else playTable[playerPositioning.x][playerPositioning.y] = "O";

    }

    public void PrintPlayerComment(boolean turn) {
        if (turn) {
            System.out.println("Its Player 'X' turn");
        } else {
            System.out.println("Its Player 'O' turn");
        }
    }

    public void PrintMatrix() {
        for (int line = 0; line < playTable.length; line++) {
            for (int col = 0; col < playTable.length; col++) {

                System.out.print(playTable[line][col]);
                if (col == playTable.length - 1) System.out.println();
            }
        }
    }

    public int WhoWin() {
        int winnerIndex = 0;
        //WARNING EXTREME HARDCODE
        //Needed conditions to win
        // TODO assemble with algorithm
        if (
                (playTable[0][0].equals("X") && playTable[2][2].equals("X") && playTable[4][4].equals("X")) ||
                        (playTable[4][0].equals("X") && playTable[2][2].equals("X") && playTable[0][4].equals("X")) ||
                        (playTable[0][0].equals("X") && playTable[0][2].equals("X") && playTable[0][4].equals("X")) ||
                        (playTable[2][0].equals("X") && playTable[2][2].equals("X") && playTable[2][4].equals("X")) ||
                        (playTable[4][0].equals("X") && playTable[4][2].equals("X") && playTable[4][4].equals("X")) ||
                        (playTable[0][0].equals("X") && playTable[2][0].equals("X") && playTable[4][0].equals("X")) ||
                        (playTable[0][2].equals("X") && playTable[2][2].equals("X") && playTable[4][2].equals("X")) ||
                        (playTable[0][4].equals("X") && playTable[2][4].equals("X") && playTable[4][4].equals("X"))

        ) winnerIndex = 1;
        else if (
                (playTable[0][0].equals("O") && playTable[2][2].equals("O") && playTable[4][4].equals("O")) ||
                        (playTable[4][0].equals("O") && playTable[2][2].equals("O") && playTable[0][4].equals("O")) ||
                        (playTable[0][0].equals("O") && playTable[0][2].equals("O") && playTable[0][4].equals("O")) ||
                        (playTable[2][0].equals("O") && playTable[2][2].equals("O") && playTable[2][4].equals("O")) ||
                        (playTable[4][0].equals("O") && playTable[4][2].equals("O") && playTable[4][4].equals("O")) ||
                        (playTable[0][0].equals("O") && playTable[2][0].equals("O") && playTable[4][0].equals("O")) ||
                        (playTable[0][2].equals("O") && playTable[2][2].equals("O") && playTable[4][2].equals("O")) ||
                        (playTable[0][4].equals("O") && playTable[2][4].equals("O") && playTable[4][4].equals("O"))
        ) winnerIndex = 2;
        return winnerIndex;

    }

    public void EndGame(int gameTimer, int winState) {
        PrintMatrix();
        System.out.println("Game END");
        if (gameTimer == 9) {
            System.out.println("DRAW");
        } else if (winState == 1) {
            System.out.println("Player 'X' WINS");
        } else System.out.println("Player 'O' WINS");
    }

}

