import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int s = sc.nextInt();
    int n = sc.nextInt();
    int m = sc.nextInt();
    int[] d = new int[s-1];
    int pre = sc.nextInt();
    int curr = 0;
    for (int i = 1; i < s; i++) {
      curr = sc.nextInt();
      if (curr - pre > 0) d[i-1] = 1;
      else d[i-1] = -1;
      pre = curr;
    }
    int[] e = new int[s-1];
    int sign = d[0];
    int count = 1;
    int j = 0; // next available in e
    for (int i = 1; i < s-1; i++) {
      if (sign == d[i]) {
        count++;
      } else {
        e[j++] = count;
        sign = d[i];
        count = 1;
      }
    }
    e[j] = count;
    int peak = 0;
    int valley = 0;
    if (d[0] > 0) {
      for (int i = 1; i <= j; i++) {
        if (i % 2 == 1) {
          if (e[i] >= n-1 && e[i-1] >= n-1) peak++;
        } else {
          if (e[i] >= m-1 && e[i-1] >= m-1) valley++;
        }
      }
    } else {
      for (int i = 1; i <= j; i++) {
        if (i % 2 == 1) {
          if (e[i] >= m-1 && e[i-1] >= m-1) valley++;
        } else {
          if (e[i] >= n-1 && e[i-1] >= n-1) peak++;
        }
      } 
    }
//    System.out.println(Arrays.toString(d));
//    System.out.println(Arrays.toString(e));
    System.out.println(peak + " " + valley);
    sc.close();
  }
}
