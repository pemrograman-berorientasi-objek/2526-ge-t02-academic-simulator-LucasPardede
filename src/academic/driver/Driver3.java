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

            // Perubahan: Split input untuk memisahkan command dari data
            String[] parts = line.split("#", 2); 
            if (parts.length < 2) {
                // Abaikan baris input yang tidak sesuai format
                continue;
            }
            String command = parts[0]; // Harusnya "enrollment-add"
            String dataString = parts[1]; // Data sesungguhnya: 12S1102#12S20050#2021/2022#odd
            String[] data = dataString.split("#"); // Split data sesuai separator '#'

            if (command.equals("enrollment-add")) { // Memastikan ini adalah command untuk enrollment
                if (data.length == 4) {
                    String courseCode = data[0];
                    String studentId = data[1];
                    String academicYear = data[2];
                    String semester = data[3];
                    enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester));
                }
            }
            // Jika ada command lain yang tidak dikenal, akan diabaikan
        }

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }

        input.close();
    }
}
