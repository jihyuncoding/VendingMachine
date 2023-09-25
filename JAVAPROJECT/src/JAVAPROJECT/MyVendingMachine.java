package JAVAPROJECT;

import java.util.Scanner;
import java.util.ArrayList;

public class MyVendingMachine implements VendingMachineInterface {
	Scanner scanner = new Scanner(System.in);

	private int profit = 0;
	private int selNo;
	private int num;
	private String name;
	private int selection;
	Admin manager;
	public ArrayList<Product> productList = new ArrayList<Product>(3);

	public MyVendingMachine() {
		manager = new Admin();
		manager.SetAdmin();
	}

	public void setProduct() {
		
		int numProducts = 0;

		while (numProducts <= 0) {
			numProducts = checkInputValue("몇 개의 제품을 입력하시겠습니까? : ", "문자를 입력하셨습니다. 숫자를 입력하세요.");
		}

		int i = 0;
		while (i < numProducts) {
			System.out.println(" ");
			System.out.println("******* " + (i + 1) + "번째 제품 **********");
			System.out.print("제품 이름: ");
			String productName = scanner.nextLine();

			System.out.print("제품 가격: ");
			int price = checkInputValue("", "문자를 입력하셨습니다. 숫자를 입력하세요.");

			int stock = checkInputValue("제품 수량: ", "문자를 입력하셨습니다. 숫자를 입력하세요.");
			Product newProduct = new Product(productName, price, stock);
			productList.add(newProduct);

			i++;
		}

	}

	public int menuPrint() {
		System.out.println(" ");

		while (true) {
			if (productList != null && productList.size() > 0) {
				System.out.println("구매할 제품의 번호를 입력하세요.");
				for (int i = 0; i < productList.size(); i++) {
					System.out.println((i + 1) + ". " + productList.get(i).productName + " " + productList.get(i).price
							+ "원 " + productList.get(i).stock + "개");
				}
				System.out.print("---------> ");
				selNo = scanner.nextInt();

				System.out.print("수량: ");
				num = scanner.nextInt();

				if (!productList.get(selNo - 1).sellProduct(num)) {
					System.out.println(productList.get(selNo - 1).productName + "을/를 " + num + "개 선택하셨습니다. ");
					System.out.println("재고가 부족합니다.");
					break;
				}

				profit += num * productList.get(selNo - 1).price;

				System.out.println(productList.get(selNo - 1).productName + "을/를 " + num + "개 선택하셨습니다. ");
				System.out.println("총 금액: " + num * productList.get(selNo - 1).price);

				if (productList.get(selNo - 1).stock == 0) {
					productList.remove(selNo - 1);
				}

				return selNo;
			} else {
				System.out.println("등록된 상품이 없습니다.");
				return -1;
			}
		}

		return -1;
	}

	public int calSum(int selNo, int num) {
		if (selNo > 0 && selNo <= productList.size()) {
			Product selectedProduct = productList.get(selNo - 1);
			if (selectedProduct.stock > 0) {
				profit += num * selectedProduct.price;
				return profit;
			}
		}
		return profit;
	}

	public void adminMenu() {
		boolean exitMenu = false;
		while (!exitMenu) {
			System.out.println(" ");
			System.out.println("1. 제품 등록");
			System.out.println("2. 매출 확인");
			System.out.println("3. 전체 제품정보 확인");
			System.out.println("4. 재고 확인");
			System.out.println("5. 재고 추가");
			System.out.println("6. 이전 메뉴");
			System.out.println("------> ");
			int selection = checkInputValue("", "문자를 입력하셨습니다.");

			if (selection == -1) {
				adminMenu(); // 문자 입력 시 다시 adminMenu() 호출
				return;
			}

			switch (selection) {
			case 1:
				setProduct();
				break;
			case 2:
				System.out.println("현재 총 매출액 : " + profit + "원");
				break;
			case 3:
				if (productList != null) {
					System.out.println("########################");
					for (int i = 0; i < productList.size(); i++) {
						productList.get(i).showInfo();
						System.out.println();
					}
					System.out.println("총 " + productList.size() + "개의 제품이 있습니다.");
					System.out.println("########################");
				} else {
					System.out.println("등록된 제품이 없습니다.");
				}
				break;
			case 4:
				System.out.print("어떤 상품의 재고를 확인할까요? ");
				name = scanner.nextLine().trim();

				checkStock(name);
				break;
			case 5:
				System.out.print("어떤 상품의 재고를 추가할까요?(제품이름) ");
				name = scanner.nextLine().trim();

				System.out.print("몇 개 추가할까요? ");
				int theNbOfPrct = scanner.nextInt();

				addStock(name, theNbOfPrct);
				break;
			case 6:
				exitMenu = true;
				break;
			}
		}
	}

	public void checkStock(String name) {
		int stockNumIndex = -1;
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).productName.equals(name)) {
				stockNumIndex = i;
				break;
			}
		}

		if (stockNumIndex != -1) {
			System.out.println(name + " 재고 : " + productList.get(stockNumIndex).stock + "개");
		} else {
			System.out.println("없는 상품입니다.");
		}
	}

	public void addStock(String name, int theNbOfPrct) {

		int stockNumIndex = -1;
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).productName.equals(name)) {
				stockNumIndex = i;
				break;
			}
		}

		if (stockNumIndex != -1) {
			productList.get(stockNumIndex).stock += theNbOfPrct;
			int Allstock = productList.get(stockNumIndex).stock;
			System.out.println(name + " 상품 총 재고 : " + Allstock + "개");
		} else {
			System.out.println("없는 상품입니다.");
		}
	}

	public int checkInputValue() {
		scanner.nextLine();

		return checkInputValue("Enter a number: ", "Error: Invalid input. Please enter a valid number.");
	}

	public int checkInputValue(String prompt, String errorMessage) {
		System.out.print(prompt);
		String input = scanner.nextLine().trim();

		if (input.isEmpty()) {
			return checkInputValue(prompt, errorMessage);
		}

		try {
			int value = Integer.parseInt(input);
			return value;
		} catch (NumberFormatException e) {
			System.out.println(errorMessage);
			return -1;
		}
	}
}
