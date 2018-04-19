package TestBot0;

import battlecode.common.RobotController;
import scala.Int;
import scala.Unit;

import java.util.ArrayList;
import java.util.HashMap;


// A class implementing a simple version of behaviour trees for java.

public class BehaviourTree {

    public class Node{
        /**
         * Represents Nodes
         */
        public ArrayList<Node> children; // A list of all children of this Node. It is empty if the node is a leaf
        public NodeType type; // The type of the node
        public String functionName; // The name of the function the node contains. It is an empty string if the node is not a leaf.

        public Node(){
            /**
             * Default constructor.
             *
             */
            type = null;
            children = new ArrayList<>();
            functionName = "";
        }

        public Node(NodeType t){
            /**
             * This constructor is typically used to build non-leaf nodes.
             * @param : t : the type of the node to build.
             */
            children = new ArrayList<>();
            type = t;
            functionName = "";
        }

        public Node(NodeType t, String f){
            /**
             * This constructor is used to build leaves.
             * @param: t : the type of the node
             * @param: f : the name of the function to call.
             */
            children = new ArrayList<>();
            type = t;
            functionName = f;
        }

        public void add_child(Node child){
            /**
             * Adds a child at the end of the children list.
             * @param: child : the child to add.
             */
            children.add(child);
        }

        public Functions.ReturnType tick_down(RobotController rc, UnitController uc){
            /**
             *
             * Propagates the tick down the tree and return the result of the functions in the leaves.
             */
            if (type==null)
                return Functions.ReturnType.FAIL;

            switch (type){
                case LEAF:{
                    return Functions.run(functionName,rc,uc);
                }
                case QUESTION:{
                    for (Node n : children){
                        Functions.ReturnType result = n.tick_down(rc,uc);
                        if (result != Functions.ReturnType.FAIL){
                            return result;
                        }
                    }
                    return Functions.ReturnType.SUCCESS;
                }
                case SEQUENTIAL:{
                    for (Node n : children){
                        Functions.ReturnType result = n.tick_down(rc,uc);
                        if (result != Functions.ReturnType.SUCCESS)
                            return result;
                    }
                    return Functions.ReturnType.FAIL;
                }

            }
            return Functions.ReturnType.FAIL;
        }
    }

    private Node root;

    public BehaviourTree(){
        /**
         *
         * Default constructor
         */
        root = new Node();
    }

    public BehaviourTree(String text){
        /**
         *
         * Construct a BeahaviourTree from a text.
         * The text must be of the form :
         * 1 line per node.
         * "(node_id) (node_type) (node_child1) (node_child2) ... (node_child_last)" if the node is not a leaf
         * "(node_id) (leaf) (function_name)" if the node is a leaf.
         * The root id should be 0.
         */
        HashMap<Integer,Node> allNodes = new HashMap<>();
        String[] lines = text.split("\n");
        for (String line : lines){
            String[] elements = line.split(" ");
            if (elements[1].equals("seq")){
                allNodes.put(Integer.parseInt(elements[0]),new Node(NodeType.SEQUENTIAL));
            }
            if (elements[1].equals("question")){
                allNodes.put(Integer.parseInt(elements[0]),new Node(NodeType.QUESTION));
            }
            if (elements[1].equals("leaf")){
                allNodes.put(Integer.parseInt(elements[0]),new Node(NodeType.LEAF,elements[2]));
            }
        }
        for (String line:lines){
            String[] elements = line.split(" ");
            if (!elements[1].equals("leaf")){
                for (int i = 2; i < elements.length; i++){
                    allNodes.get(Integer.parseInt(elements[0])).add_child(allNodes.get(Integer.parseInt(elements[i])));
                }
            }
        }
        root = allNodes.get(0);
    }

    public Functions.ReturnType tickDown(RobotController rc,UnitController uc){
        return root.tick_down(rc,uc);
    }

}
