package LoyaltyPlatform.Db;

import LoyaltyPlatform.Transaction.Transaction;
import LoyaltyPlatform.Wallet.GiftsProgramWallet;
import LoyaltyPlatform.Wallet.LevelsProgramWallet;

public class Db {
    private ClientsTable clientsTable;
    private OwnersTable ownersTable;
    private EmployeesTable employeesTable;
    private ShopsTable shopsTable;
    private CoalitionsTable coalitionsTable;
    private LevelsProgramsTable levelsProgramsTable;
    private GiftsProgramsTable giftsProgramsTable;
    private LevelsTable levelsTable;
    private DiscountsTable discountsTable;
    private GiftsTable giftsTable;
    private GiftsProgramWalletsTable giftsProgramWalletsTable;
    private LevelsProgramWalletsTable levelsProgramWalletsTable;

    public Db(){
        clientsTable = new ClientsTable();
        ownersTable = new OwnersTable();
        employeesTable = new EmployeesTable();
        shopsTable = new ShopsTable();
        coalitionsTable = new CoalitionsTable();
        levelsProgramsTable = new LevelsProgramsTable();
        giftsProgramsTable = new GiftsProgramsTable();
        levelsTable = new LevelsTable();
        discountsTable = new DiscountsTable();
        giftsTable = new GiftsTable();
        giftsProgramWalletsTable = new GiftsProgramWalletsTable();
        levelsProgramWalletsTable = new LevelsProgramWalletsTable();
    }

    public ClientsTable getClientsTable() {
        return clientsTable;
    }

    public OwnersTable getOwnersTable() {
        return ownersTable;
    }

    public EmployeesTable getEmployeesTable() {
        return employeesTable;
    }

    public ShopsTable getShopsTable() {
        return shopsTable;
    }

    public CoalitionsTable getCoalitionsTable() {
        return coalitionsTable;
    }

    public LevelsProgramsTable getLevelsProgramsTable() {
        return levelsProgramsTable;
    }

    public GiftsProgramsTable getGiftsProgramsTable() {
        return giftsProgramsTable;
    }

    public LevelsTable getLevelsTable() {
        return levelsTable;
    }

    public DiscountsTable getDiscountsTable() {
        return discountsTable;
    }

    public GiftsTable getGiftsTable() {
        return giftsTable;
    }

    public GiftsProgramWalletsTable getGiftsProgramWalletsTable() {
        return giftsProgramWalletsTable;
    }

    public LevelsProgramWalletsTable getLevelsProgramWalletsTable() {
        return levelsProgramWalletsTable;
    }
}
