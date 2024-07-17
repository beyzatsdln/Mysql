package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatement {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/personelliste";
        String kullaniciadi = "root";
        String sifre = "Aa!123";

        Connection connection = null;
        java.sql.PreparedStatement preparedStatement = null ;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection(url,kullaniciadi,sifre);

          String tablo = "CREATE TABLE ad_soyad (`tckn` VARCHAR(11) NOT NULL,`ad` VARCHAR(45) NOT NULL,`soyad` VARCHAR(45) NOT NULL, PRIMARY KEY (`tckn`))";

          preparedStatement = connection.prepareStatement(tablo);
          preparedStatement.executeUpdate();

            String ekleme = "INSERT INTO ad_soyad (TCKN, ad, soyad) VALUES(?,?,?)" ;
            preparedStatement = (java.sql.PreparedStatement) connection.prepareStatement(ekleme);
            preparedStatement.setString(1, "87898965678");
            preparedStatement.setString(2, "Beyzanur");
            preparedStatement.setString(3, "kavgaci");
            preparedStatement.addBatch();

            preparedStatement.setString(1, "98787656786");
            preparedStatement.setString(2, "Asli");
            preparedStatement.setString(3, "Tok");
            preparedStatement.addBatch();

            preparedStatement.setString(1, "65898965678");
            preparedStatement.setString(2, "Nuriye");
            preparedStatement.setString(3, "Karaca");

            preparedStatement.addBatch();

            preparedStatement.executeBatch();

            System.out.println("veri eklendi");

            String select = "SELECT * FROM ad_soyad";
            preparedStatement = connection.prepareStatement(select);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String TCKN = resultSet.getString("TCKN");
                String ad = resultSet.getString("ad");
                String soyad = resultSet.getString("soyad");

                System.out.println("TCKN: " + TCKN + ", ad: " + ad + ", soyad: " + soyad);
            }

            String update = "UPDATE ad_soyad SET soyad = ? Where ad = ?";
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, "Nur");
            preparedStatement.setString(2, "Asli");
            preparedStatement.executeUpdate();

            System.out.println("soyad güncellendi");

            String delete = "DELETE FROM ad_soyad Where ad = ?";
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, "Beyzanur");
            preparedStatement.executeUpdate();

            System.out.println("veri silindi");

        } catch (SQLException e) {
        e.printStackTrace();

    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
}



