import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    boolean flag = true;
    String res = "";
    for (int i = 0; i < str.length(); i++) {
      if (flag) {
        res += Character.toString(str.charAt(i));
        flag = false;
      }
      if (str.charAt(i) == '-') {
        flag = true;
      }
    }
    System.out.println(res);
  }
}
