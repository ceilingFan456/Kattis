import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int t = sc.nextInt();
    int k = sc.nextInt();
    sc.nextLine();
    long[] array = new long[t];
    for (int i = 0; i < n; i++) {
      int curr = sc.nextInt();
      array[curr - 1] += 1;
    }
    sc.nextLine();
    long res = 0;
    for (int i = 0; i < t; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      sc.nextLine();
      res += array[i] * b;
      array[i] = array[i] * b + (2 - array[i]) * a;
    }
    Arrays.sort(array);
    for (int i = 0; i < k; i++) {
      res -= array[i];
    }
    System.out.println(res);
    sc.close();
  }
}
