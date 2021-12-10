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
    int t = sc.getInt();
    while (t-- > 0) {
      int n = sc.getInt();
      int m = sc.getInt();
      int[] labs = new int[n];
      for (int i = 0; i < n; i++) labs[i] = sc.getInt();
      @SuppressWarnings("unchecked")
      ArrayList<Integer>[] graph = (ArrayList<Integer>[]) new ArrayList[n];
      int[] indeg = new int[n];
      for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
      for (int i = 0; i < m; i++) {
        int a = sc.getInt() - 1;
        int b = sc.getInt() - 1;
        graph[a].add(b);
        indeg[b]++;
      }
      LinkedList<Integer> queue1 = new LinkedList<>();
      LinkedList<Integer> queue2 = new LinkedList<>();
      for (int i = 0; i < n; i++) {
        if (indeg[i] == 0) {
          if (labs[i] == 1) queue1.add(i);
          else queue2.add(i);
        }
      }
      int count = -1;
      //System.out.println("queue1 is " + queue1);
      //System.out.println("queue2 is " + queue2);
      //System.out.println("graph is " + Arrays.toString(graph));
      //System.out.println("indeg is " + Arrays.toString(indeg));
      //System.out.println("labs is " + Arrays.toString(labs));
      while (!queue1.isEmpty() || !queue2.isEmpty()) {
        //break;
        if (!queue1.isEmpty()) count++;
        while (!queue1.isEmpty()) {
          //System.out.println("queue1 : " + queue1);
          int u = queue1.poll();
          for (int v : graph[u]) {
            indeg[v]--;
            if (indeg[v] == 0) {
              if (labs[v] == 1) queue1.add(v);
              else queue2.add(v);
            }
          }
        }
        if (!queue2.isEmpty()) count++;
        while (!queue2.isEmpty()) {
          //System.out.println("queue2 : " + queue2);
          int u = queue2.poll();
          for (int v : graph[u]) {
            indeg[v]--;
            if (indeg[v] == 0) {
              if (labs[v] == 1) queue1.add(v);
              else queue2.add(v);
            }
          }
        }
      }
      //System.out.println("queue1 is " + queue1);
      //System.out.println("queue2 is " + queue2);
      //System.out.println("graph is " + Arrays.toString(graph));
      //System.out.println("indeg is " + Arrays.toString(indeg));
      //System.out.println("labs is " + Arrays.toString(labs));
      sc.println(count);
    }
    sc.close();
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
