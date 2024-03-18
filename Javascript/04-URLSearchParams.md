## URLSearchParams()

Object로 지정된 변수를 쿼리스트링 포맷으로 변경시킬 수 있다.

```javascript
let param = {
  custCorpNm: this.searchObj.custCorpNm,
  ctrtNm: this.searchObj.ctrtNm,
  pageNo: this.searchObj.pageNo,
  pageSize: this.searchObj.pageSize,
  sortKey: this.searchObj.sortKey,
  sortVal: this.searchObj.sortVal
};

## ajax called
url: `CAP-GET-/sale/deposit?` + new URLSearchParams(params).toString()
```

```javascript
console.log(new URLSearchParams(params).toString());
// pageNo=1&pageSize=10&sortKey=ctrtNm&sortVal=D
```

### 현재의 쿼리스트링을 가져오는 방법

```javascript
let params = new URLSearchParams(location.search);
let value = params.get("key"); // good!

for (const [key, value] of mySearchParams.entries()) {
  console.log(key + ', ' + value); // good!
}
```