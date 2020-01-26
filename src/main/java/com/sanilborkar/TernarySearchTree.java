package com.sanilborkar;

import com.google.common.base.Strings;

import java.util.List;


/*
 * Implementation for a ternary search tree node
 */
public class TernarySearchTree {

    private TernarySearchTreeNode root;

    // Specifies if we have reached the current node from the left, right
    // or center pointer from the parent node
    private enum NodeDirection {
        LEFT,
        CENTER,
        RIGHT
    }

    TernarySearchTree() {
        this.root = null;
    }

    TernarySearchTree(final List<String> words) {
        for (String word : words) {
            this.insert(word);
        }
    }

    public TernarySearchTreeNode getRoot() {
        return root;
    }

    public void setRoot(final TernarySearchTreeNode root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return this.getRoot() == null;
    }

    /**
     * Inserts data into the ternary search tree
     * @param stringToInsert The String to insert
     * @return The {@link TernarySearchTreeNode} pointing to the last character in stringToInsert
     */
    public TernarySearchTreeNode insert(final String stringToInsert) {

        // Store all characters in lowercase
        final String data = stringToInsert.toLowerCase();

        TernarySearchTreeNode currentNode = this.getRoot();
        TernarySearchTreeNode previousNode = currentNode;

        for (int iter = 0; iter < data.length(); iter++) {

            Character ch = data.charAt(iter);

            // Node to insert
            TernarySearchTreeNode dataNode = new TernarySearchTreeNode(ch);
            // Last character of data
            if (iter == data.length() - 1) {
                dataNode.setEndOfWord(true);
            }

            if (this.isEmpty()) {
                this.setRoot(dataNode);
                previousNode = dataNode;
                currentNode = previousNode.getCenter();
                continue;
            }

            // If the character we are trying to insert is equal to the current node data, move center
            NodeDirection nodeDirection = NodeDirection.CENTER;
            while (currentNode != null) {
                previousNode = currentNode;

                if (ch < currentNode.getCharacter()) {
                    currentNode = currentNode.getLeft();
                    nodeDirection = NodeDirection.LEFT;
                } else if (ch > currentNode.getCharacter()) {
                    currentNode = currentNode.getRight();
                    nodeDirection = NodeDirection.RIGHT;
                } else {
                    currentNode = currentNode.getCenter();
                    nodeDirection = NodeDirection.CENTER;
                    iter++;

                    // If iter equals length of the word to insert, we already have that word in the TST.
                    // In this case, we just need to set the "endOfWord" boolean flag to insert this word into the TST.
                    if (iter == data.length()) {
                        previousNode.setEndOfWord(true);
                        return currentNode;
                    }

                    ch = data.charAt(iter);
                    dataNode = new TernarySearchTreeNode(ch);

                    // Last character of data
                    if (iter == data.length() - 1) {
                        dataNode.setEndOfWord(true);
                    }
                }
            }

            // Empty location - place the character (node)
            // currentNode == null
            switch (nodeDirection) {
                case LEFT:
                    previousNode.setLeft(dataNode);
                    break;
                case CENTER:
                    previousNode.setCenter(dataNode);
                    break;
                case RIGHT:
                    previousNode.setRight(dataNode);
                    break;
            }

            previousNode = dataNode;
            currentNode = previousNode.getCenter();
        }

        return previousNode;
    }

    /**
     * Finds a word in the TST
     * @param wordToLookup The word to search for
     * @return true if wordToLookup exists, false otherwise
     */
    public boolean find(final String wordToLookup) {

        // If tree is empty or the word is null/empty, return false
        if (this.isEmpty() || Strings.isNullOrEmpty(wordToLookup)) {
            return false;
        }

        // Convert the search keyword to lowercase as the TST is maintained in lower case
        final String searchWord = wordToLookup.toLowerCase();

        TernarySearchTreeNode currentNode = this.getRoot();
        for (int i = 0; i < searchWord.length() && currentNode != null; i++) {

            Character ch = searchWord.charAt(i);

            while (currentNode != null && ch != currentNode.getCharacter()) {

                if (ch < currentNode.getCharacter()) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode = currentNode.getRight();
                }
            }

            // All potential paths containing the searchWord are exhausted
            if (currentNode == null) {
                return false;
            }

            // If we are at the last character of the search string
            if (i == searchWord.length() - 1 && currentNode.isEndOfWord()) {
                return true;
            }

            currentNode = currentNode.getCenter();
        }

        return false;
    }
}