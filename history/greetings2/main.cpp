#include <iostream>
#include <cstring>
using namespace std;

int main() {
  char p[3000];
  cin >> p;
  int n = (int)strlen(p);
  for (int i = 0; i < n-1; i++) cout << p[i];
  for (int i = 1; i < n; i++) cout << p[i];
  return 0;
}
