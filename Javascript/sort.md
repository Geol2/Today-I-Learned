# sort()

정렬에 관한 함수
sort() 만을 가지고 해당 배열을 정렬할 수 없으므로 예제코드를 참고하자.

```javascript
let sno = [4, 8, 3, 7, 1, 9];
sno.sort(function (a, b) {
	return b - a;
}); // 내림차순 ( 9 8 7 4 3 1 )

sno.sort(function (a, b) {
	return a - b;
}); // 오름차순 ( 1 3 4 7 8 9 )

sno.sort();
// ASCII 순서대로 하기 때문에 숫자의 크기대로 나오지 않는다.
```
