import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int w = sc.nextInt();
    sc.nextLine();
    int n = sc.nextInt();
    sc.nextLine();
    int sum = 0;
    for (int i = 0; i < n; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      sc.nextLine();
      sum += a * b;
    }
    System.out.println(sum / w);
  }
}
