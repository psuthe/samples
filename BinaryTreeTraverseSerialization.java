package com.prs;

import java.io.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by peter on 4/20/16.
 */
public class BinaryTreeTraverseSerialization {

    public static void main(String[] args){

        BinaryTreeTraverseSerialization btt = new BinaryTreeTraverseSerialization();
        TreeSerialized root = btt.initTree();

        try {
            File file = btt.serializeTree(root);
            TreeSerialized deserialized = btt.deserializeTree(file);
            System.out.println("Tree repopulated");
        } catch (Exception e){
            System.out.println("Something's wrong:" + e.getMessage());
        }
    }

    public TreeSerialized initTree(){
        TreeSerialized root = new TreeSerialized(40, null, null);
        TreeSerialized node20 = new TreeSerialized(20, null, null);
        TreeSerialized node30 = new TreeSerialized(30, null, null);
        TreeSerialized node60 = new TreeSerialized(60, null, null);
        TreeSerialized node55 = new TreeSerialized(55, null, null);
        TreeSerialized node45 = new TreeSerialized(45, null, null);
        node20.setLeftNode(node60);
        node30.setLeftNode(node55);
        node30.setRightNode(node45);
        root.setLeftNode(node20);
        root.setRightNode(node30);
        return root;
    }

    public File serializeTree(TreeSerialized tree) throws Exception {
        File file = new File("serialized_tree.ser");
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(tree);
            outStream.close();
            fileOut.close();
        } catch (NotSerializableException e){
            e.printStackTrace();
        }
        return file;
    }

    public TreeSerialized deserializeTree(File file) throws Exception {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        TreeSerialized root = (TreeSerialized) in.readObject();
        in.close();
        fileIn.close();
        return root;
    }
}
