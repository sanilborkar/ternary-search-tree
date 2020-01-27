package com.sanilborkar;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


class TernarySearchTreeTest {

    @Test
    void getRoot() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("Hello"));
        TernarySearchTree ternarySearchTree = new TernarySearchTree(words);
        assert ternarySearchTree.getRoot().getCharacter() == words.get(0).toUpperCase().charAt(0);

        ternarySearchTree = new TernarySearchTree();
        assert ternarySearchTree.getRoot() == null;
    }

    @Test
    void isEmpty() {
        TernarySearchTree ternarySearchTree = new TernarySearchTree();
        assert ternarySearchTree.isEmpty();
    }

    /**
     * The insertion can be verified using a visual representation of the TST
     * at https://www.cs.usfca.edu/~galles/visualization/TST.html
     */
    @Test
    void insert() {
        TernarySearchTree ternarySearchTree = new TernarySearchTree();

        String firstWordToInsert = "ball";
        char expectedRootChar = firstWordToInsert.toUpperCase().charAt(0);

        // Insert into empty tree
        assert ternarySearchTree.insert(firstWordToInsert) != null;
        assert ternarySearchTree.getRoot().getCharacter() == expectedRootChar;

        assert ternarySearchTree.insert("BAT") != null;
        assert ternarySearchTree.getRoot().getCharacter() == expectedRootChar;

        assert ternarySearchTree.insert("battery") != null;
        assert ternarySearchTree.getRoot().getCharacter() == expectedRootChar;

        assert ternarySearchTree.insert("airport") != null;
        assert ternarySearchTree.getRoot().getCharacter() == expectedRootChar;

        assert ternarySearchTree.insert("BaLLet") != null;
        assert ternarySearchTree.getRoot().getCharacter() == expectedRootChar;

        assert ternarySearchTree.insert("bALLs") != null;
        assert ternarySearchTree.getRoot().getCharacter() == expectedRootChar;

        assert ternarySearchTree.insert("air") != null;
        assert ternarySearchTree.getRoot().getCharacter() == expectedRootChar;
    }

    /**
     * The insertion can be verified using a visual representation of the TST
     * at https://www.cs.usfca.edu/~galles/visualization/TST.html
     */
    @Test
    void search() {
        TernarySearchTree ternarySearchTree = new TernarySearchTree();

        // Search in an empty TST
        assert !ternarySearchTree.search("Airport");

        ternarySearchTree.insert("Bell");

        // Search for a word that contains a word in the TST as its prefix
        assert !ternarySearchTree.search("belittle");

        // Insert into TST
        ternarySearchTree.insert("Stargazing");
        ternarySearchTree.insert("Apple");
        ternarySearchTree.insert("Battery");
        ternarySearchTree.insert("apocalypse");
        ternarySearchTree.insert("euphoria");
        ternarySearchTree.insert("baGel");
        ternarySearchTree.insert("air");

        // Search for a word present in the TST
        assert ternarySearchTree.search("bEll");

        // Search for a word that is the prefix of a word in the TST
        assert !ternarySearchTree.search("star");

        // Search for a word present in the TST
        assert ternarySearchTree.search("aPPle");
        assert ternarySearchTree.search("air");
    }
}