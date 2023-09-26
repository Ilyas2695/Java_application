import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import java.nio.file.Files;
import java.nio.file.Paths;


public class Profile {

    // deletes a friend
    public void deleteFriend(String username, String friendName) {
        // moves the file content to the list
        List<String> list = returnLines(FileNames.FRIENDS_DATA);

        // checks if the list is empty
        if (!list.isEmpty()) {

            // iterates through each line (member of the list)
            for (String line : list) {

                // checks if the username equals to the user's name
                if (line.split(",")[FriendsIndexes.USERNAME.index].equals(username)) {

                    // converts a line to the list
                    List<String> names = new ArrayList<String>(Arrays.asList(line.split(",")));

                    // removing the friend from the list
                    names.remove(friendName);

                    // removing the line from the "file", to replace it
                    list.remove(line);

                    // creates a new modified line, without the friend
                    String modifiedLine = String.join(",", names.toArray(new String[0]));

                    // adding this modified line
                    list.add(modifiedLine);
                    break;
                }
            }
            // overwrites the file
            if (overwriteFile(list, FileNames.FRIENDS_DATA)) {
                System.out.printf("You have successfully deleted %s from your list of friends\n", friendName);
            }
        } else {
            System.out.println("Your list of friends is empty");
        }
    }

    // return the whole file's content as a list
    public static List<String> returnLines(String fileName) {
        // creates the list
        List<String> lines = new ArrayList<>();

        try {
            // fills a list with the content of the file
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("error occurred while returnLines");
            System.out.println(e.getMessage());
        }
        return lines;
    }

    // overwrites the file
    public static boolean overwriteFile(List<String> lines, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            // iterates through the list and writes its content to the file
            for (String modifiedLine : lines) {
                writer.write(modifiedLine);
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("error occurred while overWriteFile");
            System.out.println(e.getMessage());
            return false;
        }
    }

    // adds a friend
    public void addFriend(String username, String friendName) {
        // moves the file content to the list
        List<String> lines = returnLines(FileNames.FRIENDS_DATA);

        // checks if the list is empty
        if (!lines.isEmpty()) {

            // switch statement to decide what to print, depending on the input
            switch(findAndAddFriendToFile(lines, username, friendName)) {
                case 1 -> {
                    // appends the new friend name to the line in the file
                    if (overwriteFile(lines, FileNames.FRIENDS_DATA)){
                        System.out.printf("You have successfully added %s to the friends\n", friendName);
                    }
                }
                case 2 -> {
                    // prints, if the user has provided name that is already in the list
                    System.out.println("You have already added this friend to your list");
                }
                case 3 -> {
                    // if the file is not empty, but the list of friends of our user is empty
                    lines.add(username + "," + friendName);
                    if (overwriteFile(lines, FileNames.FRIENDS_DATA)) {
                        System.out.printf("You have successfully added %s to the friends\n", friendName);
                    }
                }
            }
        } else {
            // if the file was empty, then it simply writes it, by adding the friend to the list
            lines.add(username + "," + friendName);
            if (overwriteFile(lines, FileNames.FRIENDS_DATA)) {
                System.out.printf("You have successfully added %s to the friends\n", friendName);
            }
        }
    }

    // returns three outputs, depending on the input
    private int findAndAddFriendToFile(List<String> lines, String username, String friendName) {
        // possible outputs
        final int ADD_THE_FRIEND = 1;
        final int FRIEND_ALREADY_IN_FILE = 2;
        final int NO_USER_IN_FILE = 3;

        // iterates through the lines of file
        for (String lineOfNames : lines) {

            // checks if the username is equal to the user's name
            if (lineOfNames.split(",")[FriendsIndexes.USERNAME.index].equals(username)) {

                // checks if the friend's name is already in the user's friend list
                if (Arrays.binarySearch(lineOfNames.split(","), friendName) >= 0) {
                    return FRIEND_ALREADY_IN_FILE;
                } else {
                    // modifies the line, and adds it to the "file"
                    String modifiedLine = lineOfNames + "," + friendName;

                    // replacing the whole line
                    lines.set(lines.indexOf(lineOfNames), modifiedLine);
                    return ADD_THE_FRIEND;
                }
            }
        }
        // if the name of our user was not found
        return NO_USER_IN_FILE;
    }

    // displays the list of friends
    public void viewFriendList() {
        // moves the file content to the list
        List<String> lines = returnLines(FileNames.FRIENDS_DATA);

        // checks if the list is not empty
        if (!lines.isEmpty()) {

            for (String line : lines) {
                // finds the name of our user
                if (line.split(",")[FriendsIndexes.USERNAME.index].equals(Rifter.getInstance().getUserName())) {
                    String[] listOfFriends = line.split(",");

                    // prints each name of friend with an index
                    for (int i = 1; i < listOfFriends.length; i++) {
                        System.out.printf("%d. %s\n", i, listOfFriends[i]);
                    }
                    // ends the method, when finished
                    return;
                } else {
                    System.out.println("Your list of friends is empty");
                }
            }
        } else {
            System.out.println("Your list of friends is empty");
        }
    }

    // displays the leaderboard of friends
    public void showFriendsLeaderboard() {
        List<String> lines = returnLines(FileNames.FRIENDS_DATA);
        List<String> names = new ArrayList<>();

        // finds the line that contains the friends of our user
        for (String line : lines) {
            if (line.split(",")[FriendsIndexes.USERNAME.index].equals(Rifter.getInstance().getUserName())) {
                names = Arrays.asList(line.split(","));
                break;
            }
        }

        // sorts the HashMap in descending order by value, using the streams
        HashMap<String, Integer> hashMap = VirtualCoin.returnHashMap(FileNames.USER_BALANCE).entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        // checks if the hash map or list of names is empty
        if (hashMap.isEmpty() || names.isEmpty()) {
            System.out.println("The leader board is empty");
            return;
        }

        int index = 1;
        // displays the leaderboard of friends
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            if (names.contains(entry.getKey())) {
                if (entry.getKey().equals(Rifter.getInstance().getUserName())) {
                    System.out.printf("%d. %-20s - %6d\n", index, entry.getKey().toUpperCase(), entry.getValue());
                } else {
                    System.out.printf("%d. %-20s - %6d\n", index, entry.getKey(), entry.getValue());
                }
                index++;
            }
        }
    }

    // displays the global leaderboard in descending order
    public void showGlobalLeaderboard() {
        // sorts the HashMap in descending order by value, using the streams
        HashMap<String, Integer> hashMap = VirtualCoin.returnHashMap(FileNames.USER_BALANCE).entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        // checks if the hash map is empty
        if (hashMap.isEmpty()) {
            System.out.println("The leader board is empty");
            return;
        }

        int index = 1;
        // displays the global leaderboard
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            if (entry.getKey().equals(Rifter.getInstance().getUserName())) {
                System.out.printf("%d. %-20s - %6d\n", index, entry.getKey().toUpperCase(), entry.getValue());
            } else {
                System.out.printf("%d. %-20s - %6d\n", index, entry.getKey(), entry.getValue());
            }
            index++;
        }

    }
}
