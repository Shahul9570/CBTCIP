import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Library Management System!");

        System.out.println("Are you an admin or a user? (admin/user)");
        String role = scanner.nextLine();
            if (role.equalsIgnoreCase("admin")) {
                System.out.println("What do you want to do? (addBook/updateBook/deleteBook/addMember/updateMember/deleteMember)");
                String action = scanner.nextLine();
                if (action.equalsIgnoreCase("addBook")) {
                    Admin.createBookManually();
                } else if (action.equalsIgnoreCase("updateBook")) {
                    Admin.updateBookManually();
                } else if (action.equalsIgnoreCase("deleteBook")) {
                    Admin.deleteBookManually();
                } else if (action.equalsIgnoreCase("addMember")) {
                    
                    Admin.addMembermanual();
                } else if (action.equalsIgnoreCase("updateMember")) {
                    Admin.updateMembermanual();
                } else if (action.equalsIgnoreCase("deleteMember")) {
                   
                    Admin.deleteMembermanual();
                } else {
                    System.out.println("Invalid action.");
                }
            } else if (role.equalsIgnoreCase("user")) {
                User.browseBooks();
                System.out.println("Enter keyword to search for a book:");
                String keyword = scanner.nextLine();
                User.searchBook(keyword);
                User.issueBook(101, 201);
                User.returnBook(101, 201);
                   // User.sendEmail("Please help me with this query.");
            } else {
                System.out.println("Invalid role. Please enter 'admin' or 'user'.");
            }
    
            scanner.close();
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String category = resultSet.getString("category");
                System.out.println("Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Category: " + category);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }}}
    
    