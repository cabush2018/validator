package com.epam.sudoku.verifier;

public class VerifierException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 52983540688171329L;
	private final VerifierCode code;

	public VerifierException(VerifierCode code, String message) {
		super(message);
		this.code = code;
	}

	public VerifierCode getCode() {
		return code;
	}
	
}
