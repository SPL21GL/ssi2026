/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ssi.finalproject.dbaccess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import ssi.finalproject.entities.*;

public class DatabaseMockDataTest {

    private static ConnectionSource connectionSource;

    @BeforeAll
    public static void setupConnection() throws Exception {
        System.out.println("Verbinde mit Datenbank für Mock-Daten...");
        connectionSource = DatabaseManager.getConnection();
    }

    @Test
    public void insertMockData() throws Exception {
        System.out.println("Generiere realistische Lager-Daten...");

        // 1. DAOs erstellen
        Dao<StoLoc, String> stoLocDao = DaoManager.createDao(connectionSource, StoLoc.class);
        Dao<Customer, String> customerDao = DaoManager.createDao(connectionSource, Customer.class);
        Dao<AppUser, String> userDao = DaoManager.createDao(connectionSource, AppUser.class);
        Dao<Item, String> itemDao = DaoManager.createDao(connectionSource, Item.class);
        
        Dao<LoadingUnit, String> luDao = DaoManager.createDao(connectionSource, LoadingUnit.class);
        Dao<StockObject, String> stockDao = DaoManager.createDao(connectionSource, StockObject.class);
        
        Dao<PickingOrder, Integer> pickOrderDao = DaoManager.createDao(connectionSource, PickingOrder.class);
        Dao<OutboundOrder, String> oboDao = DaoManager.createDao(connectionSource, OutboundOrder.class);
        Dao<OboLine, String> oboLineDao = DaoManager.createDao(connectionSource, OboLine.class);
        Dao<PickingOrderLine, String> pickLineDao = DaoManager.createDao(connectionSource, PickingOrderLine.class);

        // ==========================================
        // PHASE 1: STAMMDATEN
        // ==========================================
        
        // Lagerplätze
        StoLoc locA = new StoLoc(); locA.setStoLocId("A-01-01"); locA.setPickSequence(10);
        
        StoLoc locB = new StoLoc(); locB.setStoLocId("A-01-02"); locB.setPickSequence(20);
        stoLocDao.createIfNotExists(locA);
        stoLocDao.createIfNotExists(locB);

        // Kunden
        Customer cust1 = new Customer(); cust1.setCustomerId("CUST-1000"); cust1.setAllowedLuType("EURO");
        customerDao.createIfNotExists(cust1);

        // User (Mitarbeiter)
        AppUser user1 = new AppUser(); user1.setUserId("U-001"); user1.setName("Klaus Stapler");
        AppUser user2 = new AppUser(); user2.setUserId("U-002"); user2.setName("Diggi");
        userDao.createIfNotExists(user1);
        userDao.createIfNotExists(user2);

        // Artikel
        Item item1 = new Item(); item1.setItemId("ART-001"); item1.setName("Hammer 500g"); 
        item1.setItemLength(30); item1.setPickingStoLoc(locA); // Foreign Key Referenz!
        
        Item item2 = new Item(); item2.setItemId("ART-002"); item2.setName("Schrauben M8x50"); 
        item2.setItemLength(5); item2.setPickingStoLoc(locB);
        
        itemDao.createIfNotExists(item1);
        itemDao.createIfNotExists(item2);

        // ==========================================
        // PHASE 2: BESTÄNDE
        // ==========================================
        
        // Ladeeinheit (z.B. Europalette) auf Platz A-01-01
        LoadingUnit lu1 = new LoadingUnit(); lu1.setLuId("LU-999901"); 
        lu1.setLuType("EURO"); lu1.setStoLoc(locA);
        luDao.createIfNotExists(lu1);

        // Bestand auf der Palette (50 Hämmer)
        StockObject stock1 = new StockObject(); 
        stock1.setLoadingUnit(lu1); stock1.setStockObjectNumber(1); 
        stock1.setItem(item1); stock1.setAmount(50);
        stockDao.createIfNotExists(stock1);

        // ==========================================
        // PHASE 3: AUFTRÄGE
        // ==========================================
        
        Date now = new Date();
        
        // Kommissionierauftrag (PickingOrder)
        PickingOrder pickOrder = new PickingOrder();
        pickOrder.setPickingOrderId(1001);
        
        pickOrder.setStatus(OrderState.NEU);
        pickOrder.setUser(user2); // Diggi bearbeitet das
        pickOrder.setCustomer(cust1);
        pickOrder.setStartTime(now);
        pickOrderDao.createIfNotExists(pickOrder);

        // Kundenauftrag (OutboundOrder)
        OutboundOrder obo = new OutboundOrder();
        obo.setOrderId("OBO-2024-01");
        obo.setPickingOrder(pickOrder);
        obo.setState(OrderState.NEU);
        obo.setCustomer(cust1);
        obo.setDeliveryTime(now);
        oboDao.createIfNotExists(obo);

        // ==========================================
        // PHASE 4: POSITIONEN (LINES)
        // ==========================================
        
        // Kundenauftrags-Position (Kunde will 5 Hämmer)
        OboLine oboLine = new OboLine();
        oboLine.setOutboundOrder(obo);
        oboLine.setLineNumber(1);
        oboLine.setItem(item1);
        oboLine.setOrderAmount(5);
        oboLine.setDeliveryAmount(0);
        oboLineDao.createIfNotExists(oboLine);

        // Pick-Position für den Mitarbeiter
        PickingOrderLine pickLine = new PickingOrderLine();
        pickLine.setPickingOrder(pickOrder);
        pickLine.setLineNumber(1);
        pickLine.setStatus(OrderState.NEU);
        pickLine.setPickSequence(locA.getPickSequence());
        pickLine.setStoLoc(locA);
        pickLine.setItem(item1);
        pickLine.setOrderAmount(5);
        pickLine.setBookingAmount(0);
        // Da wir Composite-FK als flache Felder abgebildet haben:
        pickLine.setTargetLuId("LU-999901");
        pickLine.setTargetStockObject(1);
        pickLineDao.createIfNotExists(pickLine);

        System.out.println("✅ Mock-Daten wurden erfolgreich in die Datenbank gepumpt!");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        if (connectionSource != null) {
            DatabaseManager.close();
        }
    }
}