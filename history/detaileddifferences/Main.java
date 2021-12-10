import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < n; i++) {
      String a = sc.nextLine();
      String b = sc.nextLine();
      String res = "";
      for (int j = 0; j < a.length(); j++) {
        if (a.charAt(j) == b.charAt(j)) {
          res += ".";
        } else {
          res += "*";
        }
      }
      System.out.println(a);
      System.out.println(b);
      System.out.println(res);
      System.out.println();
    }
  }
}
