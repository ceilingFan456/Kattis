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
  static double BIG = 1000000000;

  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int n = sc.getInt(); 
    while (n-- > 0) {
      int m = sc.getInt();
      double[][] points = new double[m][2];
      double[][] graph = new double[m][m];
      for (int i = 0; i < m; i++) {
        points[i][0] = sc.getDouble();
        points[i][1] = sc.getDouble();
      }
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < m; j++) {
          double length = Math.hypot(points[i][0] - points[j][0], points[i][1] - points[j][1]);
          graph[i][j] = length;
          graph[j][i] = length;
        }
      }
      //System.out.println(Arrays.deepToString(graph));
      double[] distance = new double[m];
      for (int i = 0; i < m; i++) distance[i] = BIG;
      double[] solved = new double[m];
      int count = 0;
      int next = 0;
      double result = 0;
      while (count < m-1) {
        solved[next] = 1;
        count++;
        for (int i = 0; i < m; i++) {
          if (solved[i] == 0 && distance[i] > graph[next][i]) {
            distance[i] = graph[next][i];
          }
        }
        double min = BIG;
        for (int i = 0; i < m; i++) {
          if (solved[i] == 0 && distance[i] < min) {
            min = distance[i];
            next = i;
          }
        }
        result += min;
        //System.out.println("distance is ");
        //System.out.println(Arrays.toString(distance));
      }
      sc.println(result);
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
