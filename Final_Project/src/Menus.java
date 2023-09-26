public class Menus {

    // prints the landing page
    public static void landingMenu() {
        System.out.printf("%sWelcome to \"Rifter\"%s\n", "-".repeat(3), "-".repeat(3));
        System.out.print("""
                1.      Login
                2.      Register
                3.      Forgot password
                4.      Exit
                choose your option:  """);
    }

    // prints the main menu
    public static void mainMenu() {
        System.out.printf("%sMAIN MENU%s\n", "-".repeat(10), "-".repeat(10));
        System.out.print("""
                1.      Purchase history
                2.      My Profile
                3.      Earn the VRCs
                4.      Buy the items
                5.      Logout
                6.      Exit
                choose your option: """);
    }

    
    // prints the menu of user's profile
    public static void myProfileMenu() {
        System.out.print("""
                1.      Add a friend
                2.      Delete a friend
                3.      View the list of friends
                4.      View the Leaderboard
                5.      View the global Leaderboard
                6.      Go back
                choose your option:   """);
    }

    // clears the screen
    public static void clearScreen()  {
        // clear the screen
        for (int i = 0; i < 500; i++) {
            System.out.println("\b");
        }
    }
}
