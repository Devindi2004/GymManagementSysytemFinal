package org.example.gymmanagementsystem.model;

import org.example.gymmanagementsystem.dto.DietPlanDTO;
import org.example.gymmanagementsystem.dto.SupplementDTO;
import org.example.gymmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplimentModel {
    public ArrayList<SupplementDTO> getAllSuppliment() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from suppliment");
        ArrayList<SupplementDTO> supplementDTOS = new ArrayList<>();
        while (resultSet.next()) {
            SupplementDTO supp = new SupplementDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
            supplementDTOS.add(supp);
        }
        return supplementDTOS;
    }

    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select suppliment_id from suppliment order by suppliment_id DESC limit 1");
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

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from suppliment where suppliment_id = ?", id);
    }

    public boolean save(SupplementDTO supp) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into suppliment values (?,?,?,?,?)", supp.getSupplimentId(), supp.getName(), supp.getDescription(),supp.getPrice(), supp.getQuantity());
    }

    public boolean update(SupplementDTO supplementDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update suppliment set name = ?, description = ? , price = ?, quantity = ? WHERE suppliment_id = ?", supplementDTO.getName(), supplementDTO.getDescription(),supplementDTO.getPrice(),supplementDTO.getQuantity(), supplementDTO.getSupplimentId());

        }

    public char[] getSupplementName(String supplementId) {
        try {
            ResultSet resultSet = CrudUtil.execute("select name from suppliment where suppliment_id = ?", supplementId);
            if (resultSet.next()) {
                return resultSet.getString(1).toCharArray();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
