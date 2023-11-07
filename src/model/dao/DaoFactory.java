package model.dao;

import model.dao.impl.SellerDaoImplJDBC;

public class DaoFactory {

    //macete para nao expor a implementacao deixando somente a interface
    public static SellerDao createSellerDao(){
        return new SellerDaoImplJDBC();


    }
}
