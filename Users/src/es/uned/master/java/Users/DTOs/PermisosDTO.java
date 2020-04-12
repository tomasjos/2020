/**
 * DTO para los nodos de Permisos, equivalentes a los de Roles
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.DTOs;

import org.neo4j.driver.internal.shaded.reactor.util.annotation.Nullable;


public class PermisosDTO extends EnumerableEntity {
    public PermisosDTO(String nombre, int orden, @Nullable Integer internalId) {
        super(nombre, orden, internalId);
    }
}
