package org.example.gymmanagementsystem.model;

import org.example.gymmanagementsystem.dto.DietPlanDTO;
import org.example.gymmanagementsystem.dto.SessionDTO;
import org.example.gymmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SessionModel {
    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select session_id from session order by session_id DESC limit 1");
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

    public ArrayList<SessionDTO> getAllSession() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from session");
        ArrayList<SessionDTO> sessionDTOS = new ArrayList<>();
        while (resultSet.next()) {
            SessionDTO sessionDTO = new SessionDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
            sessionDTOS.add(sessionDTO);
        }
        return sessionDTOS;
    }


    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from session where session_id = ?", id);
    }

    public boolean save(SessionDTO s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into session values (?,?,?,?,?)", s.getSessionId(), s.getClassID(), s.getType(), s.getTime(), s.getDate());

    }

    public boolean update(SessionDTO s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update session set class_id = ?, type = ?,time = ?, date = ? WHERE session_id = ?", s.getClassID(), s.getType(), s.getTime(), s.getDate(), s.getSessionId());

    }
}

