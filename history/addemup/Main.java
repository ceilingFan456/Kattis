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
    method();
  }
  public static void method() {
    Kattio sc = new Kattio(System.in, System.out);
    int n = sc.getInt();
    int sum = sc.getInt();
    ArrayList<Integer> lst = new ArrayList<>();
    HashSet<Integer> set = new HashSet<>();
    HashSet<Integer> extra = new HashSet<>();
    for (int i = 0; i < n; i++) {
      int a = sc.getInt();
      if (set.contains(a)) {
        extra.add(a);
      } else {
        set.add(a);
        lst.add(a);
      }
      int b = turn(a);
      if (b != -1) {
        if (set.contains(b)) {
          extra.add(b);
        } else {
          set.add(b);
          lst.add(b);
        }
      }
    }
    for (int i : lst) {
      int p = sum - i;
      if (p != turn(i) && set.contains(p)) {
        sc.println("YES");
        sc.close();
        return;
      }
      if (p == turn(i) && extra.contains(p)) {
        sc.println("YES");
        sc.close();
        return;
      }
    }
    sc.println("NO");
    sc.close();
    return;
  }

  public static int turn(int a) {
    int res = 0;
    while (a > 0) {
      int e = a % 10;
      if (e == 3 || e == 4 || e == 7) return -1;
      else if (e == 6) e = 9;
      else if (e == 9) e = 6;
      res = res * 10 + e;
      a = a / 10;
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
