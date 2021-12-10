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
    int[] out = new int[n];
    int[] e = new int[n];
    @SuppressWarnings("unchecked")
    ArrayList<Integer>[] graph = (ArrayList<Integer>[]) new ArrayList[n];
    for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      e[i] = sc.getInt();
      int m = sc.getInt();
      for (int j = 0; j < m; j++) {
        int u = sc.getInt() - 1;
        graph[i].add(u); // u -> i 
        out[u]++;
      }
    }
    PriorityQueue<Meeting> heap = new PriorityQueue<Meeting>();
    for (int i = 0; i < n; i++) {
      if (out[i] == 0) heap.offer(new Meeting(i, e[i]));
    }
    int max = 0;
    for (int i = n-1; i >= 0; i--) {
      int v = heap.poll().i;
      //System.out.println(v + " " + e[v]);
      int time = e[v] + i;
      if (time > max) max = time;
      for (int u : graph[v]) {
        out[u]--;
        if (out[u] == 0) heap.offer(new Meeting(u, e[u]));
      }
    }
    sc.println(max);
    sc.close();
  }
}

class Meeting implements Comparable<Meeting> {
  int i;
  int e;
  public Meeting(int i, int e) {
    this.i = i;
    this.e = e;
  }
  public int compareTo(Meeting m) {
    return e - m.e;
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
