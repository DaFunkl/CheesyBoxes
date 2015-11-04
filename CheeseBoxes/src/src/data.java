package src;

public class data {
	private int sizeX;
	private int sizeY;
	int maxVal;
	int marked = 0;

	// 1 for p1, 2 for p2,
	private char[][] score;
	boolean[][] matA;
	boolean[][] matB;

	public data(int x, int y) {
		this.sizeX = x;
		this.sizeY = y;
		score = new char[x][y];
		matA = new boolean[x][y + 1];
		matB = new boolean[x + 1][y];
		maxVal = 2 * x * y + x + y;
	}

	boolean legitInput(int x) {
		return 0 < x && x <= maxVal;
	}

	int mark(int x, char p) {
		if (legitInput(x)) {
			int[] id = indexToArray(x);
			if (id[2] == 0) {
				if (matA[id[0]][id[1]])
					return 0;
				int ret = 1;
				matA[id[0]][id[1]] = true;
				if (isScore(id, p))
					ret++;
				marked++;
				return ret;
			}
			if (id[2] == 1) {
				if (matB[id[0]][id[1]])
					return 0;
				int ret = 1;
				matB[id[0]][id[1]] = true;
				if (isScore(id, p))
					ret++;
				marked++;
				return ret;
			}
		}
		return -1;
	}

	boolean isScore(int[] cood, char p) {
		if (cood[2] == 0)
			return isA(cood, p);
		if (cood[2] == 1)
			return isB(cood, p);
		return false;
	}

	/*
	 * a b c d e f g a(0,0)a, b(0,0)b, c(1,0)b, d(0,1)a, e(0,1)b, f(1,1)b,
	 * g(0,2)a
	 */
	private boolean isA(int[] x, char p) {
		boolean valid1 = false;
		boolean valid2 = false;
		if (x[1] != 0) {
			x[1]--;
			if (chS(x)) {
				x[2] = 1;
				if (chS(x)) {
					x[0]++;
					if (chS(x)) {
						valid1 = true;
					}
					x[0]--;
				}
				x[2] = 0;
			}
			x[1]++;
		}
		if (valid1)
			score[x[0]][x[1] - 1] = p;
		if (x[1] != (sizeY)) {
			x[1]++;
			if (chS(x)) {
				x[1]--;
				x[2] = 1;
				if (chS(x)) {
					x[0]++;
					if (chS(x)) {
						valid2 = true;
					}
					x[0]--;
				}
				x[1]++;
				x[2] = 0;
			}
			x[1]--;
		}
		if (valid2)
			score[x[0]][x[1]] = p;
		return valid1 || valid2;
	}

	/*
	 * b e a d g c f a(0,0)b, b(0,0)a, c(0,1)a, d(1,0)b, e(1,0)a, f(1,1)a,
	 * g(2,0)b
	 */
	private boolean isB(int[] x, char p) {
		boolean valid1 = false;
		boolean valid2 = false;
		if (x[0] != 0) {
			x[0]--;
			if (chS(x)) {
				x[2] = 0;
				if (chS(x)) {
					x[1]++;
					if (chS(x)) {
						valid1 = true;
					}
					x[1]--;
				}
				x[2] = 1;
			}
			x[0]++;
		}
		if (valid1)
			score[x[0] - 1][x[1]] = p;
		if (x[0] != sizeX) {
			x[0]++;
			if (chS(x)) {
				x[0]--;
				x[2] = 0;
				if (chS(x)) {
					x[1]++;
					if (chS(x)) {
						valid2 = true;
					}
					x[1]--;
				}
				x[0]++;
				x[2] = 1;
			}
			x[0]--;
		}
		if (valid2)
			score[x[0]][x[1]] = p;
		return valid1 || valid2;
	}

	boolean chS(int[] a) {
		if (a[2] == 0)
			return this.matA[a[0]][a[1]];
		if (a[2] == 1)
			return this.matB[a[0]][a[1]];
		return false;
	}

	boolean isMarked(int x) {
		int[] id = indexToArray(x);
		if (id[2] == 0)
			return matA[id[0]][id[1]];
		if (id[2] == 1)
			return matB[id[0]][id[1]];
		return false;
	}

	
	int[] indexToArray(int index) {
		int[] ret = new int[3];
		int delta = this.sizeX;
		boolean mat = true; // true -> a, else to b
		ret[1] = 0; // y value of array
		while (index > delta) {
			delta += this.sizeX + 1;
			if (index <= delta) {
				mat = false;
				break;
			}
			delta += this.sizeX;
			ret[1]++;
		}
		ret[0] = 1 + delta - index;
		ret[2] = mat ? 0 : 1;
		ret[0] = mat ? sizeX - ret[0] : (sizeX + 1) - ret[0];
		return ret;
	}

	private int index = 1;

	void printField() {
		StringBuilder out = new StringBuilder();
		index = 1;
		for (int i = 0; i < sizeY; i++) {
			out.append(rowA(i));
			out.append(rowB(i));
		}
		out.append(rowA(sizeY));
		System.out.println(out);
	}

	
	StringBuilder rowA(int row) {
		StringBuilder ret = new StringBuilder();
		ret.append("  *");
		for (int j = 0; j < sizeX; j++) {
			if (matA[j][row])
				ret.append("  -");
			else {
				if (index < 10)
					ret.append(" ");
				if (index < 100)
					ret.append(" ");
				ret.append(index);
			}
			ret.append("  *");
			index++;
		}
		ret.append("\n");
		return ret;
	}


	StringBuilder rowB(int row) {
		StringBuilder out = new StringBuilder();
		if (matB[0][row])
			out.append("  |");
		else {
			if (index < 10)
				out.append(" ");
			if (index < 100)
				out.append(" ");
			out.append(index);
		}
		index++;
		for (int j = 1; j <= sizeX; j++) {
			if (score[j - 1][row] == '1' || score[j - 1][row] == '2') {
				out.append(" p");
				out.append(score[j - 1][row]);
			} else
				out.append("   ");
			if (matB[j][row])
				out.append("  |");
			else {
				if (index < 10)
					out.append(" ");
				if (index < 100)
					out.append(" ");
				out.append(index);
			}
			index++;
		}
		out.append("\n");
		return out;
	}

	public void printResult(String n1, String n2) {
		int p1 = 0;
		int p2 = 0;
		for(char[] a : score){
			for(char b : a){
				if(b == '1') p1++;
				if(b == '2') p2++;
			}
		}
		String s = "And the Winner is: ";
		if(p1  > p2) s += n1 + "(p1)";
		if(p1  < p2) s += n1 + "(p2)";
		if(p1 == p2) s = "Draw:";
		s += "\n"+ n1+ "(p1):\t"+ p1+"points";
		s += "\n"+ n2+ "(p2):\t"+ p2+"points";
		System.out.println(s);
	}

}
