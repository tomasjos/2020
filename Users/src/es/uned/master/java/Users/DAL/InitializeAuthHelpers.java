/**
 * CLASE QUE SE EMPLEA AL INICIAR LA DATABASE
 * Devuelve todos los roles ordenados, y los permisos, también ordenados
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */


package es.uned.master.java.Users.DAL;

import es.uned.master.java.Users.DTOs.Auxiliar;
import es.uned.master.java.Users.DTOs.PermisosDTO;
import es.uned.master.java.Users.DTOs.RolesDTO;
import es.uned.master.java.Users.Schema.NodosEntities;

import java.util.ArrayList;
import java.util.Collections;

public class InitializeAuthHelpers {
    /**
     * ROLES!!!
     * @return
     */
    public static ArrayList<RolesDTO> getOrderedRolesDisponibles() {
        RolesDisponibles = new ArrayList<RolesDTO>();
        for(NodosEntities.Roles rol : NodosEntities.Roles.values())
        {
            RolesDisponibles.add(new RolesDTO(rol.name(), rol.idx, null, null));
        }
        Collections.sort(RolesDisponibles, new Auxiliar.EntityEnumComparer());
        return RolesDisponibles;
    }
    private static ArrayList<RolesDTO> RolesDisponibles;//ArrayList para que vengan ordenados


    /**
     * PERMISOS!!!
     * @return
     */
    public static ArrayList<PermisosDTO> getOrderedPermisosDisponibles() {
        PermisosDisponibles = new ArrayList<PermisosDTO>();
        for(NodosEntities.Permisos perm : NodosEntities.Permisos.values())
        {
            PermisosDisponibles.add(new PermisosDTO(perm.name(), perm.idx, null));
        }
        Collections.sort(PermisosDisponibles, new Auxiliar.EntityEnumComparer());
        return PermisosDisponibles;
    }
    private static ArrayList<PermisosDTO> PermisosDisponibles;//ArrayList para que vengan ordenados
}
