/**
 * CLASE ABSTRACTA CON LOS TIPOS DE EXCEPCIONES Y UN MÉTODO PARA AL QUE LLAMARÁN LOS CONSTRUCTORES DE LAS CLASES
 * DERIVADAS PARA CONSTRUIR EL MENSAJE DE ERROR
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */


package es.uned.master.java.covid.Excepciones;

public abstract class AbstractExcepciones extends RuntimeException{
    public AbstractExcepciones(String exception) {
    }

    public static enum TipoExcepcion{
        CONSTRUCTOR_NULO,
        ERROR_ARCHIVO,
        ERROR_DB,
        ERROR_INESPERADO,
        CHYPHER_EXCEPTION,
        MODELO_INVALIDO
    }
    protected static String getMensajeError(TipoExcepcion tipo)
    {
        String respuesta = "";
        switch(tipo) {
            case CONSTRUCTOR_NULO:
                respuesta = "\nCONSTRUCTOR VACÍO!!!";
                break;
            case ERROR_ARCHIVO:
                respuesta = "\nEL ARCHIVO NO EXISTE, TÍO!! " +
                        " SI LA NADA NO LA HAY Y AFIRMAS LO CONTRARIO... ERES UN FANTASMA!!!" +
                        " acojonante...";
                break;
            case ERROR_DB:
                respuesta = "\nERROR RELACIONADO CON LA DB";
                break;
            case ERROR_INESPERADO:
                respuesta = "\nERROR INESPERADO, CONSULTE LAS TRAZAS";
                break;
            case CHYPHER_EXCEPTION:
                respuesta = "\nUooooo!!! Cypher Injection!!!!";
                break;
            case MODELO_INVALIDO:
                respuesta = "\nEl modelo facilitado no es válido";
                break;
            default:
                respuesta = "";
                break;
        }
        return respuesta;
    }
}
