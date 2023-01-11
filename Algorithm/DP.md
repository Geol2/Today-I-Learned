# Dynamic Programming

가장 기본적인 것은 재귀와 분할정복, 완전탐색 이다.

무엇보다 배열에 대한 정의와 점화식을 만들어서 생각하는 과정이 중요한 것 같다.

```c++
#include<iostream>
using namespace std;

int main() {
	int S[301] = {0,};
	int D[301][3] = { 0, };

	int n;
	int num;
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> num;
		S[i] = num;
	}

	D[1][1] = S[1];
	D[1][2] = 0;
	D[2][1] = S[2];
	D[2][2] = S[1] + S[2];

	for (int i = 3; i <= n; i++) {
		D[i][1] = max(D[i - 2][1], D[i - 2][2]) + S[i];
		D[i][2] = D[i - 1][1] + S[i];
	}

	cout << max(D[n][0], D[n][1]) << endl;
	return 0;
}
```

## `D[i][j]` 정의

`D[i][j`] 은 현재까지 j개의 계단을 연속해서 밟고 i번째 계단까지 올라섰을 때 점수 합의 최대값, 단 i번째 계단은 반드시 밟아야 함

## 점화식
```
D[i][1] = max(D[i - 2][1], D[i - 2][2]) + S[i];
D[i][2] = D[i - 1][1] + S[i];
```