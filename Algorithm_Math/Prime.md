# 에라토스테네스의 체

- 소수 : `0.13`과 같은 유리수를 뜻하는 것이 아니라 `1`과 `자신의 수`만 가지고 나누어 떨어지고 자기자신의 곱셈의 역수가 없는 수를 뜻한다.

  - 아예 모른다면 지금부터 알면 되니 학창시절의 수학을 참고하면 된다.
  - 간단한 개념이지만 로직을 구현한다고 하면 어느정도 생각이 필요한 작업이다.

- 에라토스테네스라는 고대 그리스 수학자가 발견해낸 소수를 찾는 방법에 대해 말해주고 마치 체로 치듯이 수를 걸러낸다고 해서 에라토스테네스의 체라고 불린다.
- `f(x)=x/1P(x)` 수열을 말하는데, 1P(x)는 x가 소수라면 1, 아니라면 0을 판단하는 판별함수이고 `O(n)`의 시간복잡도를 가지고 있다.

- 1 ~ 100까지 수를 적는다.
  - 1은 소수가 될 수 없으므로 지운다.
  - 2는 소수이다. 2를 제외한 2의 배수는 소수가 될 수 없으므로 지운다.
  - 3은 소수이다. 3을 제외한 3의 배수는 소수가 될 수 없으므로 지운다.
  - 5는 소수이다. 5를 제외한 5의 배수는 소수가 될 수 없으므로 지운다.
  - 6은 소수가 아니다. 2의 배수와 3의 배수가 될 수 있으므로 지워졌다.
  - 7은 소수이다. 7을 제외한 7의 배수는 소수가 될 수 없으므로 지운다.
  - 8은 소수가 아니다. 2의 배수에서 지워졌다.
  - 9는 소수가 아니다. 3의 배수에서 지워졌다.
  - 10은 소수가 아니다. 2의 배수에서 지워졌다.
  - 11은 소수이고 이 이후론 11 > √100이기 때문에 생각할 필요가 없다.

```C++
// 흔히 작성되는 방법
bool isPrime(int n) {
    if( n < 2 ) return false;
    for( int i = 2; i <= (int)sqrt(n); i++) {
        if( n % i == 0 ) return false;
    }
    return true;
}
```

- 비슷한 소수 찾기 개념
  - [정수론 : 윌런스의 공식(위키)](https://namu.wiki/w/%EC%9C%8C%EB%9F%B0%EC%8A%A4%EC%9D%98%20%EA%B3%B5%EC%8B%9D), [정수론 : 윌슨의 정리(위키)](https://namu.wiki/w/%EC%9C%8C%EC%8A%A8%EC%9D%98%20%EC%A0%95%EB%A6%AC) 등등 존재하지만, 알아듣기 어려우니 수학을 잘하면 더 보도록 하자.ㅋㅋ
