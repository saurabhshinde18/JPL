import java.util.ArrayList;

class Book {
    String title, author;
    int year;
    boolean isAvailable;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
    }

    public void displayStatus() {
        System.out.println(title + " by " + author + " (" + year + ") - " + (isAvailable ? "Available" : "Not Available"));
    }
}

class Member {
    String name;
    int id;
    ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Member(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable) {
            borrowedBooks.add(book);
            book.isAvailable = false;
            System.out.println(name + " borrowed \"" + book.title + "\".");
        } else {
            System.out.println("\"" + book.title + "\" is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.isAvailable = true;
            System.out.println(name + " returned \"" + book.title + "\".");
        } else {
            System.out.println(name + " has not borrowed \"" + book.title + "\".");
        }
    }

    public void displayBorrowedBooks() {
        System.out.println(name + "'s Borrowed Books:");
        for (Book book : borrowedBooks) {
            System.out.println("- " + book.title);
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Book book1 = new Book("Java Programming", "John Doe", 2021);
        Book book2 = new Book("Data Structures", "Jane Smith", 2020);

        Member member1 = new Member("Alice", 1);
        Member member2 = new Member("Bob", 2);

        member1.borrowBook(book1);
        member2.borrowBook(book1);
        member1.borrowBook(book2);

        member1.displayBorrowedBooks();
        member2.displayBorrowedBooks();

        book1.displayStatus();
        book2.displayStatus();

        member1.returnBook(book1);
        book1.displayStatus();
    }
}
