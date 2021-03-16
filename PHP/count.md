# count()
## count ( Countable|array $value , int $mode = COUNT_NORMAL ) : int

- 배열이나 객체의 모든 개수를 찾을 수 있음.
- $mode에서 COUNT_RECURSIVE (or 1)를 넣을 수 있는데, 사용하게 된다면 다차원 배열에서 모든 요소의 개수를 재귀적으로 탐색한다.

Example 1.
```php
$dinner = array('스위트콘과 아스프라거스',
                '레몬 치킨',
                '삶은 망태버섯');
$dishes = count($dinner);
print '총 메뉴 개수: $dishes';
```
Example 1 output : 
```
총 메뉴 개수 : 3
```

[docs](https://www.php.net/manual/en/function.count)