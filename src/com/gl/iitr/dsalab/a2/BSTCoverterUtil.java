package com.gl.iitr.dsalab.a2;

import java.util.Iterator;
import java.util.LinkedList;

public class BSTCoverterUtil {
	
	static Node headNode = null;
	static Node previousNode = null;
	
	static class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	static Node newNode(int data) {
		return new Node(data);
	}

	static Node insert(Node root, int data) {

		if (root == null) {
			return newNode(data);
		} else if (data < root.data) {
			root.left = insert(root.left, data);
		} else if (data > root.data) {
			root.right = insert(root.right, data);
		}
		return root;
	}
	
	static void inorderTravel(Node root, LinkedList<Integer> list) {

		if (root == null) {
			return;
		}

		inorderTravel(root.left, list);
		list.addLast(root.data);
		inorderTravel(root.right, list);
	}

	static int findHeightOfBST(Node root) {

		if (root == null) {
			return 0;
		}

		int leftHeight = 0, rightHeight = 0;

		if (root.left != null) {
			leftHeight = findHeightOfBST(root.left);
		}

		if (root.right != null) {
			rightHeight = findHeightOfBST(root.right);
		}

		int h = (leftHeight > rightHeight) ? leftHeight : rightHeight;
		return h+1;
	}
	
	static Node convertBSTToRightSqewed(Node root) {
		
		if(root == null) {
			return null;
		}
		
		convertBSTToRightSqewed(root.left);
		
		Node leftNode = root.left;
		Node rightNode = root.right;
		
		if(headNode == null) {
			headNode = root;
		}
		else {
			previousNode.right = root;
		}
		
		root.left = null;
		previousNode = root;
		
		convertBSTToRightSqewed(rightNode);
		
		return headNode;
	}
	
	public BSTCoverterUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void convertBSTToRightSqewedByAppraoch1(Node root, int noOfNodes) {

		// Get the height of the original tree
		int originalBSTheight = findHeightOfBST(root) - 1;

		// fetch in-order traversal list
		LinkedList<Integer> list = new LinkedList<Integer>();
		inorderTravel(root, list);

		// Build new tree which is Right Skewed Tree
		Node rightSquedBSTRoot = null;

		System.out.println("Output : ");
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {

			Integer data = it.next();
			System.out.print(data + " ");
			rightSquedBSTRoot = insert(rightSquedBSTRoot, data);
		}

		// Get the height of the converted tree (ie: Skewed BST)
		int convertedBSTheight = findHeightOfBST(rightSquedBSTRoot) - 1;

		System.out.println("\nNumber Of Nodes of BST : " + noOfNodes);
		System.out.println("Height of the Original BST : " + originalBSTheight);
		System.out.println("Height of the Skewed BST : " + convertedBSTheight);

		// Validate the converted tree height for the correct BST tree conversion
		if (convertedBSTheight == (noOfNodes - 1)) {
			System.out.println("Given BST converted to Right Skewed BST successfully");
		} else {
			System.out.println("Given BST convertion to Right Skewed BST failed");
		}
	}
	
	public static void convertBSTToRightSqewedByAppraoch2(Node root, int noOfNodes) {
		
		// Get the height of the original tree
		int originalBSTheight = findHeightOfBST(root) - 1;

		// Build new tree which is Right Skewed Tree
		Node rightSquedBSTRoot = convertBSTToRightSqewed(root);

		// Get the height of the converted tree (ie: Skewed BST)
		int convertedBSTheight = findHeightOfBST(rightSquedBSTRoot) - 1;

		System.out.println("\nNumber Of Nodes of BST : " + noOfNodes);
		System.out.println("Height of the Original BST : " + originalBSTheight);
		System.out.println("Height of the Skewed BST : " + convertedBSTheight);

		// Validate the converted tree height for the correct BST tree conversion
		if (convertedBSTheight == (noOfNodes - 1)) {
			System.out.println("Given BST converted to Right Skewed BST successfully");
		} else {
			System.out.println("Given BST convertion to Right Skewed BST failed");
		}
	}

	public static void main(String[] args) {

		Node root = null;

		
		
		/*
		 * 
		int noOfNodes = 8; 
		root = insert(root, 40);
		root = insert(root, 20);
		root = insert(root, 10);
		root = insert(root, 30);
		root = insert(root, 60);
		root = insert(root, 50);
		root = insert(root, 70);
		root = insert(root, 80);
		*/
		
		int noOfNodes = 6;
		root = insert(root, 50);
		root = insert(root, 30);
		root = insert(root, 60);
		root = insert(root, 10);
		root = insert(root, 55);
		root = insert(root, 35);
		
		//This approach creates a new right Sqewed BST from the existing BST
		//convertBSTToRightSqewedByAppraoch1(root, noOfNodes);
		
		//Modify the existing BST to flatten to Right Sqewed
		convertBSTToRightSqewedByAppraoch2(root, noOfNodes);
	}
}
