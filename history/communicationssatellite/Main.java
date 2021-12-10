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
  static int BIG = 1000000000;

  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int n = sc.getInt();
    int[][] points = new int[n][3];
    for (int i = 0; i < n; i++) {
      int a = sc.getInt();
      int b = sc.getInt();
      int c = sc.getInt();
      points[i] = new int[]{a, b, c};
    }
    double[][] graph = new double[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j) continue;
        graph[i][j] = Math.hypot(points[i][0] - points[j][0], points[i][1] - points[j][1]) - points[i][2] - points[j][2];
      }
    }
    int[] solved = new int[n];
    double[] distance = new double[n];
    for (int i = 0; i < n; i++) distance[i] = BIG;
    int count = n-1;
    int curr = 0;
    double result = 0;
    while (count-- > 0) {
      solved[curr] = 1;
      for (int i = 0; i < n; i++) {
        if (solved[i] == 0 && graph[curr][i] < distance[i]) distance[i] = graph[curr][i];
      }
      double min = BIG;
      int id = 0;
      for (int i = 0; i < n; i++) {
        if (solved[i] == 0 && distance[i] < min) {min = distance[i]; id = i;}
      }
      result += min;
      curr = id;
    }
    sc.println(result);
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
