import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;

// validates the data
public class Validation {

    // minimum length of the password
    public static int PASSWORD_LENGTH = 6;

    // checks if the word contains both numbers and letters
    public static boolean containsOnlyAlphanumeric(String word) {
        String onlyNumbers = ".*[0-9].*";
        String onlyLetters = ".*[a-zA-Z].*";
        return word.matches(onlyLetters) && word.matches(onlyNumbers);
    }

    // checks if the email has the common email form
    public static boolean isEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    // checks if the word is in the file
    public static boolean isFileContains(String word, String fileName) {
        try {
            // iterates through the file
            Scanner scanner = new Scanner(new File(fileName));
            while(scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");

                // returns true if the word in the line, otherwise, returns false
                if (Arrays.binarySearch(data, word) >= 0) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            System.out.println("the error has occurred");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
