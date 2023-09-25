package JAVAPROJECT;

public interface VendingMachineInterface {

	public abstract void setProduct();

	public abstract int menuPrint();

	public abstract int calSum(int selNo, int num);

	public abstract void adminMenu();

	public abstract void checkStock(String name);

	public abstract void addStock(String name, int theNbOfPrct);
}
