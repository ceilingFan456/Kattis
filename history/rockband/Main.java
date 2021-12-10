import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt();
    int s = sc.nextInt();
    int[][] a = new int[m][s];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < s; j++) {
        a[i][j] = sc.nextInt();
      }
    }
    int[] b = new int[s+1];
    int count = 0;
    for (int i = 0; i < s; i++) {
      for (int j = 0; j < m; j++) {
        if (b[a[j][i]] == 0) {
          count += 1;
          b[a[j][i]] = 1;
        }
      }
      if (count == i + 1) break;
    }
    System.out.println(count);
    for (int i = 1; i <= s; i++) {
      if (b[i] != 0) System.out.print(i + " ");
    }
    sc.close();
  }
}
