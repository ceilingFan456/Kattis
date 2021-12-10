import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int res = 0;
    sc.nextLine();
    for (int i = 0; i < n; i++) {
      int x = sc.nextInt();
      sc.nextLine();
      res += Math.pow(x / 10, x % 10);
    }
    System.out.println(res);
  }
}
