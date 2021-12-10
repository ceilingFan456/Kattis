import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    double result = 0;
    for (int i = 0; i < n; i++) {
      double a = sc.nextDouble();
      double b = sc.nextDouble();
      result += a*b;
    }
    System.out.println(result);
    sc.close();
  }
}
