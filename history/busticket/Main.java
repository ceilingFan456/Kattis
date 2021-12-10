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
    int s = sc.getInt();
    int p = sc.getInt();
    int m = sc.getInt();
    int n = sc.getInt();
    int[] days = new int[n];
    int count = 0;
    for (int i = 0; i < n; i++) {
      days[i]= sc.getInt();
      if (i == 0 || days[i] != days[i-1]) count++;
    }
    int[][] dp = new int[count][2];
    int top = -1;
    for (int i = 0; i < n; i++) {
      if (i == 0 || days[i] != days[i-1]) {
        top++;
        dp[top][0] = days[i];
        dp[top][1] = 1;
      } else {
        dp[top][1]++;
      }
      //System.out.println(Arrays.deepToString(dp));
    }
    TreeSet<Pair> tree = new TreeSet<>();
    for (int i = count-1; i >= 0; i--) {
      int date = dp[i][0];
      int trips = dp[i][1];
      Pair pair = tree.ceiling(new Pair(date+m, 0));
      long min1 = pair == null ? p : p + pair.y;
      pair = tree.ceiling(new Pair(date+1, 0));
      long min2 = pair == null ? s*trips : s*trips + pair.y;
      if (min1 > min2) tree.add(new Pair(date, min2));
      else tree.add(new Pair(date, min1));
    }
    //System.out.println(tree);
    sc.println(tree.first().y);
    sc.close();
  }
}

class Pair implements Comparable<Pair> {
  int x;
  long y;
  public Pair(int a, long b) {x = a; y = b;}
  public int compareTo(Pair p) {
    return x - p.x;
  }
  public String toString() {
    return x + " " + y;
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
