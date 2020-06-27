package tree;

import sun.misc.JavaAWTAccess;

/**
 * this class mainly stores the information of each node
 * @author AAAAA
 *
 */
public class Element implements java.lang.Comparable<Element>{
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Element() {
		super();
	}
	public Element(int id, String name) {
		this();
		this.id = id;
		this.name = name;
	}
	@Override
	public int compareTo(Element e) {
		// TODO Auto-generated method stub
		return (this.getId()>e.getId())?1:((this.getId()==e.getId())?0:-1);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id+"-"+this.name;
	}

	
}
