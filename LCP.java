import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
	public static class SuffixArray {
		public final String[] suffixes;
		public int[] cumulativeRanks;
		public int[] lcp;
		private final int N;

		public SuffixArray(String s) {
			N = s.length();
			suffixes = new String[N];
			cumulativeRanks = new int[N];
			lcp = new int[N];
			for (int i = 0; i < N; i++)
				suffixes[i] = s.substring(i);
			Arrays.sort(suffixes);
			cumulativeRanks[0] = suffixes[0].length();
			lcp[0] = 0;
			for (int i = 1; i < suffixes.length; i++) {
				lcp[i] = lcp(i);
				cumulativeRanks[i] = cumulativeRanks[i - 1] + suffixes[i].length()
						- lcp[i];

			}
		}

		public SuffixArray(String[] s) {
			N = s.length;
			suffixes = s;
			cumulativeRanks = new int[N];
			lcp = new int[N];
			Arrays.sort(suffixes);
			cumulativeRanks[0] = suffixes[0].length();
			lcp[0] = 0;
			for (int i = 1; i < suffixes.length; i++) {
				lcp[i] = lcp(i);
				cumulativeRanks[i] = cumulativeRanks[i - 1] + suffixes[i].length()
						- lcp[i];

			}
		}

		public int length() {
			return N;
		}

		public int index(int i) {
			return N - suffixes[i].length();
		}

		public String select(int i) {
			return i < 0 ? "" : suffixes[i];
		}

		public int rank(String query) {
			int lo = 0, hi = N - 1;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				int cmp = query.compareTo(suffixes[mid]);
				if (cmp < 0)
					hi = mid - 1;
				else if (cmp > 0)
					lo = mid + 1;
				else
					return mid;
			}
			return lo;
		}

		private static int lcp(String s, String t) {
			int N = Math.min(s.length(), t.length());
			for (int i = 0; i < N; i++)
				if (s.charAt(i) != t.charAt(i))
					return i;
			return N;
		}

		public int lcp(int i) {
			return lcp(suffixes[i], suffixes[i - 1]);
		}

		public int lcp(int i, int j) {
			return lcp(suffixes[i], suffixes[j]);
		}

		String lexicographicRank(int k) {
			if (k < 0 || k > cumulativeRanks[cumulativeRanks.length - 1])
				return "INVALID";
			int index = Arrays.binarySearch(cumulativeRanks, k);
			if (index < 0) {
				index = -1 * index - 1;
				k = index == 0 ? k : k- cumulativeRanks[index-1] ;
				return suffixes[index].substring(0,k + lcp[index]);
			} else
				return suffixes[index];

		}
	}
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			/*String s = br.readLine();
			int N = Integer.parseInt(s);
			String[] r = new String[N];
			for (int i = 0; i < r.length; i++) {
				s = br.readLine();
				r[i] = s;
			}
			s = br.readLine();
			int M = Integer.parseInt(s);
			int[] queries = new int[M];
			for (int i = 0; i < queries.length; i++) {
				s = br.readLine();
				queries[i] = Integer.parseInt(s);
			}
			ArrayList<String> l = new ArrayList<String>();
			for (int i = 0; i < r.length; i++) {
				for (int j = 0; j < r[i].length(); j++) {
					l.add(r[i].substring(j));
				}
			}
			String[] array = (String[]) l.toArray(new String[l.size()]);
			SuffixArray suffix = new SuffixArray(array);
			for (int i = 0; i < queries.length; i++) {
				System.out.println(suffix.lexicographicRank(queries[i]));
			}*/
            String s="banana@";
            SuffixArray suffix=new SuffixArray(s);
            for(int i=0;i<suffix.lcp.length;i++)
            System.out.println(suffix.lcp[i]);
        }
}
