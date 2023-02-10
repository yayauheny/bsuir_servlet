package com.bsuir.servlet.studentservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public class Student implements Comparable<Student> {
        private String name;
        private double averageScore;
        private int course;

        public Student(String surname, double averageScore, int course) {
            this.name = surname;
            this.averageScore = Math.round(averageScore * 100.0) / 100.0;
            this.course = course;
        }

        public String getName() {
            return name;
        }

        public double getAverageScore() {
            return averageScore;
        }

        public int getCourse() {
            return course;
        }

        @Override
        public int compareTo(Student o) {
            return this.name.compareTo(o.name);
        }
    }

    List<Student> students = getRandomStudentList();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sortType = request.getParameter("sortType");
        sortType = (sortType == null || sortType.isEmpty()) ? "name" : sortType;

        sortStudents(students, sortType);
//        request.setAttribute("students", students);
//        request.getRequestDispatcher("/index.jsp").forward(request, response);

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Student List</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>Name</th>");
        writer.println("<th>Average Score</th>");
        writer.println("<th>Course</th>");
        writer.println("</tr>");
        for (Student student : students) {
            writer.println("<tr>");
            writer.println("<td>" + student.getName() + "</td>");
            writer.println("<td style='text-align: center;'>" + student.getAverageScore() + "</td>");
            writer.println("<td style='text-align: center;'>" + student.getCourse() + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
        writer.println("</body>");
        writer.println("</html>");
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

        for (Student student : students) {
            System.out.println(student);
        }

        return students;
    }


    public void destroy() {
    }
}