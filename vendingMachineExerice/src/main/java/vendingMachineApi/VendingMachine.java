package vendingMachineApi;
import java.util.List;

import vendingMachineEntities.Bucket;
import vendingMachineEntities.Coin;
import vendingMachineEntities.Item;


public interface VendingMachine 
{   
    public long selectItemAndGetPrice(Item item);
    public void insertCoin(Coin coin);
    public List<Coin> refund();
    public Bucket<Item, List<Coin>> collectItemAndChange();   
    public void reset();
}



