package vendingMachineExerice.vendingMachineExerice;

import vendingMachineApi.VendingMachine;
import vendingMachineEntities.Bucket;
import vendingMachineEntities.Coin;
import vendingMachineEntities.Item;
import vendingMachineExceptions.NotSufficientChangeException;
import vendingMachineExceptions.SoldOutException;
import vendingMachineFactory.VendingMachineFactory;
import vendingMachineImplimentation.VendingMachineImpl;
import org.junit.Ignore;
import java.util.List; 
import org.junit.AfterClass; 
import org.junit.BeforeClass; 
import org.junit.Test; 
import static org.junit.Assert.*;


public class VendingMachineTest {
		
	private static VendingMachine vendingMa; 

	@BeforeClass 
	public static void setUp()
	{ 
		
		vendingMa = VendingMachineFactory.createVendingMachine(); 
		
	} 
	
	@AfterClass 
	public static void tearDown()
	
	{ 
		vendingMa = null; 
	
	} 
	
	
	@Test 
	public void testBuyItemWithExactPrice() {
		
		long price = vendingMa.selectItemAndGetPrice(Item.COKE); 
		//price should be Coke's price 
		assertEquals(Item.COKE.getPrice(), price); 
		//25 cents paid 
		vendingMa.insertCoin(Coin.QUARTER); 
		Bucket<Item, List<Coin>> bucket = vendingMa.collectItemAndChange(); 
		Item item = bucket.getFirst(); 
		List<Coin> change = bucket.getSecond(); 
		//should be Coke 
		assertEquals(Item.COKE, item); 
		//there should not be any change 
		assertTrue(change.isEmpty());
		} 
	
	@Test 
	 public void testBuyItemWithMorePrice()
		{ 
		long price = vendingMa.selectItemAndGetPrice(Item.SODA); 
		assertEquals(Item.SODA.getPrice(), price); 
		vendingMa.insertCoin(Coin.QUARTER); 
		vendingMa.insertCoin(Coin.QUARTER); 
		Bucket<Item, List<Coin>> bucket = vendingMa.collectItemAndChange(); 
		Item item = bucket.getFirst(); 
		List<Coin> change = bucket.getSecond(); 
	
		//should be Coke 
		assertEquals(Item.SODA, item); 
	
		//there should not be any change 
		assertTrue(!change.isEmpty()); 
	
		//comparing change 
		assertEquals(50 - Item.SODA.getPrice(), getTotal(change)); 
	
		} 
	
		@Test 
		public void testRefund()
		{ 
		long price = vendingMa.selectItemAndGetPrice(Item.PEPSI); 
		assertEquals(Item.PEPSI.getPrice(), price); 
		vendingMa.insertCoin(Coin.DIME); vendingMa.insertCoin(Coin.NICKLE); 
		vendingMa.insertCoin(Coin.PENNY); 
		vendingMa.insertCoin(Coin.QUARTER); 
		assertEquals(41, getTotal(vendingMa.refund())); 
		
		} 
	
		@Test(expected=SoldOutException.class) 
		public void testSoldOut()
		{ 
			for (int i = 0; i < 5; i++) 
		{ 
			vendingMa.selectItemAndGetPrice(Item.COKE); 
			vendingMa.insertCoin(Coin.QUARTER); 
			vendingMa.collectItemAndChange(); 
			
		} 
	} 
		
		@Test(expected=NotSufficientChangeException.class) 
		public void testNotSufficientChangeException(){ 
		for (int i = 0; i < 5; i++) 
		{ 
			vendingMa.selectItemAndGetPrice(Item.SODA); 
			vendingMa.insertCoin(Coin.QUARTER); 
			vendingMa.insertCoin(Coin.QUARTER); 
			vendingMa.collectItemAndChange(); 
			vendingMa.selectItemAndGetPrice(Item.PEPSI); 
			vendingMa.insertCoin(Coin.QUARTER); 
			vendingMa.insertCoin(Coin.QUARTER); 
			vendingMa.collectItemAndChange(); 
			
		} 
		} 
	
	@Test(expected=SoldOutException.class) 
	public void testReset()
	{ 
		VendingMachine vmachine = VendingMachineFactory.createVendingMachine(); 
		vmachine.reset(); 
		vmachine.selectItemAndGetPrice(Item.COKE); 
		
	} 
	
	@Ignore 
	public void testVendingMachineImpl()
	
	{ 
		VendingMachineImpl vm = new VendingMachineImpl(); 
	} private long getTotal(List<Coin> change)
	{ 
		long total = 0; for(Coin c : change){ total = total + c.getDenomination(); } return total; 
	} 
	
}
	

		

