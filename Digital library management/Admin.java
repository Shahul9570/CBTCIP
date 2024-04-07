import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class Admin {

    public static void addBook(int id, String title, String author, String category) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO books (id, title, author, category) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.setString(2, title);
                statement.setString(3, author);
                statement.setString(4, category);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Book added successfully.");
                } else {
                    System.out.println("Failed to add book.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBook(int id, String title, String author, String category) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE books SET title=?, author=?, category=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, title);
                statement.setString(2, author);
                statement.setString(3, category);
                statement.setInt(4, id);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Book updated successfully.");
                } else {
                    System.out.println("Failed to update book.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBook(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM books WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Book deleted successfully.");
                } else {
                    System.out.println("Failed to delete book.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addMember(int id, String name, String email) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO members (id, name, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.setString(2, name);
                statement.setString(3, email);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Member added successfully.");
                } else {
                    System.out.println("Failed to add member.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMember(int id, String name, String email) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE members SET name=?, email=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setInt(3, id);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Member updated successfully.");
                } else {
                    System.out.println("Failed to update member.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMember(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM members WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Member deleted successfully.");
                } else {
                    System.out.println("Failed to delete member.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addMembermanual() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter member ID:");
        int id = scanner.nextInt();
        System.out.println("Enter member Name:");
        String name = scanner.next();
        System.out.println("Enter member Email:");
        String email = scanner.next();
        addMember(id, name, email);

    }


    public static void updateMembermanual() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter member ID:");
        int id = scanner.nextInt();
        System.out.println("Enter member Name:");
        String name = scanner.next();
        System.out.println("Enter member Email:");
        String email = scanner.next();
        updateMember(id, name, email);

    }
    public static void deleteMembermanual() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter member ID:");
        int id = scanner.nextInt();
        deleteMember(id);
    }
    public static void createBookManually() {
       
        String query = "INSERT INTO books (id, title, author, category) VALUES (?, ?, ?, ?)";

         Scanner scanner = new Scanner(System.in);
         System.out.println("Enter book ID:");
         int id = scanner.nextInt();
         System.out.println("Enter book title:");
         String title = scanner.next();
         System.out.println("Enter book author:");
         String author = scanner.next();
         System.out.println("Enter book category:");
         String category = scanner.next();
         addBook(id, title, author, category);
         scanner.close();
    }

    public static void updateBookManually() {
        String query = "UPDATE books SET title=?, author=?, category=? WHERE id=?";
         Scanner scanner = new Scanner(System.in);
         System.out.println("Enter book ID to update:");
         int id = scanner.nextInt();
         System.out.println("Enter updated title:");
         String title = scanner.next();
         System.out.println("Enter updated author:");
         String author = scanner.next();
         System.out.println("Enter updated category:");
         String category = scanner.next();
         updateBook(id, title, author, category);
         scanner.close();
    }

    public static void deleteBookManually() {

        String query = "DELETE FROM books WHERE id=?";

         Scanner scanner = new Scanner(System.in);
         System.out.println("Enter book ID to delete:");
         int id = scanner.nextInt();
         deleteBook(id);
         scanner.close();
    }
}
