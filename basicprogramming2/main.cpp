#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

int main() {
  int N; cin >> N;
  int t; cin >> t;
  if (t == 1) {
    vector<int> v;
    unordered_map<int,int> m;
    int a;
    while (cin >> a) {
      v.push_back(a);
      m[a] = 1;
    }
    bool flag = false;
    for (int i = 0; i < N; i++) {
      a = v[i];
      if (m.find(7777-a) != m.end()) {flag = true; break;}
    }
    if (flag) printf("Yes\n");
    else printf("No\n");
  }
  else if (t == 2) {
    unordered_map<int,int> m;
    int a; 
    bool dup = false;
    while (cin >> a) {
      if (m.find(a) != m.end() ) {dup = true; break;}
      m[a] = 1;
    }
    if (dup) printf("Contains duplicate\n");
    else printf("Unique\n");
  }
  else if (t == 3) {
    unordered_map<int,int> m;
    int a;
    bool found = false;
    while (cin >> a) {
      if (m.find(a) != m.end()) {
        if (m[a] == N/2) {found = true; printf("%d\n", a); break;}
        else m[a] = m[a] + 1;
      }
      else m[a] = 1;
    }
    if (!found) printf("-1\n");
  }
  else if (t == 4) {
    int arr[N];
    int a;
    int i = 0;
    while(cin >> a) {
      arr[i++] = a;
    }

    int j = N-1;
    i = 0;
    int s;
    int k = N/2; // looking for kth
    int median = 0;
    int z = 0;
    while (1) {
      a = arr[i]; // current pivot
      s = i+1; // location of first bigger
      for (int p = i+1; p <= j; p++) {
        if (arr[p] < a) {
          int c = arr[p];
          arr[p] = arr[s];
          arr[s] = c;
          s++;
        }
        //for (int x= 0; x < N; x++) printf("%d ", arr[x]);
        //printf("\n");
      }
      arr[i] = arr[s-1];
      arr[s-1] = a;
      if (s-1 == k) {median = arr[s-1];break;}
      else if (s-1 < k) {
        i = s;
      }
      else {
        j = s-2;
      }
      //printf("new i = %d, j = %d, k = %d\n", i, j, k);
      //for (int x= 0; x < N; x++) printf("%d ", arr[x]);
      //printf("\n");
    }
    if (N % 2 == 1) printf("%d\n", median); 
    else {
      int a = -1;
      int count = 0;
      for (i = 0; i < N; i++) {
        if (arr[i] > a && arr[i] < median) a = arr[i];
        else if (arr[i] == median) count++;
      }
      if (count > 1) printf("%d %d\n", median, median);
      else printf("%d %d\n", a, median);
    }
  }
  else if (t == 5) {
    vector<int> v;
    int a;
    while(cin >> a) {
      if (100 <= a && a <= 999) v.push_back(a);
    }
    sort(v.begin(), v.end());
    for (auto i: v) {
      printf("%d ", i);
    }
    printf("\n");
  }
  return 0;
}
