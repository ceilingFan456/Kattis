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
    int sx = sc.getInt();
    int sy = sc.getInt();
    int ex = sc.getInt();
    int ey = sc.getInt();
    boolean leftright = sx > ex;
    boolean updown = sy > ey;
    if (leftright) {
      int tmp = sx;
      sx = ex;
      ex = tmp;
    }
    if (updown) {
      int tmp = sy;
      sy = ey;
      ey = tmp;
    }
    ArrayList<Pair> lst = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int x = leftright ? sx + ex - sc.getInt() : sc.getInt();
      int y = updown ? sy + ey - sc.getInt() : sc.getInt();
      if (x < sx || x > ex) continue;
      if (y < sy || y > ey) continue;
      lst.add(new Pair(x, y));
    }
    Collections.sort(lst);
    //System.out.println(lst);
    ArrayList<Pair> dp = new ArrayList<>();
    for (int i = 0; i < lst.size(); i++) {
      int s = 0; 
      int j = dp.size()-1;
      Pair curr = lst.get(i);
      while (s <= j) {
        int mid = (s + j) / 2;
        if (curr.y > dp.get(mid).y) {
          j = mid - 1;
        } else {
          s = mid + 1;
        }
      } 
      //System.out.println("s is " + s);
      if (s == dp.size()) dp.add(curr);
      else dp.set(s, curr);
      //System.out.println(dp);
    }
    sc.println(dp.size());
    sc.close();
  }
}

class Pair implements Comparable<Pair> {
  int x;
  int y;

  public Pair(int a, int b) {x = a; y = b;}

  public int compareTo(Pair p) {
    return x == p.x ? p.y - y : p.x - x;
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
