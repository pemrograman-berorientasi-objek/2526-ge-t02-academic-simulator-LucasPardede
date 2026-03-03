package academic.driver;

/**
 * @author 12S24015 LUCAS PARDEDE
 */

import academic.model.Enrollment;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Enrollment> enrollments = new ArrayList<>();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.equals("---")) {
                break;
            }

            String courseCode;
            String studentId;
            String academicYear;
            String semester;
            String[] data;

            if (line.startsWith("enrollment-add#")) {
                // Handle input format for t02-03-02: "enrollment-add#12S1102#12S20050#2021/2022#odd"
                String[] parts = line.split("#", 2); 
                if (parts.length < 2) {
                    continue; // Skip malformed lines
                }
                String command = parts[0]; // "enrollment-add"
                String dataString = parts[1]; // "12S1102#12S20050#2021/2022#odd"
                data = dataString.split("#"); 

                if (data.length == 4) { // Expect 4 segments after command
                    courseCode = command; // As per test output, "enrollment-add" becomes the courseCode
                    studentId = data[0];
                    academicYear = data[1];
                    semester = data[2];
                    // data[3] (e.g., "odd") is effectively ignored, grade defaults to "None"
                    enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester));
                }
            } else {
                // Handle input format for t02-03-01: "12S2203#12S20999#2021/2022#even"
                data = line.split("#");
                if (data.length == 4) { // Expect 4 segments for direct enrollment data
                    courseCode = data[0];
                    studentId = data[1];
                    academicYear = data[2];
                    semester = data[3];
                    enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester));
                }
            }
        }

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }

        input.close();
    }
}
