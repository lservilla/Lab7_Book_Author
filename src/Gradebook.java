public class Gradebook {

    public static final int DEFAULT_SIZE = 10;

    private Student[] students;
    private int numStudents;

    public Gradebook(int initialSize) {
        students = new Student[initialSize];
        numStudents = 0;
    }

    public void addStudent(Student s) {
        if(isFull()) {
            expandGradebook();
        }
        students[numStudents] = s;
        numStudents++;
    }

    public void removeStudent(Student s) {
        // find the student
        int index = -1;
        for(int i = 0; i < numStudents; i++) {
            if(students[i].equals(s)) {
                index = i;
                break;
            }
        }

        if(index > -1) {
            // found the student, move others down a slot
            for(int i = index; i < numStudents - 1; i++) {
                students[i] = students[i+1];
            }
            numStudents--;
            students[numStudents] = null;
        }
    }

    private void expandGradebook() {
        Student[] newArr = new Student[students.length*2];
        for(int i = 0; i < students.length; i++) {
            newArr[i] = students[i];
        }
        students = newArr;
    }

    private boolean isFull() {
        return numStudents >= students.length;
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < numStudents; i++) {
            Student s = students[i];
            result += s.getID() + ", " + s.getName() + ", " + s.getGrade();
            result += "\n";
        }
        return result;
    }

    public double getAverageGrade() {
        double sum = 0;
        for(int i = 0; i < numStudents; i++) {
            sum += students[i].getGrade();
        }
        return sum/numStudents;
    }

    public String passingStudents(int passingGrade) {
        String result = "\nPassing Students\n";
        for(int i = 0; i < numStudents; i++) {
            if(students[i].getGrade() >= passingGrade) {
                result += students[i] + " " + students[i].getName() + "\n";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Gradebook gb = new Gradebook(DEFAULT_SIZE);

        gb.addStudent(new Student("Bob", 90));
        gb.addStudent(new Student("Jane", 100));
        gb.addStudent(new Student("Joe", 50));

        System.out.println(gb);
        System.out.println("average grade = " + gb.getAverageGrade());

        System.out.println(gb.passingStudents(70));
    }

}