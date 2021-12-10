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
  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int n = sc.getInt();
    int[] map1 = new int[1001]; // 0 - 1000
    //int[] map2 = new int[n];
    @SuppressWarnings("unchecked")
    ArrayList<Integer>[] raw = (ArrayList<Integer>[]) new ArrayList[n];
    @SuppressWarnings("unchecked")
    ArrayList<Integer>[] graph = (ArrayList<Integer>[]) new ArrayList[n];
    @SuppressWarnings("unchecked")
    ArrayList<Integer>[] reverse = (ArrayList<Integer>[]) new ArrayList[n];
    for (int i = 0; i < n; i++) {
      raw[i] = new ArrayList<>();
      graph[i] = new ArrayList<>();
      reverse[i] = new ArrayList<>();
    }
    ArrayList<Integer> sequence = new ArrayList<>(); // inside is the id 
    int[] visited = new int[n];
    for (int i = 0; i < n; i++) {
      int id = sc.getInt();
      map1[id] = i;
      //map2[i] = id;
      sequence.add(id);
      int m = sc.getInt();
      for (int j = 0; j < m; j++) {
        int u = sc.getInt();
        raw[i].add(u);
      }
    }
    for (int i = 0; i < n; i++) {
      for (int u : raw[i]) {
        graph[i].add(map1[u]);
        reverse[map1[u]].add(i);
      }
    }
    //System.out.println(Arrays.toString(raw));
    //System.out.println(Arrays.toString(graph));
    //System.out.println(Arrays.toString(reverse));
    // preparation done
    // check reachability
    int[] colour = new int[n];
    int cc = 0;
    dfs1(map1[0], graph, visited, colour, cc);
    cc++;
    for (int i = 0; i < n; i++) {
      if (visited[i] == 0) {
        dfs1(i, graph, visited, colour, cc);
        cc++;
      }
    }
    ArrayList<Integer> unreachable = new ArrayList<>();
    for (int v : sequence) {
      if (colour[map1[v]] != colour[map1[0]]) unreachable.add(v);
    }
    // reachability done
    // check trapped
    colour = new int[n];
    cc = 0;
    visited = new int[n];
    dfs1(map1[0], reverse, visited, colour, cc);
    cc++;
    for (int i = 0; i < n; i++) {
      if (visited[i] == 0) {
        dfs1(i, reverse, visited, colour, cc);
        cc++;
      }
    }
    ArrayList<Integer> trapped = new ArrayList<>();
    for (int v : sequence) {
      if (colour[map1[v]] != colour[map1[0]]) trapped.add(v);
    }
    if (trapped.isEmpty() && unreachable.isEmpty()) {
      sc.println("NO PROBLEMS");
    } else {
      for (int v : trapped) {
        sc.println("TRAPPED " + v);
      }
      for (int v : unreachable) {
        sc.println("UNREACHABLE " + v);
      }
    }
    sc.close();
  }

  public static void dfs1(int v, ArrayList<Integer>[] graph, int[] visited, int[] colour, int cc) {
    visited[v] = 1;
    colour[v] = cc;
    for (int u : graph[v]) {
      if (visited[u] == 0) dfs1(u, graph, visited, colour, cc);
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
