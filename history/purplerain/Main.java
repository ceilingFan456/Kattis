import java.util.*;

public class Main {
  public static void main(String[] args) {
    String str = new Scanner(System.in).nextLine();
    int value = 0;
    int max = 0;
    int maxIndex1 = -1;
    int maxIndex2 = 0;
    int down = 0;
    int min = 0;
    int minIndex1 = -1;
    int minIndex2 = 0;
    int up = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == 'B') {
        value += 1;
        if ((value - min) > up) {
          up = value - min;
          minIndex2 = i;
        }
        if (value > max) {
          max = value;
          maxIndex1 = i;
        }
      } else {
        value -= 1;
        if ((max - value) > down) {
          down = max - value;
          maxIndex2 = i;
        }
        if (value < min) {
          min = value;
          minIndex1 = i;
        }
      }
      //System.out.printf("%d   %d %d %d %d    %d %d %d %d\n", value, max, maxIndex1, maxIndex2, down, min, minIndex1, minIndex2, up);
    }
   if (up > down) {
     System.out.println((minIndex1 + 2) + " " + (minIndex2 + 1));
   } else if (up == down) {
     if (minIndex1 < maxIndex1) {
       System.out.println((minIndex1 + 2) + " " + (minIndex2 + 1));
     } else {
       System.out.println((maxIndex1 + 2) + " " + (maxIndex2 + 1));
     }
   } else {
    System.out.println((maxIndex1 + 2) + " " + (maxIndex2 + 1));
   }
  }
}
