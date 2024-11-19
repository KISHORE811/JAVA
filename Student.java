public class Student {
    String name;
    int age;
    int subject1, subject2, subject3, subject4, subject5;
    int totalMarks;
    double averageMarks;

    public Student(String name, int age, int subject1, int subject2, int subject3, int subject4, int subject5) {
        this.name = name;
        this.age = age;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.subject4 = subject4;
        this.subject5 = subject5;
        this.totalMarks = subject1 + subject2 + subject3 + subject4 + subject5;
        this.averageMarks = totalMarks / 5.0;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Marks: ");
        System.out.println("Subject 1: " + subject1);
        System.out.println("Subject 2: " + subject2);
        System.out.println("Subject 3: " + subject3);
        System.out.println("Subject 4: " + subject4);
        System.out.println("Subject 5: " + subject5);
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Marks: " + averageMarks);
    }
}
