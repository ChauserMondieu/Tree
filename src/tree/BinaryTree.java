package tree;

import java.util.LinkedList;
import java.util.List;

public class BinaryTree {
	//设计内部类，存储二叉树节点信息
	//design inner class, store node information
	static class Node{
		Node leftChild = null;
		Node rightChild = null;
		Node parent = null;
		Element val;

		public Node(Node leftChild, Node rightChild, Node parent, Element val) {
			super();
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.parent = parent;
			this.val = val;
		}
		
		//Single Node as root
		public Node(Element val){
			this(null,null,null, val);
		}
		
		//Single Node as leaf node
		public Node(Node parent, Element val){
			this(null,null,parent,val);
		}

	}
	
	
	// set up basic properties of this tree
	private Node root;
	private int size = 0;
	
	//constructor of the binary tree
	public BinaryTree() {
		super();
	}
	
	public BinaryTree(Node root) {
		super();
		this.root = root;
		this.size ++;
	}
	
	// add element, using recursion
	public boolean addElement(Element e){
		if(root==null){
			root = new Node(e);
			size ++;
			return true;
		}else{
			addElementWithParent(null,root,e);
			return true;
		}
	}

	// if enter from leftChild, flag is true, 
	// otherwise it is false
	private boolean flag = true;
	
	private void addElementWithParent(Node parent, Node node, Element e){

		Node newNode;
		if(null==node){
			size ++;
			newNode = new Node(parent,e);
			if(flag){
				parent.leftChild = newNode;
			}else{
				parent.rightChild = newNode;
			}
			return;
		}
		
		if(node.val.compareTo(e)==0){
			flag = true;
			addElementWithParent(node,node.leftChild, e);
		}else if(node.val.compareTo(e)>0){
			flag = true;
			addElementWithParent(node,node.leftChild, e);
		}else if(node.val.compareTo(e)<0){
			flag = false;
			addElementWithParent(node,node.rightChild, e);
		}

	}
	
	public void traverseTree(Node node){
		if(null!=node){
			traverseTree(node.leftChild);
			System.out.print(node.val.toString()+" -> ");
			traverseTree(node.rightChild);
		}
	}
	
	
	public static void main(String[] args) {
		BinaryTree bst = new BinaryTree();

		List<Element> list = new LinkedList<Element>();
		list.add(new Element(1,"abcd"));
		list.add(new Element(16,"abcd"));
		list.add(new Element(23,"abcd"));
		list.add(new Element(5,"abcd"));
		list.add(new Element(8,"abcd"));
		list.add(new Element(97,"abcd"));
		list.add(new Element(24,"abcd"));
		list.add(new Element(12,"abcd"));
		list.add(new Element(13,"abcd"));
		list.add(new Element(22,"abcd"));
		list.add(new Element(6,"abcd"));
		list.add(new Element(9,"abcd"));
		for(Element items: list){
			bst.addElement(items);
		}
		
		bst.traverseTree(bst.root);
	}
	
}
