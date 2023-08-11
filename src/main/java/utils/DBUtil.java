package utils;

import java.sql.*;

public class DBUtil {
    static Connection connection = null;
    static Statement statement = null;

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/capstone", "root", "rohis1403");
            statement = connection.createStatement();
            System.out.println("koneksi ke DB berhasil ");
        } catch (SQLException e) {
            System.out.println("Koneksi Ke DB Gagal!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet selectAll(String tableName) throws SQLException {
        return statement.executeQuery("select * from " + tableName);
    }

    public static ResultSet selectUser(String email, String password) throws SQLException {
//        return statement.executeQuery ("select * from users where email ='"+email+"'and"+"password='"+password+"'");
        String sql = ("select * from users where email ='" + email + "' and " + "password='" + password + "'");
        System.out.println("sql = " + sql);
        return statement.executeQuery(sql);
    }

    public static boolean insert(String tableName, int id_pasien, int age, String sex, String bp, String cholesterol, String na_to_k) {
        String query = "insert into " + tableName + " values (" + id_pasien + ", " + age + ", '" + sex + "', '" + bp + "', '" + cholesterol + "', " + na_to_k + ")";
        System.out.println(query);
        try {
            if (statement.executeUpdate(query) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean insertDrug(String tableName, int id_drug, String type_drug) {
        String query = "insert into " + tableName + " values (" + id_drug + ", '" + type_drug + "')";
        System.out.println(query);
        try {
            if (statement.executeUpdate(query) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean insertRole (String tableName, int id_pasien, int id_drug) {
        String query = "insert into " + tableName + " ( id_pasien, id_drug )" + " values (" + id_pasien + ", " + id_drug + ")";
        System.out.println(query);
        try {
            if (statement.executeUpdate(query)>0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(String tableName, int id_pasien, int age, String sex,  String bp, String cholesterol, String na_to_k) {
        //membuat query
        String query = "update " + tableName + " set age = " + age + ", sex = '" + sex + "', bp = '" + bp + "', cholesterol = '" + cholesterol + "', na_to_k = " + na_to_k + " where id_pasien = " + id_pasien;
        System.out.println(query);
        try {
            if (statement.executeUpdate(query) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean updateDrug(String tableName, int id_drug, String type_drug) {
        //membuat query
        String query = "update " + tableName + " set type_drug = '" + type_drug + "' where id_drug = " + id_drug;
        System.out.println(query);
        try {
            if (statement.executeUpdate(query) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(String tableName, int id_pasien) {
        //membuat query
        String query = "delete from " + tableName + " where id_pasien = " + id_pasien;
        System.out.println(query);
        try {
            if (statement.executeUpdate(query) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean deleteDrug(String tableName, int id_drug) {
        //membuat query
        String query = "delete from " + tableName + " where id_drug = " + id_drug;
        System.out.println(query);
        try {
            if (statement.executeUpdate(query) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean deleteRelasi(String tableName, int id) {
        //membuat query
        String query = "delete from " + tableName + " where id = " + id;
        System.out.println(query);
        try {
            if (statement.executeUpdate(query) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean insertRegist(String tableName, String email, String password) {
        //create query
        String query = "insert into " + tableName + "(email , password) values ('" + email + "', MD5('" + password + "'))";
        System.out.println("Query = " + query);
        try {
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Query TRUE");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet selectAllRole (String tableName1, String tableName2, String tableName3, String age, String type_drug) throws SQLException {
        String query = "select "+ tableName3 + ".id, " + tableName1 + "." + age + ", " + tableName2 + "." + type_drug +
                " from " + tableName1 + ", " + tableName2 + ", " + tableName3 +
                " where " + tableName1 + ".id_pasien = " + tableName3 + ".id_pasien AND " + tableName2 + ".id_drug = " + tableName3 + ".id_drug" +
                " ORDER BY relation.id";
        System.out.println(query);
        return statement.executeQuery(query);


    }
}


