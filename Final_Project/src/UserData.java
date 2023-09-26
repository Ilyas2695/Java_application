import java.io.*;
import java.util.Scanner;

// user data class is needed to deal with a personal data of the user
public class UserData{

    // main method for the login function
    public boolean loginMain(Rifter rifter) {
        System.out.println("LOGIN");
        // prompts the user until the right form of data is written

        System.out.print("Please, write your user name: ");
        rifter.setUserName(ImportClasses.scanner.next());

        System.out.print("Please, write your password: ");
        rifter.setPassword(ImportClasses.scanner.next());
        // to catch newline character
        ImportClasses.scanner.nextLine();

        // checks if the user has not written wrong form of data
        if (Validation.containsOnlyAlphanumeric(rifter.getUserName()) &&
                Validation.containsOnlyAlphanumeric(rifter.getPassword())) {

            return login(rifter.getUserName(), rifter.getPassword());

        } else {
            System.out.println("One of the sections does not match the form, Try again");
            return false;
        }
    }

    // main method for the registration function
    public void registrationMain() {
        System.out.println("REGISTRATION");
        // brief instruction
        System.out.println("""
                1) Your username and password must contain numbers and letters.
                2) Password has to be at least 6 characters long
                3) Write 'quit' whenever you want to quit
                """);
        // prompts the user until the right form of data is written
        while (true) {
            System.out.print("Please, create your user name: ");
            String username = ImportClasses.scanner.next();
            if (username.equals("quit")) return;

            System.out.print("Please, write your email: ");
            String email = ImportClasses.scanner.next();
            if (email.equals("quit")) return;

            System.out.print("Please, create your password: ");
            String password = ImportClasses.scanner.next();
            if (password.equals("quit")) return;

            System.out.print("Please, repeat your password: ");
            // to catch newline character
            ImportClasses.scanner.nextLine();

            // checks if the user has written wrong form of data
            if (password.equals(ImportClasses.scanner.next()) &&
                    Validation.containsOnlyAlphanumeric(username) &&
                    Validation.isEmail(email) &&
                    Validation.containsOnlyAlphanumeric(password)) {

                // checks if the given username and email are already occupied
                if (!(Validation.isFileContains(username, FileNames.USER_DATA) ||
                        Validation.isFileContains(email, FileNames.USER_DATA))) {

                    // checks if the password does not have a required length
                    if (password.length() < Validation.PASSWORD_LENGTH) {
                        System.out.println("The length of the password must contain at least 6 characters, Try again");
                    } else {
                        register(username, password, email);

                        // to catch newline char
                        ImportClasses.scanner.nextLine();
                        return;
                    }
                } else {
                    System.out.println("Email or username are already occupied, please provide another, Try again");
                }
            } else {
                System.out.println("One of the sections does not match the form, or passwords are not the same, Try again");
            }
        }
    }

    // main method for the reset password function
    public void resetPasswordMain() {
        System.out.println("RESET THE PASSWORD");
        System.out.println("Write 'quit' whenever you want to quit");
        System.out.print("Please, write your email: ");
        // prompts the user, until the right form of the email is provided
        String email;
        while (true) {
            email = ImportClasses.scanner.nextLine();
            if (email.equals("quit")) return;
            // checks if the email has right form
            if (!Validation.isEmail(email)) {
                System.out.print("the email does not match the email form, please, Try again: ");
            } else {
                break;
            }
        }
        // prompts the user, until the right form of the password is provided
        System.out.print("Please, write your new password: ");
        String password;
        while (true) {
            password = ImportClasses.scanner.nextLine();
            if (password.equals("quit")) return;
            // checks if the password match the requirements
            if (Validation.containsOnlyAlphanumeric(password) && password.length() >= Validation.PASSWORD_LENGTH) {
                resetPassword(email, password);
                return;
            }
            System.out.print("Password has to contain only numbers and letters and be at least 6 characters long, Try again: ");
        }
    }

    // appends the user data to the file, and prints the message if all is good
    private void register(String username, String password, String email) {
            try {
                FileWriter writer = new FileWriter(FileNames.USER_DATA, true);
                writer.write(email + "," + username + "," + password + "\n");
                writer.close();
                System.out.println("Registration was successful, now you can login");
            } catch (IOException e) {
                System.out.println("the error has occurred while register");
                System.out.println(e.getMessage());
            }
    }

    // checks if the given username and password are equal to the ones that are in the file
    private boolean login(String username, String password) {
        try {
            Scanner scanner = new Scanner(new File(FileNames.USER_DATA));
            // iterates through the file and splits each line to find the user data
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[UserDataIndexes.USERNAME.index].equals(username) && parts[UserDataIndexes.PASSWORD.index].equals(password)) {
                    System.out.println("Login was successful");
                    return true;
                }
            }
            System.out.println("User name or the password does not match");
            return false;
        } catch (IOException e) {
            System.out.println("the error has occurred while login");
            System.out.println(e.getMessage());
        }
        return false;
    }

    // rewrites the line that contains a password
    private void resetPassword(String email, String newPassword) {
        boolean userFound = false;

        try {
            Scanner scanner = new Scanner(new File(FileNames.USER_DATA));
            StringBuilder builder = new StringBuilder();

            // iterates through the file, splitting each line
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                // checks if the line contains the email
                if (parts[UserDataIndexes.EMAIL.index].equals(email)) {

                    // appends the line of data
                    builder.append(email).append(",").append(parts[UserDataIndexes.USERNAME.index]).append(",").append(newPassword).append("\n");
                    userFound = true;
                } else {
                    builder.append(line).append("\n");
                }
            }
            scanner.close();
            // if user was found, program resets the password
            if (userFound) {
                FileWriter writer = new FileWriter(FileNames.USER_DATA);
                writer.write(builder.toString());
                writer.close();
                Rifter.getInstance().setPassword(newPassword);
                System.out.println("Password has been changed");
            } else {
                System.out.println("Email was not found, password cannot be changed");
            }
        } catch (IOException e) {
            System.out.println("the error has occurred while resetPassword");
            System.out.println(e.getMessage());
        }
    }
}
