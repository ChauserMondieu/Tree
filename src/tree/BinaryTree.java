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
		int leftChildHeight = 0;
		int rightChildHeight = 0;
		// if negative, left tree is taller, 
		// the other way round.
		int heightDiff;
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
	private Node root = null;
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
	
	public int getSize(){
		return this.size;
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
			System.out.println("discard " + node.toString());
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
			System.out.println(node.heightDiff + " " + node.leftChildHeight + " " + node.rightChildHeight);
			traverseTree(node.rightChild);
		}
	}
	
	public int getBalance(Node node){
		if(null==node.leftChild && null==node.rightChild){
			node.heightDiff = 0;
			return 0;
		}
		if(null == node.rightChild){
			node.leftChildHeight = getBalance(node.leftChild)+1;
			node.rightChildHeight = 0;
		}else if(null == node.leftChild){
			node.rightChildHeight = getBalance(node.rightChild)+1;
			node.leftChildHeight = 0;
		}else{
			node.rightChildHeight = getBalance(node.rightChild)+1;
			node.leftChildHeight = getBalance(node.leftChild)+1;
		}
		node.heightDiff = node.leftChildHeight - node.rightChildHeight;
		return (node.leftChildHeight>=node.rightChildHeight)?node.leftChildHeight:node.rightChildHeight;
	}
	
	// left is true, else the other way round
	private boolean itr_flag = true;
	// add new elements, iteration
	public boolean addElement(Node node, Element e){
		if(null==node){
			root = new Node(e);
			size ++;
			return true;
		}
		// temp points to the position where
		// new node is to be added
		Node temp = node;
		// pointer will always point to the 
		// last leaf node in the tree
		Node pointer = node;
		while(null!=temp){
			pointer = temp;
			if(node.val.compareTo(e)==0){
				System.out.println("discard:" + node.toString());
				return false;
			}else if(temp.val.compareTo(e)>0){
				temp = pointer.leftChild;
				itr_flag = true;
			}else if(temp.val.compareTo(e)<0){
				temp = pointer.rightChild;
				itr_flag = false;
			}
		}
		temp = new Node(e);
		if(itr_flag){
			pointer.leftChild = temp;
		}else{
			pointer.rightChild = temp;
		}
		temp.parent = pointer;
		size ++;
		return true;
	}
	
	public static void main(String[] args) {
		BinaryTree bst = new BinaryTree();

		List<Element> list = new LinkedList<Element>();
		list.add(new Element(16,"abcd"));
		list.add(new Element(11,"abcd"));
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
			//bst.addElement(items);
			bst.addElement(bst.root,items);
		}
		
		int w = bst.getBalance(bst.root);
		System.out.println(w);
		bst.traverseTree(bst.root);
	}
	
}
