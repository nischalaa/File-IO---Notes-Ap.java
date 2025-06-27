import java.io.*;
import java.util.Scanner;

public class NotesManager {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Simple Notes App ===");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a note");
            System.out.println("2. View all notes");
            System.out.println("3. Overwrite notes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your note: ");
                    String note = scanner.nextLine();
                    appendNote(note);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.print("Enter new content to overwrite: ");
                    String newContent = scanner.nextLine();
                    overwriteNotes(newContent);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
        System.out.println("Exiting Notes App.");
    }

    // Append a note
    public static void appendNote(String note) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("Note added.");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    // Overwrite all notes
    public static void overwriteNotes(String content) {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            writer.write(content + System.lineSeparator());
            System.out.println("Notes overwritten.");
        } catch (IOException e) {
            System.out.println("Error overwriting notes: " + e.getMessage());
        }
    }

    // Read all notes
    public static void readNotes() {
        System.out.println("\n--- Saved Notes ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found.");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }
}
