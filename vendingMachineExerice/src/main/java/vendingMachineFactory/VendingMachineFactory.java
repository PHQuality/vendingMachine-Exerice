package vendingMachineFactory;

import vendingMachineApi.VendingMachine;
import vendingMachineImplimentation.VendingMachineImpl;

public class VendingMachineFactory {
	
	public static VendingMachine createVendingMachine() {
        return new VendingMachineImpl();
    }
}





