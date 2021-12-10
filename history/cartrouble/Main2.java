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
  static int[] visited;
  static int colour;
  static int[] group;
  static ArrayList<Integer> toposort;

  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int n = sc.getInt();
    visited = new int[n];
    group = new int[n];
    toposort = new ArrayList<Integer>();
    ArrayList<Integer>[] graph = (ArrayList<Integer>[]) new ArrayList[n];
    for (int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>();
    ArrayList<Integer>[] reverse = (ArrayList<Integer>[]) new ArrayLit[n];
    for (int i = 0; i < n; i++) reverse[i] = new ArrayList<Integer>();
    for (int i = 0; i < n; ++) {
      int id = sc.getInt();
      int m = sc.getInt();
      for (int j = 0; j < m; j++) {
        int p = sc.getInt();
        graph[id].add(p);
        reverse[p].add(id);
      }
    }
    colour = -1;
    // prparation done
    for (int i = 0; i < n; i++) {
      if (visited[i] == 0) {
        colour++;
        dfs(i, graph);
      }
    }
    // toposort done
    colour = -1;
    visited = new int[n];
    for (int i = n-1; i >= 0; i--) {
      int v = toposort.get(i);
      if (visited[v] == 0) {
        colour++;
        dfs(v);
      }
    }
    if (colour == 0) sc.println("NO PROBLEMS");
    else {
      int ring = 
    }
    sc.close();
  }

  public static void dfs(int v, ArrayList<Integer>[] graph) {
    visited[v] = 1;
    group[v] = colour;
    for (int u : graph[v]) {
      if (visited[u] == 0) {
        dfs(u, graph);
      }
    }
    toposort.add(v);
  }
}

class Street {
  int a;
  public Street(int i) a = i;
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
