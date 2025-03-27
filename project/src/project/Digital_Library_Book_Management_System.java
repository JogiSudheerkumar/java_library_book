package project;

import java.util.*;

// Main class for the Digital Library Book Management System
class Digital_Library_Book_Management_System {

    // Inner class representing a Book
    private static class Book {
        String bookId, title, author, genre, status;
        
        // Constructor to initialize book details
        Book(String bookId, String title, String author, String genre, String status) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.status = status;
        }
    }
    
    // HashMap to store books using book ID as the key
    private final Map<String, Book> books = new HashMap<>();
    private final Scanner sc = new Scanner(System.in);

    // Method to add a new book to the library
    public void addBook() {
        System.out.print("Enter Book ID: ");
        String bookId = sc.nextLine();
        
        // Check if the book ID already exists
        if (books.containsKey(bookId)) {
            System.out.println("Error: Book ID must be unique.\n");
            return;
        }
        
        System.out.print("Enter Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Enter Author: ");
        String author = sc.nextLine().trim();
        
        // Ensure that title and author are not empty
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Error: Title and Author cannot be empty.\n");
            return;
        }
        
        System.out.print("Enter Genre: ");
        String genre = sc.nextLine();
        System.out.print("Enter Availability Status (Available/Checked Out): ");
        String status = sc.nextLine().trim();
        
        // Ensure that status is either "Available" or "Checked Out"
        if (!status.equals("Available") && !status.equals("Checked Out")) {
            System.out.println("Error: Availability status must be 'Available' or 'Checked Out'.\n");
            return;
        }
        
        // Store the new book in the HashMap
        books.put(bookId, new Book(bookId, title, author, genre, status));
        System.out.println("Book added successfully!\n");
    }

    // Method to view all books in the library
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.\n");
            return;
        }
        
        // Iterate over the book collection and display details
        for (Book book : books.values()) {
            System.out.println("ID: " + book.bookId + ", Title: " + book.title + 
                               ", Author: " + book.author + ", Genre: " + book.genre + 
                               ", Status: " + book.status);
        }
        System.out.println();
    }

    // Method to search for a book by ID or Title
    public void searchBook() {
        System.out.print("Enter Book ID or Title to search: ");
        String searchKey = sc.nextLine();
        
        // Iterate through books and find a match
        for (Book book : books.values()) {
            if (book.bookId.equals(searchKey) || book.title.equalsIgnoreCase(searchKey)) {
                System.out.println("Found: ID: " + book.bookId + ", Title: " + book.title + 
                                   ", Author: " + book.author + ", Genre: " + book.genre + 
                                   ", Status: " + book.status + "\n");
                return;
            }
        }
        System.out.println("Book not found.\n");
    }

    // Method to update an existing book's details
    public void updateBook() {
        System.out.print("Enter Book ID to update: ");
        String bookId = sc.nextLine();
        
        // Check if the book exists
        if (books.containsKey(bookId)) {
            Book book = books.get(bookId);
            
            // Allow users to update individual fields while keeping others unchanged
            System.out.println("Leave blank if no change is needed.");
            System.out.print("Enter new Title: ");
            String title = sc.nextLine().trim();
            System.out.print("Enter new Author: ");
            String author = sc.nextLine().trim();
            System.out.print("Enter new Genre: ");
            String genre = sc.nextLine();
            System.out.print("Enter new Availability Status (Available/Checked Out): ");
            String status = sc.nextLine().trim();
            
            // Validate status input
            if (!status.isEmpty() && !status.equals("Available") && !status.equals("Checked Out")) {
                System.out.println("Error: Availability status must be 'Available' or 'Checked Out'.\n");
                return;
            }
            
            // Update book details only if new values are provided
            book.title = title.isEmpty() ? book.title : title;
            book.author = author.isEmpty() ? book.author : author;
            book.genre = genre.isEmpty() ? book.genre : genre;
            book.status = status.isEmpty() ? book.status : status;
            
            System.out.println("Book updated successfully!\n");
        } else {
            System.out.println("Book not found.\n");
        }
    }

    // Method to delete a book from the library
    public void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String bookId = sc.nextLine();
        
        // Check if the book exists
        if (books.containsKey(bookId)) {
            books.remove(bookId);
            System.out.println("Book deleted successfully!\n");
        } else {
            System.out.println("Book not found.\n");
        }
    }

    // Main menu loop to interact with the system
    public void run() {
        while (true) {
            System.out.println("\nLibrary System Menu");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete a Book Record");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            String choice = sc.nextLine();
            
            // Handle menu options using switch-case
            switch (choice) {
                case "1": addBook(); break;
                case "2": viewBooks(); break;
                case "3": searchBook(); break;
                case "4": updateBook(); break;
                case "5": deleteBook(); break;
                case "6": 
                    System.out.println("Exiting Library System.");
                    return;
                default: 
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    // Main method to start the program
    public static void main(String[] args) {
        // Create an instance of the system and start the menu loop
        Digital_Library_Book_Management_System library = new Digital_Library_Book_Management_System();
        library.run();
    }
}
