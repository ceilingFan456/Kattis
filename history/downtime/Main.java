import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    int[] servers = new int[n];
    int top = 0;
    for (int i = 0; i < n; i++) {
      int curr = sc.nextInt();
      boolean notReplaced = true;
      for (int j = 0; j < top; j++) {
        if (servers[j] + 1000 <= curr) {
          servers[j] = curr;
          notReplaced = false;
          break;
        }
      }
      if (notReplaced) servers[top++] = curr;
//      System.out.println(Arrays.toString(servers));
    }
//    System.out.println(top);
    if (n == 0) System.out.println(0);
    else System.out.println((top - 1) / k + 1);
    sc.close();
  }
}
