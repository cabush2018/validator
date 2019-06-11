package com.epam.sudoku.verifier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VerifierTest {

	Verifier verifier = new Verifier();

	@Test
	public void testProcess1To9OnAllLines() {
		String[] args = { "./src/test/resources/1-9.txt" };
		int result = verifier.process(args);
		assertThat(result, is(VerifierCode.FAILED_VALIDATION.getCode()));
	}
	
	@Test
	public void testProcessValidMatrix() {
		String[] args = { "./src/test/resources/validSudoku.txt" };
		int result = verifier.process(args);
		assertThat(result, is(VerifierCode.SUCCESS.getCode()));
	}
	
	@Test
	public void testProcessRowInvalidMatrix() {
		String[] args = { "./src/test/resources/rowInvalidSudoku.txt" };
		int result = verifier.process(args);
		assertThat(result, is(VerifierCode.FAILED_VALIDATION.getCode()));
	}
	
	@Test
	public void testProcessColumnInvalidMatrix() {
		String[] args = { "./src/test/resources/columnInvalidSudoku.txt" };
		int result = verifier.process(args);
		assertThat(result, is(VerifierCode.FAILED_VALIDATION.getCode()));
	}
		

}
