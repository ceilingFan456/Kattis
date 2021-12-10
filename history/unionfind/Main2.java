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
import java.util.*;

public class Main2 {
  static int[] root;
  static int[] size;

  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in);
    int n = sc.getInt();
    int q = sc.getInt();
    root = new int[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {root[i] = i; size[i] = 1;}
    for (int i = 0; i < q; i++) {
      String op = sc.getWord();
      int a = sc.getInt();
      int b = sc.getInt();
      int pa = parent(a);
      int pb = parent(b);
      if (op.equals("=")) union(pa, pb);
      else {
        if (pa == pb) sc.println("yes");
        else sc.println("no");
      }
//      sc.println(Arrays.toString(root));
//      sc.println(Arrays.toString(rank));
    }
    sc.close();
  }

  public static int parent(int a) {
    int p = a;
    while (root[p] != p) p = root[p]; 
    int b = root[a];
    root[a] = p;
    while (b != root[a]) {
      p = root[b];
      root[b] = root[a];
      b = p;
    }
    return root[a];
  }

  /*
  public static String check(int a, int b) {
    if (parent(a) == parent(b)) return "yes";
    return "no";
  }
  */

  /*
  public static void union(int a, int b) {
    int pa = parent(a);
    int pb = parent(b);
    if (pa == pb) return;
    if (rank[pa] > rank[pb]) {
      root[pb] = pa;
      return;
    } else {
      root[pa] = pb;
      if (rank[pa] == rank[pb]) rank[pb]++;
      return;
    }
  }
  */

  public static void union(int pa, int pb) {
    if (pa == pb) return;
    if (size[pa] > size[pb]) {
      root[pb] = pa;
      size[pa] += size[pb];
      return;
    } else {
      root[pa] = pb;
      size[pb] += size[pa];
      return;
    }
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
