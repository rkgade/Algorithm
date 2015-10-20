import java.io.*;
import java.util.*;
 
public class Main {
 
	BufferedReader br;
	PrintWriter out;
	StringTokenizer st;
	boolean eof;
 
	static final int P = 1_000_000_007;
 
	int get(int[] f, int pos) {
		int ret = 0;
		for (int i = pos; i >= 0; i = (i & (i + 1)) - 1) {
			ret = Math.max(ret, f[i]);
		}
		return ret;
	}
 
	void put(int[] f, int pos, int val) {
		for (int i = pos; i < f.length; i |= i + 1) {
			f[i] = Math.max(f[i], val);
		}
	}
 
	static final Random rng = new Random(1234);
 
	static class Node {
		int key;
		int size;
		int val;
		int sumVal;
 
		Node left, right;
 
		public Node(int key, int val, Node left, Node right) {
			this.key = key;
			this.val = val;
			this.left = left;
			this.right = right;
			updateInfo();
		}
 
		Node updateInfo() {
			size = 1 + getSize(left) + getSize(right);
			sumVal = (int) (((long) val + getSum(left) + getSum(right)) % P);
			if (sumVal < 0) {
				sumVal -= P;
			}
			if (sumVal >= P) {
				sumVal -= P;
			}
			return this;
		}
 
		static int getSize(Node v) {
			return v == null ? 0 : v.size;
		}
 
		static int getSum(Node v) {
			return v == null ? 0 : v.sumVal;
		}
 
		static Node insert(Node v, int key, int val) {
			if (rng.nextInt(getSize(v) + 1) == 0) {
				Node[] tmp = split(v, key);
				return new Node(key, val, tmp[0], tmp[1]);
			}
			if (key <= v.key) {
				v.left = insert(v.left, key, val);
			} else {
				v.right = insert(v.right, key, val);
			}
			return v.updateInfo();
		}
 
		static Node[] split(Node v, int splitKey) {
			if (v == null)
				return new Node[2];
			Node[] ret;
			if (splitKey < v.key) {
				ret = split(v.left, splitKey);
				v.left = ret[1];
				ret[1] = v;
			} else {
				ret = split(v.right, splitKey);
				v.right = ret[0];
				ret[0] = v;
			}
			v.updateInfo();
			return ret;
		}
 
		static Node merge(Node left, Node right) {
			if (left == null)
				return right;
			if (right == null)
				return left;
			Node ret;
			if (rng.nextInt(getSize(left) + getSize(right)) < getSize(left)) {
				left.right = merge(left.right, right);
				ret = left;
			} else {
				right.left = merge(left, right.left);
				ret = right;
			}
			return ret.updateInfo();
		}
	}
 
	int solve(final int[] a, int n) {
		Integer[] pos = new Integer[n];
		for (int i = 0; i < n; i++) {
			pos[i] = i;
		}
		Arrays.sort(pos, new Comparator<Integer>() {
 
			@Override
			public int compare(Integer o1, Integer o2) {
				if (a[o1] != a[o2]) {
					return a[o1] < a[o2] ? -1 : 1;
				}
				return -o1.compareTo(o2);
			}
 
		});
 
		int[] fen = new int[n];
		final int[] maxEnd = new int[n];
		int L = 0;
		for (Integer p : pos) {
			maxEnd[p] = get(fen, p) + 1;
			put(fen, p, maxEnd[p]);
			L = Math.max(L, maxEnd[p]);
		}
 
		// System.err.println(Arrays.toString(maxEnd));
 
		if (L == 2) {
			return n;
		}
 
		int ret = 0;
 
		Node[] roots = new Node[L + 1];
		for (Integer p : pos) {
			for (int i = maxEnd[p]; i >= Math.max(maxEnd[p] - 1, 1); i--) {
				if (i == 1) {
					roots[1] = Node.insert(roots[1], p, 1);
				} else {
					Node[] tmp = Node.split(roots[i - 1], p);
					int sum = Node.getSum(tmp[0]);
 
					
 
					if (i == L - 1) {
						ret += sum;
						if (ret >= P) {
							ret -= P;
						}
					}
					roots[i - 1] = Node.merge(tmp[0], tmp[1]);
					roots[i] = Node.insert(roots[i], p, sum);
				}
			}
		}
 
		return ret;
 
	}
 
	Main() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int t = nextInt();
		while (t-- > 0) {
			int n = nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			out.println(solve(a, n));
		}
		out.close();
	}
 
	public static void main(String[] args) throws IOException {
		new Main();
	}
 
	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				eof = true;
				return null;
			}
		}
		return st.nextToken();
	}
 
	String nextString() {
		try {
			return br.readLine();
		} catch (IOException e) {
			eof = true;
			return null;
		}
	}
 
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
 
	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}
 
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
} 
