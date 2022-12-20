public class MyPage {
    public static InputBox input;

    public static void main(String[] args) {
        MyPage mypage = new MyPage();
        mypage.setUI();
        mypage.pressKey(InputBox.KEY_DOWN);
        mypage.pressKey(InputBox.KEY_UP);
    }

    public void setUI() {
        input = new InputBox();
        KeyEventListener listener = new KeyEventListener() {
            @Override
            public void onKeyDown() {
                System.out.println("Key Down");
            }
            @Override
            public void onKeyUp() {
                System.out.println("Key Up");
            }
        };

        input.setKeyListener(listener);
    }

    public void pressKey(int keyType) {
        input.listenerCalled(keyType);
    }
}

interface KeyEventListener {
    public void onKeyDown();
    public void onKeyUp();
}

class InputBox {
    
    public static final int KEY_DOWN = 2;
    public static final int KEY_UP = 4;

    KeyEventListener listener;
    
    public void setKeyListener(KeyEventListener listener) {
        this.listener = listener;
    }

    public void listenerCalled(int eventType) {
        if(eventType == KEY_DOWN) {
            listener.onKeyDown();
        } else if(eventType == KEY_UP) {
            listener.onKeyUp();
        }
    }

}