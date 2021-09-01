<?php
$nums = array(2,7,11,15);
$target = 9;

function twoSum($nums, $target) {
  for($i = 0; $i < count($nums); $i++) {
    for($j = $i + 1; $j < count($nums); $j++) {
      if( (nums[$i] + nums[$j]) === $target ) {
        $result[0] = $i;
        $result[1] = $j;
        return $result;
      }
    }
  }
}

twoSum($nums, $target);