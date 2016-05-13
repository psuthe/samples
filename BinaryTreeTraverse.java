package com.prs;

import java.io.*;
import java.util.*;

/**
 * Created by peter on 4/20/16.
 */
public class BinaryTreeTraverse {

    public static void main(String[] args){

        BinaryTreeTraverse btt = new BinaryTreeTraverse();
        Tree root = btt.initTree();

        try {
            File file = btt.serializeTree(root);
            Tree rootRepoped = btt.deserializeTree(file);
            System.out.println("Tree repopulated");
        } catch (Exception e){
            System.out.println("Something's wrong");
        }
    }

    public Tree initTree(){
        Tree root = new Tree(40, null, null);
        Tree node20 = new Tree(20, null, null);
        Tree node30 = new Tree(30, null, null);
        Tree node60 = new Tree(60, null, null);
        Tree node55 = new Tree(55, null, null);
        Tree node45 = new Tree(45, null, null);
        node20.setLeftNode(node60);
        node30.setLeftNode(node55);
        node30.setRightNode(node45);
        root.setLeftNode(node20);
        root.setRightNode(node30);
        return root;
    }

    public File serializeTree(Tree tree) throws Exception{
        File file = new File("serialized_tree.txt");
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        processTree(writer, tree, tree.getNodeID(), ' ');
        writer.flush();
        writer.close();
        return file;
    }

    public void processTree(PrintWriter writer, Tree tree, int parent, char side) throws Exception{
        if (tree == null){
            return;
        }
        StringBuilder nodeOutput = new StringBuilder("" + tree.getNodeID());
        nodeOutput.append("-");
        nodeOutput.append(parent);
        if (side != ' '){
            nodeOutput.append("-");
            nodeOutput.append(side);
        }
        writer.println(nodeOutput.toString());

        processTree(writer, tree.getLeftNode(), tree.getNodeID(), 'l');
        processTree(writer, tree.getRightNode(), tree.getNodeID(), 'r');
    }

    public Tree deserializeTree(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Tree root = repopulateTree(reader);
        reader.close();
        return root;
    }

    public Tree repopulateTree(BufferedReader reader) throws Exception {
        Tree root = null;
        Map<Tree, AbstractMap.SimpleEntry> links = new HashMap<>();
        Map<String, Tree> nodes = new HashMap<>();
        String input;
        while ((input = reader.readLine()) != null){
            StringTokenizer st = new StringTokenizer(input, "-");
            String nodeID = st.nextToken();
            String parent = st.nextToken();
            String side = (st.hasMoreTokens()) ? st.nextToken() : "";
            Tree node = new Tree(Integer.parseInt(nodeID), null, null);
            nodes.put(nodeID, node);
            if (!nodeID.equals(parent)) {
                links.put(node, new AbstractMap.SimpleEntry(parent, side));
            } else {
                root = node;
            }
        }

        links.entrySet().forEach((e) -> linkChildToParent(e.getKey(), e.getValue(), nodes));

        return root;
    }

    private void linkChildToParent(Tree child, AbstractMap.SimpleEntry parentSide, Map<String, Tree> nodes){
        String parentID = parentSide.getKey().toString();
        String side = parentSide.getValue().toString();
        Tree parent = nodes.get(parentID);
        if (side.equals("l")) {
            parent.setLeftNode(child);
        } else {
            parent.setRightNode(child);
        }
    }

    public class Tree {
        private int nodeID;
        private Tree leftNode;
        private Tree rightNode;

        public Tree(int nodeID, Tree leftNode, Tree rightNode) {
            this.nodeID = nodeID;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public int getNodeID() {
            return nodeID;
        }

        public Tree getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Tree leftNode) {
            this.leftNode = leftNode;
        }

        public Tree getRightNode() {
            return rightNode;
        }

        public void setRightNode(Tree rightNode) {
            this.rightNode = rightNode;
        }
    }
}
