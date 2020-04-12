/**
 * SERVICES QUE LE FACILITAN A LOS DE ARRIBA (LOS DE LA CAPA DE PRESENTACIÓN) UN ACCESO DE TERCIOPELO A LA DB
 * EN REALIDAD... ESTA CLASE ABRE LAS PUERTAS DEL AVERNO!!!!
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.Services;


import es.uned.master.java.Users.DAOs.PermisoDAO;
import es.uned.master.java.Users.DAOs.RolDAO;
import es.uned.master.java.Users.DAOs.UserDAO;
import es.uned.master.java.Users.DTOs.PermisosDTO;
import es.uned.master.java.Users.DTOs.RolesDTO;
import es.uned.master.java.Users.DTOs.UsuarioDTO;
import es.uned.master.java.Utils.IOrdenCampos;

import java.util.List;

public class UserServices implements IUsersServices {

    /**
     * USUARIOS
     * @param user
     * @return
     */

    /**
     * @param user El userDTO a dar de alta
     * @return devuelve si se ha insertado. En principio si no se inserta debería explotar...
     * @throws es.uned.master.java.covid.Excepciones.GenericException si revienta algo
     */
    public boolean AltaUser(UsuarioDTO user)
    {
        new UserDAO().save(user);
        return true;
    }
    /**
     * Método asíncrono. No devuelve más que la esperanza en que todo vaya bien.
     * Con los hilos nunca se sabe...
     * @param user El userDTO a dar de alta de forma asíncrona
     */
    public void AltaUserAsync(UsuarioDTO user)
    {
        new Thread(new Runnable() {
            public void run() {
                new UserDAO().save(user);
            }
        }).start();
    }

    /**
     * @param usuarioModificado La clave está en el ID interno. Es el único campo que no se modifica y que sirve para
     *                          identificar a qué usuario le metemos mano
     * @return verdadero si todo va bien. Si no debería explotar.
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo falla.
     */
    public boolean ModificaUser(UsuarioDTO usuarioModificado)
    {
        new UserDAO().update(usuarioModificado);
        return true;
    }

    /**
     * Método asíncrono
     * @see #ModificaUser(UsuarioDTO)
     * @param usuarioModificado el usuario a modificar, el identificador interno no se modifica, sirve para determinar
     *                          qué usuario modificamos
     */
    public void ModificaUserAsync(UsuarioDTO usuarioModificado)
    {
        new Thread(new Runnable() {
            public void run() {
                new UserDAO().update(usuarioModificado);
            }
        }).start();
    }

    /**
     * Devuelve el usuario
     * @param id la estrella de la muerte. Es long, por si A-K.
     * @return el userDTO deseado.
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public UsuarioDTO getUserById(long id)
    {
        return new UserDAO().get(id).get();
    }

    /**
     * Devuelve un usuario mediante su mail
     * @param mail un platillo volante
     * @return el userDTO deseado
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public UsuarioDTO getUserByEmail(String mail)
    {
        return new UserDAO().getUserByEmail(mail).get();
    }

    /**
     * @return Devuelve todos lo userDTO a saco
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<UsuarioDTO> getAll(){
        return new UserDAO().getAll();
    }

    /**
     * Método para buscar en base a criterios
     * @param campo el campo a buscar (Nombre, email...)
     * @param valorCampo el valor del campo a buscar
     * @return lo buscado
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<UsuarioDTO> getAllByCriterio(String campo, Object valorCampo){
        return new UserDAO().getAllByCriterio(campo, valorCampo);
    }

    /**
     * @return el total de registros en un entero
     */
    public int countAllUsers(){
        return new UserDAO().countAll();
    }

    /**
     * Devuelve los usuarios paginados
     * @param entidad la entidad que será el name del Enum Usuarios
     * @param ResultadosPorPagina Cuántas cosicas quieres obtener
     * @param Pagina el núm de página a mostrar
     * @param ordenCampo es el Enum de la interfaz (asc: ascendente, desc: de lado sin cafeína
     * @return algo devolverá, digo yo... Amos
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<UsuarioDTO> getAllUsersPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo ordenCampo){
        return new UserDAO().getAllPaged(entidad, ResultadosPorPagina, Pagina, ordenCampo);
    }

    /**
     * Aquí borro un user, a coste 0
     * @param Usuario una COSA con destello Humano
     * @return la COSA
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public boolean borrarUsuario(Object Usuario){
        new UserDAO().delete(Usuario);
        return true;
    }

    /**
     * Lo de arriba pero asíncrono
     * @see #borrarUsuario(Object)
     * @param Usuario
     */
    public void borrarUsuarioAsync(Object Usuario){
        new Thread(new Runnable() {
            public void run() {
                new UserDAO().delete(Usuario);
            }
        }).start();
    }



    /**
     * ROLES... Esto es lo que hace el viento cuando quiere jugar.
     */
    /**
     * Te devuelvo un rol si me pasas un id
     * @param id el id interno
     * @return lo que pides, si no sabes, no pidas
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public RolesDTO getRolById(long id)
    {
        return new RolDAO().get(id).get();
    }

    /**
     * Todos los roles a saco
     * @return los roles a saco
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<RolesDTO> getAllRoles(){
        return new RolDAO().getAll();
    }

    /**
     * Busca roles en base a un criterio
     * @param campo el campo (en este caso el ENum.name() de ROL
     * @param valorCampo el valor a buscar
     * @return lo que pides
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<RolesDTO> getAllRolesByCriterio(String campo, Object valorCampo){
        return new RolDAO().getAllByCriterio(campo, valorCampo);
    }

    /**
     * Quieres saber cuántos roles hay, llamar a este método es una forma
     * @return el total de roles existentes.
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public int countAllRoles(){
        return new RolDAO().countAll();
    }

    /**
     * Los roles paginados
     * @param entidad el enum.name() de ROL
     * @param ResultadosPorPagina cuántos roles por página
     * @param Pagina la página a buscar
     * @param ordenCampo el enum, ascendente o descendente
     * @return lo que pides, si no no sabes no molestes
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<RolesDTO> getAllRolesPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo ordenCampo){
        return new RolDAO().getAllPaged(entidad, ResultadosPorPagina, Pagina, ordenCampo);
    }

    /**
     * PERMISOS. Son una forma pija de llamar a las vacaciones "inteligentes".
     */
    /**
     * Te doy el permiso si me das el id interno. Palabrita
     * @param id el id interno
     * @return lo que pides
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public PermisosDTO getPermisoById(long id)
    {
        return new PermisoDAO().get(id).get();
    }

    /**
     * Todos los permisos a saco
     * @return lo dicho
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<PermisosDTO> getAllPermisos(){
        return new PermisoDAO().getAll();
    }

    /**
     * Permisos buscados
     * @param campo el campo a buscar, el Enum.name de Permiso
     * @param valorCampo el valor a buscar
     * @return lo que pides
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<PermisosDTO> getAllPermisosByCriterio(String campo, Object valorCampo){
        return new PermisoDAO().getAllByCriterio(campo, valorCampo);
    }

    /**
     * El total de permisos existentes
     * @return lo dicho
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public int countAllPermisos(){
        return new PermisoDAO().countAll();
    }

    /**
     * Permisos paginados
     * @param entidad el Enum.name() de Permiso
     * @param ResultadosPorPagina ¿En serio no lo sabes?
     * @param Pagina la página actual
     * @param ordenCampo Asc/Desc del enum
     * @return lo que pides
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<PermisosDTO> getAllPermisosPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo ordenCampo){
        return new PermisoDAO().getAllPaged(entidad, ResultadosPorPagina, Pagina, ordenCampo);
    }


    /**
     * Aquí te meto el permiso al rol deseado
     * @param rol From
     * @param permiso TO
     */
    public void addPermisoToRol(RolesDTO rol, PermisosDTO permiso)
    {
        new RolDAO().AddPermiso(rol,permiso);
    }

    /**
     * Te quito el permiso al rol que me digas
     * @param rol Origen
     * @param permiso Fin
     */
    public void removePermiso(RolesDTO rol, PermisosDTO permiso)
    {
        new RolDAO().RemovePermiso(rol,permiso);
    }

    /**
     * Le meto un rol al user que me digas
     * @param user Al que le meto
     * @param rol Al que le doy
     */
    public void addRolToUser(UsuarioDTO user, RolesDTO rol)
    {
        new UserDAO().AddRol(user,rol);
    }

    /**
     * Lo de arriba pero al revés
     * @see #addRolToUser(UsuarioDTO, RolesDTO) 
     * @param user venía de aquí
     * @param rol iba para allí
     */
    public void removeRolToUser(UsuarioDTO user, RolesDTO rol)
    {
        new UserDAO().RemoveRol(user,rol);
    }

    /**
     * En async
     * @see #addPermisoToRol(RolesDTO, PermisosDTO) 
     * @param rol
     * @param permiso
     */
    public void addPermisoToRolAsync(RolesDTO rol, PermisosDTO permiso)
    {
        new Thread(new Runnable() {
            public void run() {
                new RolDAO().AddPermiso(rol, permiso);
            }
        }).start();
    }

    /**
     * EN async
     * @see #removePermiso(RolesDTO, PermisosDTO) 
     * @param rol
     * @param permiso
     */
    public void removePermisoAsync(RolesDTO rol, PermisosDTO permiso)
    {
        new Thread(new Runnable() {
            public void run() {
                new RolDAO().RemovePermiso(rol,permiso);
            }
        }).start();
    }

    /**
     * En Async
     * @see #addRolToUser(UsuarioDTO, RolesDTO) 
     * @param user
     * @param rol
     */
    public void addRolToUserAsync(UsuarioDTO user, RolesDTO rol)
    {
        new Thread(new Runnable() {
            public void run() {
              new UserDAO().AddRol(user,rol);
            }
        }).start();
    }

    /**
     * En async
     * @see #removeRolToUser(UsuarioDTO, RolesDTO)
     * @param user
     * @param rol
     */
    public void removeRolToUserAsync(UsuarioDTO user, RolesDTO rol)
    {
        new Thread(new Runnable() {
            public void run() {
                new UserDAO().RemoveRol(user,rol);
            }
        }).start();
    }
}
