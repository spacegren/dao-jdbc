package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== teste number 1 : seller findById ======");
        Seller seller = sellerDao.findyById(2);
        System.out.println(seller);

        System.out.println("=== teste number 2 : seller findById ======");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list){
            System.out.println(obj);
        }
    }
}