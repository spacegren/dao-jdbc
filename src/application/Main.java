package application;

import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Department obj = new Department(1, "books");

        Seller seller = new Seller(77,"nicacio" , "nicacio@gmail.com" , new Date(),3400.0 ,obj);
        System.out.println(seller);

    }
}