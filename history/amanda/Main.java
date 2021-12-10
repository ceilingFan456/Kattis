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
    int m = sc.getInt();
    @SuppressWarnings("unchecked")
    ArrayList<Pair>[] graph = (ArrayList<Pair>[]) new ArrayList[n];
    for (int i = 0; i < n; i++) graph[i] = new ArrayList<Pair>();
    for (int i = 0; i < m; i++) {
      int a = sc.getInt()-1;
      int b = sc.getInt()-1;
      int w = sc.getInt();
      graph[a].add(new Pair(b, w));
      graph[b].add(new Pair(a, w));
    }
    int[] colour = new int[2*n]; // keep track of colour of all points
    for (int i = 0; i < 2*n; i++) colour[i] = -1; // means havent been visited 
    int[] box = new int[2*n]; // keep track of count of that colour
    int res = 0; 
    boolean flag = true;
    int[] build = new int[2*n];
    int[] empty = new int[2*n];
    int c = 0; // pointer to the smaller colour of the current pair
    for (int i = 0; i < n; i++) {
      if (colour[i] == -1) {
        // bfs to colour
        LinkedList<Integer> queue = new LinkedList<>(); 
        queue.offer(i);
        colour[i] = c;
        box[colour[i]]++;
        flag = true;
        while (!queue.isEmpty()) {
          int v = queue.poll();
          for (Pair p : graph[v]) {
            if (colour[p.b] == -1) { // the point is new 
              if (p.w == 0) {
                colour[p.b] = colour[v];
                box[colour[p.b]]++;
                empty[colour[v]] = 1;
                if (build[colour[v]] == 1 || empty[colour[v] == c ? c+1 : c] == 1) flag = false;
              } else if (p.w == 2) {
                colour[p.b] = colour[v];
                box[colour[v]]++;
                build[colour[v]] = 1;
                if (empty[colour[v]] == 1 || build[colour[v] == c ? c+1 : c] == 1) flag = false;
              } else if (p.w == 1) {
                colour[p.b] = colour[v] == c ? c+1 : c;
                box[colour[p.b]]++;
              }
              queue.offer(p.b);
            } else { // the point is already coloured
              if (p.w == 0) {
                if (colour[v] != colour[p.b]) flag = false;
                else {
                  empty[colour[v]] = 1;
                  if (build[colour[v]] == 1 || empty[colour[v] == c ? c+1 : c] == 1) flag = false;
                }
              } else if (p.w == 2) {
                if (colour[v] != colour[p.b]) flag = false;
                else {
                  build[colour[v]] = 1;
                  if (empty[colour[v]] == 1 || build[colour[v] == c ? c+1 : c] == 1) flag = false;
                }
              } else if (p.w == 1) {
                if (colour[p.b] == colour[v]) flag = false;
                // else continue
              }
            }
          }
        } // bfs done
        // check validity
        if (flag == false) break;
        else if (build[c] == 1) res += box[c];
        else if (build[c+1] == 1) res += box[c+1];
        else if (empty[c] == 1) res += box[c+1];
        else if (empty[c+1] == 1) res += box[c];
        else res += box[c] > box[c+1] ? box[c+1] : box[c];
        c += 2; // update colour pair
      }
    }
    if (flag == false) sc.println("impossible");
    else sc.println(res);
    sc.close();
  }
}

class Pair {
  int b;
  int w;
  public Pair(int x, int y) {b = x; w = y;}
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
