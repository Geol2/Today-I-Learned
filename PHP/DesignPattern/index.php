<?php 
class Korean {
    public function text() {
        return '안녕하세요.';
    }
}

class Factory {
    static public function getInstance() {
        echo "팩토리::객체를 생성하여 반환합니다.\n";
        return new Korean();
    }
}