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
        System.out.println();

        System.out.println("=== teste number 2 : seller findByDepartment ======");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list){
            System.out.println(obj);
            System.out.println();
        }
        System.out.println("=== teste number 3 : seller findAll");
        list = sellerDao.findAll();
        for (Seller obj : list){
            System.out.println(obj);
            System.out.println();
        }

        System.out.println("=== teste number 4 : seller Insert");

        Seller sellernew = new Seller(null,"nick","nick@gmail.com",new Date(), 4.000,department);
        sellerDao.insert(sellernew);
        System.out.println("Inserted! new id  =" + sellernew.getId());
        System.out.println();

        System.out.println("=== teste 5 : seller update ====");
        seller = sellerDao.findyById(1);
        seller.setName("sara pereira");
        sellerDao.update(seller);
        System.out.println("updated completed !!");




    }
}