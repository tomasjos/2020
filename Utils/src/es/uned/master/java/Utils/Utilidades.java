/**
 * CLASE CON UTILIDADES QUE PUEDEN SER REQUERIDAS EN DIFERENTES MÓDULOS DEL PROYECTO
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */


package es.uned.master.java.Utils;

import es.uned.master.java.covid.Excepciones.AbstractExcepciones;
import es.uned.master.java.covid.Excepciones.GenericException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {
    /**
     * Método para obtener el hash MD5 para los passwords
     *
     * @param pass Password del que sacar el hash MD5
     * @return String con el hash
     * @see <a href="https://www.geeksforgeeks.org/md5-hash-in-java/">Copiado de aquí :)</a>
     * @throws NoSuchAlgorithmException Si no lo meto casca. Por lo demás, si no encuentra la instancia MD5, apaga y vámonos
     */
    public static String getMd5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pass.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (Exception e)
        {
            throw new GenericException(AbstractExcepciones.TipoExcepcion.ERROR_INESPERADO);
        }
    }

    /**
     * INDICA SI UNA CADENA ES NULA O ESTÁ VACÍA
     * @param cadena
     * @return
     */
    public static boolean cadenaNulaEmpty(String cadena)
    {
        boolean resultado = false;
        if(cadena == null || cadena.trim().length() == 0)
        {
            resultado = true;
        }
        return resultado;
    }


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * REGEX QUE INDICA SI UN EMAIL ES VÁLIDO O ES UNA CADENA CHUNGA SIN SENTIDO
     * @param mail
     * @return
     */
    public static boolean esMailValido(String mail) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(mail);
        return matcher.find();
    }
}
