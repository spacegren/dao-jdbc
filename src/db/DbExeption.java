package db;

public class DbExeption extends RuntimeException {

    private static final long serialVersionUID = 1l;

    public DbExeption(String msg){
        super(msg);
    }
}
