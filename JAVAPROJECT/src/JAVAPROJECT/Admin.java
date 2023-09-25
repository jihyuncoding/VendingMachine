package JAVAPROJECT;

import java.util.Scanner;
public class Admin {
	private int id;
	private String pw;
	
	public void SetAdmin() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("-----------------------------------------------");
	    System.out.println("[VendingMachine]");
	    System.out.println("-----------------------------------------------");
	    System.out.println("관리자를 생성합니다.");
	    System.out.println("새로운 id를 생성하세요(숫자만 생성가능) : ");
	    
	    while (!scanner.hasNextInt()) {
	        System.out.println("아이디는 숫자입니다.");
	        System.out.println("id : ");
	        scanner.nextLine();
	    }
	    
	    id = scanner.nextInt();
	    scanner.nextLine();
	    System.out.println("새로운 pw를 생성하세요(숫자, 영문_대소문자 생성가능): ");
	    pw = scanner.nextLine();

	    System.out.println("새로운 관리자 ID: " + id + "  비밀번호: " + "*".repeat(pw.length()));

	}

	public boolean checkAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("관리자 권한이 있어야 하는 메뉴입니다.");
        System.out.println("id: ");
        int correctId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("pw: ");
        String correctPw = scanner.nextLine();


        if (id == correctId && pw.equals(correctPw)) {
            return true;
        } else {
            System.out.println("관리자 권한이 없습니다.");
            return false;
        }
    }
	
}
