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

public class Main4 {
  public static void main(String[] args) {  
    Kattio sc = new Kattio(System.in, System.out);
    int s = sc.getInt();
    int p = sc.getInt();
    int m = sc.getInt();
    int n = sc.getInt();
    int[] lst = new int[n];
    int count = 0;
    for (int i = 0; i < n; i++) {
      lst[i] = sc.getInt();
      if (i == 0 || lst[i] != lst[i-1]) count++;
    }
    int[][] days = new int[count][2];
    long[] dp = new long[count+1];
    //System.out.println(count);
    count = -1;
    for (int i = 0; i < n; i++) {
      if (i == 0 || lst[i] != lst[i-1]) {
        count++;
        days[count][0] = lst[i];
        days[count][1] = 1;
      } else {
        days[count][1]++;
      }
    }
    //System.out.println(Arrays.deepToString(days));
    //System.out.println(Arrays.toString(dp));
    int pointer = count; // the id of last ele
    //System.out.println("count is " + count);
    for (int i = dp.length-2; i >= 0; i--) {
      long min1 = dp[i+1] + days[i][1] * s;
      while (days[pointer][0] >= days[i][0] + m) pointer--;
      long min2 = dp[pointer+1] + p;
      if (min1 > min2) dp[i] = min2;
      else dp[i] = min1;
      //System.out.println(Arrays.toString(dp));
    }
    sc.println(dp[0]);
    sc.close();
  }
}

class Pair {
  int date;
  int freq;
  public Pair(int a, int b) {
    date = a;
    freq = b;
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
