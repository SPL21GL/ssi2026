package ssi.finalproject.dbaccess;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;
import ssi.finalproject.entities.*;

public class DatabaseSetupTest {

    private static ConnectionSource connectionSource;

    @BeforeAll
    public static void setupConnection() throws Exception {
        System.out.println("Starte Datenbank-Setup Test...");
        // Holt die Verbindung und erstellt die Datei 'lager.db', falls sie fehlt
        connectionSource = DatabaseManager.getConnection();
    }

    @Test
    public void testDatabaseAndTablesCreation() throws Exception {
        System.out.println("Verbindung steht. Lege Tabellen in der korrekten Reihenfolge an...");

        // --- 1. STAMMDATEN ---
        TableUtils.createTableIfNotExists(connectionSource, StoLoc.class);
        TableUtils.createTableIfNotExists(connectionSource, Customer.class);
        TableUtils.createTableIfNotExists(connectionSource, AppUser.class);
        TableUtils.createTableIfNotExists(connectionSource, Item.class);

        // --- 2. BESTÄNDE ---
        TableUtils.createTableIfNotExists(connectionSource, LoadingUnit.class);
        TableUtils.createTableIfNotExists(connectionSource, StockObject.class);

        // --- 3. AUFTRÄGE ---
        TableUtils.createTableIfNotExists(connectionSource, PickingOrder.class);
        TableUtils.createTableIfNotExists(connectionSource, OutboundOrder.class);

        // --- 4. LINIEN & TRACKING ---
        TableUtils.createTableIfNotExists(connectionSource, OboLine.class);
        TableUtils.createTableIfNotExists(connectionSource, PickingOrderLine.class);
        TableUtils.createTableIfNotExists(connectionSource, PerfTrack.class);

        System.out.println("BOOM! Alle Tabellen wurden erfolgreich generiert!");

        // Assert: Prüfen, ob die Datei wirklich physisch erstellt wurde
        File dbFile = new File("lager.db");
        assertTrue(dbFile.exists(), "Die Datenbank-Datei lager.db sollte existieren!");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        // Nach dem Test sauber aufräumen und DB-Lock freigeben
        if (connectionSource != null) {
            DatabaseManager.close();
        }
    }
}
