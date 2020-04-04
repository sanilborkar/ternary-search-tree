# Ternary Search Tree

## Overview

A ternary search tree (TST) is a special trie data structure where the child nodes of a standard trie are ordered as a binary search tree.

Reference : [GeeksForGeeks](https://www.geeksforgeeks.org/ternary-search-tree/)

You can learn more about TST on [Wikipedia](https://en.wikipedia.org/wiki/Ternary_search_tree) and [GeeksForGeeks](https://www.geeksforgeeks.org/ternary-search-tree/).

## Usage

Some common usages are given below.

### Constructor

The default constructor initializes an empty TST.

```java
TernarySearchTree ternarySearchTree = new TernarySearchTree();
```

You can even initialize it with a list of words like a dictionary.

```java
ArrayList<String> words = new ArrayList<>();
words.add("hellow", "World", "very", "good", "evening");
TernarySearchTree ternarySearchTree = new TernarySearchTree(words);
```

### Insert

You can insert a word into an initialized TST using the following code:

```java
String wordToInsert = "Bienvenue";
TernarySearchTreeNode root = ternarySearchTree.insert(wordToInsert);
```

A successful insert returns the root of the resulting TST.

### Search

You can search for a word using the *search* method which returns a *true* if the specified word is present in the TST, *false* otherwise.

```java
boolean found = ternarySearchTree.search("Airport");
```

### Delete

You can delete a word from the TST using *delete*.

```java
boolean success = ternarySearchTree.delete("Airport");
```

If the word was present in the TST, *delete* returns a *true* else it returns a *false*.

## Author

[Sanil Sinai Borkar](https://github.com/sanilborkar) with :blue_heart:

## License

*ternary-search-tree* is licensed under [MIT license](https://github.com/sanilborkar/ternary-search-tree/blob/master/LICENSE).
