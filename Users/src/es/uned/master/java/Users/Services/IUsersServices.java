/**
 * INTERFAZ DE LOS SERVICES QUE LE FACILITAN A LOS DE ARRIBA (LOS DE LA CAPA DE PRESENTACIÓN) UN ACCESO DE TERCIOPELO A LA DB
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.Services;

import es.uned.master.java.Users.DTOs.PermisosDTO;
import es.uned.master.java.Users.DTOs.RolesDTO;
import es.uned.master.java.Users.DTOs.UsuarioDTO;
import es.uned.master.java.Utils.IOrdenCampos;

import java.util.List;

public interface IUsersServices {
    /**
     * @see #AltaUser(UsuarioDTO)
     */
    boolean AltaUser(UsuarioDTO user);
    /**
     * @see #AltaUserAsync(UsuarioDTO)
     */
    void AltaUserAsync(UsuarioDTO user);
    /**
     * @see #getUserByEmail(String mail)
     */
    UsuarioDTO getUserByEmail(String mail);
    /**
     * @see #ModificaUser(UsuarioDTO usuarioModificado)
     */
    boolean ModificaUser(UsuarioDTO usuarioModificado);
    /**
     * @see #ModificaUserAsync(UsuarioDTO usuarioModificado)
     */
    void ModificaUserAsync(UsuarioDTO usuarioModificado);
    /**
     * @see #getUserById(long id)
     */
    UsuarioDTO getUserById(long id);
    /**
     * @see #getAllUsersPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo ordenCampo)
     */
    List<UsuarioDTO> getAllUsersPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo ordenCampo);
    /**
     * @see #getAll()
     */
    List<UsuarioDTO> getAll();
    /**
     * @see #getAllByCriterio(String campo, Object valorCampo)
     */
    List<UsuarioDTO> getAllByCriterio(String campo, Object valorCampo);
    /**
     * @see #countAllUsers()
     */
    int countAllUsers();
    /**
     * @see #borrarUsuario(Object Usuario)
     */
    boolean borrarUsuario(Object Usuario);
    /**
     * @see #borrarUsuarioAsync(Object Usuario)
     */
    void borrarUsuarioAsync(Object Usuario);
    /**
     * @see #getRolById(long id)
     */
    RolesDTO getRolById(long id);
    /**
     * @see #getAllRoles()
     */
    List<RolesDTO> getAllRoles();
    /**
     * @see #getAllRolesByCriterio(String campo, Object valorCampo)
     */
    List<RolesDTO> getAllRolesByCriterio(String campo, Object valorCampo);
    /**
     * @see #countAllRoles()
     */
    int countAllRoles();
    /**
     * @see #getPermisoById(long id)
     */
    PermisosDTO getPermisoById(long id);
    /**
     * @see #getAllPermisos()
     */
    List<PermisosDTO> getAllPermisos();
    /**
     * @see #getAllPermisosByCriterio(String campo, Object valorCampo)
     */
    List<PermisosDTO> getAllPermisosByCriterio(String campo, Object valorCampo);
    /**
     * @see #countAllPermisos()
     */
    int countAllPermisos();
    /**
     * @see #getAllPermisosPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo ordenCampo)
     */
    List<PermisosDTO> getAllPermisosPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo ordenCampo);
    /**
     * @see #addPermisoToRol(RolesDTO rol, PermisosDTO permiso)
     */
    void addPermisoToRol(RolesDTO rol, PermisosDTO permiso);
    /**
     * @see #removePermiso(RolesDTO rol, PermisosDTO permiso
     */
    void removePermiso(RolesDTO rol, PermisosDTO permiso);
    /**
     * @see #addRolToUser(UsuarioDTO user, RolesDTO rol)
     */
    void addRolToUser(UsuarioDTO user, RolesDTO rol);
    /**
     * @see #removeRolToUser(UsuarioDTO user, RolesDTO rol)
     */
    void removeRolToUser(UsuarioDTO user, RolesDTO rol);
    /**
     * @see #addPermisoToRolAsync(RolesDTO rol, PermisosDTO permiso)
     */
    void addPermisoToRolAsync(RolesDTO rol, PermisosDTO permiso);
    /**
     * @see #removePermisoAsync(RolesDTO rol, PermisosDTO permiso)
     */
    void removePermisoAsync(RolesDTO rol, PermisosDTO permiso);
    /**
     * @see #addRolToUserAsync(UsuarioDTO user, RolesDTO rol)
     */
    void addRolToUserAsync(UsuarioDTO user, RolesDTO rol);
    /**
     * @see #removeRolToUserAsync(UsuarioDTO user, RolesDTO rol)
     */
    void removeRolToUserAsync(UsuarioDTO user, RolesDTO rol);
}
