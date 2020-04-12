/**
 * IMPLEMENTACIÓN PROPIA DE LAS EXCEPCIONES
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.covid.Excepciones;

public class GenericException extends AbstractExcepciones {
    public GenericException(TipoExcepcion tipo)
    {
        super(
                getMensajeError(tipo) +
                        "Localizado en: " + Thread.currentThread().getStackTrace()[0].toString()
        );
    }
}
