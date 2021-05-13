import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Stack;

public class Algorithm {

    TreeSet <String> duplicated;

    /* The search Algorithm */
    // algrothim choice
    public void Search(Board initial, PriorityQueue < Board > queue, int choice) throws CloneNotSupportedException {

        duplicated = new TreeSet < String > ();
        initial.cost = getCost(initial, choice);
        initial.parent = null; // since it's the root
        queue.add(initial);
        duplicated.add(getCode(initial));

        int nodeExplored = 0;
        int maxNodes = 0;
        while (true) {
            if (queue.isEmpty()) {
                System.err.println("There is no solution");
                break;
            } else {
                nodeExplored++;

                if (queue.size() > maxNodes)
                    maxNodes = queue.size();

                Board temp = queue.remove();
                System.out.println("Expanding the state with g(n)=" + temp.depth + " and h(n)=" + (temp.cost - temp.depth));
                temp.PrintBoard(temp);

                if (temp.IsGoalState(temp)) {
                    System.out.println(" A Solution has been found )");
                    temp.PrintBoard(temp);
                    printPath(temp);
                    System.out.println("Solution depth: " + (temp.depth));
                    System.out.println("Number of Explored nodes: " + nodeExplored);
                    System.out.println("Max Number of nodes in the Q: " + maxNodes);

                    break;
                } else {

                    Board temp1 = new Board(temp); //up
                    Board temp2 = new Board(temp); //down
                    Board temp3 = new Board(temp); //left
                    Board temp4 = new Board(temp); //right

                    if (MoveUp(temp1))
                        if (!duplicated.contains(getCode(temp1))) {
                            temp1.cost = getCost(temp1, choice);
                            queue.add(temp1);
                            duplicated.add(getCode(temp1));
                        }
                    if (MoveDown(temp2))
                        if (!duplicated.contains(getCode(temp2))) {
                            temp2.cost = getCost(temp2, choice);
                            queue.add(temp2);
                            duplicated.add(getCode(temp2));
                        }
                    if (MoveLeft(temp3))
                        if (!duplicated.contains(getCode(temp3))) {
                            temp3.cost = getCost(temp3, choice);
                            queue.add(temp3);
                            duplicated.add(getCode(temp3));
                        }
                    if (MoveRight(temp4))
                        if (!duplicated.contains(getCode(temp4))) {
                            temp4.cost = getCost(temp4, choice);
                            queue.add(temp4);
                            duplicated.add(getCode(temp4));
                        }
                }
            }
        }
    }

    /* move the blank space up if possible or false*/
    public boolean MoveUp(Board b) {
        int x = 0, y = 0, z = 0;
        for (int i = 0; i < b.board.length; i++) {
            for (int j = 0; j < b.board.length; j++) {
                if (b.board[i][j] == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        if (x == 0)
            return false;
        else {
            z = b.board[x - 1][y];
            b.board[x - 1][y] = 0;
            b.board[x][y] = z;
            b.cost = b.CountMisplaced();
            return true;
        }
    }

    /* move the blank space down if possible or false*/
    public boolean MoveDown(Board b) {
        int x = 0, y = 0, z = 0;
        for (int i = 0; i < b.board.length; i++) {
            for (int j = 0; j < b.board.length; j++) {
                if (b.board[i][j] == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        if (x == b.board.length - 1)
            return false;
        else {
            z = b.board[x + 1][y];
            b.board[x + 1][y] = 0;
            b.board[x][y] = z;
            b.cost = b.CountMisplaced();
            return true;
        }
    }

    /* move the blank space left if possible or false*/
    public boolean MoveLeft(Board b) {
        int x = 0, y = 0, z = 0;
        for (int i = 0; i < b.board.length; i++) {
            for (int j = 0; j < b.board.length; j++) {
                if (b.board[i][j] == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        if (y == 0)
            return false;
        else {
            z = b.board[x][y - 1];
            b.board[x][y - 1] = 0;
            b.board[x][y] = z;
            b.cost = b.CountMisplaced();
            return true;
        }
    }

    /* move the blank space right if possible or false*/
    public boolean MoveRight(Board b) {
        int x = 0, y = 0, z = 0;
        for (int i = 0; i < b.board.length; i++) {
            for (int j = 0; j < b.board.length; j++) {
                if (b.board[i][j] == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        if (y == b.board.length - 1)
            return false;
        else {
            z = b.board[x][y + 1];
            b.board[x][y + 1] = 0;
            b.board[x][y] = z;
            b.cost = b.CountMisplaced();
            return true;
        }
    }

    /* encode the state as string */
    public String getCode(Board b) {
        String state = "";
        for (int i = 0; i < b.board.length; i++) {
            for (int j = 0; j < b.board.length; j++) {
                state += b.board[i][j];
            }
        }
        return state;
    }

    /* print the solution path from the root to the leave */
    public void printPath(Board b) {
        System.out.println("The Solution Path");
        Board temp = b;
        Stack s = new Stack();
        while (temp != null) {
            s.push(temp);
            temp = temp.parent;
        }
        int step = 0;
        while (!s.isEmpty()) {
            Board t = (Board) s.pop();
            System.out.println("((step))" + (step++) + " ");
            t.PrintBoard(t);
        }
    }

    /* compute the cost base on the user's choice*/
    public int getCost(Board b, int choice) {
        if (choice == 1)
            return b.depth;
        else if (choice == 2)
            return b.depth + b.CountMisplaced();
        else
            return b.depth + b.ManhattanDistance();

    }

    /* read the user input */
    public int[][] readInput() {
        System.out.println("Welcome to Wijdan 8-puzzle solver.\nType “1” to use a default puzzle, or “2” to enter your own puzzle.");
        Scanner scan = new Scanner(System.in);
        int c = scan.nextInt();
        if (c == 1) {
            int b[][]={{1,5,2},{7,3,4},{6,0,8}};
            return b;
        }
        System.out.println("Enter your puzzle, use a zero to represent the blank \nEnter the first row, use space between numbers");
        Scanner scan2 = new Scanner(System.in);
        String text = scan2.nextLine();
        String[] splited = text.split(" ");
        int temp[][] = new int[3][3];
        // first row
        for (int i = 0; i < 3; i++)
            temp[0][i] = Integer.parseInt(splited[i]);
        System.out.println("Enter the second row, use space between numbers");
        text = scan2.nextLine();
        splited = text.split(" ");
        // second row
        for (int i = 0; i < 3; i++)
            temp[1][i] = Integer.parseInt(splited[i]);
        System.out.println("Enter the third row, use space between numbers");
        text = scan2.nextLine();
        splited = text.split(" ");
        // third row
        for (int i = 0; i < 3; i++)
            temp[2][i] = Integer.parseInt(splited[i]);
        return temp;

    }

    public static void main(String args[]) throws Exception {
        PriorityQueue < Board > queue = new PriorityQueue < Board > (new Comparator < Board > () {
            public int compare(Board b1, Board b2) {
                return Integer.compare(b1.cost, b2.cost);
            }
        });
        Algorithm a = new Algorithm();
        Board b1 = new Board();
        int b[][] = a.readInput();
        b1.board = b;
        b1.parent = null;
        System.out.println("Enter your choice of algorithm\n 1. Uniform Cost Search\n 2. A* with the Misplaced   Tile heuristic.\n 3. A* with the Manhattan distance heuristic.");
        Scanner scan = new Scanner(System.in);
        int c = scan.nextInt();
        a.Search(b1, queue, c);
    }
}
