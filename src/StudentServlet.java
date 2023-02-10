import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public class Student implements Comparable<Student> {
        private String surname;
        private double averageScore;
        private int course;

        public Student(String surname, double averageScore, int course) {
            this.surname = surname;
            this.averageScore = averageScore;
            this.course = course;
        }

        public String getSurname() {
            return surname;
        }

        public double getAverageScore() {
            return averageScore;
        }

        public int getCourse() {
            return course;
        }

        @Override
        public int compareTo(Student o) {
            return this.surname.compareTo(o.surname);
        }
    }

    private List<Student> students;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        students = getRandomStudentList();
        execute(students, "surname", response);
    }

    private void execute(List<Student> students, String sortType, HttpServletResponse response)
            throws IOException {
        sortStudents(students, sortType);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>Surname</th>");
        out.println("<th>Average Score</th>");
        out.println("<th>Course</th>");
        out.println("</tr>");

        for (Student student : students) {
            out.println("<tr>");
            out.println("<td>" + student.getSurname() + "</td>");
            out.println("<td>" + student.getAverageScore() + "</td>");
            out.println("<td>" + student.getCourse() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    private void sortStudents(List<Student> students, String sortType) {
        switch (sortType) {
            case "surname":
                Collections.sort(students);
                break;
            case "averageScore":
                students.sort((s1, s2) -> Double.compare(s1.getAverageScore(), s2.getAverageScore()));
                break;
            case "course":
                students.sort((s1, s2) -> Integer.compare(s1.getCourse(), s2.getCourse()));
                break;
            default:
                Collections.sort(students);
                break;
        }
    }

    private List<Student> getRandomStudentList() {
        List<Student> students = new ArrayList<>();
        String[] names = {"John", "Jane", "James", "Jessica", "Jacob"};
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            String surname = names[random.nextInt(names.length)];
            double averageScore = 4 + 6 * random.nextDouble();
            int course = 1 + random.nextInt(4);
            students.add(new Student(surname, averageScore, course));
        }

        return students;
    }
}


