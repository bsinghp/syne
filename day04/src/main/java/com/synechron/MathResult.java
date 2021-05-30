package com.synechron;

import org.springframework.hateoas.RepresentationModel;

public class MathResult extends RepresentationModel {
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
