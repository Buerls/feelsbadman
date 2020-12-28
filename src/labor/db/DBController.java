package labor.db;

import labor.control.CustomerController;
import labor.model.Customer;

import java.sql.*;
import java.util.Collection;

public class DBController {




    public static boolean login_auth(String customer__user_id, String customer_user_password, String login_type) throws SQLException {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        boolean bool = false;
        String SELECT = null;
        String WHERE = " where ";
        String AND = " and ";
        String SQL_COLUMN1 = null;
        String SQL_COLUMN2 = null;

        if (login_type.equals("customer")) {
            SELECT = "SELECT * FROM stok_takip.customer";
            SQL_COLUMN1 = "customer_user_id=";
            SQL_COLUMN2 = "customer_user_password=";
        } else if (login_type.equals("worker")) {
            SELECT = "SELECT * FROM stok_takip.depo_calisan";
            SQL_COLUMN1 = "calisan_kullaniciAdi=";
            SQL_COLUMN2 = "calisan_sifre=";
        }

        try {

            connection = helper.getConnection();
            preparedStatement = connection.prepareStatement(SELECT + WHERE + SQL_COLUMN1 + "?" + AND + SQL_COLUMN2 + "?");

            preparedStatement.setString(1, customer__user_id);
            preparedStatement.setString(2, customer_user_password);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                bool = true;
                CustomerController.logged_in_user_id = customer__user_id;
                CustomerController.logged_in_user_password = customer_user_password;
            }


        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }

        return bool;


    }





}
