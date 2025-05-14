package org.example.gymmanagementsystem.model;

import org.example.gymmanagementsystem.dto.ClassInfoDTO;
import org.example.gymmanagementsystem.dto.DietPlanDTO;
import org.example.gymmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class diatPlanModel {

    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select diat_plan_id from diat_plan order by diat_plan_id DESC limit 1");
        char tableCharactor = 'D';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format("D%03d", nextIdNumber);
        }
        return tableCharactor + "001";
    }

    public boolean save(DietPlanDTO d) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into diat_plan values (?,?,?,?)", d.getDietPlanId(), d.getPlanName(), d.getFood(), d.getDrink());
    }

    public boolean update(DietPlanDTO d) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update diat_plan set plan_name = ?, food = ?,drink = ? WHERE diat_plan_id = ?", d.getPlanName(), d.getFood(), d.getDrink(), d.getDietPlanId());

    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from diat_plan where diat_plan_id = ?", id);
    }

    public ArrayList<DietPlanDTO> getAllDiatPlan() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from diat_plan");
        ArrayList<DietPlanDTO> DietPlanDTOS = new ArrayList<>();
        while (resultSet.next()) {
            DietPlanDTO dt = new DietPlanDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );
            DietPlanDTOS.add(dt);
        }
        return DietPlanDTOS;
    }


}


