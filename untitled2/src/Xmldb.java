import java.sql.*;

public class Xmldb {
    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test3.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE NEWS " +
                    " (TITLE TEXT  PRIMARY KEY   NOT NULL, " +
                    " LINK            TEXT     NOT NULL, " +
                    " AUTHOR            TEXT     NOT NULL, " +
                    " PUBDATE            TEXT     NOT NULL, " +
                    " CATEGORY           TEXT     NOT NULL, " +
                    " COMMENTS            TEXT     NOT NULL, " +
                    " DESCRIPTION            TEXT     NOT NULL, "+
                    " CHANNEL            TEXT     NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}

