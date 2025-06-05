package org.example.gymmanagementsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.gymmanagementsystem.dto.SupplierDTO;
import org.example.gymmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {


    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select supplier_id from supplier order by supplier_id DESC limit 1");
        char tableCharactor = 'S';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format("S%03d", nextIdNumber);
        }
        return tableCharactor + "001";
    }

    public static ObservableList AllSupplierI() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select supplier_id from supplier");
        ObservableList list = FXCollections.observableArrayList();

        while (rs.next()){
            list.add(rs.getString("supplier_id"));

        }
        return list;
    }

    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from supplier");
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        while (resultSet.next()) {
            SupplierDTO sp = new SupplierDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );
            supplierDTOS.add(sp);
        }
        return supplierDTOS;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from supplier where supplier_id = ?", id);
    }

    public boolean update(SupplierDTO s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update supplier set name = ?, contact = ?,item = ? WHERE supplier_id = ?",s.getName(),s.getContact(),s.getItem(),s.getSupplierId());

    }

    public boolean save(SupplierDTO s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into supplier values (?,?,?,?)", s.getSupplierId(),s.getName(),s.getContact(),s.getItem());

    }
}
