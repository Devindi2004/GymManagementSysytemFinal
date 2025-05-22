package org.example.gymmanagementsystem.model;

import org.example.gymmanagementsystem.dto.AttendanceDTO;
import org.example.gymmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceModel {
    public ArrayList<AttendanceDTO> getAllAttendance() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from attendance");
        ArrayList<AttendanceDTO> attendanceDTOS = new ArrayList<>();
        while (resultSet.next()) {
            AttendanceDTO at = new AttendanceDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            );
            attendanceDTOS.add(at);
        }
        return attendanceDTOS;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from attendance where attendance_id = ?", id);

    }

    public boolean save(AttendanceDTO a) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into attendance values (?,?,?)", a.getAttendanceId(),a.getMemberId(),a.getDate());

    }

    public boolean update(AttendanceDTO a) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update attendance set member_id = ?, date = ? WHERE attendance_id = ?",a.getMemberId(),a.getDate(),a.getAttendanceId());

    }
    public static String getNextId() throws SQLException, ClassNotFoundException {
        String result;
        ResultSet rs = CrudUtil.execute("select attendance_id from attendance order by attendance_id DESC limit 1");
        char tableCharactor = 'A';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            result = String.format("A%03d", nextIdNumber);
        } else {
            result = tableCharactor + "001";
        }
        return result;
    }
}