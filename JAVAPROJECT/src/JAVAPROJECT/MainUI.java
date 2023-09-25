package JAVAPROJECT;

import java.util.Scanner;

public class MainUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        MyVendingMachine vendingMachine = new MyVendingMachine(); 


        while (true) {
            System.out.println(" ");
            System.out.println("1. 제품 구매");
            System.out.println("2. 관리자");
            System.out.println("3. 종료");
            System.out.println("메뉴를 선택하세요:");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    vendingMachine.menuPrint();
                    break;
                case 2:
                    boolean isAdmin = vendingMachine.manager.checkAdmin();
                    if (isAdmin) {
                        vendingMachine.adminMenu(); 
                    }
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");

                    scanner.close();
                    System.exit(0);
            }
        }
    }
}