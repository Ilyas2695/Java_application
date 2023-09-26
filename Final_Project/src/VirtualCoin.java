import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import java.time.LocalDate;


public class VirtualCoin {

    // returns the hash map with items to buy
    private HashMap<String, Integer> getItems() {
        HashMap<String, Integer> items = new HashMap<>();
        items.put("Toyota Camry 70", 100_000);
        items.put("Bike GIANT", 5_000);
        items.put("MacBook Air", 4_500);
        items.put("Logitech Keyboard", 1_000);
        items.put("Rifter Mag", 500);
        // sorting the hash map by its value in descending order, using streams
        return items.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    // returns the current VRC balance
    public static int getVRC(String username) {

        File file = new File(FileNames.USER_BALANCE);

        // checks if the file is empty
        if (file.length() != 0) {

            try {
                Scanner scanner = new Scanner(file);

                // reads the file
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");

                    // finds the line with a name of user
                    if (parts[BalanceIndexes.USERNAME.index].equals(username)){

                        // returns the current VRC balance
                        return Integer.parseInt(parts[BalanceIndexes.VRCBALANCE.index]);
                    }
                }
                return 0;
            } catch (FileNotFoundException e) {
                System.out.println("error occurred while getVRC");
                System.out.println(e.getMessage());
                return 0;
            }
        }
        return 0;
    }

    // finds the user's type
    private HashMap<String, String> playerTypeIdentify() {

        //Key is a question, value is an answer

        // questions for socializer players
        HashMap<String, String> socializer = new HashMap<>();
        socializer.put("Which UK-based organization works to eliminate global poverty and promote social justice through fundraising, campaigning, and advocacy?\n" +
                "A) Save the Children\n" +
                "B) Oxfam\n" +
                "C) The Red Cross\n" +
                "D) UNICEF", "B");
        socializer.put("Which UK law, passed in 1967, decriminalized homosexual acts between consenting adults in private?\n" +
                "A) The Equal Pay Act\n" +
                "B) The Race Relations Act\n" +
                "C) The Abortion Act\n" +
                "D) The Sexual Offences Act", "D");
        socializer.put("Which UK activist, who passed away in 2018, founded the Stephen Lawrence Charitable Trust to help young people from ethnic minority backgrounds fulfill their potential?\n" +
                "A) Stephen Hawking\n" +
                "B) Stephen Fry\n" +
                "C) Stephen Sondheim\n" +
                "D) Stephen Lawrence", "D");
        socializer.put("Which UK-based movement, founded in 2010, campaigns against the harmful effects of social media and advocates for greater transparency and accountability from tech companies?\n" +
                "A) Extinction Rebellion\n" +
                "B) Black Lives Matter UK\n" +
                "C) Me Too Movement UK\n" +
                "D) The Center for Humane Technology UK", "D");
        socializer.put("Which UK organization, founded in 2004, works to support refugees and asylum seekers by providing legal advice, advocacy, and practical support?\n" +
                "A) Refugee Action\n" +
                "B) The Refugee Council\n" +
                "C) Asylum Aid\n" +
                "D) The Red Cross", "A");

        // questions for explorer players
        HashMap<String, String> explorer = new HashMap<>();
        explorer.put("Who discovered penicillin, a life-saving antibiotic, in a laboratory in London in 1928?\n" +
                "A) Alexander Fleming\n" +
                "B) Charles Darwin\n" +
                "C) Louis Pasteur\n" +
                "D) Marie Curie", "A");
        explorer.put("Who is credited with discovering the structure of DNA while working at the University of Cambridge?\n" +
                "A) Maurice Wilkins\n" +
                "B) Rosalind Franklin\n" +
                "C) Linus Pauling\n" +
                "D) James Watson and Francis Crick", "D");
        explorer.put("Which English explorer is credited with circumnavigating the globe in 1577-1580, and is known for his contributions to navigation and mapping?\n" +
                "A) Sir Francis Drake\n" +
                "B) Captain James Cook\n" +
                "C) Sir Walter Raleigh\n" +
                "D) Sir John Franklin", "A");
        explorer.put("What famous paleontological discovery was made in southern England in 1824, and revolutionized our understanding of prehistoric life?\n" +
                "A) The discovery of the first hominid fossils\n" +
                "B) The discovery of the first dinosaur fossils\n" +
                "C) The discovery of the first fossilized insects\n" +
                "D) The discovery of the first fossilized fish", "B");
        explorer.put("Who discovered the law of gravity, which revolutionized our understanding of the natural world, while sitting under an apple tree at his family home in Lincolnshire?\n" +
                "A) Galileo Galilei\n" +
                "B) Albert Einstein\n" +
                "C) Isaac Newton\n" +
                "D) Johannes Kepler", "C");

        // questions for achiever players
        HashMap<String, String> achiever = new HashMap<>();
        achiever.put("Who is the British scientist and theoretical physicist who developed the theory of general relativity, making major contributions to our understanding of the universe?\n" +
                "A) Isaac Newton\n" +
                "B) Stephen Hawking\n" +
                "C) Richard Feynman\n" +
                "D) Brian Cox", "B");
        achiever.put("Who was the British Prime Minister who played a crucial role in the defeat of Nazi Germany during World War II, and also helped to found the United Nations?\n" +
                "A) Winston Churchill\n" +
                "B) Clement Attlee\n" +
                "C) Margaret Thatcher\n" +
                "D) Tony Blair", "A");
        achiever.put("Which British computer scientist is considered to be the father of computer science, having developed the concept of a \"universal machine\" that could perform any computation?\n" +
                "A) Charles Babbage\n" +
                "B) Alan Turing\n" +
                "C) Tim Berners-Lee\n" +
                "D) Ada Lovelace", "B");
        achiever.put("Which British athlete won four gold medals at the 2012 London Olympics, becoming the first British athlete to win four golds at a single Olympics since 1908?\n" +
                "A) Mo Farah\n" +
                "B) Bradley Wiggins\n" +
                "C) Jessica Ennis-Hill\n" +
                "D) Chris Hoy", "C");
        achiever.put("Which British musician and songwriter is considered one of the greatest cultural icons of the 20th century, and is known for his innovative and influential music and persona?\n" +
                "A) David Bowie\n" +
                "B) Freddie Mercury\n" +
                "C) John Lennon\n" +
                "D) Elvis Costello", "A");

        // questions for killer players
        HashMap<String, String> killer = new HashMap<>();
        killer.put("What is the oldest regiment in the British Army, having been formed in 1660?\n" +
                "A) The Household Cavalry\n" +
                "B) The Coldstream Guards\n" +
                "C) The Grenadier Guards\n" +
                "D) The Royal Scots Dragoon Guards", "B");
        killer.put("Which naval battle, fought between the British Royal Navy and the combined French and Spanish fleets in 1805, is considered one of the greatest British victories in history?\n" +
                "A) The Battle of Trafalgar\n" +
                "B) The Battle of Jutland\n" +
                "C) The Battle of the Atlantic\n" +
                "D) The Battle of Waterloo", "A");
        killer.put("Which British military officer is known for his victories against Napoleon Bonaparte in the Peninsular War, and later served as Prime Minister of the UK twice?\n" +
                "A) Arthur Wellesley, Duke of Wellington\n" +
                "B) Horatio Nelson, Viscount Nelson\n" +
                "C) Douglas Haig, 1st Earl Haig\n" +
                "D) Bernard Montgomery, 1st Viscount Montgomery of Alamein", "A");
        killer.put("Which British military operation, conducted in 1982, successfully recaptured the Falkland Islands from Argentine occupation?\n" +
                "A) Operation Granby\n" +
                "B) Operation Herrick\n" +
                "C) Operation Telic\n" +
                "D) Operation Corporate", "D");
        killer.put("Which British military unit, founded in 1940, is known for their secretive and highly skilled operations, and is also known as the \"SAS\"?\n" +
                "A) The Royal Marines\n" +
                "B) The Parachute Regiment\n" +
                "C) The Army Air Corps\n" +
                "D) The Special Air Service", "D");

        // hash map with player types
        HashMap<String, HashMap<String, String>> playerType = new HashMap<>();
        playerType.put("1", socializer);
        playerType.put("2", explorer);
        playerType.put("3", achiever);
        playerType.put("4", killer);

        // player chooses his type here
        String choice;
        System.out.println("Please choose your player type first, this will affect the quiz");
        while(true) {
            System.out.println("""
                1.   Socializer
                2.   Explorer
                3.   Achiever
                4.   Killer
                """);
            System.out.print("Please, choose: ");
            choice = ImportClasses.scanner.next();
            // data validation
            if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                return playerType.get(choice);
            }
            System.out.println("Please, choose only from the options");
        }
    }

    // the game
    public void rifterGame(Rifter rifter) {
        // brief description and rules
        System.out.println("""
                Rules and Description of the game:
                1.   Every time you answer right, the VRC is doubled
                2.   If your answer is wrong, you loose all the VRC
                3.   There are only 5 questions
                4.   Only one answer is right
                5.   You have to choose your player type to start the game
                6.   Your initial balance is 100 VRC
                7.   Maximum you can win: 3200 VRC
                8.   All questions are about the UK
                """);
        // initial variables
        int questionNumber = 1;
        int VRC = 100;
        final int MAXIMUM_VRC = 3200;
        String yesNot;

        // iterates through the hash map that contains the questions
        for (Map.Entry<String, String> entry : playerTypeIdentify().entrySet()) {
            // to catch a newline character
            ImportClasses.scanner.nextLine();

            Rifter.pressEnter();
            Menus.clearScreen();

            String question = entry.getKey();
            String answer = entry.getValue();

            System.out.printf("question %d\n%s\n", questionNumber, question);
            System.out.print("\nWrite your answer: ");
            // checks if the question was right
            if (ImportClasses.scanner.next().equals(answer)) {
                VRC *= 2;
                /* 
                checks if the user answered all questions,
                3200 because this value is reachable when all questions are answered
                */
                if (VRC == MAXIMUM_VRC) break;
                System.out.printf("Right answer, your VRC doubled: %d. Do you want to continue? (Y/N) ", VRC);

                // validates the user input
                while (true) {
                    yesNot = ImportClasses.scanner.next();
                    if (yesNot.equals("Y")) {
                        System.out.printf("We continue the game, your current balance: %d\n", VRC);
                        questionNumber++;
                        break;
                    } else if (yesNot.equals("N")) {
                        System.out.printf("Good, thank you for the game, you have won: %d VRCs\n", VRC);
                        // adds the VRC
                        addVRC(VRC, rifter);
                        return;
                    } else {
                        System.out.print("Please, choose only from the options, Try again:");
                    }
                }
            } else {
                System.out.println("Answer is wrong, game over, you lost all your current VRC.");
                return;
            }
        }
        // adds the VRC
        System.out.println("Congratulations! You have answered all questions and won 3200 VRC");
        addVRC(VRC, rifter);
    }

    // purchasing part
    public void buyItems(Rifter rifter) {
        System.out.println("write 'quit' whenever you want to quit");

        String itemName;

        HashMap<String, Integer> items = getItems();

        // converts the keySet into a list, so the user will be able to choose an item by its index
        List<String> names = new ArrayList<>(items.keySet());

        System.out.println("Welcome to our market\n");

        // prints the items to buy
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%d. %-20s - %-6d VRC\n\n", i+1, names.get(i), items.get(names.get(i)));
        }
        // asks the user
        System.out.println("Please write index of item you want to buy");
        // validates the user input
        while (true) {
            itemName = ImportClasses.scanner.nextLine();
            if (itemName.equals("quit")) return;

            // checks if input is number
            if (itemName.matches("^[0-9]$") || itemName.matches("-\\d+")) {

                // converts the user input into the integer. Minus 1 because user chooses form 1, not from 0
                int index = Integer.parseInt(itemName) - 1;

                // checks if the item is existing
                if (!(index < 0 || index > names.size()-1)) {

                    // checks if the user has enough VRC to buy and item
                    if (getVRC(rifter.getUserName()) >= items.get(names.get(index))) {
                        subtractVRC(items.get(names.get(index)), rifter);
                        System.out.printf("Congratulations, you have bought the %s\n", names.get(index));

                        // adds an item to the purchase history
                        addToPurchaseHistory(names.get(index), rifter);
                        return;
                    }
                    else {
                        System.out.println("Sorry, you have less VRC, Choose another item:");
                    }
                } else {
                    System.out.println("Please, choose only from the options, Try again:");
                }
            }
            else {
                System.out.println("Please, write only numbers when you choose, you can quit only when write 'quit'");
            }
        }
    }

    // rewrites the file
    private static void writeHashMap(HashMap<String, Integer> hashMap, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String key : hashMap.keySet()) {
                writer.write(key + "," + hashMap.get(key));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("error occurred while writeHashMap");
            System.out.println(e.getMessage());
        }
    }

    // adds the VRC to the file
    public void addVRC(int VRC, Rifter rifter) {
        HashMap<String, Integer> hashMap = returnHashMap(FileNames.USER_BALANCE);

        // checks if the hash map is empty
        if (!hashMap.isEmpty()) {

            // checks if the user does not have the VRC
            if (!hashMap.containsKey(rifter.getUserName())) {

                // puts the new key and value into the hash map
                hashMap.put(rifter.getUserName(), VRC);

            } else {
                // adds the VRC to the existing balance
                hashMap.put(rifter.getUserName(), hashMap.get(rifter.getUserName()) + VRC);
            }
            writeHashMap(hashMap, FileNames.USER_BALANCE);
        } else {
            // simply writes the file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(FileNames.USER_BALANCE));
                writer.write(rifter.getUserName() + "," + VRC);
                writer.close();
            } catch (IOException e) {
                System.out.println("error occurred while addVRC");
                System.out.println(e.getMessage());
            }
        }
    }

    // used when user buys something
    public void subtractVRC(int VRC, Rifter rifter) {
        // fills the hash map
        HashMap<String, Integer> hashMap = returnHashMap(FileNames.USER_BALANCE);
        // subtracts the VRC
        hashMap.put(rifter.getUserName(), hashMap.get(rifter.getUserName()) - VRC);
        // rewrites the file
        writeHashMap(hashMap, FileNames.USER_BALANCE);
    }

    // returns content of the file as a hash map
    public static HashMap<String, Integer> returnHashMap(String fileName) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        File file = new File(fileName);

        // returns empty hash map if the file is empty
        if (file.length() == 0) return hashMap;

        try {
            Scanner scanner = new Scanner(file);
            //reading the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                String name = parts[BalanceIndexes.USERNAME.index]; // key is name
                int balance = Integer.parseInt(parts[BalanceIndexes.VRCBALANCE.index]); // value is balance

                // fills a hash map
                hashMap.put(name, balance);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("error occurred while returnHashMap");
            System.out.println(e.getMessage());
        }
        return hashMap;
    }

    // adds to the item to the purchase history
    private void addToPurchaseHistory(String item, Rifter rifter) {
        // returns content of the file as a list
        List<String> list = Profile.returnLines(FileNames.PURCHASE_HISTORY);

        // checks if the user's name in the file
        if (Validation.isFileContains(rifter.getUserName(), FileNames.PURCHASE_HISTORY)) {

            // iterates through the list and finds the user's name
            for (String line : list) {
                if (line.split(",")[PurchaseHistoryIndexes.USERNAME.index].equals(rifter.getUserName())) {

                    /*
                    adds the date of purchase
                    here I used spacial character '<' to further separate item and date
                    see the viewPurchaseHistory method
                    */
                    String modifiedLine = line + "," + item + "<" + LocalDate.now();

                    // replaces the whole line
                    list.set(list.indexOf(line), modifiedLine);
                    break;
                }
            }
        }
        else {
            list.add(rifter.getUserName() + "," + item+ "<" + LocalDate.now());
        }
        // overwrites the file
        Profile.overwriteFile(list, FileNames.PURCHASE_HISTORY);
    }

    // displays the purchase history
    public void viewPurchaseHistory(Rifter rifter) {
        // returns content of the file as a list
        List<String> list = Profile.returnLines(FileNames.PURCHASE_HISTORY);

        // iterates through the list to find the user's name and print his purchase history
        for (String line : list) {
            if (line.split(",")[PurchaseHistoryIndexes.USERNAME.index].equals(rifter.getUserName())) {
                System.out.println("Name of item:         Price:  Date of purchase:\n");
                String[] purchases = line.split(",");

                // displays the purchase history
                for (int i = 1; i < purchases.length; i++) {
                    System.out.printf("%-20s - %-5d - %10s\n",
                            purchases[i].split("<")[PurchaseHistoryIndexes.USERNAME.index],
                            getItems().get(purchases[i].split("<")[PurchaseHistoryIndexes.USERNAME.index]),
                            purchases[i].split("<")[PurchaseHistoryIndexes.DATE.index]);
                }
                return;
            }
        }
        System.out.println("You have not bought any items yet");
    }

}
