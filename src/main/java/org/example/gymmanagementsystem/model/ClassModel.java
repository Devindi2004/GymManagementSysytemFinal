package org.example.gymmanagementsystem.model;

import org.example.gymmanagementsystem.dto.ClassInfoDTO;
import org.example.gymmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassModel {

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select class_id from class order by class_id DESC limit 1");
        char tableCharactor ='C';
        if(rs.next()){
            String lastId =rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString =String.format("C%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor+"001";
    }

    public boolean save(ClassInfoDTO classInfoDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into class values (?,?,?)",classInfoDTO.getClassId(),classInfoDTO.getTime(),classInfoDTO.getDuration());
    }

    public boolean update(ClassInfoDTO classInfoDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE class SET time = ?, duration = ? WHERE class_id = ?",classInfoDTO.getTime(),classInfoDTO.getDuration(),classInfoDTO.getClassId());

    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from class where class_id = ?",id);
    }

    public ArrayList<ClassInfoDTO> getAllClass() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from class");
        ArrayList<ClassInfoDTO> classInfoDTOS = new ArrayList<>();
        while(resultSet.next()){
            ClassInfoDTO classInfoDTO = new ClassInfoDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            );
            classInfoDTOS.add(classInfoDTO);
        }
        return classInfoDTOS;
    }
}

