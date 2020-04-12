/**
 * DTO para los nodos de Roles, equivalentes a los de Permisos
 *
 * A diferencia de los Permisos, tienen permisos vinculados, de ahí que añada la variable del listado
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */


package es.uned.master.java.Users.DTOs;

import org.neo4j.driver.internal.shaded.reactor.util.annotation.Nullable;

import java.util.List;

//Era un enum, pero no lo podía ordenar, así que lo convierto en clase e implemento el COmparable
public class RolesDTO extends EnumerableEntity {
    /**
     * En el constructor le paso el listado de permisos asociados, en caso de que existan
     * @param nombre
     * @param orden
     * @param internalId
     * @param permisos
     */
    public RolesDTO(String nombre, int orden, @Nullable Integer internalId, List<PermisosDTO> permisos) {

        super(nombre, orden, internalId);
        this.permisos = permisos;
    }

    public List<PermisosDTO> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisosDTO> permisos) {
        this.permisos = permisos;
    }

    List<PermisosDTO> permisos;
}
