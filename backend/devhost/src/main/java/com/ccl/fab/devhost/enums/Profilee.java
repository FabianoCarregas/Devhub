package com.ccl.fab.devhost.enums;

public enum Profilee {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private Integer code;
	private String description;
	
	private Profilee(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static Profilee toEnum(Integer code) {
		
			if (code == null) {
				return null;
			}
			
			for (Profilee value : Profilee.values()) {
				if (code.equals(value.getCode())) {
					return value;
				}
			
		}
		
		throw new IllegalArgumentException("Invalid id" + code);
	}
	
}
