package org.example.gymmanagementsystem.model;

import org.example.gymmanagementsystem.dbConnection.DBConnection;
import org.example.gymmanagementsystem.dto.SupplementDTO;
import org.example.gymmanagementsystem.dto.TrainerDTO;
import org.example.gymmanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainerModel {
    public ArrayList<TrainerDTO> getAllTrainer() throws SQLException, ClassNotFoundException {

//        String sql = "select * from trainer";
//        String sql2 = "select * from order";
//
//        //step 1    me connection eken thamai transaction eka handle karanne
//        Connection connection = DBConnection.getInstance().getConnection();
//        //step 2 connection eke gate eka false karanwa /// dta database ekt ymnne na
//        connection.setAutoCommit(false);
//        // step 3
//        try{
//            boolean issaved =  saveOrder("oid") ;//getTrainer();
//
//            if (issaved){
//
//                boolean issaved2 =  true ; //secondSql();
//
//                if (issaved2){
//
//                    //wada dekma harinm
//                    connection.commit();
//                    //return true;
//
//                }else {
//                    connection.rollback();
//                    //return false
//                }
//            }else {
//                connection.rollback();
//                //return false
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            connection.rollback();  // data eliyt dnw //////
//            //return false;
//        }finally {
//            connection.setAutoCommit(true);
//            // finally eke aniwaryen meka true krnn one
//        }


        ResultSet resultSet = CrudUtil.execute("select * from trainer");
        ArrayList<TrainerDTO> trainerDTOS = new ArrayList<>();
        while (resultSet.next()) {
            TrainerDTO t = new TrainerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            trainerDTOS.add(t);
        }
        return trainerDTOS ;
    }

//    private boolean saveOrder(String oid) {
//        return true;
//    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select trainer_id from trainer order by trainer_id DESC limit 1");
        char tableCharactor = 'T';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format("T%03d", nextIdNumber);
        }
        return tableCharactor + "001";
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from trainer where trainer_id = ?", id);
    }

    public boolean
    save(TrainerDTO t) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into trainer values (?,?,?,?)", t.getTrainerId(),t.getName(),t.getContact(),t.getSpecialization());
    }

    public boolean update(TrainerDTO trainerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update trainer set name = ?, contact = ?,specialization  = ? WHERE trainer_id = ?", trainerDTO.getName(), trainerDTO.getContact(), trainerDTO.getSpecialization(),trainerDTO.getTrainerId());

    }
}
