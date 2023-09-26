/*
Student ID: 2216574
Name of the Group: Verity Rift
*/
public class Main {

    public static void main(String[] args) {
        // main loop of the program
        while(true) {
            if (Rifter.getInstance().landingPage()) {
                break;
            }
            if (Rifter.getInstance().mainMenu()) {
                break;
            }
        }
    }
}
