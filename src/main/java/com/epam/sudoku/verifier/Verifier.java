package com.epam.sudoku.verifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Verifier {

	private static final int SIZE = 9;
	private static final int VALID_SUM = 45;

	private Logger log = Logger.getLogger(this.getClass().getSimpleName());

	public static void main(String[] args) {
		Verifier verifier = new Verifier();
		System.exit(verifier.process(args));
	}

	int process(String[] args) {
		try {
			Integer matrix[][] = readInput(args[0]);
			checkMatrix(matrix);
			return 0;
		} catch (VerifierException e) {
			log.log(Level.SEVERE, e.toString());
			return e.getCode().getCode();
		} catch (Throwable t) {
			log.log(Level.SEVERE, t.toString());
			return VerifierCode.GENERIC_ERROR.getCode();
		}
	}

	private void checkMatrix(Integer[][] matrix) {
		checkAll(matrix, "line");
		checkAll(transpose(matrix), "column");
		for (int i = 0; i < SIZE / 3; i++) {
			for (int j = 0; j < SIZE / 3; j++) {
				checkBlock(matrix, i, j);
			}
		}
	}

	private void checkAll(Integer[][] matrix, String dimension) {
		for (int i = 0; i < SIZE; i++) {
			checkOne(matrix[i], dimension, i);
		}
	}

	private void checkBlock(Integer[][] matrix, int blockLine, int blockColumn) {
		List<Integer> data = new ArrayList<>();
		for (int line = 0; line < SIZE / 3; line++) {
			for (int column = 0; column < SIZE / 3; column++) {
				data.add(matrix[3 * blockLine + line][3 * blockColumn + column]);
			}
		}
		checkOne(data.toArray(new Integer[] {}), "block", blockLine + blockColumn * SIZE);
	}

	private static final Integer[] CORRECT_SEQUENCE = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	private void checkOne(Integer[] integers, String dimension, int i) {
		Integer[] inputSequence = Arrays.copyOf(integers, integers.length);
		Arrays.sort(inputSequence);
		if (!Arrays.equals(inputSequence, CORRECT_SEQUENCE))
			throw new VerifierException(VerifierCode.FAILED_VALIDATION, dimension + " " + String.valueOf(i));
	}

	private Integer[][] transpose(Integer[][] matrix) {
		int size = matrix[0].length;
		Integer[][] transpose = new Integer[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				transpose[j][i] = matrix[i][j];
			}
		}
		return transpose;
	}

	private Integer[][] readInput(String string) throws IOException {
		Path path = Paths.get(string);
		List<String> lines = Files.lines(path).collect(Collectors.toList());

		Integer[][] result = new Integer[lines.size()][];
		for (int i = 0; i < lines.size(); i++) {
			List<Integer> ints = Arrays.stream(lines.get(i).split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());
			Integer[] line = ints.toArray(new Integer[] {});
			result[i] = line;
		}
		return result;
	}
}
