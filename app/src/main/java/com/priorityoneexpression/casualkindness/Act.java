package com.priorityoneexpression.casualkindness;

/**
 * Created by Jason on 2/9/2015.
 */
public class Act {
	private String name;
	private String description;

	public Act(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
