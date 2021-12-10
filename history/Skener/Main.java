import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int r = sc.nextInt();
    int c = sc.nextInt();
    int zr = sc.nextInt();
    int zc = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < r; i++) {
      String line = sc.nextLine();
      String res = "";
      for (int j = 0; j < c; j++) {
        res += Character.toString(line.charAt(j)).repeat(zc);
      }
      res = res + "\n";
      System.out.print(res.repeat(zr));
    }
    sc.close();
  }
}
