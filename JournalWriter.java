import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

public class JournalWriter {
    public void main(String[] args) {

        // showing the first time massage
        System.out.println(" ________________________");
        System.out.println("|      journal writer    | ");
        System.out.println(" ^^^^^^^^^^^^^^^^^^^^^^^^^");

        System.out.println("select one from the menu:");

        // declearing varibles
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> journals = new ArrayList<>();

        // loop for the menu

        while (isRunning) {
            showMenu();

            System.out.print("enter your choice :");
            int choice = readNumber(scanner);

            switch (choice) {
                case 1:
                    viewJournals(journals);
                    break;

                case 2:
                    writeJournal(journals, scanner);
                    break;

                case 3:
                    deleteJournal(journals, scanner);
                    break;

                case 4:
                    saveJournals(journals);
                    break;

                case 5:
                    isRunning = false;
                    System.out.println("bye !");
                    break;

                default:
                    System.out.println("not a valid option");
                    break;
            }
        }
        scanner.close();
    }

    public void showMenu() { // just showing the menu
        System.out.println("________________________");
        System.out.println("1. view journals");
        System.out.println("2. write journal");
        System.out.println("3. delete a journal");
        System.out.println("4. save journals");
        System.out.println("5. exit");
        System.out.println("________________________");
    }

    public int readNumber(Scanner scanner) { // NUmberFormat include

        while (true) {
            try {
                String input = scanner.nextLine();
                int number = Integer.parseInt(input);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("invalid input. please enter a number!");
            }
        }

    }

    public void viewJournals(ArrayList<String> journals) { // showing the list

        if (journals.isEmpty()) {
            System.out.println("there is no journals yet.");
        } else {
            for (int i = 0; i < journals.size(); i++) {
                System.out.println((i + 1) + ". " + journals.get(i));
            }
        }

    }

    public void writeJournal(ArrayList<String> journals, Scanner scanner) {
        System.out.print("enter your today's journal: ");
        String newJournal = scanner.nextLine();

        // check if journal ok?

        if (newJournal.isEmpty()) {
            System.out.println("you didn't type anything!");
        } else {
            journals.add(newJournal);
            System.out.println("you journal is added successfully !");
        }

    }

    public void deleteJournal(ArrayList<String> journals, Scanner scanner) {
        System.out.print("enter the number of journal you want to delete :");
        int numOfDelete = readNumber(scanner);

        // check if deletion is ok?
        if (numOfDelete < 0 || numOfDelete > journals.size()) {
            System.out.println("there's no journal with that number ! enter again.");
        } else {
            journals.remove(numOfDelete - 1);
            System.out.println("the number " + numOfDelete + " journal removed !");
        }

    }

    public void saveJournals(ArrayList<String> journals) {

        String savedJournalsPath = "/home/photon/projects/java/journalApp/saved journals/journal.txt";
        try (FileWriter writer = new FileWriter(savedJournalsPath);) {

            LocalDateTime now = LocalDateTime.now();
            String time = now.format(DateTimeFormatter.ofPattern("HH:mm"));
            String date = now.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));

            for (int i = 0; i < journals.size(); i++) {
                writer.write("journal #" + i + 1 + "   " + time + " " + date + "\n" + journals.get(i) + "\n\n");
                writer.write("\n");
            }
            System.out.println("journals saved successfully!");

        } catch (IOException e) {
            System.out.println("cannot save the file something went wrong !");
        }

        System.out.println("hello world");

    }

}
