package com.sanilborkar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;


class TernarySearchTreeTest {

    @Test
    void getRoot() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("Hello"));
        TernarySearchTree ternarySearchTree = new TernarySearchTree(words);
        assertEquals(words.get(0).toUpperCase().charAt(0), ternarySearchTree.getRoot().getCharacter(), "TST root does not match");

        ternarySearchTree = new TernarySearchTree();
        assertNull(ternarySearchTree.getRoot());
    }

    @Test
    void isEmpty() {
        TernarySearchTree ternarySearchTree = new TernarySearchTree();
        assertTrue(ternarySearchTree.isEmpty());

        ternarySearchTree.insert("WORLD");
        assertFalse(ternarySearchTree.isEmpty());
    }

    @Test
    void insert() {
        TernarySearchTree ternarySearchTree = new TernarySearchTree();

        // Check null and empty strings
        assertNull(ternarySearchTree.insert(null));
        assertNull(ternarySearchTree.insert(""));

        String firstWordToInsert = "ball";
        char expectedRootChar = firstWordToInsert.toUpperCase().charAt(0);

        // Insert into empty tree
        assertNotNull(ternarySearchTree.insert(firstWordToInsert));
        assertEquals(expectedRootChar, ternarySearchTree.getRoot().getCharacter(), "insert failed");

        assertNotNull(ternarySearchTree.insert("BAT"));
        assertEquals(expectedRootChar, ternarySearchTree.getRoot().getCharacter(), "insert failed");

        assertNotNull(ternarySearchTree.insert("battery"));
        assertEquals(expectedRootChar, ternarySearchTree.getRoot().getCharacter(), "insert failed");

        assertNotNull(ternarySearchTree.insert("airport"));
        assertEquals(expectedRootChar, ternarySearchTree.getRoot().getCharacter(), "insert failed");

        assertNotNull(ternarySearchTree.insert("BaLLet"));
        assertEquals(expectedRootChar, ternarySearchTree.getRoot().getCharacter(), "insert failed");

        assertNotNull(ternarySearchTree.insert("bALLs"));
        assertEquals(expectedRootChar, ternarySearchTree.getRoot().getCharacter(), "insert failed");

        assertNotNull(ternarySearchTree.insert("air"));
        assertEquals(expectedRootChar, ternarySearchTree.getRoot().getCharacter(), "insert failed");
    }

    @Test
    void search() {
        TernarySearchTree ternarySearchTree = new TernarySearchTree();

        // Search in an empty TST
        assertFalse(ternarySearchTree.search("Airport"));

        ternarySearchTree.insert("Bell");

        // Search for a word that contains a word in the TST as its prefix
        assertFalse(ternarySearchTree.search("belittle"));

        // Insert into TST
        ternarySearchTree.insert("Stargazing");
        ternarySearchTree.insert("Apple");
        ternarySearchTree.insert("Battery");
        ternarySearchTree.insert("apocalypse");
        ternarySearchTree.insert("euphoria");
        ternarySearchTree.insert("baGel");
        ternarySearchTree.insert("air");

        // Search for a word present in the TST
        assertTrue(ternarySearchTree.search("bEll"));

        // Search for a word that is the prefix of a word in the TST
        assertFalse(ternarySearchTree.search("star"));

        // Search for a word present in the TST
        assertTrue(ternarySearchTree.search("aPPle"));
        assertTrue(ternarySearchTree.search("air"));

        // Check null and empty strings
        assertFalse(ternarySearchTree.search(null));
        assertFalse(ternarySearchTree.search(""));
    }

    @Test
    void delete() {
        TernarySearchTree ternarySearchTree = new TernarySearchTree();

        // Delete from an empty TST
        assertFalse(ternarySearchTree.delete("Airport"));

        // Insert into TST
        ternarySearchTree.insert("mandolin");
        ternarySearchTree.insert("GuiTar");
        ternarySearchTree.insert("PIANO");
        ternarySearchTree.insert("FlutE");
        ternarySearchTree.insert("Drums");
        ternarySearchTree.insert("bongos");
        ternarySearchTree.insert("guitarist");
        ternarySearchTree.insert("pianobar");

        assertTrue(ternarySearchTree.delete("guitarist"));
        assertFalse(ternarySearchTree.delete("guitarist"));
        assertTrue(ternarySearchTree.delete("guitar"));

        assertTrue(ternarySearchTree.delete("bongos"));
        assertFalse(ternarySearchTree.search("bongos"));

        assertFalse(ternarySearchTree.delete("bongos"));

        assertFalse(ternarySearchTree.delete("cello"));
        assertFalse(ternarySearchTree.search("cello"));

        assertTrue(ternarySearchTree.delete("MANDOLIN"));
        assertFalse(ternarySearchTree.search("MANDOLIN"));

        assertTrue(ternarySearchTree.delete("piAno"));
        assertFalse(ternarySearchTree.search("piAno"));
        assertTrue(ternarySearchTree.search("PIANObar"));

        // Check null and empty strings
        assertFalse(ternarySearchTree.delete(null));
        assertFalse(ternarySearchTree.delete(""));
    }
}