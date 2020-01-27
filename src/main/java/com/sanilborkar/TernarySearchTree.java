package com.sanilborkar;

import com.google.common.base.Strings;

import java.util.List;


/**
 * Implementation for a ternary search tree (TST).
 *
 * @author sanilborkar
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

    /**
     * Constructs an empty ternary search tree (TST).
     */
    public TernarySearchTree() {
        this.root = null;
    }

    /**
     * Constructs and initializes a ternary search tree (TST) with the words specified.
     * @param words list of words to initialize the tree with
     */
    public TernarySearchTree(final List<String> words) {
        for (String word : words) {
            this.insert(word);
        }
    }

    /**
     * Returns the root of the TST.
     * @return the root of the TST
     */
    public TernarySearchTreeNode getRoot() {
        return root;
    }

    private void setRoot(final TernarySearchTreeNode root) {
        this.root = root;
    }

    /**
     * Checks if the TST is empty or not.
     * @return true if the TST is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.getRoot() == null;
    }

    /**
     * Inserts data into the TST.
     * @param stringToInsert the string to insert
     * @return the {@link TernarySearchTreeNode} pointing to the last character in stringToInsert
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
     * Finds a word in the TST.
     * @param wordToLookup the word to search for
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