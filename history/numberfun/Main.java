import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = Integer.parseInt(sc.nextLine());
    for (int i = 0; i < n; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      int c = sc.nextInt();
      sc.nextLine();
      if (test(a, b, c) || test(b, a, c)) {
        System.out.println("Possible");
      } else {
        System.out.println("Impossible");
      }
    }
  }

  public static boolean test(int a, int b, int c) {
    if (a + b == c) return true;
    if (a - b == c) return true;
    if (a * b == c) return true;
    if (b != 0 && (float) a / b == c) return true;
    return false;
  }
}
