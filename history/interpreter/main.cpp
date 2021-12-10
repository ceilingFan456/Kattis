#include <iostream>
#include <string>

using namespace std;

int main() {
  int ram[2000] = {};
  int reg[10] = {};
  int i = 0;
  while (cin >> ram[i]) {
    i++;
  }
  int pc = 0;
  int count = 0;
  while (1) {
    int ins = ram[pc];
    pc++;
    count++;
    int a = ins / 100;
    if (a == 1) {
      printf("%d", count);
      break;
    }
    else if (a == 2) {
      int d = ins/10 - 20;
      int n = ins%10;
      reg[d] = n;
    }
    else if (a == 3) {
      int d = ins/10 - 30;
      int n = ins%10;
      reg[d] = (reg[d] + n) % 1000;
    }
    else if (a == 4) {
      int d = ins/10 - 40;
      int n = ins%10;
      reg[d] = (reg[d] * n) % 1000;
    }
    else if (a == 5) {
      int d = ins/10 - 50;
      int s = ins%10;
      reg[d] = reg[s];
    }
    else if (a == 6) {
      int d = ins/10 - 60;
      int s = ins%10;
      reg[d] = (reg[d] + reg[s]) % 1000;
    }
    else if (a == 7) {
      int d = ins/10 - 70;
      int s = ins%10;
      reg[d] = (reg[d] * reg[s]) % 1000;
    }
    else if (a == 8) {
      int d = ins/10 - 80;
      int b = ins%10;
      reg[d] = ram[reg[b]];
    }
    else if (a == 9) {
      int s = ins/10 - 90;
      int b = ins%10;
      ram[reg[b]] = reg[s];
    }
    else if (a == 0) {
      int d = ins/10;
      int s = ins%10;
      if (reg[s] != 0) {
        pc = reg[d];
      }
    }
  }
  return 0;
}
