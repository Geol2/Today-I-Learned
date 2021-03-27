# result_array() || row_array()

- $query->row_array() : 1건에 대한 배열을 반환해준다.

```php
Array
(
    [itemID] => 521
    [itemSKU] => image part number
    [itemName] => multiple images
    [itemSDesc] =>
    [addDate] => 2018-07-12 16:17:09
    [chgDate] => 0000-00-00 00:00:00
    [ctgID] => 67
    [ctgName] => Pipe Bending
    [parentID] => 46
    [itemImg] => 234-gy_hello1.png
)
```

---

- $query->result_array() : 여러건에 대한 배열을 반환해주는데 배열 안에 배열이 있는 형태.

```php
Array
(
  [0] => Array
    (
      [itemID] => 521
      [itemSKU] => image part number
      [itemName] => multiple images
      [itemSDesc] =>
      [addDate] => 2018-07-12 16:17:09
      [chgDate] => 0000-00-00 00:00:00
      [ctgID] => 67
      [ctgName] => Pipe Bending
      [parentID] => 46
      [itemImg] => 234-gy_hello1.png
    )

  [1] => Array
    (
      [itemID] => 521
      [itemSKU] => image part number
      [itemName] => multiple images
      [itemSDesc] =>
      [addDate] => 2018-07-12 16:17:09
      [chgDate] => 0000-00-00 00:00:00
      [ctgID] => 67
      [ctgName] => Pipe Bending
      [parentID] => 46
      [itemImg] => cac1f0ad0720ac05e76fd990de2d309e.png
    )

  [2] => Array
    (
      [itemID] => 521
      [itemSKU] => image part number
      [itemName] => multiple images
      [itemSDesc] =>
      [addDate] => 2018-07-12 16:17:09
      [chgDate] => 0000-00-00 00:00:00
      [ctgID] => 67
      [ctgName] => Pipe Bending
      [parentID] => 46
      [itemImg] => eee779a15e340e2a0f4d0b682e900862.png
    )
)
```
