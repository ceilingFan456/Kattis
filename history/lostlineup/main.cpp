#include <iostream>
using namespace std;

int main() {
  int n; cin >> n;
  int q[n]; 
  q[0] = 1;
  for (int i = 2; i <= n; i++) {
    int a; cin >> a;
    q[a+1] = i;
  }
  for (int i = 0; i < n; i++) printf("%d ", q[i]);
  printf("\n");
  return 0;
}
