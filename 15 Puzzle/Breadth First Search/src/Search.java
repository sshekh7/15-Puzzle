import java.util.ArrayList;
import java.util.HashSet;

public class Search {
    private int numExpandedNodes = 0;
    public ArrayList<Node> BFS (Node root){
        ArrayList<Node> pathSolution = new ArrayList<>();
        boolean puzzleSolved = false;

        /**
         * Two things:
         * 1. An ArrayList has been utilized to implement the queue for the bfs algorithm. This is because
         *      a hashset does not maintains the insertion order of the elements.
         * 2. A copy of the queue is stored in the form of a hashset as it will allow fast lookup.
         */
        ArrayList<Node> unexploredQueue = new ArrayList<>();
        HashSet<String> unexploredHashNodes = new HashSet<>();

        // the hashNodes does not care about the insertion order as it simply stores and looks up the elements
        HashSet<String> hashNodes = new HashSet<>();

        // adding the first element in the queue to start the bfs algorithm.
        unexploredQueue.add(root);
        // the same element is added in the hashset as it will be used for lookup in the future.
        unexploredHashNodes.add(root.gameBoard.toString());

        // main loop that traverses through the queue, expands the nodes and checks if any children is the solution
        while(!puzzleSolved && !unexploredQueue.isEmpty()){

            // get and remove the first element from the unexplored queue, and add the element to hashNodes
            Node curNode = unexploredQueue.get(0);
            unexploredQueue.remove(0);
            unexploredHashNodes.remove(curNode.gameBoard.toString());
            hashNodes.add(curNode.gameBoard.toString());

            // expand the node and increase the counter variable
            curNode.expandNode();
            numExpandedNodes++;
            
            // check all the childNodes of the curNode and see if there is any solution among them.
            // If yes, break the while loop and print the solution. If no, then add the childNodes to the end of the queue
            for(int i = 0; i < curNode.childNodes.size(); i++){
                Node curChild = curNode.childNodes.get(i);
                if(curChild.solutionFound()){
                    puzzleSolved = true;
                    pathSolution.add(curChild);
                    TraceSolution(curChild, pathSolution);
                }
                // utilized hashset for O(1) lookup
                if(!unexploredHashNodes.contains(curChild.gameBoard.toString()) && !hashNodes.contains(curChild.gameBoard.toString())){
                    unexploredQueue.add(curChild);
                }
            }
        }
        return pathSolution;
    }

    // backtracks from the solution node all the way to the root node thus provides with the solution path of the puzzle
    public void TraceSolution(Node startNode, ArrayList<Node> pathSolution) {
        Node curNode = startNode;
        while (curNode.parent != null){
            curNode = curNode.parent;
            pathSolution.add(curNode);
        }
    }
    
    // getter function for numExpandedNodes
    public int getNumExpandedNodes() {
        return numExpandedNodes;
    }
}
