# Today-I-Learned

## Category
- [PHP](#PHP)
 

# PHP
- How to use **in_array()**
```
<?php
    // array type "$os"
    $os = array("Mac", "NT", "Irix", "Linux");
    if (in_array("Irix", $os)) { // 만약, $os에 "Irix" 가 있다면...
        echo "Got Irix";
    }
    if (in_array("mac", $os)) { // 만약, $os에 "mac" 이 있다면...
        echo "Got mac";
    }
?>
```
