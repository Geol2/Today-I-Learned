# Array

## every

모두가 `true`라면 `true`를 반환한다.

```javascript
let data = [
  { name: "jack", value: 123},
  { name: "jam", value: 456},
  { name: "test", value: 789},
  { name: "kim", value: 345},
];
let result = data.every(x => {
  return x.value > 100
});
let result2 = data.every(x => {
  return x.value > 140
});

console.log(result);  // true
console.log(result2); // false
```

## some

하나라도 `true`라면 `true`를 반환한다.

```javascript
let data = [
  { name: "jack", value: 123},
  { name: "jam", value: 456},
  { name: "test", value: 789},
  { name: "kim", value: 345},
];
let result = data.some(x => {
  return x.value > 130
});
let result2 = data.some(x => {
  return x.value > 130
});

console.log(result);  // true
console.log(result2); // true
```