public class UseStringMethods {

    public void printContainWords(String str, String findStr) {
        String[] strArr = str.split(" ");
        
        for(String data : strArr) {
            if( data.contains(findStr) ) {
                System.out.println(data + " contains " + findStr);
            }
        }
    }

    public void countChar(String str, char c) {
        char[] chArr = str.toCharArray();
        int count = 0;

        for(char ch : chArr) {
            if(ch == c) {
                count++;
            }
        }

        System.out.println( "char \'" + c + "\'" + " count is " + count);
    }

    public void findAnyCaseString(String str, String findStr) {
        String lowerStr = str.toLowerCase();
        int index = lowerStr.indexOf(findStr);
        System.out.println(findStr + " is appeared at " + index);
    }

    public void findString(String str, String findStr) {
        int index = str.indexOf(findStr);
        System.out.println(findStr + " is appeared at " + index);
    }

    public void printWord(String str) {
        String[] strArr = str.split(" ");

        for(String data : strArr) {
            System.out.println( data );
        }
    }
    
    public static void main(String[] args) {
        String str = "The String class represents character strings.";
        
        UseStringMethods usm = new UseStringMethods();
        usm.printWord(str);
        usm.findString(str, "string");
        usm.findAnyCaseString(str, "string");
        usm.countChar(str, 's');
        usm.printContainWords(str, "ss");
    }
}