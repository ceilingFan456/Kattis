#include <iostream>
using namespace std;

int main() {
  int n; cin >> n;
  string str;
  int dp[2] = {};
  while (cin >> str) {
    dp[0] += str.at(0) == '0' ? 1 : 0;
    dp[0] += str.at(1) == '0' ? 1 : 0;
    dp[1] += str.at(2) == '0' ? 1 : 0;
    dp[1] += str.at(3) == '0' ? 1 : 0;
  }
  int less = dp[0] < dp[1] ? dp[0] : dp[1];
  int pair = less / 2;
  int tb = dp[0] - 2*pair;
  int lr = dp[1] - 2*pair;
  printf("%d %d %d", pair, tb, lr);
  return 0;
}
