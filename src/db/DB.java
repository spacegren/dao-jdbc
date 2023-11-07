package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class DB {

    private static Connection conect = null;

    public static Connection getConect(){
        if (conect == null){
            try{
                Properties prop = loadProperties();
                String url = prop.getProperty("dburl");
                conect = DriverManager.getConnection(url , prop);

            }catch (SQLException e){
                throw new DbExeption("erro na conexao com o banco de dados : " + e.getMessage());
            }
        }
        return conect;
    }

    public static void closeConnection(){
        if (conect != null){
            try{
                conect.close();
            }catch (SQLException e){
                throw new DbExeption("erro ao fechar conexao : " + e.getMessage());
            }
        }
    }

    private static Properties loadProperties(){
        try(FileInputStream filestream = new FileInputStream("db.properties")){
            Properties prop = new Properties();
            prop.load(filestream);
            return prop;

        }catch (IOException e){
            throw new DbExeption("erro ao carregar propriedades : " + e.getMessage());
        }
    }

    public static void closeStatement(Statement st){
        if (st != null){
            try{
                st.close();
            }catch (SQLException e){
                throw new DbExeption("erro ao fechar statemant : " + e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                throw new DbExeption("erro ao fechar resultset :" + e.getMessage());
            }
        }
    }
}
