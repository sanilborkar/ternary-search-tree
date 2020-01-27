package com.sanilborkar;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


class TernarySearchTreeTest {

    @Test
    void getRoot() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("Hello"));
        TernarySearchTree ternarySearchTree = new TernarySearchTree(words);
        assert ternarySearchTree.getRoot().getCharacter() == words.get(0).toLowerCase().charAt(0);

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

        // Insert into empty tree
        assert ternarySearchTree.insert("Ball") != null;

        assert ternarySearchTree.insert("BAT") != null;

        assert ternarySearchTree.insert("battery") != null;

        assert ternarySearchTree.insert("airport") != null;

        assert ternarySearchTree.insert("BaLLet") != null;

        assert ternarySearchTree.insert("bALLs") != null;

        assert ternarySearchTree.insert("air") != null;
    }

    /**
     * The insertion can be verified using a visual representation of the TST
     * at https://www.cs.usfca.edu/~galles/visualization/TST.html
     */
    @Test
    void find() {
        TernarySearchTree ternarySearchTree = new TernarySearchTree();

        // Search in an empty TST
        assert !ternarySearchTree.find("Airport");

        ternarySearchTree.insert("Bell");

        // Search for a word that contains a word in the TST as its prefix
        assert !ternarySearchTree.find("belittle");

        // Insert into TST
        ternarySearchTree.insert("Stargazing");
        ternarySearchTree.insert("Apple");
        ternarySearchTree.insert("Battery");
        ternarySearchTree.insert("apocalypse");
        ternarySearchTree.insert("euphoria");
        ternarySearchTree.insert("baGel");
        ternarySearchTree.insert("air");

        // Search for a word present in the TST
        assert ternarySearchTree.find("bEll");

        // Search for a word that is the prefix of a word in the TST
        assert !ternarySearchTree.find("star");

        // Search for a word present in the TST
        assert ternarySearchTree.find("aPPle");
        assert ternarySearchTree.find("air");
    }
}