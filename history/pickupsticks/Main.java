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

public class Main {
  static ArrayList<Integer>[] graph;
  static int[] visited;
  static ArrayList<Integer> toposort;
  static boolean cyclic;

  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int n = sc.getInt();
    int m = sc.getInt();
    @SuppressWarnings("unchecked")
    ArrayList<Integer>[] tmp = (ArrayList<Integer>[]) new ArrayList[n];
    graph = tmp;
    for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
    visited = new int[n];
    cyclic = false;
    toposort = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      int a = sc.getInt()-1;
      int b = sc.getInt()-1;
      graph[a].add(b);
    }
    for (int i = 0; i < n; i++) {
      if (visited[i] == 0) {
        dfs(i);
      }
    }
    if (cyclic) sc.println("IMPOSSIBLE");
    else {
      for (int i = toposort.size()-1; i >= 0; i--) sc.println(toposort.get(i) + 1);
    }
    sc.close();
  }

  public static void dfs(int v) {
    //System.out.println("v is " + v);
    visited[v] = 1; // visiting
    for (int u : graph[v]) {
      if (visited[u] == 0) dfs(u);
      else if (visited[u] == 1) cyclic = true;
    }
    visited[v] = 2;
    toposort.add(v);
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
