package io.github.sanilborkar;


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

    /**
     * Returns the character present at this TST node.
     * @return the character at this TST node
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Checks if this TST node is the end of a word.
     * @return true if this TST node is the end of a word, false otherwise.
     */
    boolean isEndOfWord() {
        return isEndOfWord;
    }

    void setEndOfWord(final boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    /**
     * Returns the left child of this TST node.
     * @return the left child of this TST node
     */
    TernarySearchTreeNode getLeft() {
        return left;
    }

    /**
     * Returns the center/middle child of this TST node.
     * @return the center/middle child of this TST node
     */
    TernarySearchTreeNode getCenter() {
        return center;
    }

    /**
     * Returns the right child of this TST node.
     * @return the right child of this TST node
     */
    TernarySearchTreeNode getRight() {
        return right;
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

    boolean isLeafNode() {
        return this.left == null && this.center == null && this.right == null;
    }
}