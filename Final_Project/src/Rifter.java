import java.util.List;

public class Rifter {
    private static Rifter instance = null;

    //fields
    private String password;
    private String userName;
    private String email;

    private Rifter() {

    }
    // singleton, to use everywhere the same instance of the class
    public static Rifter getInstance() {
        if (instance == null) {
            instance = new Rifter();
        }
        return instance;
    }

    //getters
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    /*
    this getter is modified, because when the user logins into the account, he does not provide the email
    that is why the program finds email in the file
    */
    public String getEmail() {

        List<String> userData = Profile.returnLines(FileNames.USER_DATA);

        for (String line : userData) {
            if (line.split(",")[UserDataIndexes.USERNAME.index].equals(getUserName())) {
                setEmail(line.split(",")[UserDataIndexes.EMAIL.index]);
                break;
            }
        }
        return email;
    }

    //setters
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // needed to "stop" the program before clearing the screen
    public static void pressEnter() {
        System.out.print("Press Enter...");
        ImportClasses.scanner.nextLine();
    }

    // default message if user has chosen wrong option
    public static void defaultSwitch() {
        System.out.println("choose only from the options");
        pressEnter();
        Menus.clearScreen();
    }

    // main menu method
    public boolean mainMenu() {
        // creating the classes
        VirtualCoin virtualCoin = new VirtualCoin();
        Profile friends = new Profile();
        while(true) {
            // main menu
            Menus.mainMenu();
            // asks for the option
            String choice = ImportClasses.scanner.next();
            Menus.clearScreen();
            switch(choice) {
                case "1" -> {
                    ImportClasses.scanner.nextLine();
                    // prints the purchase history
                    System.out.printf("%sPURCHASE HISTORY%s\n", "-".repeat(15), "-".repeat(15));
                    virtualCoin.viewPurchaseHistory(Rifter.getInstance());
                    pressEnter();
                    Menus.clearScreen();
                }
                // moves to myProfile method, where the user can choose further options
                case "2" -> myProfile(friends);

                case "3" -> {
                    // moves to the game, where the user can earn VRC (virtual reality coin)
                    System.out.printf("%sTHE RIFTER GAME%s\n", "-".repeat(20), "-".repeat(20));
                    virtualCoin.rifterGame(Rifter.getInstance());
                    ImportClasses.scanner.nextLine();
                    pressEnter();
                    Menus.clearScreen();
                }

                case "4" -> {
                    // moves to the page, where the user can buy items
                    ImportClasses.scanner.nextLine();
                    System.out.printf("%sBUY THE ITEMS%s\n", "-".repeat(15), "-".repeat(15));
                    virtualCoin.buyItems(Rifter.getInstance());
                    pressEnter();
                    Menus.clearScreen();
                }

                case "5" -> {
                    ImportClasses.scanner.nextLine();
                    // moves to the landing page, where the user can log in, register, or reset password
                    System.out.println("LOGOUT");
                    pressEnter();
                    Menus.clearScreen();
                    return false;
                }

                case "6" -> {
                    // stops the program
                    System.out.println("Good bye!");
                    return true;
                }

                default -> defaultSwitch();
            }
        }
    }

    // landing page method, user can log in, register or reset password
    public boolean landingPage() {
        UserData userData = new UserData();
        while(true) {
            // landing page menu
            Menus.landingMenu();

            // switch statement to choose the options
            switch (ImportClasses.scanner.nextLine()) {
                // login option
                case "1" -> {
                    if (userData.loginMain(Rifter.getInstance())) {
                        Rifter.pressEnter();
                        Menus.clearScreen();
                        return false;
                    }
                    Rifter.pressEnter();
                    Menus.clearScreen();
                }
                // Registration option
                case "2" -> {
                    userData.registrationMain();
                    Rifter.pressEnter();
                    Menus.clearScreen();
                }
                // Reset password option
                case "3" -> {
                    userData.resetPasswordMain();
                    Rifter.pressEnter();
                    Menus.clearScreen();
                }
                case "4" -> {
                    // stops the program
                    System.out.println("Good bye!");
                    return true;
                }
                default -> defaultSwitch();
            }
        }
    }

    // user's profile
    private void myProfile(Profile friends) {
        ImportClasses.scanner.nextLine();
        while(true) {
            Menus.clearScreen();
            System.out.printf("%sMY PROFILE%s\n", "-".repeat(15), "-".repeat(15));
            // personal data of the user
            System.out.printf("""
                    Personal data:
                    Username: %30s
                    Password: %30s
                    Email: %33s
                    """, userName, password, getEmail());
            // current balance
            System.out.printf("Current balance: %23d\n\n", VirtualCoin.getVRC(userName));
            // user's profile manu
            Menus.myProfileMenu();

            switch (ImportClasses.scanner.nextLine()) {
                // add friend option, user can add an existing user to the friend list
                case "1" -> {
                    System.out.println("ADD FRIEND");

                    // prompts the user to write the name of a friend
                    System.out.print("Please, write a name of your friend: ");
                    String friendName = ImportClasses.scanner.next();

                    // checks if friend has an account
                    if (!Validation.isFileContains(friendName, FileNames.USER_DATA)) {
                        System.out.println("No player was found to add");
                    } else {
                        // checks if the user wants to add himself to the list of friends
                        if (friendName.equals(userName)) {
                            System.out.println("Sorry, you cannot add yourself to your friends list");
                        } else {
                            // adds
                            friends.addFriend(userName, friendName);
                        }
                    }
                    ImportClasses.scanner.nextLine();
                    pressEnter();
                }
                // deletes the friend
                case "2" -> {
                    System.out.println("DELETE FRIEND");

                    // prompts the user
                    System.out.print("Please, write a name of your friend: ");
                    String friendName = ImportClasses.scanner.next();

                    // checks if the friend was found
                    if (!Validation.isFileContains(friendName, FileNames.USER_DATA)) {
                        System.out.println("No player was found to delete");
                    } else {
                        // checks if the user has written himself
                        if (friendName.equals(userName)) {
                            System.out.println("Sorry, you cannot delete yourself");
                        } else {
                            // deletes
                            friends.deleteFriend(userName, friendName);
                        }
                    }
                    ImportClasses.scanner.nextLine();
                    pressEnter();
                }
                // prints the list of friends
                case "3" -> {
                    System.out.println("FRIEND LIST");
                    friends.viewFriendList();
                    pressEnter();
                }
                // prints the leaderboard of friends
                case "4" -> {
                    System.out.println("LEADERBOARD OF FRIENDS");
                    friends.showFriendsLeaderboard();
                    pressEnter();
                }
                // prints the global leaderboard
                case "5" -> {
                    System.out.println("GLOBAL LEADERBOARD");
                    friends.showGlobalLeaderboard();
                    pressEnter();
                }
                // moves back to the main menu
                case "6" -> {
                    Menus.clearScreen();
                    return;
                }
                default -> defaultSwitch();
            }
        }
    }
}
