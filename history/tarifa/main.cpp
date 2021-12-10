#include <iostream>

using namespace std;

int main() {
  int x; cin >> x;
  int n; cin >> n;
  int s = x;
  int p = 0;
  while (cin >> p) {
    s += x-p;
  }
  cout << s;
  return 0;
}
