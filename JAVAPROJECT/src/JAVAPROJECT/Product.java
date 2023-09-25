package JAVAPROJECT;

import java.util.ArrayList;


public class Product {
	String productName;
    int price;
    int stock;
    
    public ArrayList<Product> productList = new ArrayList<>(3); 
    
    public Product(String productName, int price, int stock) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;

    }
    
    public boolean sellProduct(int num) {
        if (stock - num < 0) {
            return false;
        } else if (stock - num == 0) {
            stock -= num;
            return true;
        } else {
            stock -= num;
            return true;
        }
    }
	
	public void showInfo() {
		System.out.println("제품 이름: " + productName);
	    System.out.println("제품 가격: " + price);
	    System.out.println("제품 재고: " + stock);
	}

}
