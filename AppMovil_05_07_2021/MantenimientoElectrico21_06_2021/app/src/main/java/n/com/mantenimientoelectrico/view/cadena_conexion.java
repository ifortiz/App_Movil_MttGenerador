package n.com.mantenimientoelectrico.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



    public class cadena_conexion {



        public String driver, url, ip, bd, usr, pass;
        public Connection conexion;


        public cadena_conexion(String ip, String bd, String usr, String pass) {
            driver = "oracle.jdbc.driver.OracleDriver";

            this.bd = bd;
            this.usr = usr;
            this.pass = pass;

            url = new String("jdbc:oracle:thin:@" + ip + ":1521:" + bd);

            try {
                Class.forName(driver).newInstance();

                conexion = DriverManager.getConnection(url, usr, pass);
                // revisan el logcat para ver el mensaje
                System.out.println("Conexion a Base de Datos " + bd + " Exitosa");

            } catch (Exception exc) {
                System.out.println("Error al tratar de abrir la base de Datos "
                        + bd + " : " + exc);
            }
        }

        public Connection getConexion() {
            return conexion;
        }

        public Connection CerrarConexion() throws SQLException {
            conexion.close();
            conexion = null;
            return conexion;
        }


    }


