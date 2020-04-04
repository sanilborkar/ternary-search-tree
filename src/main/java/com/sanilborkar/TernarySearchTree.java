package com.sanilborkar;

import java.util.ArrayList;
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

    private class DeleteResult {
        boolean success;
        boolean isSubstring;

        DeleteResult(boolean success, boolean isSubstring) {
            this.success = success;
            this.isSubstring = isSubstring;
        }
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
     * Searches for a word in the TST.
     * @param wordToSearch the word to search for
     * @return true if wordToSearch exists, false otherwise
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
     * Deletes a string from the TST.
     * @param stringToDelete the string to delete from the TST
     * @return true if word was deleted, false if word was not present in the TST
     */
    public boolean delete(final String stringToDelete) {

        ArrayList<NodeDirection> directions = new ArrayList<>();

        final DeleteResult result = delete(getRoot(), stringToDelete.toUpperCase().toCharArray(), 0, directions);
        if (result.success && !result.isSubstring) {
            cleanUp(getRoot(), stringToDelete.toUpperCase().toCharArray(), 0);
        }

        return result.success;
    }

    private DeleteResult delete(final TernarySearchTreeNode node, final char[] word, final int index, final ArrayList<NodeDirection> directions) {

        if (node == null) {
            return new DeleteResult(false, false);
        }

        final char currentChar = word[index];
        if (currentChar < node.getCharacter()) {

            directions.add(NodeDirection.LEFT);
            return delete(node.getLeft(), word, index, directions);
        } else if (currentChar > node.getCharacter()) {

            directions.add(NodeDirection.RIGHT);
            return delete(node.getRight(), word, index, directions);
        } else {

            if (index == word.length - 1 && node.isEndOfWord()) {

                node.setEndOfWord(false);
                directions.add(NodeDirection.CENTER);

                boolean isSubstring;
                if (node.getCenter() != null) {
                    directions.add(NodeDirection.CENTER);
                    isSubstring = true;
                } else {
                    isSubstring = false;
                }

                return new DeleteResult(true, isSubstring);
            }

            directions.add(NodeDirection.CENTER);
            return delete(node.getCenter(), word, index + 1, directions);
        }
    }

    private boolean cleanUp(TernarySearchTreeNode node, final char[] word, final int index) {

        if (node == null) {
            return false;
        }

        final char currentChar = word[index];
        if (currentChar < node.getCharacter()) {
            return cleanUp(node.getLeft(), word, index);
        } else if (currentChar > node.getCharacter()) {
            return cleanUp(node.getRight(), word, index);
        } else {

            if (index == word.length - 1 && node.isEndOfWord()) {

                if (node.isLeafNode()) {
                    node = null;
                    return true;
                }
            }

            boolean isLeafNode = cleanUp(node.getCenter(), word, index + 1);
            if (isLeafNode && node.isLeafNode()) {
                node = null;
                return true;
            }
        }

        return false;
    }
}