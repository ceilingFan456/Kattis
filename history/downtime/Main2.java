import java.util.*;

public class Main2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] b = new int[110000]; // b[0] = a[0], b[i] = a[i] - a[i-1]
    int n = sc.nextInt();
    int k = sc.nextInt();
    int curr = 0;
    for (int i = 0; i < n; i++) {
      curr = sc.nextInt();
      b[curr] += 1;
      b[curr + 1000] -= 1;
    }
    int[] a = new int[curr + 2000];
    int max = 0;
    for (int i = 0; i < curr + 2000; i++) {
      if (i == 0) a[i] = b[i];
      else a[i] = a[i-1] + b[i];
      if (a[i] > max) max = a[i];
    }
    System.out.println((max - 1) / k + 1);
    sc.close();
  }
}
