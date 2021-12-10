#include <iostream>
using namespace std;

int main() {
  int n; cin >> n;
  int a;
  int tokens[n];
  for (int i = 0; i < n; i++) {
    cin >> tokens[i];
  }
  for (int i = 0; i < n; i++) {
    printf("%d\n", tokens[n-1-i]);
  }
  return 0;
}
