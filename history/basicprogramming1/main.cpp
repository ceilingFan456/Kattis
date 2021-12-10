#include <iostream>
using namespace std;

#define MAX 200000

int main() {
  int N; cin >> N;
  int t; cin >> t;
  if (t == 1) {
    printf("7\n");
  } 
  else if (t == 2) {
    int a; cin >> a;
    int b; cin >> b;
    if (a > b) printf("Bigger\n");
    else if (a == b) printf("Equal\n");
    else printf("Smaller\n");
  }
  else if (t == 3) {
    int a; cin >> a;
    int b; cin >> b;
    int c; cin >> c;
    if ((a >= b && a <= c) || (a >= c && a <= b)) printf("%d\n", a);
    else if ((b >= a && b <= c) || (b >= c && b <= a)) printf("%d\n", b);
    else printf("%d\n", c);
  }
  else if (t == 4) {
    long long sum = 0;
    int a;
    while(cin >> a) {
      sum += a;
    }
    printf("%lld\n", sum);
  }
  else if (t == 5) {
    long long sum = 0;
    int a;
    while (cin >> a) {
      sum += a % 2 == 0 ? a : 0;
    }
    printf("%lld\n", sum);
  }
  else if (t == 6) {
    int a;
    while (cin >> a) {
      a = a % 26;
      printf("%c", 'a' + a); 
    }
    printf("\n");
  }
  else if (t == 7) {
    int arr[MAX];
    int i = 0;
    int a;
    while (cin >> a) {
      arr[i++] = a;
    }
    int visited[MAX] = {0};
    i = 0;
    visited[0] = 1;
    while (1) {
      i = arr[i];
      if (i < 0 || i >= N) {printf("Out\n"); break;}
      else if (i == N-1) {printf("Done\n"); break;}
      else if (visited[i] != 0) {printf("Cyclic\n"); break;}
      else visited[i] = 1;
    }
  }
  return 0;
}
