public class Board {

    public int board[][];
    public int cost;
    public int depth;
    public Board parent;

    public Board() {
        int defultBoard[][] = {};
        board = defultBoard;
        cost = 0;
        depth = 0;
        parent = null;
    }

    public Board(Board b) {
        int[][] myInt = new int[b.board.length][];
        for (int i = 0; i < b.board.length; i++)
            myInt[i] = b.board[i].clone();
        this.board = myInt;
        this.cost = 0;
        this.depth = b.depth + 1;
        this.parent = b;
    }

    public Board(int[][] board, int cost) {
        super();
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    /* to compute the misplaced tiles */
    public int CountMisplaced() {
        int check = 1;
        int numberOfMisplaced = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (check != board[i][j] && board[i][j] != 0)
                    numberOfMisplaced++;
                check++;
            }
        }
        return numberOfMisplaced;
    }

    /* to compute the Manhattan Distance */
    public int ManhattanDistance() {
        int check = 1;
        int goalBoard[][] = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i == board.length - 1 && j == board.length - 1) {
                    goalBoard[i][j] = 0;
                    break;
                }
                goalBoard[i][j] = check;
                check++;
            }
        }
        int manhattanDistance = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (goalBoard[i][j] == 0)
                    break;
                if (goalBoard[i][j] == board[i][j])
                    continue;
                else {
                    int x = 0, y = 0;
                    for (int ii = 0; ii < 3; ii++) {
                        for (int jj = 0; jj < 3; jj++) {
                            if (goalBoard[i][j] == board[ii][jj]) {
                                x = ii;
                                y = jj;
                                manhattanDistance += Math.abs((x - i)) + Math.abs((y - j));
                            }
                        }
                    }
                }
            }
        }
        return manhattanDistance;
    }

    /* Check whether this state is the goal state */
    public boolean IsGoalState(Board b) {
        int check = 1;
        for (int i = 0; i < b.board.length; i++) {
            for (int j = 0; j < b.board.length; j++) {
                if (i == b.board.length - 1 && j == b.board.length - 1) {
                    if (b.board[i][j] == 0)
                        return true;
                } else if (b.board[i][j] != check)
                    return false;
                check++;
            }
        }
        return true;
    }

    /* to print the board */
    public void PrintBoard(Board b) {
        for (int i = 0; i < b.board.length; i++) {
            for (int j = 0; j < b.board.length; j++) {
                System.out.print(" " + b.board[i][j]);
            }
            System.out.println();
        }
    }
}