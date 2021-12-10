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
    while (n-- > 0) {
      int s = sc.getInt();
      int p = sc.getInt();
      int[][] lst = new int[p][2];
      for (int i = 0; i < p; i++) {lst[i][0] = sc.getInt(); lst[i][1] = sc.getInt();}
      Edge[] edgeList = new Edge[p*(p-1)/2];
      int k = 0; // counter for edgelist
      for (int i = 0; i < p; i++) {
        for (int j = 0; j < i; j++) {
          double length = Math.hypot(lst[i][0] - lst[j][0], lst[i][1] - lst[j][1]);
          edgeList[k++] = new Edge(i, j, length);
        }
      }
      Arrays.sort(edgeList);
      //System.out.println(Arrays.toString(edgeList));
      //System.out.println(Arrays.deepToString(lst));
      Set set = new Set(p);
      double max = 0;
      k = 0;
      while (true) {
        if (set.s <= s) break;
        Edge e = edgeList[k++];
        if (set.union(e.i, e.j)) {
          if (e.w > max) max = e.w;
        }
      }
      sc.printf("%.2f\n", max);
    }
    sc.close();
  }
}

class Set {
  int[] root;
  int[] rank;
  int s;

  public Set(int n) {
    root = new int[n];
    for (int i = 0; i < n; i++) root[i] = i;
    rank = new int[n];
    s = n;
  }

  public int parent(int i) {
    if (root[i] == i) return i;
    root[i] = parent(root[i]);
    return root[i];
  }

  public boolean union(int i, int j) {
    int pi = parent(i);
    int pj = parent(j);
    if (pi == pj) return false;
    if(rank[pi] > rank[pj]) {
      root[pj] = pi;
      s--;
    } else {
      root[pi] = pj;
      if (rank[pi] == rank[pj]) rank[pj]++;
      s--;
    }
    return true;
  }
}

class Edge implements Comparable<Edge> {
  int i;
  int j;
  double w;
  public Edge(int a, int b, double c) {i = a; j = b; w = c;}
  public int compareTo(Edge e) {
    if (w < e.w) return -1;
    if (w > e.w) return 1;
    return i == e.i ? j - e.j : i - e.i;
  }
  public String toString() {
    return "edge: i = " + i + ", j = " + j + ", w = " + w;
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
