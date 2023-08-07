/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsupermarket.mvc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import newsupermarket.mvc.db.DBConnection;
import newsupermarket.mvc.model.ItemModel;

/**
 *
 * @author Thenuk De Silva
 */
public class ItemController {

    public ArrayList<ItemModel> getAllItems() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT*FROM Item";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rst = statement.executeQuery();
        ArrayList<ItemModel> itemModels = new ArrayList<>();

        while (rst.next()) {
            ItemModel item = new ItemModel(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5));
            itemModels.add(item);
        }
        return itemModels;
    }

    public String saveItem(ItemModel item) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String query = "INSERT INTO Item VALUES(?,?,?,?,?)";

        PreparedStatement preparedstatement = connection.prepareStatement(query);
        preparedstatement.setString(1, item.getItemCode());
        preparedstatement.setString(2, item.getDescription());
        preparedstatement.setString(3, item.getPackSize());
        preparedstatement.setDouble(4, item.getUnitPrice());
        preparedstatement.setInt(5, item.getQoh());

        if (preparedstatement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";

        }
    }

    public ItemModel searchItem(String itemCode) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT*FROM Item WHERE ItemCode=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, itemCode);
        ResultSet rst = statement.executeQuery();
        while (rst.next()) {
            ItemModel item = new ItemModel(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5));
            return item;
        }
        return null;
    }

    public String updateItem(ItemModel item) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String query = "UPDATE Item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=?";

        PreparedStatement preparedstatement = connection.prepareStatement(query);
        preparedstatement.setString(5, item.getItemCode());
        preparedstatement.setString(1, item.getDescription());
        preparedstatement.setString(2, item.getPackSize());
        preparedstatement.setDouble(3, item.getUnitPrice());
        preparedstatement.setInt(4, item.getQoh());

        if (preparedstatement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";

        }
    }

    public String deleteItem(String itemCode) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String query = "DELETE FROM Item WHERE ItemCode=?";

        PreparedStatement preparedstatement = connection.prepareStatement(query);
        preparedstatement.setString(1,itemCode);

        if (preparedstatement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";

        }
    }
}
