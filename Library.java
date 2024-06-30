import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private Map<Member, List<Book>> borrowedBooks;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        borrowedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void borrowBook(Member member, Book book) {
        if (book.isBorrowed()) {
            System.out.println("Book is already borrowed.");
        } else {
            book.borrow();
            borrowedBooks.computeIfAbsent(member, k -> new ArrayList<>()).add(book);
            System.out.println("Book borrowed successfully.");
        }
    }

    public void returnBook(Member member, Book book) {
        if (borrowedBooks.containsKey(member) && borrowedBooks.get(member).contains(book)) {
            book.returnBook();
            borrowedBooks.get(member).remove(book);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("This member did not borrow this book.");
        }
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Borrowed: " + book.isBorrowed());
        }
    }

    public void listMembers() {
        for (Member member : members) {
            System.out.println("Name: " + member.getName() + ", Member ID: " + member.getMemberId());
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }
}
