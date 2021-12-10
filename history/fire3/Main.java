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
    int r = sc.getInt();
    int c = sc.getInt();
    int[][] graph = new int[r][c];
    int[][] visited = new int[r][c];
    Pair joe = null;
    LinkedList<Pair> queue = new LinkedList<>();
    for (int i = 0; i < r; i++) {
      String line = sc.getWord();
      for (int j = 0; j < c; j++) {
        graph[i][j] = line.charAt(j);
        if (graph[i][j] == 'J') {joe = new Pair(i, j);}
        if (graph[i][j] == 'F') {queue.add(new Pair(i, j));}
      }
    }
    queue.add(joe);
//    System.out.println(Arrays.deepToString(graph));
//    System.out.println(queue);
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.x - 1 >= 0 && graph[curr.x-1][curr.y] == '.') {
        graph[curr.x-1][curr.y] = graph[curr.x][curr.y];
        visited[curr.x-1][curr.y] = visited[curr.x][curr.y] + 1;
        queue.add(new Pair(curr.x-1, curr.y));
      }
      if (curr.x + 1 < r && graph[curr.x+1][curr.y] == '.') {
        graph[curr.x+1][curr.y] = graph[curr.x][curr.y];
        visited[curr.x+1][curr.y] = visited[curr.x][curr.y] + 1;
        queue.add(new Pair(curr.x+1, curr.y));
      }
      if (curr.y - 1 >= 0 && graph[curr.x][curr.y-1] == '.') {
        graph[curr.x][curr.y-1] = graph[curr.x][curr.y];
        visited[curr.x][curr.y-1] = visited[curr.x][curr.y] + 1;
        queue.add(new Pair(curr.x, curr.y-1));
      }
      if (curr.y + 1 < c && graph[curr.x][curr.y+1] == '.') {
        graph[curr.x][curr.y+1] = graph[curr.x][curr.y];
        visited[curr.x][curr.y+1] = visited[curr.x][curr.y] + 1;
        queue.add(new Pair(curr.x, curr.y+1));
      }
    }
    //print(graph, r, c);
    //printb(visited, r, c);
    int steps = BIG;
    for (int i = 0; i < c; i++) {
      if (graph[0][i] == 'J' && visited[0][i] < steps) steps = visited[0][i];
      if (graph[r-1][i] == 'J' && visited[r-1][i] < steps) steps = visited[r-1][i];
    }
    for (int i = 0; i < r; i++) {
      if (graph[i][0] == 'J' && visited[i][0] < steps) steps = visited[i][0];
      if (graph[i][c-1] == 'J' && visited[i][c-1] < steps) steps = visited[i][c-1]; 
    }
    if (steps == BIG) sc.println("IMPOSSIBLE");
    else sc.println(steps+1);
    sc.close();
  }

  public static void print(int[][] graph, int r, int c) {
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        System.out.print((char)graph[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void printb(int[][] graph, int r, int c) {
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        System.out.print(graph[i][j] + " ");
      }
      System.out.println();
    }
  }
}

class Pair {
  int x;
  int y;
  public Pair(int a, int b) {x = a; y = b;}
  public String toString() {return x + " " + y;}
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
