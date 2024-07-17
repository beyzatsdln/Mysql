package JDBI;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.StatementException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7719569";
        String kullaniciadi = "sql7719569";
        String sifre = "tNpd8ESyFM";


        Jdbi jdbi = Jdbi.create(url, kullaniciadi, sifre);
        try (Handle handle = jdbi.open()) {
            handle.execute("CREATE TABLE IF NOT EXISTS `sql7719569`.`ad_soyad` (`tckn` VARCHAR(11) NOT NULL,`ad` VARCHAR(45) NOT NULL,`soyad` VARCHAR(45) NOT NULL,PRIMARY KEY (`tckn`))");

            handle.execute("INSERT INTO ad_soyad (tckn, ad, soyad) VALUES (?, ?, ?)","76567898767", "Ali", "Karaca");
            handle.execute("INSERT INTO ad_soyad (tckn, ad, soyad) VALUES (?, ?, ?)","98567898767", "Beyza", "Kavgaci");
            handle.execute("INSERT INTO ad_soyad (tckn, ad, soyad) VALUES (?, ?, ?)","65567898767", "Asil", "Tok");

            String kayit = String.valueOf(handle.select("SELECT * FROM ad_soyad WHERE ad IN (?,?,?)", "Ali", "Beyza", "Asil")
                    .mapToMap()
                    .list());
            System.out.println("Kayit:" + kayit);

            handle.execute("UPDATE ad_soyad SET soyad = ?  Where ad = ?", "Tasdelen","Beyza");
            System.out.println("Soyad güncellendi");

            handle.execute("DELETE FROM ad_soyad WHERE tckn = ?", "65567898767");
            System.out.println("65567898767 tckn'li kayit silindi");


        } catch (StatementException e) {
            e.printStackTrace();
        }
    }
}

