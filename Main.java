import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;


public class Main {

	static int arrForIndexSort[];
	static Integer map1[];
	static Integer map2[];

	static class FastIO {

		public static final int DEFAULT_BUFFER_SIZE = 65536;
		public static final int DEFAULT_INTEGER_SIZE = 11;
		public static final int DEFAULT_LONG_SIZE = 20;
		public static final int DEFAULT_WORD_SIZE = 256;
		public static final int DEFAULT_LINE_SIZE = 8192;
		public static final int EOF = -1;

		private final InputStream in;
		private final OutputStream out;

		private byte[] inBuffer;
		private int nextIn, inLength;
		private byte[] outBuffer;
		private int nextOut;

		private char[] charBuffer;
		private byte[] byteBuffer;

		public FastIO(InputStream in, OutputStream out, int inBufferSize, int outBufferSize) {
			this.in = in;
			this.inBuffer = new byte[inBufferSize];
			this.nextIn = 0;
			this.inLength = 0;

			this.out = out;
			this.outBuffer = new byte[outBufferSize];
			this.nextOut = 0;

			this.charBuffer = new char[DEFAULT_LINE_SIZE];
			this.byteBuffer = new byte[DEFAULT_LONG_SIZE];
		}

		public FastIO(InputStream in, OutputStream out) {
			this(in, out, DEFAULT_BUFFER_SIZE, DEFAULT_BUFFER_SIZE);
		}

		public FastIO(InputStream in, OutputStream out, int bufferSize) {
			this(in, out, bufferSize, bufferSize);
		}

		public char nextChar() {
			byte b;
			while (isSpace(b = read()))
				;

			return (char) b;
		}

		public String next() {
			byte b;
			while (isSpace(b = read()))
				;

			int pos = 0;
			do {
				charBuffer[pos++] = (char) b;
				ensureCapacity(pos);
			} while (!isSpace(b = read()));

			return new String(charBuffer, 0, pos);
		}

		public String nextLine() {
			byte b;
			int pos = 0;

			while (!isLine(b = read())) {
				charBuffer[pos++] = (char) b;
				ensureCapacity(pos);
			}

			return new String(charBuffer, 0, pos);
		}

		public int nextInt() {
			byte b;
			while (isSpace(b = read()))
				;

			boolean negative = false;
			int result = b - '0';
			if (b == '-') {
				negative = true;
				result = 0;
			}

			while (isDigit(b = read()))
				result = (result * 10) + (b - '0');

			return negative ? -result : result;
		}

		public long nextLong() {
			byte b;
			while (isSpace(b = read()))
				;

			boolean negative = false;
			long result = b - '0';
			if (b == '-') {
				negative = true;
				result = 0;
			}

			while (isDigit(b = read()))
				result = (result * 10) + (b - '0');

			return negative ? -result : result;
		}

		public float nextFloat() {
			byte b;
			while (isSpace(b = read()))
				;

			int pos = 0;
			do {
				charBuffer[pos++] = (char) b;
			} while (!isSpace(b = read()));

			return Float.parseFloat(new String(charBuffer, 0, pos));
		}

		public float nextFloat2() {
			byte b;
			while (isSpace(b = read()))
				;

			boolean negative = false;
			float result = b - '0';
			if (b == '-') {
				negative = true;
				result = 0;
			}

			while (isDigit(b = read()))
				result = (result * 10) + (b - '0');

			float d = 1;
			if (b == '.') {
				while (isDigit(b = read()))
					result += (b - '0') / (d *= 10);
			}

			return negative ? -result : result;
		}

		public double nextDouble() {
			byte b;
			while (isSpace(b = read()))
				;

			int pos = 0;
			do {
				charBuffer[pos++] = (char) b;
			} while (!isSpace(b = read()));

			return Double.parseDouble(new String(charBuffer, 0, pos));
		}

		public double nextDouble2() {
			byte b;
			while (isSpace(b = read()))
				;

			boolean negative = false;
			double result = b - '0';
			if (b == '-') {
				negative = true;
				result = 0;
			}

			while (isDigit(b = read()))
				result = (result * 10) + (b - '0');

			double d = 1;
			if (b == '.') {
				while (isDigit(b = read()))
					result += (b - '0') / (d *= 10);
			}

			return negative ? -result : result;
		}

		public BigInteger nextBigInteger() {
			return new BigInteger(next());
		}

		public BigDecimal nextBigDecimal() {
			return new BigDecimal(next());
		}

		public char[] nextToCharArray() {
			byte b;
			while (isSpace(b = read()))
				;

			int pos = 0;
			do {
				charBuffer[pos++] = (char) b;
				ensureCapacity(pos);
			} while (!isSpace(b = read()));

			char[] array = new char[pos];
			System.arraycopy(charBuffer, 0, array, 0, pos);
			return array;
		}

		public int[] nextIntArray(int size) {
			int[] array = new int[size];
			for (int i = 0; i < size; i++)
				array[i] = nextInt();

			return array;
		}

		public long[] nextLongArray(int size) {
			long[] array = new long[size];
			for (int i = 0; i < size; i++)
				array[i] = nextLong();

			return array;
		}

		public int[][] nextInt2DArray(int Y, int X) {
			int[][] array = new int[Y][X];
			for (int y = 0; y < Y; y++)
				for (int x = 0; x < X; x++)
					array[y][x] = nextInt();

			return array;
		}

		public void print(char c) {
			write((byte) c);
		}

		public void print(char[] chars) {
			print(chars, 0, chars.length);
		}

		public void print(char[] chars, int start) {
			print(chars, start, chars.length);
		}

		public void print(char[] chars, int start, int end) {
			for (int i = start; i < end; i++)
				write((byte) chars[i]);
		}

		public void print(String s) {
			for (int i = 0; i < s.length(); i++)
				write((byte) s.charAt(i));
		}

		public void print(int i) {
			if (i == 0) {
				write((byte) '0');
				return;
			}
			if (i == Integer.MIN_VALUE) {
				write((byte) '-');
				write((byte) '2');
				write((byte) '1');
				write((byte) '4');
				write((byte) '7');
				write((byte) '4');
				write((byte) '8');
				write((byte) '3');
				write((byte) '6');
				write((byte) '4');
				write((byte) '8');
				return;
			}

			if (i < 0) {
				write((byte) '-');
				i = -i;
			}

			int pos = 0;
			while (i > 0) {
				byteBuffer[pos++] = (byte) ((i % 10) + '0');
				i /= 10;
			}

			while (pos-- > 0)
				write(byteBuffer[pos]);
		}

		public void print(long l) {
			if (l == 0) {
				write((byte) '0');
				return;
			}
			if (l == Long.MIN_VALUE) {
				write((byte) '-');
				write((byte) '9');
				write((byte) '2');
				write((byte) '2');
				write((byte) '3');
				write((byte) '3');
				write((byte) '7');
				write((byte) '2');
				write((byte) '0');
				write((byte) '3');
				write((byte) '6');
				write((byte) '8');
				write((byte) '5');
				write((byte) '4');
				write((byte) '7');
				write((byte) '7');
				write((byte) '5');
				write((byte) '8');
				write((byte) '0');
				write((byte) '8');
				return;
			}

			if (l < 0) {
				write((byte) '-');
				l = -l;
			}

			int pos = 0;
			while (l > 0) {
				byteBuffer[pos++] = (byte) ((l % 10) + '0');
				l /= 10;
			}

			while (pos-- > 0)
				write(byteBuffer[pos]);
		}

		public void print(float f) {
			String sf = Float.toString(f);
			for (int i = 0; i < sf.length(); i++)
				write((byte) sf.charAt(i));
		}

		public void print(double d) {
			String sd = Double.toString(d);
			for (int i = 0; i < sd.length(); i++)
				write((byte) sd.charAt(i));
		}

		public void printls(char c) {
			print(c);
			write((byte) ' ');
		}

		public void printls(String s) {
			print(s);
			write((byte) ' ');
		}

		public void printls(int i) {
			print(i);
			write((byte) ' ');
		}

		public void printls(long l) {
			print(l);
			write((byte) ' ');
		}

		public void printls(float f) {
			print(f);
			write((byte) ' ');
		}

		public void printls(double d) {
			print(d);
			write((byte) ' ');
		}

		public void printls() {
			write((byte) ' ');
		}

		public void println(char c) {
			print(c);
			write((byte) '\n');
		}

		public void println(char[] chars) {
			print(chars, 0, chars.length);
			write((byte) '\n');
		}

		public void println(String s) {
			print(s);
			write((byte) '\n');
		}

		public void println(int i) {
			print(i);
			write((byte) '\n');
		}

		public void println(long l) {
			print(l);
			write((byte) '\n');
		}

		public void println(float f) {
			print(f);
			write((byte) '\n');
		}

		public void println(double d) {
			print(d);
			write((byte) '\n');
		}

		public void println() {
			write((byte) '\n');
		}

		public void printf(String format, Object... args) {
			String s = String.format(format, args);
			for (int i = 0; i < s.length(); i++)
				write((byte) s.charAt(i));
		}

		public void fprint(char c) {
			print(c);
			flushBuffer();
		}

		public void fprint(String s) {
			print(s);
			flushBuffer();
		}

		public void fprint(int i) {
			print(i);
			flushBuffer();
		}

		public void fprint(long l) {
			print(l);
			flushBuffer();
		}

		public void fprint(float f) {
			print(f);
			flushBuffer();
		}

		public void fprint(double d) {
			print(d);
			flushBuffer();
		}

		public void fprintf(String format, Object... args) {
			printf(format, args);
			flushBuffer();
		}

		private byte read() {
			if (nextIn >= inLength) {
				if ((inLength = fill()) == EOF)
					return EOF;
				nextIn = 0;
			}

			return inBuffer[nextIn++];
		}

		private void write(byte b) {
			if (nextOut >= outBuffer.length)
				flushBuffer();

			outBuffer[nextOut++] = b;
		}

		private int fill() {
			try {
				return in.read(inBuffer, 0, inBuffer.length);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public void flush() {
			flushBuffer();
		}

		private void flushBuffer() {
			if (nextOut == 0)
				return;

			try {
				out.write(outBuffer, 0, nextOut);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			nextOut = 0;
		}

		public void close() {
			flush();
		}

		public void exit(char c) {
			fprint(c);
			System.exit(0);
		}

		public void exit(String s) {
			fprint(s);
			System.exit(0);
		}

		public void exit(int i) {
			fprint(i);
			System.exit(0);
		}

		public void exit(long l) {
			fprint(l);
			System.exit(0);
		}

		public void exit(float f) {
			fprint(f);
			System.exit(0);
		}

		public void exit(double d) {
			fprint(d);
			System.exit(0);
		}

		public void exit(String format, Object... args) {
			fprintf(format, args);
			System.exit(0);
		}

		public void exit() {
			flushBuffer();
			System.exit(0);
		}

		private void ensureCapacity(int size) {
			if (size < charBuffer.length)
				return;

			char[] array = new char[size * 2];
			System.arraycopy(charBuffer, 0, array, 0, size);
			charBuffer = array;
		}

		private boolean isDigit(byte b) {
			return b >= '0' && b <= '9';
		}

		private boolean isLine(byte b) {
			return b == '\n' || b == '\r' || b == EOF;
		}

		private boolean isSpace(byte b) {
			return b == ' ' || b == '\t' || b == '\n' || b == '\r' || b == '\f' || b == EOF;
		}
	}

	static class Pair{
		int first;
		int second;
		Pair(int first, int second){
			this.first = first;
			this.second = second;
		}
		@Override
		public boolean equals(Object b) {
			Pair a = (Pair)b;
			if(this.first == a.first && this.second==a.second) {
				return true;
			}
			return false;
		}
	}
	class PairSorter implements Comparator<Main.Pair>{
		public int compare(Pair a, Pair b) {
			if(a.first!=b.first) {
				return a.first-b.first;
			}
			return a.second-b.second;
		}
	}
	static class DoublePair{
		double first;
		double second;
		DoublePair(double first, double second){
			this.first = first;
			this.second = second;
		}
		@Override
		public boolean equals(Object b) {
			Pair a = (Pair)b;
			if(this.first == a.first && this.second==a.second) {
				return true;
			}
			return false;
		}
	}
	class DoublePairSorter implements Comparator<Main.DoublePair>{
		public int compare(DoublePair a, DoublePair b) {
			 if(a.second>b.second) {
				 return 1;
			 }
			 else if(a.second<b.second) {
				 return -1;
			 }
			 return 0;
		}
	}
	class IndexSorter implements Comparator<Integer>{
		public int compare(Integer a, Integer b) {
			//desc
			if(arrForIndexSort[b]==arrForIndexSort[a]) {
				return b-a;
			}
			return arrForIndexSort[b]-arrForIndexSort[a];
		}
	}
	class ListSorter implements Comparator<List>{
		public int compare(List a, List b) {
			return b.size()-a.size();
		}
	}
	static class DisjointSet{
		int[] dsu;
		public DisjointSet(int n) {
			makeSet(n);
		}
		public void makeSet(int n) {
			dsu = new int[n+1];
			//*** 1 Based indexing ***
			for(int i=1;i<=n;i++) {
				dsu[i] = -1;
			}
		}
		public int find(int i) {
			while(dsu[i] > 0) {
				i = dsu[i];
			}
			return i;
		}
		public void union(int i, int j) {
			int iRep = find(i);
			int jRep = find(j);
			if(iRep == jRep) {
				return;
			}
			if(dsu[iRep]>dsu[jRep]) {
				dsu[jRep] += dsu[iRep];
				dsu[iRep] = jRep;
			}
			else {
				dsu[iRep] += dsu[jRep];
				dsu[jRep] = iRep;
			}
		}
	}
	//Lazy Propagation for Range Update
	static class SegmentTreeLP{
		long[] tree;
		long[] originalTree;
		public SegmentTreeLP(long[] a){
			originalTree = a;
			int n = a.length;
			tree = new long[4*n];
			build(1, 0, n-1);
		}
		public void build(int node, int start, int end){
			if(start==end) {
				tree[node] = originalTree[start];
				return;
			}
			int mid = (start+ end)/2;
			build(2*node, start, mid);
			build(2*node+1, mid+1, end);
			tree[node] = 0;
		}
		public void update(int node, int start, int end, int l, int r, int val) {
			if(l > r) {
				return;
			}
			if(start ==l && end == r) {
				tree[node] += val;
				return;
			}
			int mid = (start+ end)/2;
			update(2*node, start, mid, l, Math.min(mid, r), val);
			update(2*node+1, mid+1, end, Math.max(l, mid+1), r, val);
		}
		public long query(int node, int start, int end, int idx) {
			if(start == end) {
				return tree[node];
			}
			int mid = (start+end)/2;
			if(idx<=mid) {
				return tree[node] + query(2*node, start, mid,idx);
			}
			else {
				return tree[node] + query(2*node+1, mid+1, end, idx);
			}
		}
	}
	static class SegmentTree{
		int[] tree;
		int[] originalArr;
		public SegmentTree(int[] a) {
			int n = a.length;
			tree = new int[4*n];
			build(1, a, 0, n-1);
			originalArr = a;

		}
		public void build(int node, int[] a, int start, int end){
			if(start == end) {
				tree[node] = a[start];
				return;
			}
			int mid = (start+end)/2;
			build(2*node, a, start, mid);
			build(2*node+1, a, mid+1, end);
			tree[node] = Math.min(tree[2*node], tree[2*node+1]);
		}
		public void update(int node, int start, int end, int idx, int val) {
			if(start == end) {
				tree[node] = originalArr[idx] = val;
				return;
			}
			int mid = (start+ end)/2;
			if(idx<=mid) {
				update(2*node, start, mid, idx, val);
			}
			else {
				update(2*node+1, mid+1, end, idx, val);
			}
			tree[node] = Math.min(tree[2*node], tree[2*node+1]);
		}
		public int query(int node, int start, int end, int l, int r) {
			if(r<start || l>end) {
				return Integer.MAX_VALUE;
			}
			if(start>=l && end<=r) {
				return tree[node];
			}
			int mid = (start+end)/2;
			int p1 = query(2*node, start, mid, l, r);
			int p2 = query(2*node+1, mid+1, end, l, r);
			return Math.min(p1, p2);
		}
	}
	//1 Based Indexing
	static class BIT{
		long[] tree;
		public BIT(int n) {
			tree = new long[n];
		}
		public BIT(int[] a) {
			this(a.length);
			for(int i=1;i<tree.length;i++) {
				add(i, a[i]);
			}
		}
		public void add(int i, int val) {
			for(int j=i;j<tree.length;j += j&(-j)) {
				tree[j] += val;
			}
		}
		public long sum(int i) {
			long ret = 0;
			for(int j=i;j>0;j -= j&(-j)) {
				ret += tree[j];
			}
			return ret;
		}
		public long query(int l, int r) {
			long lSum = 0;
			if(l>1) {
				lSum = sum(l-1);
			}
			long rSum = sum(r);
			return rSum-lSum;
		}
	}
	//1 Based Indexing
	static class BIT2D{
		long[][] tree;
		public BIT2D(int n, int m) {
			tree = new long[n][m];
		}
		public BIT2D(int[][] a) {
			this(a.length, a[0].length);
			for(int i=1;i<tree.length;i++) {
				for(int j=1;j<tree[0].length;j++) {
					add(i,j, a[i][j]);
				}
			}
		}
		public void add(int x, int y,int val) {
			for(int i=x;i<tree.length;i += i&(-i)) {
				for(int j=y;j<tree.length;j += j&(-j)) {
					tree[i][j] += val;
				}
			}
		}
		public long sum(int x, int y) {
			long ret = 0;
			for(int i=x;i>0;i -= i&(-i)) {
				for(int j=y;j>0;j -= j&(-j)) {
					ret += tree[i][j];
				}
			}
			return ret;
		}
		public long query(int r1, int c1, int r2, int c2) {
			long sum = sum(r2, c2);
			if(r1>1) {
				sum -= sum(r1-1, c2);
			}
			if(c1>1) {
				sum -= sum(r2, c1-1);
			}
			if(r1>1 && c1>1) {
				sum += sum(r1-1, c1-1);
			}
			return sum;
		}
	}
	//1 Based Indexing
		static class BIT3D{
			long[][][] tree;
			int n, m, o;
			public BIT3D(int n, int m, int o) {
				this.n = n;
				this.m = m;
				this.o = o;
				tree = new long[n][m][o];
			}
			public BIT3D(int[][][] a) {
				this(a.length, a[0].length, a[0][0].length);
				for(int i=1;i<tree.length;i++) {
					for(int j=1;j<tree[0].length;j++) {
						for(int k=1;k<tree[0][0].length;k++) {
							add(i,j,k, a[i][j][k]);
						}
					}
				}
			}
			public void add(int x, int y,int z,int val) {
				for(int i=x;i<tree.length;i += i&(-i)) {
					for(int j=y;j<tree.length;j += j&(-j)) {
						for(int k=y;k<tree.length;k += k&(-k)) {
							tree[i][j][k] += val;
						}
					}
				}
			}
			public long sum(int x, int y, int z) {
				long ret = 0;
				for(int i=x;i>0;i -= i&(-i)) {
					for(int j=y;j>0;j -= j&(-j)) {
						for(int k=z;k>0;k -= k&(-k)) {
							ret += tree[i][j][k];
						}
					}
				}
				return ret;
			}
			public long query(int x1, int y1, int z1, int x2, int y2, int z2) {
				long sum = sum(n-1,m-1,o-1);
				if(x1-1>0 && y1-1>0 && z1-1>0) {
					sum += sum(x1-1,y1-1,z1-1);
				}
				sum -= sum(x2,y2,z2);
				return sum;
			}
		}
	public static void main(String[] args) throws IOException {
		int T = 1;
		InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastIO io = new FastIO(inputStream, outputStream);
//		T = io.nextInt();
		//try {
		while (T-- > 0) {
			//sb.append("Case ").append(t1-T).append(":\n");
			solve(io);
		}
//		}catch(Exception e) {
//
//		}
		io.flush();
		io.close();
	}
	public static void solve(FastIO io) throws IOException {
		int n = io.nextInt();
		int[] dp = new int[n+1];
		dp[0] = 1;
		int mod = 1000000007;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<7;j++) {
				if(i-j>=0) {
					dp[i] += dp[i-j];
					dp[i] %= mod;
				}
			}
		}
		io.print(dp[n]);
	}

}
