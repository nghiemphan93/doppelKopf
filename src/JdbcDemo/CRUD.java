package JdbcDemo;

import java.sql.*;

public class CRUD {
    public Connection connection = null;
    public Statement statement = null;
    public ResultSet resultSet = null;

    public void insertDemo(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String dbUrl = "jdbc:mysql://localhost:3306/doppelkopf?useSSL=false";
        String user = "student";
        String pass = "student";

        try {
            // 1. get connection to db
            connection = DriverManager.getConnection(dbUrl, user, pass);

            //2. create statement
            statement = connection.prepareStatement("INSERT INTO player(name, pass) VALUES (?, ?)");

            // 3. insert a new employee
            System.out.println("Inserting a new player");

            ((PreparedStatement) statement).setString(1, "Phan");
            ((PreparedStatement) statement).setString(2, "phan");


            // 4. verify by a list of employees
            resultSet = ((PreparedStatement) statement).executeQuery();


        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void updateDemo(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
        String user = "student";
        String pass = "student";

        try {
            // 1. get connection to db
            connection = DriverManager.getConnection(dbUrl, user, pass);

            //2. create statement
            statement = connection.createStatement();

            // 3. insert a new employee
            System.out.println("Updating a new employee");

            int rowsAffected = statement.executeUpdate(
                    "UPDATE employees "
                            +   "SET email = 'john.doe@luv2code.com' "
                            +   "WHERE last_name = 'Doe' and first_name = 'John'"
            );

            System.out.println(rowsAffected + " rows affected");

            // 4. verify by a list of employees
            resultSet = statement.executeQuery("select * from employees order by last_name");

            // 4. Process the result set
            while(resultSet.next()){
                System.out.println(
                        resultSet.getString("name")
                                + ", "
                                + resultSet.getString("pass")
                );
            }


        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void selectDemo(){
    Connection myConn = null;
    PreparedStatement myStmt = null;
    ResultSet myRs = null;

		try {
        // 1. Get a connection to database
        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doppelkopf?useSSL=false", "student" , "student");

        // 2. Prepare statement
        myStmt = myConn.prepareStatement("select * from " + "player");

        // 3. Set the parameters
//        myStmt.setString(1, "player");

        // 4. Execute SQL query
        myRs = myStmt.executeQuery();

        // 5. Display the result set
        display(myRs);
    }
		catch (Exception exc) {
            System.out.println(exc.getMessage());
    }
		finally {
        if (myRs != null) {
            try {
                myRs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (myStmt != null) {
            try {
                myStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (myConn != null) {
            try {
                myConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

    private static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            String name = myRs.getString("name");
            String pass = myRs.getString("pass");
            System.out.printf("%s, %s\n", name, pass);
        }
    }

    public void deleteDemo(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
        String user = "student";
        String pass = "student";

        try {
            // 1. get connection to db
            connection = DriverManager.getConnection(dbUrl, user, pass);

            //2. create statement
            statement = connection.createStatement();

            // 3. delete an employee
            System.out.println("Deleting a employee");

            int rowsAffected = statement.executeUpdate(
                    "DELETE FROM employees "
                            +   "WHERE last_name = 'Doe' and first_name = 'John'"
            );

            System.out.println(rowsAffected + " rows affected");

            // 4. verify by a list of employees
            resultSet = statement.executeQuery("select * from employees order by last_name");

            // 4. Process the result set
            while(resultSet.next()){
                System.out.println(
                        resultSet.getString("last_name")
                                + ", "
                                + resultSet.getString("first_name")
                );
            }


        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {

        }
    }
}
