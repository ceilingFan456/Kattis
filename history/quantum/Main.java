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
  static long BIG = (1 << 62) - 1;
  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int t = sc.getInt();
    while (t -- > 0) {
      int l = sc.getInt();
      int n = 1 << l;
      int nop = sc.getInt();
      int nq = sc.getInt();
      String[] op = new String[nop];
      int[] w = new int[nop]; 
      for (int i = 0; i < nop; i++) {
        op[i] = sc.getWord();
        w[i] = sc.getInt();
      }
      long[][] graph = new long[n][n];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (i == j) graph[i][j] = 0;
          else graph[i][j] = BIG;
        }
      }
      // preparation done
      // add edges, that is the case of {}
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < nop; j++) {
          int u = convert(i, op[j], l);
          if (graph[i][u] > w[j]) graph[i][u] = w[j];
        }
      }
      // run the dp algorithm
      for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            long alt = 0;
            if (graph[i][k] == BIG || graph[k][j] == BIG) alt = BIG;
            else alt = graph[i][k] + graph[k][j];
            if (alt < graph[i][j]) graph[i][j] = alt;
          }
        }
      }
      // dp table done
      for (int i = 0; i < nq; i++) {
        String a = sc.getWord();
        String b = sc.getWord();
        int v = change(a, l);
        int u = change(b, l);
        if (graph[v][u] == BIG) sc.print("NP ");
        else sc.print(graph[v][u] + " ");
      }
      sc.println();
    }
    sc.close();
  }

  public static int convert(int a, String op, int l) {
    int res = 0;
    for (int i = l-1; i >= 0; i--) {
      int b = a % 2;
      a = a / 2;
      if (op.charAt(i) == 'N') b = b;
      else if (op.charAt(i) == 'F') b = b == 1 ? 0 : 1;
      else if (op.charAt(i) == 'S') b = 1;
      else if (op.charAt(i) == 'C') b = 0;
      res = res + (1 << (l-1-i)) * b;
    }
    return res;
  }

  public static int change(String a, int l) {
    int res = 0;
    for (int i = 0; i < l; i++) {
      res = res << 1;
      if (a.charAt(i) == '1') res += 1;
    }
    return res;
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
