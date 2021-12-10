import java.util.Scanner;

class Main {
  public static void main (String[] args) {
    Scanner sc = new Scanner(System.in);
    String name = sc.nextLine();
    String result = "" + name.charAt(0);
    for (int i = 1; i < name.length(); i++) {
      if (name.charAt(i) != name.charAt(i-1)) {
        result += name.charAt(i);
      }
    }
    System.out.print(result);
    sc.close();
  }
}
