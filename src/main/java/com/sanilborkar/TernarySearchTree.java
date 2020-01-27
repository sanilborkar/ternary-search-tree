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
     * Inserts a string into the TST.
     * @param stringToInsert the string to insert
     * @return the root of the TST after the insert
     */
    public TernarySearchTreeNode insert(final String stringToInsert) {
        root = insert(this.root, stringToInsert.toUpperCase().toCharArray(), 0);
        return getRoot();
    }

    private TernarySearchTreeNode insert(TernarySearchTreeNode node, final char[] word, final int index) {

        final char currentChar = word[index];

        if (node == null) {
            node = new TernarySearchTreeNode(currentChar);
        }

        if (currentChar < node.getCharacter()) {
            node.left = insert(node.getLeft(), word, index);
        } else if (currentChar > node.getCharacter()) {
            node.right = insert(node.getRight(), word, index);
        } else {

            if (index + 1 < word.length) {
                node.center = insert(node.getCenter(), word, index + 1);
            } else {
                node.setEndOfWord(true);
            }
        }

        return node;
    }

    /**
     * Inserts data into the TST.
     * @param stringToInsert the string to insert
     * @return the {@link TernarySearchTreeNode} pointing to the last character in stringToInsert
     */
    private TernarySearchTreeNode insertIter(final String stringToInsert) {

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
     * Searches for a word in the TST.
     * @param wordToSearch the word to search for
     * @return true if wordToLookup exists, false otherwise
     */
    public boolean search(final String wordToSearch) {
        return search(getRoot(), wordToSearch.toUpperCase().toCharArray(), 0);
    }

    private boolean search(final TernarySearchTreeNode node, final char[] word, final int index) {

        if (node == null) {
            return false;
        }

        final char currentChar = word[index];
        if (currentChar < node.getCharacter()) {
            return search(node.getLeft(), word, index);
        } else if (currentChar > node.getCharacter()) {
            return search(node.getRight(), word, index);
        } else {
            if (index == word.length - 1) {
                return node.isEndOfWord();
            }

            return search(node.getCenter(), word, index+1);
        }
    }

    /**
     * Searches for a word in the TST.
     * @param wordToLookup the word to search for
     * @return true if wordToLookup exists, false otherwise
     */
    private boolean searchIter(final String wordToLookup) {

        // If tree is empty or the word is null/empty, return false
        if (this.isEmpty() || Strings.isNullOrEmpty(wordToLookup)) {
            return false;
        }

        // Convert the search keyword to uppercase the TST is maintained in upper case
        final String searchWord = wordToLookup.toUpperCase();

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

    /**
     * Deletes a string from the TST.
     * @param stringToDelete the string to delete from the TST
     * @return true if word was deleted, false if word was not present in the TST
     */
    public boolean delete(final String stringToDelete) {
        return delete(getRoot(), stringToDelete.toUpperCase().toCharArray(), 0);
    }

    private boolean delete(final TernarySearchTreeNode node, final char[] word, final int index) {

        if (node == null) {
            return false;
        }

        final char currentChar = word[index];
        if (currentChar < node.getCharacter()) {
            return delete(node.getLeft(), word, index);
        } else if (currentChar > node.getCharacter()) {
            return delete(node.getRight(), word, index);
        } else {
            if (index == word.length - 1 && node.isEndOfWord()) {
                node.setEndOfWord(false);
                return true;
            }

            return delete(node.getCenter(), word, index + 1);
        }
    }
}