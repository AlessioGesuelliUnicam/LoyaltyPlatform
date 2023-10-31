package LoyaltyPlatform.Db.Tables;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import LoyaltyPlatform.Components.Reward.Gift;

public class GiftsTableTest {

    GiftsTable giftsTable = new GiftsTable();
    Gift gift = new Gift("a",1,1);

    @Test
    final void GiftTableTest() {
        assertTrue(giftsTable.getRecords().isEmpty());
    }

    @Test
    final void GetRecordsTest() {
        giftsTable.add(gift);
        HashSet<Gift> gifts = new HashSet<>();
        gifts.add(gift);
        assertEquals(giftsTable.getRecords(), gifts);
    }

    @Test
    final void addTest(){
        assertThrows(NullPointerException.class, () -> giftsTable.add(null));
        assertTrue(giftsTable.getRecords().isEmpty());
        assertTrue(giftsTable.add(gift));
        assertTrue(giftsTable.getRecords().contains(gift));
        assertFalse(giftsTable.add(gift));
        assertTrue(giftsTable.getRecords().size() == 1);
    }

    @Test
    final void removeTest(){
        giftsTable.add(gift);
        assertThrows(NullPointerException.class, () -> giftsTable.delete(null));
        assertTrue(giftsTable.getRecords().contains(gift));
        assertTrue(giftsTable.delete(gift));
        assertFalse(giftsTable.getRecords().contains(gift));
        assertFalse(giftsTable.delete(gift));
        assertTrue(giftsTable.getRecords().isEmpty());
    }

}
