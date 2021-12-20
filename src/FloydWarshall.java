import java.io.BufferedReader;


import java.io.FileReader;

import java.util.Arrays;

public class FloydWarshall {

	public static void main(String[] args) throws InterruptedException {
		
		String file = args[0];

		new FloydWarshall(readFileToMatrix(file));

	}

	public FloydWarshall(int[][] W) throws InterruptedException {

		int[][] pred = initPred(W);

		int n = W.length;

		int[][] D = new int[5][5];

		for (int i = 0; i < W.length; i++) {

			for (int j = 0; j < W[0].length; j++) {

				D[i][j] = W[i][j];

			}

		}

		for (int k = 0; k < n; k++) {

			System.out.println("D" + k + " =");

			printArray(D);

			System.out.println("pred" + k + " =");

			printArray(pred);

			for (int i = 0; i < n; i++) {

				for (int j = 0; j < n; j++) {

					if (D[i][j] > D[i][k] + D[k][j]) {

						D[i][j] = D[i][k] + D[k][j];

						pred[i][j] = pred[k][j];

					}

				}

			}

		}

	}

	public static void printArray(int[][] W) {

		for (int i = 0; i < W.length; i++) {

			System.out.println(Arrays.toString(W[i]));

		}

		System.out.println("\n\n");

	}

	static int[][] readFileToMatrix(String file) {
		
		
		
		int rowCount = 0;

		int[][] matrix = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));

			while (br.readLine() != null) {

				rowCount++;

			}

			br.close();

			matrix = new int[rowCount][rowCount];

			br = new BufferedReader(new FileReader(file));

			String line = "";

			while ((line = br.readLine()) != null) {

				String[] tokens = line.split("\t");

				matrix[Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])] = Integer.parseInt(tokens[2]);

			}

			br.close();

			for (int row = 0; row < matrix.length; row++) {

				for (int col = 0; col < matrix[0].length; col++) {

					if (matrix[row][col] == 0) {

						matrix[row][col] = 9999;

					}

					if (row == col) {

						matrix[row][col] = 0;

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return matrix;

	}

	public static int[][] initPred(int W[][]) {

		int p[][] = new int[W.length][W.length];

		for (int i = 0; i < W.length; i++) {

			for (int j = 0; j < W.length; j++) {

				if (W[i][j] != 0 && W[i][j] != 9999) {

					p[i][j] = i + 1;

				} else {

					if (i == j) {

						p[i][j] = 0;

					} else {

						p[i][j] = 9999;

					}

				}

			}

		}

		return p;

	}

}