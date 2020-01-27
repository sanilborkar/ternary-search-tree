package com.sanilborkar;


/**
 * Implementation for a ternary search tree (TST) node.
 *
 * @author sanilborkar
 */
public class TernarySearchTreeNode {

    private Character character;
    private boolean isEndOfWord;
    TernarySearchTreeNode left;
    TernarySearchTreeNode center;
    TernarySearchTreeNode right;

    public Character getCharacter() {
        return character;
    }

    boolean isEndOfWord() {
        return isEndOfWord;
    }

    void setEndOfWord(final boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    TernarySearchTreeNode getLeft() {
        return left;
    }

    void setLeft(final TernarySearchTreeNode left) {
        this.left = left;
    }

    TernarySearchTreeNode getCenter() {
        return center;
    }

    void setCenter(final TernarySearchTreeNode center) {
        this.center = center;
    }

    TernarySearchTreeNode getRight() {
        return right;
    }

    void setRight(final TernarySearchTreeNode right) {
        this.right = right;
    }

    /**
     * Constructs a TST node containing the character specified.
     * @param character the character to be inserted into the TST node
     */
    TernarySearchTreeNode(final Character character) {
        this(character, false);
    }

    /**
     * Constructs a TST node containing the character specified.
     * @param character the character to be inserted into the TST node
     * @param isEndOfWord indicates if this TST node is the end of the word that it represents
     */
    TernarySearchTreeNode(final Character character, final boolean isEndOfWord) {
        this.character = character;
        this.isEndOfWord = isEndOfWord;
        this.left = null;
        this.center = null;
        this.right = null;
    }
}