package com.neosoft.springbootsecurity.enums;

public enum Role {

	ADMIN("ADMIN"), STUDENT_USER("STUDENT_USER");

	private final String name;

	private Role(String value) {
		this.name = value;
	}

	public String value() {
		return this.name;
	}

	@Override
	public String toString() {
		return name;
	}

}
