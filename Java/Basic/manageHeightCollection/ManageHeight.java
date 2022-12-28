
import java.util.ArrayList;

public class ManageHeight {
    private ArrayList<ArrayList<Integer>> gradeHeight = new ArrayList<ArrayList<Integer>>();;
    
    public void setData() {
        ArrayList<Integer> childArr1 = new ArrayList<Integer>();
        childArr1.add(170);
        childArr1.add(180);
        childArr1.add(173);
        childArr1.add(175);
        childArr1.add(177);
        
        ArrayList<Integer> childArr2 = new ArrayList<Integer>();
        childArr2.add(160);
        childArr2.add(165);
        childArr2.add(167);
        childArr2.add(186);
        
        ArrayList<Integer> childArr3 = new ArrayList<Integer>();
        childArr3.add(158);
        childArr3.add(177);
        childArr3.add(187);
        childArr3.add(176);
        
        ArrayList<Integer> childArr4 = new ArrayList<Integer>();
        childArr4.add(173);
        childArr4.add(182);
        childArr4.add(171);
        
        ArrayList<Integer> childArr5 = new ArrayList<Integer>();
        childArr5.add(170);
        childArr5.add(180);
        childArr5.add(165);
        childArr5.add(177);
        childArr5.add(172);
        
        gradeHeight.add(childArr1 );
        gradeHeight.add(childArr2 );
        gradeHeight.add(childArr3 );
        gradeHeight.add(childArr4 );
        gradeHeight.add(childArr5 );
    }

    public void printHeight(int classNo) {
        int classNoResult = classNo - 1;
        ArrayList<Integer> classHeight = gradeHeight.get(classNoResult);

        System.out.println("Class No. : " + classNo );
        for(int data : classHeight) {
            System.out.println(data);
        }
    }

    public void printAverage(int classNo) {
        double sum = 0.0d;
        int cnt = 0;
        for(int data : gradeHeight.get(classNo - 1)) {
            sum += data;
            cnt++;
        }
        System.out.println(sum / cnt );
    }

    public static void main(String[] args) {
        ManageHeight mh = new ManageHeight();
        mh.setData();
        for(int classNo = 1; classNo < 6; classNo++) {
            // mh.printHeight(classNo);
            mh.printAverage(classNo);
        }
    }
}