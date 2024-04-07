import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    // Method to browse all books in the library
    public static void browseBooks() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String category = resultSet.getString("category");

                System.out.println("Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Category: " + category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to search for a book by title or author
    public static void searchBook(String keyword) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM books WHERE title LIKE '%" + keyword + "%' OR author LIKE '%" + keyword + "%'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String category = resultSet.getString("category");

                System.out.println("Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Category: " + category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to issue a book to a member
    public static void issueBook(int bookId, int memberId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO issued_books (book_id, member_id) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, bookId);
                statement.setInt(2, memberId);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Book issued successfully.");
                } else {
                    System.out.println("Failed to issue book.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to return a book
    public static void returnBook(int bookId, int memberId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM issued_books WHERE book_id = ? AND member_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, bookId);
                statement.setInt(2, memberId);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Failed to return book.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to send an email query
    public static void sendEmail(String query) {
        // Placeholder method for sending email
        System.out.println("Email sent: " + query);
    }
}
