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
    int v = sc.getInt();
    int e = sc.getInt();
    while (v != 0 || e != 0) {
      //System.out.println(v + " " + e);
      PriorityQueue<Edge> edgeList = new PriorityQueue<>();
      int[] visited = new int[v];
      ArrayList<Edge> result = new ArrayList<>();
      int count = 0;
      int weight = 0;
      @SuppressWarnings("unchecked")
      ArrayList<Pair>[] graph = (ArrayList<Pair>[]) new ArrayList[v];
      for (int i = 0; i < v; i++) graph[i] = new ArrayList<Pair>();
      for (int i = 0; i < e; i++) {
        int a = sc.getInt();
        int b = sc.getInt();
        int c = sc.getInt();
        graph[a].add(new Pair(b, c)); // pair <- v, w
        graph[b].add(new Pair(a, c));
      }
      // graph updated
      // put edges of 0 into priority queue
      visited[0] = 1;
      count++;
      for (Pair p : graph[0]) {
        if (visited[p.a] == 0) {
          edgeList.offer(new Edge(0, p.a, p.b));
        }
      }
      //System.out.println(Arrays.toString(graph));
      //System.out.println(edgeList);
      // pop all from queue
      while (!edgeList.isEmpty()) {
        //System.out.println("pq is ");
        //System.out.println(edgeList);
        Edge edge = edgeList.poll();
        if (visited[edge.v1] != 0 && visited[edge.v2] != 0) continue;
        result.add(edge);
        weight += edge.w;
        int u = visited[edge.v1] == 0 ? edge.v1 : edge.v2;
        //System.out.println(u);
        visited[u] = 1;
        count++;
        for (Pair p : graph[u]) {
          if (visited[p.a] == 0) {
            edgeList.offer(new Edge(u, p.a, p.b));
          }
        }
      }
      //System.out.println(result);
      //System.out.println("count is " + count);
      if (count != v) {
        sc.println("Impossible");
      }
      else {
        Collections.sort(result, new EdgeComparator());
        sc.println(weight);
        for (Edge edge : result) {
          sc.println(edge.v1 + " " + edge.v2);
        }
      }
      v = sc.getInt();
      e = sc.getInt();
    }
    sc.close();
  }
}

class Pair {
  int a;
  int b;

  public Pair(int a, int b) {
    this.a = a;
    this.b = b;
  }

  public String toString() {
    return "Pair " + a + " " + b;
  }
}

class Edge implements Comparable<Edge> {
  int v1;
  int v2;
  int w;

  public Edge(int v1, int v2, int w) {
    if (v1 < v2) {this.v1 = v1; this.v2 = v2;}
    else {this.v1 = v2; this.v2 = v1;}
    this.w = w;
  }

  public int compareTo(Edge e) {
    if (w < e.w) return -1;
    else if (w > e.w) return 1;
    else if (v1 < e.v1) return -1;
    else if (v1 > e.v1) return 1;
    else return v2 - e.v2;
  }

  public String toString() {
    return "Edge " + v1 + " " + v2 + " " + w;
  }
}

class EdgeComparator implements Comparator<Edge> {
  public int compare(Edge e1, Edge e2) {
    if (e1.v1 < e2.v1) return -1;
    else if (e1.v1 > e2.v1) return 1;
    else if (e1.v2 < e2.v2) return -1;
    else if (e1.v2 > e2.v2) return 1;
    return 0;
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
