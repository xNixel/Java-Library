import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Books");
            System.out.println("6. List Members");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                    break;
                case 2:
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    library.addMember(new Member(name, memberId));
                    break;
                case 3:
                    System.out.print("Enter member ID: ");
                    int borrowMemberId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter book title: ");
                    String borrowTitle = scanner.nextLine();
                    Member borrowMember = library.getMembers().stream()
                        .filter(m -> m.getMemberId() == borrowMemberId)
                        .findFirst()
                        .orElse(null);
                    Book borrowBook = library.getBooks().stream()
                        .filter(b -> b.getTitle().equalsIgnoreCase(borrowTitle))
                        .findFirst()
                        .orElse(null);
                    if (borrowMember != null && borrowBook != null) {
                        library.borrowBook(borrowMember, borrowBook);
                    } else {
                        System.out.println("Member or book not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter member ID: ");
                    int returnMemberId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter book title: ");
                    String returnTitle = scanner.nextLine();
                    Member returnMember = library.getMembers().stream()
                        .filter(m -> m.getMemberId() == returnMemberId)
                        .findFirst()
                        .orElse(null);
                    Book returnBook = library.getBooks().stream()
                        .filter(b -> b.getTitle().equalsIgnoreCase(returnTitle))
                        .findFirst()
                        .orElse(null);
                    if (returnMember != null && returnBook != null) {
                        library.returnBook(returnMember, returnBook);
                    } else {
                        System.out.println("Member or book not found.");
                    }
                    break;
                case 5:
                    library.listBooks();
                    break;
                case 6:
                    library.listMembers();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }
}
