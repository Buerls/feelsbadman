package labor.db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import labor.control.CustomerController;
import labor.control.WorkerMainController;
import labor.model.Customer;
import labor.model.Product;
import labor.model.Sale;
import labor.model.Worker;

import java.sql.*;
import java.util.ArrayList;

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
//
            connection = helper.getConnection();
            preparedStatement = connection.prepareStatement(SELECT + WHERE + SQL_COLUMN1 + "?" + AND + SQL_COLUMN2 + "?");

            preparedStatement.setString(1, customer__user_id);
            preparedStatement.setString(2, customer_user_password);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                bool = true;
                if (login_type.equals("müşteri")) {
                    CustomerController.customer_name = resultSet.getString("customer_name");
                    CustomerController.customer_company = resultSet.getString("customer_company");
                    CustomerController.customer_id = resultSet.getInt("customer_id");
                    Customer customer = new Customer(
                            resultSet.getString("customer_name"),
                            resultSet.getString("customer_surname"),
                            resultSet.getString("customer_user_id"),
                            resultSet.getInt("customer_id"),
                            resultSet.getString("customer_user_password"),
                            resultSet.getString("customer_company"));
                    customer.log_info();
                }
                if (login_type.equals("çalışan")) {
                    WorkerMainController.name = resultSet.getString("worker_name") + " " + resultSet.getString("worker_surname");
                    WorkerMainController.acces_level = resultSet.getString("worker_authority");
                    Worker worker = new Worker(
                            resultSet.getString("worker_name"),
                            resultSet.getString("worker_surname"),
                            resultSet.getString("worker_user_id"),
                            resultSet.getString("worker_user_password"),
                            resultSet.getInt("worker_id"),
                            resultSet.getString("worker_authority"));
                    worker.log_info();
                }

            }


        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }

        return bool;


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
            }
            Product.product_MAX_id = i;
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
        System.out.println("Tüm ürünler cekildi.");
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
            throw new Exception("Seçilen id ile bir kayıt bulunmaktadır");
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

    public static void sale(int customer_id, int product_id, int sale_amount, String sale_date, String customer_company) throws Exception {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;

        try {
            connection = helper.getConnection();
            String sql = "INSERT INTO stok_takip.sale (customer_id, product_id, sale_date, sale_amount, customer_company) " +
                    "VALUES (?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer_id);
            preparedStatement.setInt(2, product_id);
            preparedStatement.setString(3, sale_date);
            preparedStatement.setInt(4, sale_amount);
            preparedStatement.setString(5, customer_company);


            preparedStatement.executeUpdate();
            System.out.println("Satış Eklendi.");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
            throw new Exception("Seçilen id ile bir kayıt bulunmaktadır");
        } finally {
            preparedStatement.close();
            connection.close();
        }


    }

    public static ObservableList<Sale> get_sales_all() throws SQLException {
        ArrayList<Sale> sales = new ArrayList<Sale>();
        Connection connection = null;
        DBHelper helper = new DBHelper();
        Statement statement = null;
        ResultSet resultSet;
        String select = "SELECT * FROM stok_takip.sale";
        ObservableList<Sale> sales_obv = FXCollections.observableArrayList();
        try {

            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(select);

            Sale sale;

            while (resultSet.next()) {

                sale = new Sale(
                        resultSet.getInt("sale_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getString("sale_date"),
                        resultSet.getInt("sale_amount"),
                        resultSet.getString("customer_company")

                );

                sales.add(sale);


            }
            System.out.println("Satılan ürünler cekildi.");
            sales_obv.addAll(sales);

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
        return sales_obv;

    }

    public static ObservableList<Worker> get_worker_all() throws SQLException {
        ArrayList<Worker> workers = new ArrayList<Worker>();
        Connection connection = null;
        DBHelper helper = new DBHelper();
        Statement statement = null;
        ResultSet resultSet;
        String select = "SELECT * FROM stok_takip.worker WHERE worker_authority='staff'";
        ObservableList<Worker> worker_obv = FXCollections.observableArrayList();
        try {

            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(select);

            Worker worker;

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("worker_id"));
                worker = new Worker(


                        resultSet.getString("worker_name"),
                        resultSet.getString("worker_surname"),
                        resultSet.getString("worker_user_id"),
                        resultSet.getString("worker_user_password"),
                        resultSet.getInt("worker_id"),
                        resultSet.getString("worker_authority")

                );

                workers.add(worker);

                System.out.println("kayıtlı çalışanlar cekildi.");


            }

            worker_obv.addAll(workers);

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
        return worker_obv;

    }

    public static void add_new_worker(Worker worker) throws Exception {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        try {
            connection = helper.getConnection();
            String sql = "INSERT INTO stok_takip.worker (worker_name, worker_surname, worker_user_id, worker_user_password, worker_id, worker_authority) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, worker.getName());
            preparedStatement.setString(2, worker.getSurname());
            preparedStatement.setString(3, worker.getUser_id());
            preparedStatement.setString(4, worker.getUser_password());
            preparedStatement.setInt(5, worker.getId());
            preparedStatement.setString(6, worker.getAssignment());


            preparedStatement.executeUpdate();
            System.out.println("Yeni worker Eklendi.");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
            throw new Exception("Seçilen id ile bir kayıt bulunmaktadır");
        } finally {
            preparedStatement.close();
            connection.close();
        }


    }

    public static void update_worker(Worker worker) throws SQLException {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        try {
            connection = helper.getConnection();
            String sql = "UPDATE stok_takip.worker SET worker_name=?,worker_surname=?,worker_user_id=?,worker_user_password=?,worker_authority=? WHERE worker_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, worker.getName());
            preparedStatement.setString(2, worker.getSurname());
            preparedStatement.setString(3, worker.getUser_id());
            preparedStatement.setString(4, worker.getUser_password());
            preparedStatement.setString(5, worker.getAssignment());
            preparedStatement.setInt(6, worker.getId());
            preparedStatement.executeUpdate();
            System.out.println("Woorker guncellendi.");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    public static void delete_worker(int worker_id) throws SQLException {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement preparedStatement = null;
        try {
            connection = helper.getConnection();
            String sql = "DELETE FROM stok_takip.worker WHERE worker_id=" + worker_id;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            System.out.println("Worker silindi.");

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            preparedStatement.close();
            connection.close();
        }

    }


}
