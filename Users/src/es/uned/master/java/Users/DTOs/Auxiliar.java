/**
 * CLASE AUXILIAR PARA LOS DTOs QUE INCLUYE UN COMPARATOR, PARA PODER ORDENAR LOS DTOsar de variable simple)
 *
 * Por el momento sólo lo he usado a la hora de generar la estructura básica de la base de datos, sé que en el
 * enum, le meto índice 1º a Admin, y necesitaba respetarlo para meterle el resto de permisos a ése rol y no al de Anónimo
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.DTOs;

import java.util.Comparator;

public class Auxiliar {

    // Comparer para ordenar roles, mahormente, pero sirve para cualquier Enumerable Entity futura
    public static class EntityEnumComparer implements Comparator<EnumerableEntity>
    {
        public int compare(EnumerableEntity m1, EnumerableEntity m2)
        {
            if (m1._i < m2._i) return -1;
            if (m1._i > m2._i) return 1;
            else return 0;
        }
    }
}
