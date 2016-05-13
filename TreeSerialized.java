package com.prs;

/**
 * Created by peter on 4/23/16.
 */

import java.io.*;

public class TreeSerialized implements Serializable {
        private static final long serialVersionUID = 1L;
        private int nodeID;
        private TreeSerialized leftNode;
        private TreeSerialized rightNode;

        public TreeSerialized(){
        }

        public TreeSerialized(int nodeID, TreeSerialized leftNode, TreeSerialized rightNode) {
            this.nodeID = nodeID;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public int getNodeID() {
            return nodeID;
        }

        public void setNodeID(int nodeID) {
            this.nodeID = nodeID;
        }

        public TreeSerialized getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(TreeSerialized leftNode) {
            this.leftNode = leftNode;
        }

        public TreeSerialized getRightNode() {
            return rightNode;
        }

        public void setRightNode(TreeSerialized rightNode) {
            this.rightNode = rightNode;
        }
    }
