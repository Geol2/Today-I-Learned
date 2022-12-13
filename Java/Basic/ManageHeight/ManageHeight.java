class ManageHeight {
    
    public int[][] gradeHeights;

    public void printAverage(int classNo) {
        double avg = 0.0d;
        double classTotal = 0.0d;

        for(int data: gradeHeights[classNo]) {
            classTotal += data;
        }
        avg = classTotal / gradeHeights[classNo].length;
        classTotal = 0.0d;
        
        System.out.println(avg);
    }

    public void printHeight(int classNo) {
        for(int[] height : gradeHeights) {
            System.out.println("Class No.:" + classNo);
            for(int data: height) {
                System.out.println(data);
            }
        }
    }

    public void setData() {
        gradeHeights = new int[5][];
        gradeHeights[0] = new int[5];
        gradeHeights[1] = new int[4];
        gradeHeights[2] = new int[4];
        gradeHeights[3] = new int[3];
        gradeHeights[4] = new int[5];

        gradeHeights[0][0] = 170;
        gradeHeights[0][1] = 180;
        gradeHeights[0][2] = 173;
        gradeHeights[0][3] = 175;
        gradeHeights[0][4] = 177;

        gradeHeights[1][0] = 160;
        gradeHeights[1][1] = 165;
        gradeHeights[1][2] = 167;
        gradeHeights[1][3] = 186;

        gradeHeights[2][0] = 158;
        gradeHeights[2][1] = 177;
        gradeHeights[2][2] = 187;
        gradeHeights[2][3] = 176;

        gradeHeights[3][0] = 173;
        gradeHeights[3][1] = 182;
        gradeHeights[3][2] = 181;
        
        gradeHeights[4][0] = 170;
        gradeHeights[4][1] = 180;
        gradeHeights[4][2] = 165;
        gradeHeights[4][3] = 177;
        gradeHeights[4][4] = 172;
    }

    public static void main(String[] args) {
        int classNo = 1;
        int classIndex = 0;
        ManageHeight height = new ManageHeight();
        height.setData();
        while(classNo <= 5) {
            System.out.println("Class No.:" + classNo);
            height.printAverage(classIndex);

            classNo++;
            classIndex++;
        }

    }    
}
