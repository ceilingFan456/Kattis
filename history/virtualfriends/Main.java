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
import java.util.HashMap;
import java.util.Arrays;

public class Main {
  static int[] root;
  static int[] rank;
  static int[] size;
  static int counter;
  static HashMap<String, Integer> map;

  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in);
    int n = sc.getInt();
    for (int i = 0; i < n; i++) {
      int f = sc.getInt();
      root = new int[2 * f + 1];
      rank = new int[2 * f + 1];
      size = new int[2 * f + 1];
      counter = 1;
      for (int j = 0; j < 2 * f + 1; j++) {
        root[j] = j;
        rank[j] = 0;
        size[j] = 1;
      }
      map = new HashMap<>();
      for (int j = 0; j < f; j++) {
        String a = sc.getWord();
        String b = sc.getWord();
        if (!map.containsKey(a)) map.put(a, counter++);
        if (!map.containsKey(b)) map.put(b, counter++);
        int ka = map.get(a);
        int kb = map.get(b);
        int pka = parent(ka);
        int pkb = parent(kb);
        int pp = union(pka, pkb);
        sc.println(size[pp]);
        //sc.println(Arrays.toString(root));
        //sc.println(Arrays.toString(size));
        //sc.println(Arrays.toString(rank));
      }
    }
    sc.close();
  }

  public static int parent(int ka) {
    if (ka == root[ka]) return ka;
    root[ka] = parent(root[ka]);
    return root[ka];
  }

  public static int union(int a, int b) {
    int pa = parent(a);
    int pb = parent(b);
    if (pa == pb) return pa;
    if (rank[pa] > rank[pb]) {
      root[pb] = pa;
      size[pa] += size[pb];
      return pa;
    } else {
      root[pa] = pb;
      size[pb] += size[pa];
      if (rank[pa] == rank[pb]) rank[pb]++;
      return pb;
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
