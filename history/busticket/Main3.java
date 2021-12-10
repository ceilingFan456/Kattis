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

public class Main2 {
  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int s = sc.getInt();
    int p = sc.getInt();
    int m = sc.getInt();
    int n = sc.getInt();
    int[] days = new int[n];
    for (int i = 0; i < n; i++) {
      days[i]= sc.getInt();
    }
    int last = days[n-1];
    long[] dp = new long[last+1];
    for (int i = 0; i < n; i++) {
      dp[days[i]]++;
    }
    //sc.println(Arrays.toString(dp));
    for (int i = last; i >= 0; i--) {
      if (dp[i] == 0) {dp[i] = dp[i+1]; continue;}
      long v1 = i + m > last ? p : dp[i+m] + p;
      long v2 = i + 1 > last ? s*dp[i] : dp[i+1] + s*dp[i];
      dp[i] = v1 > v2 ? v2 : v1;
    }
    //sc.println(Arrays.toString(dp));
    print(dp);
    sc.println(dp[0]);
    sc.close();
  }

  public static void print(long[] dp) {
    for (int i = 0; i < dp.length; i++) {
      if (i == 0 || dp[i] != dp[i-1]) {
        System.out.println(i + " " + dp[i]);
      }
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
