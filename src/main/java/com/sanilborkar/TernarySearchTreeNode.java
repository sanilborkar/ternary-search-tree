package com.sanilborkar;


/*
 * Implementation for a ternary search tree node
 */
public class TernarySearchTreeNode {

    private Character character;
    private boolean isEndOfWord;
    private TernarySearchTreeNode left;
    private TernarySearchTreeNode center;
    private TernarySearchTreeNode right;

    public Character getCharacter() {
        return character;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(final boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public TernarySearchTreeNode getLeft() {
        return left;
    }

    public void setLeft(final TernarySearchTreeNode left) {
        this.left = left;
    }

    public TernarySearchTreeNode getCenter() {
        return center;
    }

    public void setCenter(final TernarySearchTreeNode center) {
        this.center = center;
    }

    public TernarySearchTreeNode getRight() {
        return right;
    }

    public void setRight(final TernarySearchTreeNode right) {
        this.right = right;
    }

    TernarySearchTreeNode(final Character character) {
        this(character, false);
    }

    TernarySearchTreeNode(final Character character, final boolean isEndOfWord) {
        this.character = character;
        this.isEndOfWord = isEndOfWord;
        this.left = null;
        this.center = null;
        this.right = null;
    }
}