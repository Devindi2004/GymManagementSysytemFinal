package org.example.gymmanagementsystem.model;

import org.example.gymmanagementsystem.dbConnection.DBConnection;
import org.example.gymmanagementsystem.dto.OrderDTO;
import org.example.gymmanagementsystem.dto.OrderPayment;
import org.example.gymmanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {
    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select order_id from orders order by order_id DESC limit 1");
        char tableCharactor = 'O';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format("O%03d", nextIdNumber);
        }

        return tableCharactor + "001";
    }

    public ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from orders");
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        while (resultSet.next()) {
            OrderDTO o = new OrderDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
            orderDTOS.add(o);
        }
        return orderDTOS;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from orders where order_id = ?", id);
    }

    public boolean save(OrderDTO o, OrderPayment orderPayment, String supplementId) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isSaved = saveOrder(o);
            if (isSaved) {
                System.out.println("order saved");
                int opid = getNextopid();
                boolean isSaved2 = saveOrderPayment(orderPayment , opid);
                if (isSaved2) {
                    System.out.println("order payment saved");
                    boolean reduceQty = reduceQty(supplementId , 1);
                    if (reduceQty) {
                        System.out.println("qty reduced");
                        connection.commit();
                        return true;
                    }
                    else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
    }

    }

    private int getNextopid() {
        try {
            ResultSet rs = CrudUtil.execute("select p_id from order_payment order by p_id DESC limit 1");
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private boolean reduceQty(String supplementId, int i) throws SQLException, ClassNotFoundException {
        return  CrudUtil.execute("update suppliment set quantity = quantity - ? where suppliment_id = ?",i,supplementId);
    }

    private boolean saveOrderPayment(OrderPayment orderPayment, int opid) throws SQLException, ClassNotFoundException {
        return  CrudUtil.execute("insert into order_payment values (?,?,?)",opid,orderPayment.getAmount(),orderPayment.getOid());
    }

    private boolean saveOrder(OrderDTO o) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into orders values (?,?,?,?,?)", o.getOrderId(), o.getMemberId(), o.getName(), o.getDate(), o.getAmount());
    }

    public boolean update(OrderDTO o) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update orders set member_id = ?, name = ?,date = ?, amount = ? WHERE order_id = ?", o.getMemberId(),o.getName(),o.getDate(),o.getAmount(),o.getOrderId());

    }
}
