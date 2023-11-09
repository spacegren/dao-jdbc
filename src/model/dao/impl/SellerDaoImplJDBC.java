package model.dao.impl;

import db.DB;
import db.DbExeption;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoImplJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoImplJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    +         "(Name , Email , BirthDate , BaseSalary , DepartmentId) "
                    + "VALUES "
                    +         "(? , ? , ? , ? , ?) ",
                    +Statement.RETURN_GENERATED_KEYS);

            st.setString(1,obj.getName());
            st.setString(2,obj.getEmail());
            st.setString(3, String.valueOf(new Date(obj.getBirthDate().getTime())));
            st.setDouble(4,obj.getBaseSalary());
            st.setInt(5,obj.getDepartment().getId());

            int rowsAfeectd = st.executeUpdate();

            if (rowsAfeectd > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }else {
                throw new DbExeption("Unexpected error! no rows affectes");
            }
        }catch (SQLException e){
            throw new DbExeption(e.getMessage());
        }
        finally {
            DB.closeStatement(st);

        }
    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer obj) {

    }

    @Override
    public Seller findyById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = Department.Id "
                    + "WHERE seller.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()){
                Department dep = instanciateDepartment(rs);
                Seller obj = instanciateSeller(rs , dep);
                return obj;

            }
            return null;
        }catch (SQLException e){
            throw new DbExeption("error" + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }
    }

    private Seller instanciateSeller(ResultSet rs, Department dep) throws SQLException {

        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException {

        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("Name"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {


            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                st = conn.prepareStatement(
                        "SELECT seller.*,department.Name as DepName "
                                + "FROM seller INNER JOIN department "
                                + "ON seller.DepartmentId = Department.Id "
                                + "ORDER BY seller.Name");

                rs = st.executeQuery();

                List<Seller> list = new ArrayList<>();
                Map<Integer , Department> map = new HashMap<>();


                while (rs.next()){

                    Department dep = map.get(rs.getInt("DepartmentId"));

                    if (dep == null){
                        dep = instanciateDepartment(rs);
                        map.put(rs.getInt("DepartmentId") , dep);
                    }

                    Seller obj = instanciateSeller(rs , dep);
                    list.add(obj);

                }
                return list;  //retorna lista

            }catch (SQLException e){
                throw new DbExeption("error" + e.getMessage());
            } finally {
                DB.closeStatement(st);
                DB.closeResultSet(rs);

            }
    }

    @Override
    public List<Seller> findByDepartment(Department department){

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = Department.Id "
                            + "WHERE seller.DepartmentId = ? "
                            + "ORDER BY seller.Name");


            st.setInt(1, department.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer , Department> map = new HashMap<>();


            while (rs.next()){

                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instanciateDepartment(rs);
                    map.put(rs.getInt("DepartmentId") , dep);
                }

                Seller obj = instanciateSeller(rs , dep);
                list.add(obj);

            }
            return list;  //retorna lista

        }catch (SQLException e){
            throw new DbExeption("error" + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }
    }

}
