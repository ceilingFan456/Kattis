/** Simple yet moderately fast I/O routines.
 *
 * Example usage:
 *
 * Kattio io = new Kattio(System.in, System.out);
 *
 * while (io.hasMoreTokens()) {
 *    int n = io.getInt();
 *    double d = io.getDouble();
 *    double ans = d*n;
 *
 *    io.println("Answer: " + ans);
 * }
 *
 * io.close();
 *
 *
 * Some notes:
 *
 * - When done, you should always do io.close() or io.flush() on the
 *   Kattio-instance, otherwise, you may lose output.
 *
 * - The getInt(), getDouble(), and getLong() methods will throw an
 *   exception if there is no more data in the input, so it is generally
 *   a good idea to use hasMoreTokens() to check for end-of-file.
 *
 * @author: Kattis
 */

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Main {
  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int n = sc.getInt();
    int m = sc.getInt();
    Set set = new Set(n);
    for (int i = 0; i < m; i++) {
      int a = sc.getInt() - 1;
      int b = sc.getInt() - 1;
      set.union(a, b);
    }
    boolean flag = true;
    for (int i = 1; i < n; i++) {
      if (!set.check(0, i)) {flag = false; sc.println(i+1);}
    }
    if (flag) sc.println("Connected");
    sc.close();
  }
}

class Set {
  int[] p;
  int[] rank;

  public Set(int n) {
    p = new int[n];
    for (int i = 0; i < n; i++) p[i] = i;
    rank = new int[n];
  }

  public boolean check(int i, int j) {
    return parent(i) == parent(j);
  }

  public int parent(int i) {
    if (i == p[i]) return i;
    p[i] = parent(p[i]);
    return p[i];
  }

  public void union(int i, int j) {
    int pi = parent(i);
    int pj = parent(j);
    if (pi == pj) return;
    if (rank[pi] > rank[pj]) {
      p[pj] = pi;
    } else {
      p[pi] = pj;
      if (rank[pi] == rank[pj]) rank[pj]++;
    }
    return;
  }
}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
