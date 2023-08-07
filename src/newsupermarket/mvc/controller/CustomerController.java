/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsupermarket.mvc.controller;

import newsupermarket.mvc.model.CustomerModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import static javax.swing.UIManager.getString;
import newsupermarket.mvc.db.DBConnection;

/**
 *
 * @author Thenuk De Silva
 */
public class CustomerController {
    public String saveCustomer(CustomerModel customer)throws SQLException{
    Connection connection = DBConnection .getInstance().getConnection();
    
    String query = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";
    
    PreparedStatement preparedstatement = connection.prepareStatement(query);
    preparedstatement.setString(1,customer.getCustId());
    preparedstatement.setString(2,customer.getTitle());
    preparedstatement.setString(3,customer.getName());
    preparedstatement.setString(4,customer.getDob());
    preparedstatement.setDouble(5,customer.getSalary());
    preparedstatement.setString(6,customer.getAddress());
    preparedstatement.setString(7,customer.getCity());
    preparedstatement.setString(8,customer.getProvince());
    preparedstatement.setString(9,customer.getZip());
    
    if(preparedstatement.executeUpdate()>0){
        return "Success";
    }else{
        return "Fail";
                
                }
    }
    public ArrayList<CustomerModel>getAllCustomers() throws SQLException{
                     Connection connection=DBConnection.getInstance().getConnection();
                     
                     String query="SELECT*FROM Customer";
                     PreparedStatement statement = connection.prepareStatement(query);
                     ArrayList<CustomerModel> customerModels = new ArrayList<>();
                     ResultSet rst = statement.executeQuery();
        while (rst.next()) {            
            CustomerModel cm = new CustomerModel(rst.getString(1),
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getString(4), 
                    rst.getDouble(5),
                    rst.getString(6), 
                    rst.getString(7), 
                    rst.getString(8), 
                    rst.getString(9));
            
            customerModels.add(cm);
        }
        
        return customerModels;
    }
    public CustomerModel getCustomer(String custId)throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query="SELECT*FROM Customer WHERE CustID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, custId);
        ResultSet rst = statement.executeQuery();
        while(rst.next()){
            CustomerModel cm = new CustomerModel(rst.getString(1),
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getString(4), 
                    rst.getDouble(5),
                    rst.getString(6), 
                    rst.getString(7), 
                    rst.getString(8), 
                    rst.getString(9));
            return cm;
        }
        return null;
    }
    public String updateCustomer(CustomerModel customerModel)throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        
        String query = "UPDATE Customer SET CustTitle=?, custName=?, DOB=?, salary=?, custAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?";
        PreparedStatement preparedstatement = connection.prepareStatement(query);
        
        preparedstatement.setString(1,customerModel.getTitle());
        preparedstatement.setString(2,customerModel.getName());
        preparedstatement.setString(3,customerModel.getDob());
        preparedstatement.setDouble(4,customerModel.getSalary());
        preparedstatement.setString(5,customerModel.getAddress());
        preparedstatement.setString(6,customerModel.getCity());
        preparedstatement.setString(7,customerModel.getProvince());
        preparedstatement.setString(8,customerModel.getZip());
        preparedstatement.setString(9,customerModel.getCustId());
        
        if(preparedstatement.executeUpdate()>0){
            return "Success";
        }else{
            return "Fail";

                    }
    }
    public String deleteCustomer(String CustId)throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        
        String query = "DELETE FROM Customer WHERE CustId=?";
        PreparedStatement statement = connection.prepareCall(query);
        statement.setString(1, CustId);
        
        if(statement.executeUpdate()>0){
            return "Success";
        }else{
            return "Fail";

                    }
    }
    }
    


