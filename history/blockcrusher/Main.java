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
  public static int BIG = 1000000000;
  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int h = sc.getInt();
    int w = sc.getInt();
    while (h != 0 || w != 0) {
      int[][] block = new int[h][w];
      for (int i = 0; i < h; i++) {
        String line = sc.getWord();
        for (int j = 0; j < w; j++) {
          block[i][j] = line.charAt(j) - 48;
        }
      }
      @SuppressWarnings("unchecked")
      ArrayList<Pair>[] graph = (ArrayList<Pair>[]) new ArrayList[2+w*h];
      for (int i = 0; i < 2 + w*h; i++) graph[i] = new ArrayList<>();
      for (int i = 0; i < w; i++) graph[0].add(new Pair(i+1, block[0][i]));
      for (int i = (h-1)*w + 1; i <= h*w; i++) graph[i].add(new Pair(h*w+1, 0));
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          int id = i*w + j + 1;
          if (i != 0) graph[id].add(new Pair(id-w, block[i-1][j]));
          if (i != h-1) graph[id].add(new Pair(id+w, block[i+1][j]));
          if (j != 0) graph[id].add(new Pair(id-1, block[i][j-1]));
          if (j != w-1) graph[id].add(new Pair(id+1, block[i][j+1]));
          if (i != 0 && j != 0) graph[id].add(new Pair(id-1-w, block[i-1][j-1]));
          if (i != 0 && j != w-1) graph[id].add(new Pair(id+1-w, block[i-1][j+1]));
          if (i != h-1 && j != 0) graph[id].add(new Pair(id-1+w, block[i+1][j-1]));
          if (i != h-1 && j != w-1) graph[id].add(new Pair(id+1+w, block[i+1][j+1]));
        }
      }
      //System.out.println(Arrays.toString(graph));
      // preparation done
      int[] pa = new int[h*w+2];
      int[] d = new int[h*w+2];
      for (int i = 0; i < h*w+2; i++) {d[i] = BIG; pa[i] = -1;}
      PriorityQueue<Pair> queue = new PriorityQueue<>();
      queue.offer(new Pair(0, 0)); // add s
      d[0] = 0;
      while (!queue.isEmpty()) {
        Pair curr = queue.poll();
        if (curr.w > d[curr.v]) continue;
        for (Pair p : graph[curr.v]) {
          if (d[curr.v] + p.w < d[p.v]) {
            d[p.v] = d[curr.v] + p.w; 
            pa[p.v] = curr.v;
            queue.offer(new Pair(p.v, d[p.v]));
          }
        }
      }
      //System.out.println(Arrays.toString(d));
      // short path found
      int[] crushed = new int[h*w+2];
      int curr = pa[h*w+1];
      while (curr != 0) {
        //System.out.println(curr);
        crushed[curr] = -1; // the block is crushed
        curr = pa[curr];
      }
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          sc.print(crushed[i*w+j+1] == -1 ? " " : block[i][j]);
        }
        sc.print("\n");
      }
      sc.print("\n");
      h = sc.getInt();
      w = sc.getInt();
    }
    sc.close();
  }


  public static void print(int[][] a, int h, int w) {
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        System.out.print(a[i][j] + " ");
      }
      System.out.println();
    }
  }
}

class Pair implements Comparable<Pair> {
  int v;
  int w;

  public Pair(int a, int b) {v = a ; w= b;}

  public String toString() {return v + " " + w;}

  public int compareTo(Pair p) {
    return w == p.w ? v - p.v : w - p.w;
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
