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
    int k = sc.getInt();
    int n = sc.getInt();
    Moose[] all = new Moose[n+k-1];
    for (int i = 0; i < n+k-1; i++) {
      int year = sc.getInt();
      int str = sc.getInt();
      all[i] = new Moose(year, str);
    }
    Moose karl = all[0];
    Arrays.sort(all, new MooseComparator());
    PriorityQueue<Moose> queue = new PriorityQueue<>((x,y) -> - x.str + y.str);
    for (int i = 0; i < k-1; i++) {
      queue.offer(all[i]);
    }
    boolean flag = false;
    for (int i = 0; i < n; i++) {
      queue.offer(all[i+k-1]);
      Moose winner = queue.poll();
      if (winner.equals(karl)) {sc.println(i+2011); flag = true; break;}
    }
    if (!flag) sc.println("unknown");
    sc.close();
  }
}

class MooseComparator implements Comparator<Moose> {
  public int compare(Moose m1, Moose m2) {
    return m1.year == m2.year ? m1.str - m2.str : m1.year - m2.year;
  }
}

class Moose {
  int year;
  int str;

  public Moose(int a, int b) {year = a; str = b;}

  public boolean equals(Object o) {
    if (o instanceof Moose) {
      Moose tmp = (Moose) o;
      return str == tmp.str;
    }
    return false;
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
