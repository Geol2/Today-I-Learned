# Number of Alphabets
## Find the number of alphabets

알파벳의 개수를 나타내는 변수를 만들어준다.
```C++
int alpha[26] = { 0, };
```

```C++
for(int j = 0; j < a.length(); j++){
    alpha[a[j] - 'a']++;
    alpha[b[j] - 'a']--;
}
```

위의 a[], b[]는 string 이나 배열을 나타내고 있다.
- ` alpha[ a[i] - 'a' ]` 로 알파벳 26개의 인덱스 위치를 표현할 수 있음.
- a와 b를 비교할 때, alpha의 값들을 증가하거나 감소시켜 0이라면 같고 아니면 다른지 비교할 수 있는 방법이 있음.

```C++
for(int j = 0; j < 26; j++) {
    if(alpha[j] != 0) {
        result = false;
        break;
    }
}
```