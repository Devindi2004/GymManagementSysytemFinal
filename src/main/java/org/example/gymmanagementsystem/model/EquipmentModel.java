package org.example.gymmanagementsystem.model;

import org.example.gymmanagementsystem.dto.EquipmentDTO;
import org.example.gymmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentModel
{
    public static String getNextId() throws SQLException, ClassNotFoundException {
        String result;
        ResultSet rs = CrudUtil.execute("select equip_id from equipment order by equip_id DESC limit 1");
        char tableCharactor = 'E';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            result = String.format("E%03d", nextIdNumber);
        } else {
            result = tableCharactor + "001";
        }
        return result;
    }

    public ArrayList<EquipmentDTO> getAllequipment() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from equipment");
        ArrayList<EquipmentDTO> e = new ArrayList<>();
        ArrayList<EquipmentDTO> EquipmentDTOS = new ArrayList<>();
        while (resultSet.next()) {
            EquipmentDTO et = new EquipmentDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
            EquipmentDTOS.add(et);
        }
        return EquipmentDTOS;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from equipment where equip_id = ?", id);
    }

    public boolean save(EquipmentDTO e) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into equipment values (?,?,?,?,?)", e.getEquipId(),e.getSupplierId(),e.getClassId(),e.getName(),e.getType());

    }

    public boolean update(EquipmentDTO e) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update equipment set supplier_id = ?, class_id = ?,name = ?, type = ? WHERE equip_id = ?", e.getSupplierId(),e.getClassId(),e.getName(),e.getType(),e.getEquipId());

    }
}

