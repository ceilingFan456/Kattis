import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = Integer.parseInt(sc.nextLine());
    String a = sc.nextLine();
    String b = sc.nextLine();
    if (check(n % 2 == 0, a, b)) {
      System.out.println("Deletion succeeded");
    } else {
      System.out.println("Deletion failed");
    }
    sc.close();
  }

  public static boolean check(boolean even, String a, String b) {
    if (even) return a.equals(b);
    int n = a.length();
    for (int i = 0; i < n; i++) {
      if (a.charAt(i) == b.charAt(i)) return false;
    }
    return true;
  }
}
