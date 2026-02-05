import console.CLI;
import service.StudentService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();
        CLI cli = new CLI(scanner, studentService);
        cli.menu();
    }
}