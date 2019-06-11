package com.epam.sudoku.verifier;

public enum VerifierCode {
	SUCCESS(0, "success"),
	GENERIC_ERROR(1,"generic verifier error"),
	FAILED_VALIDATION(10, "failed sudoku rule"),
	;
	

	private final int code;
	private final String message;
	
	private VerifierCode(int code, String message){
		this.code=code;
		this.message=message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
