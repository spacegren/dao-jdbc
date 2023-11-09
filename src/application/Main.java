package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== teste number 1 : seller findById ======");
        Seller seller = sellerDao.findyById(3);
        System.out.println(seller);



    }
}