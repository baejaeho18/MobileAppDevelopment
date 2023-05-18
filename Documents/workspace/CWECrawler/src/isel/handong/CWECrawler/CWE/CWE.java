package cwe;

import java.util.ArrayList;

public class CWE {
	int id;
	String name;
//	String type;
	String description;

	ArrayList<Integer> childOf = new ArrayList<Integer>();
	ArrayList<Integer> parentOf = new ArrayList<Integer>();
	ArrayList<Integer> memberOf = new ArrayList<Integer>();
	
	ArrayList<String> exampleCode = new ArrayList<String>();
	String exampleDescription;
	
	public CWE(int id, String name, String description, 
			ArrayList<Integer> childOf, ArrayList<Integer> parentOf, ArrayList<Integer> memberOf,
			ArrayList<String> exampleCode, String exampleDescription) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.childOf = childOf;
		this.parentOf = parentOf;
		this.memberOf = memberOf;
		this.exampleCode = exampleCode;
		this.exampleDescription = exampleDescription;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public ArrayList<Integer> getChildOf() {
		return childOf;
	}
	public ArrayList<Integer> getParentOf() {
		return parentOf;
	}
	public ArrayList<Integer> getMemberOf() {
		return memberOf;
	}
	public ArrayList<String> getExampleCode() {
		return exampleCode;
	}
	public String getExampleDescription() {
		return exampleDescription;
	}
	
}
