package ssi.finalproject.dbaccess;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:lager.db";
    private static ConnectionSource connectionSource;

    public static ConnectionSource getConnection() throws SQLException {
        if (connectionSource == null) {
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
            
            
            DatabaseConnection connection = connectionSource.getReadWriteConnection("");
            try {
                
                connection.executeStatement("PRAGMA foreign_keys = ON;", DatabaseConnection.DEFAULT_RESULT_FLAGS);
            } finally {
                
                connectionSource.releaseConnection(connection);
            }
        }
        return connectionSource;
    }

    public static void close() throws Exception {
        if (connectionSource != null) {
            connectionSource.close();
        }
    }
}