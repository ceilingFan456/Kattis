import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < n; i++) {
      int p = sc.nextInt();
      sc.nextLine();
      switch (p) {
        case 0:
          System.out.println(1);
          break;
        case 1:
          System.out.println(1);
          break;
        case 2:
          System.out.println(2);
          break;
        case 3:
          System.out.println(6);
          break;
        case 4:
          System.out.println(4);
          break;
        default:
          System.out.println(0);
      }
    }
  }
}
