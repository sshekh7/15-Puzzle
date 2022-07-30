import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class Search {
    private int numExpandedNodes = 0;                                 // keeps track of the nodes expanded nodes
    private boolean puzzleSolved = false;                             // acts as a flag for the while loop
    private final ArrayList<Node> solutionPath = new ArrayList<>();         // stores the final solution of the puzzle

    // Iterative deepening search implementation, based on pseudo-code taken form the textbook page 99 figure 3.12
    public ArrayList<Node> iterativeDeepeningSearch(Node root){
        int depth = 0;
        while(!puzzleSolved || depth == 100){
            depthLimitedSearch(root, depth);                     // calls the depthLimitedSearch function on each step
            depth += 1;                                          // better results if depth += 2
        }
        return solutionPath;
    }

    // Utility function for iterativeDeepeningSearch that performs depth first search on the puzzle board till a certain depth
    private void depthLimitedSearch(Node root, int depth) {
        root.depthLevel = 0;                                    // sets the depth of the root node to zero
        Stack<Node> stack = new Stack<>();                      // stack for keeping track of the elements

        stack.push(root);                                       // add the first element to the stack

        while(!stack.isEmpty()){
            Node curNode = stack.pop();                         // pops the top element in the stack

            /*
                If the solution is found on the curNode, then it is added to the solution path and then traced back to the root node.
                Secondly, if the curNode is not the solution, then check for depth, check if there is a cycle, and expand the curNode.
                Lastly, iterate through the child nodes, update their depth levels and add them to the stack.
             */
            if(curNode.solutionFound()){
                puzzleSolved = true;
                solutionPath.add(curNode);
                TraceSolution(curNode, solutionPath);
                return;
            }

            // the dfs algorithm's while loop will break at a certain depth
            if(curNode.depthLevel > depth) continue;

            // check if curNode is in a cycle and if not, then expand the curNode
            // Add curNode's child to the stack after updating their depth levels
            else if(!isCycle(curNode)){
                numExpandedNodes++;
                curNode.expandNode();                                    // the node is expanded here
                for(int i = 0; i < curNode.childNodes.size(); i++) {
                    Node curChild = curNode.childNodes.get(i);
                    curChild.depthLevel = curNode.depthLevel+1;          // updating the depth level of the child nodes
                    stack.push(curChild);                                // add the child to the stack
                }
            }
        }

    }

    // utility function for depthLimitedSearch
    // isCycle traverses all the way to the root of the tree.
    private boolean isCycle(Node curNode){
        HashSet<String> parentNodes = new HashSet<>();                            // tracks the ancestors of the current node
        while(curNode.parent != null){
            if(parentNodes.contains(curNode.gameBoard.toString())) return true;   // will find a duplicate if there is a cycle
            parentNodes.add(curNode.gameBoard.toString());                        // if does not find a duplicate then add the current node ot the hashset
            curNode = curNode.parent;                                             // traverses up the tree until the root of the tree
        }
        return false;                                                             // reaches the end of the while loop if it doesn't find a cycle
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
