package StudentManager; // 필요에 따라 주석

class Student {
    String name, address, phone, email;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return name + " " + address + " " + phone + " " + email;
    }
}

public class StudentManager {
    public Student[] addStudent() {
        Student[] student = new Student[3];
        student[0] = new Student("Lim");
        student[1] = new Student("Min");
        student[2] = new Student("Sook", "Seoul", "010XXXXXXXX", "ask@godOfJava.com");
        return student;
    }

    public void printStudent(Student[] student) {
        for(Student data : student) {
            System.out.println(data.name + " " + data.address + " " + data.phone + " " + data.address);
        }
    }
    
    public static void main(String[] args) {
        Student[] student = null;

        StudentManager studentManager = new StudentManager();
        student = studentManager.addStudent();
        studentManager.printStudent(student);
    }
}
