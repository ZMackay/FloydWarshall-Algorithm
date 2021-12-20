import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;


/**
 * 
 * @author Zachary Mackay
 * 
 * This program will take in a file path as a command line argument. This file must be
 * tab delimited in the format nodeX	nodeY	Weight. This program is reads all edges
 *  as an undirected edge and supports multiple connections to one node or from one node.
 * 
 *
 */
public class FloydWarshall {

	public static void main(String[] args) throws InterruptedException {

		String file = args[0];

		new FloydWarshall(readFileToMatrix(file));

	}

	/**
	 * Main constructor will call the calculation and printing of all distance matrices and predecessors matrices.
	 * @param W integer matrix created from the readFileToMatrix() method
	 * 
	 */
	public FloydWarshall(int[][] W) {

		int[][] pred = initPred(W);

		int n = W.length;

		int[][] D = new int[n][n];

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

	/**
	 * This method will print the matrix given to it into a formatted output to the console
	 * @param W integer matrix created from the readFileToMatrix() method
	 */
	public static void printArray(int[][] W) {

		String str = "";

		for (int row = 0; row < W.length; row++) {
			str += String.format("%-3s", row) + "|";
			String rowStr = "";
			for (int col = 0; col < W[0].length; col++) {
				if (W[row][col] == 9999) {
					rowStr = String.format("%-4s|", "INF");
				} else {
					rowStr = String.format("%-4s|", W[row][col]);
				}
				str += rowStr;
			}

			str += "\n";
		}

		String rowStr = "";
		for (int col = 0; col < W[0].length; col++) {
			rowStr = String.format("%5s", col);
			str += rowStr;
		}

		System.out.println(str + "\n\n");

	}

	/**
	 * This method will read the file that was passed to it and convert it into an integer matrix.
	 * @param file file path that was passed by the command line arguments
	 * @return integer matrix that was read
	 */
	static int[][] readFileToMatrix(String file) {

		HashMap<Integer, Boolean> nodes = new HashMap<Integer, Boolean>();

		int rowCount = 0;

		int[][] matrix = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = "";
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split("\t");

				int Node1 = Integer.parseInt(tokens[0]);

				int Node2 = Integer.parseInt(tokens[1]);

				if (!nodes.containsKey(Node1) || !nodes.get(Node1)) {
					nodes.put(Node1, true);
					rowCount++;
				}

				if (!nodes.containsKey(Node2) || !nodes.get(Node2)) {
					nodes.put(Node2, true);
					rowCount++;
				}

			}

			br.close();

			matrix = new int[rowCount][rowCount];

			br = new BufferedReader(new FileReader(file));

			line = "";

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

	/**
	 * Initializes the matrix passed to it 
	 * @param W integer matrix.
	 * @return integer matrix filled with default values in it to make arithmetic correct.
	 */
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