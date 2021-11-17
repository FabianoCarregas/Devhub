package com.ccl.fab.devhost.enums;

public enum WorkSituation {
	
	EMPLOYED(1),
	UNEMPLOYED(2),
	FREELANCER(3);
	
	private int code;
	
	private WorkSituation(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static WorkSituation valueOf(int code) {
		for (WorkSituation value : WorkSituation.values()) {
			if (value.getCode() == code) {
				return value;
			}
			
		}
		
		throw new IllegalArgumentException("Invalid WorkSituation code");
	}
	
}
