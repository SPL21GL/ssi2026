package ssi.finalproject.dbaccess;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import ssi.finalproject.entities.*;

public class DatabaseMockDataTest {

    private static ConnectionSource connectionSource;

    @BeforeAll
    public static void setupConnection() throws Exception {
        System.out.println("Starte Datenbank-Seeding Test...");
        // Wir gehen davon aus, dass DatabaseManager existiert und die DB liefert
        connectionSource = DatabaseManager.getConnection();
    }

    @Test
    public void testSeedDatabase() throws Exception {
        System.out.println("Befülle die Tabellen mit Testdaten...");

        // ==========================================
        // 1. DAOs INITIALISIEREN
        // ==========================================
        Dao<StoLoc, String> stoLocDao = DaoManager.createDao(connectionSource, StoLoc.class);
        Dao<Customer, String> customerDao = DaoManager.createDao(connectionSource, Customer.class);
        Dao<AppUser, String> appUserDao = DaoManager.createDao(connectionSource, AppUser.class);
        Dao<Item, String> itemDao = DaoManager.createDao(connectionSource, Item.class);
        Dao<LoadingUnit, String> loadingUnitDao = DaoManager.createDao(connectionSource, LoadingUnit.class);
        Dao<StockObject, Integer> stockObjectDao = DaoManager.createDao(connectionSource, StockObject.class);
        Dao<PickingOrder, Integer> pickingOrderDao = DaoManager.createDao(connectionSource, PickingOrder.class);
        Dao<OutboundOrder, String> outboundOrderDao = DaoManager.createDao(connectionSource, OutboundOrder.class);
        Dao<OboLine, Integer> oboLineDao = DaoManager.createDao(connectionSource, OboLine.class);
        Dao<PickingOrderLine, Integer> pickingOrderLineDao = DaoManager.createDao(connectionSource, PickingOrderLine.class);
        Dao<PerfTrack, Integer> perfTrackDao = DaoManager.createDao(connectionSource, PerfTrack.class);

        
        
        
        if (itemDao.countOf() > 0)
        {
            return;
        }
        
        // ==========================================
        // 2. UNABHÄNGIGE STAMMDATEN (Keine Foreign Keys)
        // ==========================================
        
        // StoLocs anlegen
        StoLoc loc1 = new StoLoc(); loc1.setStoLocId("L-01-01"); loc1.setPickSequence(10); stoLocDao.create(loc1);
        StoLoc loc2 = new StoLoc(); loc2.setStoLocId("L-01-02"); loc2.setPickSequence(20); stoLocDao.create(loc2);
        StoLoc loc3 = new StoLoc(); loc3.setStoLocId("L-02-01"); loc3.setPickSequence(30); stoLocDao.create(loc3);

        // Customers anlegen
        Customer c1 = new Customer(); c1.setCustomerId("CUST-100"); c1.setAllowedLuType("EU-PAL"); customerDao.create(c1);
        Customer c2 = new Customer(); c2.setCustomerId("CUST-200"); c2.setAllowedLuType("BOX"); customerDao.create(c2);

        // AppUsers anlegen
        AppUser u1 = new AppUser(); u1.setUserId("U-001"); u1.setName("Max Mustermann"); appUserDao.create(u1);
        AppUser u2 = new AppUser(); u2.setUserId("U-002"); u2.setName("Anna Schmidt"); appUserDao.create(u2);

        // ==========================================
        // 3. ABHÄNGIGE STAMMDATEN (Brauchen StoLocs)
        // ==========================================
        
        // Items anlegen
        Item i1 = new Item(); i1.setItemId("ITM-001"); i1.setName("Schraube M8"); i1.setItemLength(10); i1.setItemWidth(10); i1.setItemHeight(5); i1.setPickingStoLoc(loc1); itemDao.create(i1);
        Item i2 = new Item(); i2.setItemId("ITM-002"); i2.setName("Mutter M8"); i2.setItemLength(5); i2.setItemWidth(5); i2.setItemHeight(5); i2.setPickingStoLoc(loc2); itemDao.create(i2);
        Item i3 = new Item(); i3.setItemId("ITM-003"); i3.setName("Unterlegscheibe"); i3.setItemLength(8); i3.setItemWidth(8); i3.setItemHeight(1); i3.setPickingStoLoc(loc3); itemDao.create(i3);

        // LoadingUnits anlegen
        LoadingUnit lu1 = new LoadingUnit(); lu1.setLuId("LU-1000"); lu1.setLuType("EU-PAL"); lu1.setStoLoc(loc1); loadingUnitDao.create(lu1);
        LoadingUnit lu2 = new LoadingUnit(); lu2.setLuId("LU-1001"); lu2.setLuType("BOX"); lu2.setStoLoc(loc2); loadingUnitDao.create(lu2);

        // ==========================================
        // 4. BESTÄNDE (Brauchen LoadingUnit & Item)
        // ==========================================
        
        StockObject so1 = new StockObject(); so1.setLoadingUnit(lu1); so1.setStockObjectNumber(1); so1.setItem(i1); so1.setAmount(500); stockObjectDao.create(so1);
        StockObject so2 = new StockObject(); so2.setLoadingUnit(lu1); so2.setStockObjectNumber(2); so2.setItem(i2); so2.setAmount(500); stockObjectDao.create(so2);
        StockObject so3 = new StockObject(); so3.setLoadingUnit(lu2); so3.setStockObjectNumber(1); so3.setItem(i3); so3.setAmount(1000); stockObjectDao.create(so3);

        // ==========================================
        // 5. AUFTRÄGE (Brauchen User & Customer)
        // ==========================================
        
        // PickingOrders anlegen (IDs werden generiert)
        PickingOrder po1 = new PickingOrder(); po1.setStatus(OrderState.NEU); po1.setUser(u1); po1.setCustomer(c1); po1.setStartTime(new Date()); po1.setDeliveryTime(new Date()); pickingOrderDao.create(po1);
        PickingOrder po2 = new PickingOrder(); po2.setStatus(OrderState.AKTIV); po2.setUser(u2); po2.setCustomer(c2); po2.setStartTime(new Date()); po2.setDeliveryTime(new Date()); pickingOrderDao.create(po2);

        // OutboundOrders anlegen
        OutboundOrder obo1 = new OutboundOrder(); obo1.setOrderId("OBO-2023-001"); obo1.setPickingOrder(po1); obo1.setState(OrderState.NEU); obo1.setCustomer(c1); obo1.setDeliveryTime(new Date()); outboundOrderDao.create(obo1);
        OutboundOrder obo2 = new OutboundOrder(); obo2.setOrderId("OBO-2023-002"); obo2.setPickingOrder(po2); obo2.setState(OrderState.AKTIV); obo2.setCustomer(c2); obo2.setDeliveryTime(new Date()); outboundOrderDao.create(obo2);

        // ==========================================
        // 6. LINIEN & TRACKING (Brauchen Aufträge & Items)
        // ==========================================
        
        // OboLines anlegen
        OboLine ol1 = new OboLine(); ol1.setOutboundOrder(obo1); ol1.setLineNumber(1); ol1.setItem(i1); ol1.setOrderAmount(100); ol1.setDeliveryAmount(0); oboLineDao.create(ol1);
        OboLine ol2 = new OboLine(); ol2.setOutboundOrder(obo1); ol2.setLineNumber(2); ol2.setItem(i2); ol2.setOrderAmount(50); ol2.setDeliveryAmount(0); oboLineDao.create(ol2);

        // PickingOrderLines anlegen
        PickingOrderLine pol1 = new PickingOrderLine(); pol1.setPickingOrder(po1); pol1.setLineNumber(1); pol1.setStatus(OrderState.NEU); pol1.setPickSequence(10); pol1.setStoLoc(loc1); pol1.setItem(i1); pol1.setOrderAmount(100); pol1.setBookingAmount(0); pol1.setTargetLuId("TARGET-LU-1"); pol1.setTargetStockObject(1); pickingOrderLineDao.create(pol1);
        PickingOrderLine pol2 = new PickingOrderLine(); pol2.setPickingOrder(po1); pol2.setLineNumber(2); pol2.setStatus(OrderState.NEU); pol2.setPickSequence(20); pol2.setStoLoc(loc2); pol2.setItem(i2); pol2.setOrderAmount(50); pol2.setBookingAmount(0); pol2.setTargetLuId("TARGET-LU-1"); pol2.setTargetStockObject(2); pickingOrderLineDao.create(pol2);

        // PerfTracks anlegen
        PerfTrack pt1 = new PerfTrack(); pt1.setTrackTime(new Date()); pt1.setPickingOrder(po1); pt1.setUser(u1); pt1.setPickCount(50); pt1.setDuration(120); perfTrackDao.create(pt1);
        PerfTrack pt2 = new PerfTrack(); pt2.setTrackTime(new Date()); pt2.setPickingOrder(po2); pt2.setUser(u2); pt2.setPickCount(100); pt2.setDuration(240); perfTrackDao.create(pt2);

        System.out.println("Erfolgreich! Alle Testdaten wurden generiert.");

        // ==========================================
        // 7. ASSERTS (Prüfen ob Daten wirklich da sind)
        // ==========================================
        assertEquals(3, stoLocDao.countOf(), "Es sollten genau 3 StoLocs existieren.");
        assertEquals(2, customerDao.countOf(), "Es sollten genau 2 Customers existieren.");
        assertEquals(3, itemDao.countOf(), "Es sollten genau 3 Items existieren.");
        assertEquals(2, pickingOrderDao.countOf(), "Es sollten genau 2 PickingOrders existieren.");
        assertEquals(2, perfTrackDao.countOf(), "Es sollten genau 2 PerfTracks existieren.");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        if (connectionSource != null) {
            DatabaseManager.close();
        }
    }
}