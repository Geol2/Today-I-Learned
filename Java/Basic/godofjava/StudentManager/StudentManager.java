
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

    public boolean equals(Object obj) {

        System.out.println(obj);
        System.out.println(this);

        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        Student other = (Student) obj;
        if(address == null) {
            if(other.address != null) return false;
        } else if( !address.equals(other.address) ) return false;
        
        if(name == null) {
            if(other.name != null) return false;
        } else if( !name.equals(other.name) ) return false;

        if(phone == null) {
            if( other.phone != null ) return false;
        } else if(!phone.equals(other.phone)) return false;

        return true;
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

    public void checkEquals() {
        Student a = new Student("Min1", "Seoul", "010XXXXXXXX", "ask@godofjava.com");
        Student b = new Student("Min", "Seoul", "010XXXXXXXX", "ask@godofjava.com");

        if(a.equals(b)) {
            System.out.println("Equals");
        } else {
            System.out.println("Not Equals");
        }
    }
    
    public static void main(String[] args) {
        Student[] student = null;

        StudentManager studentManager = new StudentManager();
        // student = studentManager.addStudent();
        // studentManager.printStudent(student);
        studentManager.checkEquals();
    }
}
