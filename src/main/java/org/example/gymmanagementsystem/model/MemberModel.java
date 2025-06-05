package org.example.gymmanagementsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.gymmanagementsystem.dto.MemberDTO;
import org.example.gymmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberModel {

    public static String getnextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select member_id from member order by member_id DESC limit 1");
        char tableCharactor = 'M';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format("M%03d", nextIdNumber);
        }
        return tableCharactor + "001";
    }

    public static ObservableList Allclassid() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select member_id from member");
        ObservableList list = FXCollections.observableArrayList();

        while (rs.next()){
            list.add(rs.getString("member_id"));

        }
        return list;
    }

    public static ObservableList AllMemberId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select member_id from member");
        ObservableList list = FXCollections.observableArrayList();

        while (rs.next()){
            list.add(rs.getString("member_id"));

        }
        return list;
    }

    public ArrayList<MemberDTO> getAllmember() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from member");
        ArrayList<MemberDTO> memberDTOS = new ArrayList<>();
        while (resultSet.next()) {
            MemberDTO m = new MemberDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            );
            memberDTOS.add(m);
        }
        return memberDTOS;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from member where member_id = ?", id);
    }

    public boolean save(MemberDTO m) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into member values (?,?,?,?,?,?)", m.getMemberId(),m.getDietPlanId(),m.getName(),m.getEmail(),m.getPhone(),m.getAge());
    }

    public boolean update(MemberDTO m) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update member set diat_plan_id = ?,name = ?, email = ? ,phone = ?,age = ? WHERE member_id = ?", m.getDietPlanId(),m.getName(),m.getEmail(),m.getPhone(),m.getAge(),m.getMemberId());

    }

    public String getEmail(String memberId) {
        try {
            ResultSet resultSet = CrudUtil.execute("select email from member where member_id = ?", memberId);
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
