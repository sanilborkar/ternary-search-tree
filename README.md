# Ternary Search Tree

## Overview

A ternary search tree (TST) is a special trie data structure where the child nodes of a standard trie are ordered as a binary search tree.

Reference : [GeeksForGeeks](https://www.geeksforgeeks.org/ternary-search-tree/)

You can learn more about TST on [Wikipedia](https://en.wikipedia.org/wiki/Ternary_search_tree) and [GeeksForGeeks](https://www.geeksforgeeks.org/ternary-search-tree/).

## Usage

**Currently, only strings can be inserted into the TST.**

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

### Get Root

You can retrieve the current root of an initialized TST using the following code:

```java
TernarySearchTreeNode root = ternarySearchTree.getRoot();
```

Since we only store strings into the TST at the moment, ```root``` will hold a single character.

### IsEmpty

You can check if an initialized TST is empty or not using the ```isEmpty``` function.

```java
TernarySearchTree ternarySearchTree = new TernarySearchTree();
boolean isEmptyBeforeInsert = ternarySearchTree.isEmpty();      // returns true

ternarySearchTree.insert("Overcast");
boolean isEmptyAfterInsert = ternarySearchTree.isEmpty();       // returns false
```

In this case, ```isEmptyBeforeInsert``` would be ```true``` and ```isEmptyAfterInsert``` would be ```false```.

### Insert

You can insert a word into an initialized TST using the following code:

```java
String wordToInsert = "Bienvenue";
TernarySearchTreeNode root = ternarySearchTree.insert(wordToInsert);
```

A successful insert returns the root of the resulting TST.

We cannot insert ```null``` or empty strings into the TST. Doing so will return a ```null``` value.

```java
TernarySearchTreeNode firstRoot = ternarySearchTree.insert(null);       // returns null
TernarySearchTreeNode secondRoot = ternarySearchTree.insert("");        // returns null
```

Both ```firstRoot``` and ```secondRoot``` would be ```null``` after the above statements are executed.

### Search

You can search for a word using the *search* method which returns a *true* if the specified word is present in the TST, *false* otherwise.

```java
boolean found = ternarySearchTree.search("Airport");
```

Since we cannot insert ```null``` or empty strings into the TST, searching for a ```null``` value or an empty string would always return ```false```.

```java
boolean nullFound = ternarySearchTree.search(null);     // returns false
boolean emptyFound = ternarySearchTree.search("");      // returns false
```

Both ```nullFound``` and ```emptyFound``` would be ```false``` after the above statements are executed.

### Delete

You can delete a word from the TST using *delete*.

```java
boolean success = ternarySearchTree.delete("Airport");
```

If the word was present in the TST, *delete* returns a *true* else it returns a *false*.

Since we cannot insert ```null``` or empty strings into the TST, trying to delete a ```null``` value or an empty string would always return ```false```.

```java
boolean nullSuccess = ternarySearchTree.delete(null);       // returns false
boolean emptySuccess = ternarySearchTree.delete("");        // returns false
```

Both ```nullSuccess``` and ```emptySuccess``` would be ```false``` after the above statements are executed.

## Author

[Sanil Sinai Borkar](https://github.com/sanilborkar) with :blue_heart:

## License

*ternary-search-tree* is licensed under [MIT license](https://github.com/sanilborkar/ternary-search-tree/blob/master/LICENSE).
