# next_permutation(BidirectionalIterator first, BidirectionalIterator end)

- 필요한 헤더파일 : `algorithm`

## permutation (순열)

- `{1, 2, 3}` 원소들의 모든 순열을 찾는다면,

  - 123
  - 132
  - 213
  - 231
  - 312
  - 321

- 다음 순열을 구하기 위해 `next_permutation` 함수를 이용할 수 있다.
- 만약, 입력값이 `156` 이라면 `next_permutation`은 `165`의 결과를 얻을 수 있다.

```cpp
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
  string x; cin >> x; // 156
  int i = next_permutation(x.begin(), x.end());
  if (i) cout << x <<endl; // 165
  else cout << 0 <<endl;
  return 0;
}
```
