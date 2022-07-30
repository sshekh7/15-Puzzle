import java.util.ArrayList;
import java.util.Scanner;

public class puzzle_game {
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter puzzle board: ");
        String myString = input.nextLine();

        String[] arrOfStr = myString.split(" ", 16);
        ArrayList<Integer> testBoard = new ArrayList<>();
        for(int i = 0; i < arrOfStr.length; i++){
            testBoard.add(Integer.parseInt(arrOfStr[i]));
        }

        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long startTime = System.currentTimeMillis();
        Node initialBoard = new Node(testBoard);
        Search boardSearch = new Search();
        ArrayList<Node> solutionPath = boardSearch.BFS(initialBoard);
        StringBuilder solution = new StringBuilder();
        for(int i = 0; i < solutionPath.size()-1; i++){
            if(solutionPath.get(i).gameBoard.indexOf(0) == solutionPath.get(i+1).gameBoard.indexOf(0)+4) solution.append('D');
            else if(solutionPath.get(i).gameBoard.indexOf(0) == solutionPath.get(i+1).gameBoard.indexOf(0)-4) solution.append('U');
            else if(solutionPath.get(i).gameBoard.indexOf(0) == solutionPath.get(i+1).gameBoard.indexOf(0)-1) solution.append('L');
            else if(solutionPath.get(i).gameBoard.indexOf(0) == solutionPath.get(i+1).gameBoard.indexOf(0)+1) solution.append('R');
        }
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        System.out.println("Moves: " + solution.reverse());
        System.out.println("Number of Nodes expanded: " + boardSearch.getNumExpandedNodes());
        System.out.println("Time Taken: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
        System.out.println("Memory Used: " + (afterUsedMem-beforeUsedMem)/1000.0 + "kb") ;

    }
}
