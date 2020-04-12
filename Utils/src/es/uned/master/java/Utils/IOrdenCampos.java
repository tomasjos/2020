/**
 * INTERFAZ CON ENUM DE ORDEN CAMPOS. Si los tengo en enum, evito el "CypherInjection", salvo las variables que procesa
 * el propio Cypher, nadie toca mis consultas!!!
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Utils;

public interface IOrdenCampos {
    public enum ordenCampo{
        asc,
        desc
    }
}
