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
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
  static int[] root;
  static int[] rank;
  static int[] size;

  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int n = sc.getInt();
    root = new int[500001];
    rank = new int[500001];
    size = new int[500001];
    for (int i = 0; i < 500001; i++) {
      root[i] = i;
      size[i] = 1;
    }
    int count = 0;
    for (int i = 0; i < n; i++) {
      int m = sc.getInt();
      int sum = 0;
      ArrayList<Integer> list = new ArrayList<>();
      HashSet<Integer> set = new HashSet<>();
      for (int j = 0; j < m; j++) {
        int x = sc.getInt();
        int px = parent(x);
        if (!set.contains(px)) {
          list.add(px);
          set.add(px);
          sum += size[px];
        }
      }
      if (sum == m) {
        count++;
        for (int j = 1; j < list.size(); j++) {
          union(list.get(j-1), list.get(j));
        }
      }
    }
    sc.println(count);
    sc.close();
  }

  public static int parent(int a) {
    if (root[a] == a) return a;
    root[a] = parent(root[a]);
    return root[a];
  }

  public static void union(int a, int b) {
    int pa = parent(a);
    int pb = parent(b);
    if (pa == pb) return;
    if (rank[pa] > rank[pb]) {
      root[pb] = pa;
      size[pa] += size[pb];
      return;
    } else {
      root[pa] = pb;
      if (rank[pa] == rank[pb]) rank[pb]++;
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
