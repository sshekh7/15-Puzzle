import java.util.ArrayList;
import java.util.Scanner;

public class puzzle_game {
    public static void main (String[] args) {
        // scanner is utilized to take user input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter puzzle board: ");
        String myString = input.nextLine();

        // split method splits the user input when it sees any spaces
        String[] arrOfStr = myString.split(" ", 16);
        ArrayList<Integer> testBoard = new ArrayList<>();
        // convert single string integers to int values and store them in the testBoard array
        for (String s : arrOfStr) {
            testBoard.add(Integer.parseInt(s));
        }

        System.gc();
        // for calculating the runtime and memory used by the program
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long startTime = System.currentTimeMillis();

        // instance of the initial board
        Node initialBoard = new Node(testBoard);

        // instance of the search class
        Search boardSearch = new Search(1);


        // uses the BFS algorithm to find the solution
        ArrayList<Node> solutionPath = boardSearch.BFS(initialBoard);

        // Manipulate the solution in the required output format
        StringBuilder solution = new StringBuilder();
        for(int i = 0; i < solutionPath.size()-1; i++){
            if(solutionPath.get(i).gameBoard.indexOf(0) == solutionPath.get(i+1).gameBoard.indexOf(0)+4) solution.append('D');
            else if(solutionPath.get(i).gameBoard.indexOf(0) == solutionPath.get(i+1).gameBoard.indexOf(0)-4) solution.append('U');
            else if(solutionPath.get(i).gameBoard.indexOf(0) == solutionPath.get(i+1).gameBoard.indexOf(0)-1) solution.append('L');
            else if(solutionPath.get(i).gameBoard.indexOf(0) == solutionPath.get(i+1).gameBoard.indexOf(0)+1) solution.append('R');
        }

        // to calculate the memory used by the program
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // required output values
        System.out.println("1. Heuristic: Number of misplaced tiles");
        System.out.println("   Moves: " + solution.reverse());
        System.out.println("   Number of Nodes expanded: " + boardSearch.getNumExpandedNodes());
        System.out.println("   Time Taken: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
        System.out.println("   Memory Used: " + (afterUsedMem-beforeUsedMem)/1000.0 + "kb") ;


        System.gc();

        // keeps track of the initial memory
        beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // keeps track of the initial time of the program before execution of the 2nd herustic
        startTime = System.currentTimeMillis();

        // root board of the game or the initial state of the game
        Node initialBoard2 = new Node(testBoard);

        // search instance that applies the manhattan distance heuristic on the initial board
        Search boardSearch2 = new Search(2);

        // applies the manhattan distance heuristic and stores the solution path in the solution path arraylist
        ArrayList<Node> solutionPath2 = boardSearch2.BFS(initialBoard2);

        // Manipulate the solution in the required output format
        StringBuilder solution2 = new StringBuilder();
        for(int i = 0; i < solutionPath2.size()-1; i++){
            if(solutionPath2.get(i).gameBoard.indexOf(0) == solutionPath2.get(i+1).gameBoard.indexOf(0)+4) solution2.append('D');
            else if(solutionPath2.get(i).gameBoard.indexOf(0) == solutionPath2.get(i+1).gameBoard.indexOf(0)-4) solution2.append('U');
            else if(solutionPath2.get(i).gameBoard.indexOf(0) == solutionPath2.get(i+1).gameBoard.indexOf(0)-1) solution2.append('L');
            else if(solutionPath2.get(i).gameBoard.indexOf(0) == solutionPath2.get(i+1).gameBoard.indexOf(0)+1) solution2.append('R');
        }

        // to calculate the memory used by the program
        afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // required output values
        System.out.println("2. Heuristic: Manhattan Distance");
        System.out.println("   Moves: " + solution2.reverse());
        System.out.println("   Number of Nodes expanded: " + boardSearch2.getNumExpandedNodes());
        System.out.println("   Time Taken: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
        System.out.println("   Memory Used: " + (afterUsedMem-beforeUsedMem)/1000.0 + "kb") ;
    }
}
