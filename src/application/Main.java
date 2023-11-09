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

        Seller seller = sellerDao.findyById(3);

        System.out.println(seller);
        


    }
}