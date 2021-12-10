#include <iostream>
using namespace std;

int main() {
  int n; cin >> n;
  while (n >= 10) {
    int p = 1;
    while (n > 0) {
      int c = n % 10;
      if (c != 0) p *= c;
      n = n / 10;
    }
    n = p;
  }
  cout << n << endl;
  return 0;
}
