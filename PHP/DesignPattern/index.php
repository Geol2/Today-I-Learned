<?php 
class Korean {
    public function text() {
        return '안녕하세요.';
    }
}

class English {
    public function text() {
        return 'Hello text';
    }
}

class Factory {
static public function getInstance($type = null) {
        echo "팩토리::객체를 생성하여 반환합니다.\n";
        if($type === "ko") {
            return new Korean();
        } else if($type === "en") {   
            return new Korean();
        }
    }
}

class Hello {
    public function greeting($type) {
        $ko = Factory::getInstance($type);
        return $ko->text();
    }
}

$obj = new Hello;
echo $obj->greeting("en")."\n";
echo $obj->greeting("ko")."\n";