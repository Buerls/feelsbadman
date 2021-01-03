package labor.db;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import labor.control.CustomerController;
import labor.model.Customer;
import labor.model.Product;
import labor.model.Worker;

import java.sql.*;
import java.util.ArrayList;
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

        if (login_type.equals("müşteri")) {
            SELECT = "SELECT * FROM stok_takip.customer";
            SQL_COLUMN1 = "customer_user_id=";
            SQL_COLUMN2 = "customer_user_password=";
        } else if (login_type.equals("çalışan")) {
            SELECT = "SELECT * FROM stok_takip.worker";
            SQL_COLUMN1 = "worker_user_id=";
            SQL_COLUMN2 = "worker_user_password=";
        }

        try {

            connection = helper.getConnection();
            preparedStatement = connection.prepareStatement(SELECT + WHERE + SQL_COLUMN1 + "?" + AND + SQL_COLUMN2 + "?");

            preparedStatement.setString(1, customer__user_id);
            preparedStatement.setString(2, customer_user_password);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                bool = true;
                CustomerController.user_id = customer__user_id;
                CustomerController.user_password = customer_user_password;
            }


        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }

        return bool;


    }

    public static ArrayList<Product> get_products(String product_type) throws SQLException {
        ArrayList<Product> products = new ArrayList<Product>();
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        String select = "SELECT * FROM stok_takip.product where product_type=";
        try {

            connection = helper.getConnection();
            preparedStatement = connection.prepareStatement(select + "?");
            preparedStatement.setString(1, product_type);
            resultSet = preparedStatement.executeQuery();
            Product product;
            int i = 0;
            while (resultSet.next()) {

                product = new Product(
                        resultSet.getString("product_name"),
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_price"),
                        resultSet.getString("product_photo"),
                        resultSet.getString("product_type"),
                        resultSet.getInt("product_amount")
                );

                products.add(product);
                i++;
                System.out.println(product_type + " bilgileri cekildi.");
                System.out.println(i);
            }


        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }

        return products;

    }

    public static ArrayList<String> get_customer_info(String customer__user_id, String customer_user_password) throws SQLException {
        ArrayList<String> user_info = null;
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        String SELECT = "SELECT * FROM stok_takip.customer";
        String WHERE = " where ";
        String AND = " and ";
        String SQL_COLUMN1 = "customer_user_id=";
        String SQL_COLUMN2 = "customer_user_password=";


        try {

            connection = helper.getConnection();
            preparedStatement = connection.prepareStatement(SELECT + WHERE + SQL_COLUMN1 + "?" + AND + SQL_COLUMN2 + "?");

            preparedStatement.setString(1, customer__user_id);
            preparedStatement.setString(2, customer_user_password);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                user_info = new ArrayList<>();
                String id = String.valueOf(resultSet.getInt("customer_id"));
                user_info.add(resultSet.getString("customer_name"));
                user_info.add(resultSet.getString("customer_surname"));
                user_info.add(resultSet.getString("customer_adress"));
                user_info.add(resultSet.getString("customer_payment_info"));
                user_info.add(id);

            }


        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }

        return user_info;


    }

    public static ArrayList<String> get_worker_info(String worker_user_id, String worker_user_password) throws SQLException {
        ArrayList<String> user_info = null;
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        String SELECT = "SELECT * FROM stok_takip.worker";
        String WHERE = " where ";
        String AND = " and ";
        String SQL_COLUMN1 = "worker_user_id=";
        String SQL_COLUMN2 = "worker_user_password=";


        try {

            connection = helper.getConnection();
            preparedStatement = connection.prepareStatement(SELECT + WHERE + SQL_COLUMN1 + "?" + AND + SQL_COLUMN2 + "?");


            preparedStatement.setString(1, worker_user_id);
            preparedStatement.setString(2, worker_user_password);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                user_info = new ArrayList<>();
                String id = String.valueOf(resultSet.getInt("worker_id"));
                user_info.add(resultSet.getString("worker_name"));
                user_info.add(resultSet.getString("worker_surname"));
                user_info.add(resultSet.getString("worker_authority"));
                user_info.add(id);

            }


        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }

        return user_info;


    }


    public static ArrayList<Product> get_products_all() throws SQLException {
        ArrayList<Product> products = new ArrayList<Product>();
        Connection connection = null;
        DBHelper helper = new DBHelper();
        Statement statement = null;
        ResultSet resultSet;
        String select = "SELECT * FROM stok_takip.product";
        try {

            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(select);
            Product product;
            int i = 0;
            while (resultSet.next()) {

                product = new Product(
                        resultSet.getString("product_name"),
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_price"),
                        resultSet.getString("product_photo"),
                        resultSet.getString("product_type"),
                        resultSet.getInt("product_amount")
                );

                products.add(product);
                i++;
                System.out.println("tüm ürünler cekildi.");
                System.out.println(i);

            }
            Product.product_MAX_id = i;

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
        return products;

    }

    public static void add_new_product(Product product) throws Exception {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        try {
            connection = helper.getConnection();
            String sql = "INSERT INTO stok_takip.product (product_name, product_photo, product_price, product_type, product_amount, product_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setString(2, product.getProduct_photo());
            preparedStatement.setString(3, product.getProduct_price());
            preparedStatement.setString(4, product.getProduct_type());
            preparedStatement.setInt(5, product.getProduct_amount());
            preparedStatement.setInt(6, product.getProduct_id());


            preparedStatement.executeUpdate();
            System.out.println("Yeni urun Eklendi.");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
            throw new Exception("O VAR BİZDE");
        } finally {
            preparedStatement.close();
            connection.close();
        }


    }

    public static void delete_product(int product_id) throws SQLException {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        try {
            connection = helper.getConnection();
            String sql = "DELETE FROM stok_takip.product WHERE product_id=" + product_id;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            System.out.println("Urun silindi.");
            if (product_id == Product.product_MAX_id) {
                --Product.product_MAX_id;
            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            preparedStatement.close();
            connection.close();
        }

    }

    public static void update_product(Product product) throws SQLException {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        try {
            connection = helper.getConnection();
            String sql = "UPDATE stok_takip.product SET product_name=?,product_photo=?,product_price=?,product_type=?,product_amount=? WHERE product_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setString(2, product.getProduct_photo());
            preparedStatement.setString(3, product.getProduct_price());
            preparedStatement.setString(4, product.getProduct_type());
            preparedStatement.setInt(5, product.getProduct_amount());
            System.out.println(product.getProduct_id());
            preparedStatement.setInt(6, product.getProduct_id());
            preparedStatement.executeUpdate();
            System.out.println("Urun guncellendi.");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    public static void add_new_customer(Customer customer) throws Exception {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {

            if (customer_user_id_control(customer.getUser_id())) {
                throw new SQLException("x");
            }
            connection = helper.getConnection();
            String sql = "INSERT INTO stok_takip.customer (customer_name, customer_surname, customer_user_id, customer_user_password, customer_company) " +
                    "VALUES (?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getUser_id());
            preparedStatement.setString(4, customer.getUser_password());
            preparedStatement.setString(5, customer.getCompany());


            preparedStatement.executeUpdate();
            System.out.println("Yeni müşteri Eklendi.");


        } catch (SQLException ex) {
            helper.showErrorMessage(ex);
            throw new Exception("Seçtiğiniz isimle kayıtlı müşteri bulunmaktadır");
            
        }
        assert preparedStatement != null;
        preparedStatement.close();
        connection.close();

    }

    public static boolean customer_user_id_control(String user_id) throws SQLException {
        boolean bool = false;
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        String WHERE = " where ";
        String SELECT = "SELECT * FROM stok_takip.customer";
        String SQL_COLUMN = "customer_user_id=";

        try {

            connection = helper.getConnection();
            preparedStatement = connection.prepareStatement(SELECT + WHERE + SQL_COLUMN + "?");

            preparedStatement.setString(1, user_id);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                bool = true;
            }


        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }

        return bool;


    }


}
