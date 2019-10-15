package com.github.ivanjermakov.microsubscriber.model;

import java.util.Objects;

public class TestObject {

	private String s;
	private int i;

	public TestObject() {
	}

	public TestObject(String s, int i) {
		this.s = s;
		this.i = i;
	}

	public String getS() {
		return s;
	}

	public int getI() {
		return i;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TestObject)) return false;
		TestObject that = (TestObject) o;
		return i == that.i && Objects.equals(s, that.s);
	}

	@Override
	public int hashCode() {
		return Objects.hash(s, i);
	}

}
