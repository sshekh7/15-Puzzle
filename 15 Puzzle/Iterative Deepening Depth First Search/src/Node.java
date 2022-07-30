import java.util.ArrayList;

public class Node {
    public ArrayList<Node> childNodes = new ArrayList<>();      // Stores the child nodes of the current node
    public Node parent;                                         // Stores the parent node of the current node
    public ArrayList<Integer> gameBoard = new ArrayList<>();    // Instance of the board in a node
    private final int column = 4;                               // The program can be implemented for n-puzzle by changing just this single variable
    public int depthLevel = 0;                                  // Keeps track of the depth of the node

    public Node(ArrayList<Integer> board){
        this.gameBoard.addAll(board);
    }

    // calls all the move methods to get the child nodes.
    public void expandNode(){
        // first find the blank space on the node
        int blankSpace = 0;
        for(int i = 0; i < gameBoard.size(); i++){
            if(gameBoard.get(i) == 0) {
                blankSpace = i;
                break;
            }
        }

        // calling all these methods will store all the child nodes in the childNodes array.
        moveUp(gameBoard, blankSpace);
        moveDown(gameBoard, blankSpace);
        moveRight(gameBoard, blankSpace);
        moveLeft(gameBoard, blankSpace);

    }
    // basic algorithm to check if there is a solution in a node
    public boolean solutionFound(){
//        return gameBoard.toString().equals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0]"); // this works but is slow and static
        for(int i = 0; i < gameBoard.size()-1; i++){
            if(gameBoard.get(i) != i+1) return false;
        }
        return gameBoard.get(gameBoard.size() - 1) == 0;
    }

    private void moveRight(ArrayList<Integer> board, int i){
        if(i % column < column -1){
            // will make a copy of the main puzzle and modify the copy
            ArrayList<Integer> temp = new ArrayList<>();
            copyBoard(temp, board);

            // switching the pieces
            int tempStorage = temp.get(i);
            temp.set(i, temp.get(i+1));
            temp.set(i+1, tempStorage);
            // now add this new state to the childNodes
            Node childNode = new Node(temp);
            childNodes.add(childNode);
            childNode.parent = this;
        }

    }

    private void moveLeft(ArrayList<Integer> board, int i){
        if(i % column > 0){
            // will make a copy of the main puzzle and modify the copy
            ArrayList<Integer> temp = new ArrayList<>();
            copyBoard(temp, board);

            // switching the pieces
            int tempStorage = temp.get(i);
            temp.set(i, temp.get(i-1));
            temp.set(i-1, tempStorage);

            // now add this new state to the childNodes
            Node childNode = new Node(temp);
            childNodes.add(childNode);
            childNode.parent = this;
        }
    }

    private void moveUp(ArrayList<Integer> board, int i){
        if(i >= column){
            // will make a copy of the main puzzle and modify the copy
            ArrayList<Integer> temp = new ArrayList<>();
            copyBoard(temp, board);

            // switching the pieces
            int tempStorage = temp.get(i);
            temp.set(i, temp.get(i-column));
            temp.set(i-column, tempStorage);

            // now add this new state to the childNodes
            Node childNode = new Node(temp);
            childNodes.add(childNode);
            childNode.parent = this;
        }
    }

    private void moveDown(ArrayList<Integer> board, int i){
        if(i + column < board.size()){
            // will make a copy of the main puzzle and modify the copy
            ArrayList<Integer> temp = new ArrayList<>();
            copyBoard(temp, board);

            // switching the pieces
            int tempStorage = temp.get(i);
            temp.set(i, temp.get(i+column));
            temp.set(i+column, tempStorage);

            // now add this new state to the childNodes
            Node childNode = new Node(temp);
            childNodes.add(childNode);
            childNode.parent = this;
        }
    }
    // utility function to copy one board to another board
    private void copyBoard(ArrayList<Integer> newBoard, ArrayList<Integer> board){
        for(int i = 0; i < board.size(); i++){
            newBoard.add(i, board.get(i));
        }
    }

}
