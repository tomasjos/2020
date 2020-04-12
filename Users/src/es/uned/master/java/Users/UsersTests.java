/**
 * TESTS DEL MÓDULO DE SERVICIOS
 *
 * Es una clase muy molona, porque cantará las liadas cuando toques mi código!!! Mira el control de versiones, esto
 * estaba funcionando, so destroyer.
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=XyxfCQG1t4w">Get Thy Bearings!!!!</a>
 *
 */


package es.uned.master.java.Users;

import es.uned.master.java.Users.DAOs.PermisoDAO;
import es.uned.master.java.Users.DTOs.PermisosDTO;
import es.uned.master.java.Users.DTOs.RolesDTO;
import es.uned.master.java.Users.DTOs.UsuarioDTO;
import es.uned.master.java.Users.Schema.NodosEntities;
import es.uned.master.java.Users.Services.SetUpService;
import es.uned.master.java.Users.Services.UserServices;
import es.uned.master.java.Utils.IOrdenCampos;
import es.uned.master.java.Utils.Utilidades;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Optional;

class UsersTests {
    static UserServices serv;
    static SetUpService initDb;
    @org.junit.jupiter.api.BeforeAll
    /**
     *  BEFOREALL INSTANCIA LAS CLASES NECESARIAS PAR A LOS SÚPER TESTS, SetUpService y UserServices.
     *  Dónde SetUpService es la clase que inicia la database como el Señor de todos los coders manda.
     */
    static void setUp() {
        //El meollo
        serv = new UserServices();
        //El inicializador de la db
        initDb = new SetUpService();
    }

    @org.junit.jupiter.api.AfterEach
    /**
     * AfterEach nada, pero por si a-K.
     */
    void tearDown() {
    }

    @org.junit.jupiter.api.Nested
    /**
     * Eh!! de nada, te monto la db by the face.
     * TODO -> Meterlo la db en un contenedor DOCKER
     */
    @DisplayName("INICIALIZAR LA BASE DE DATOS")
    class preparacionDbUsers {
        @org.junit.jupiter.api.Test
        @DisplayName("Generación de nodos y las relaciones de usuario")
        /**
         * Thnks to Peter Fight tienes una db de Usuarios fenomenal!!
         */
        void initUsersDb() {
            try {
                initDb.InitUsersDb();
                assert(true);
            } catch (Exception e) {
                assert(false);
            }
        }

        @DisplayName("Eliminacilón de nodos y las relaciones de usuarios")
        @org.junit.jupiter.api.Test
        /**
         * Que quieres limpiar tu db... No problemO, thnks to Peter Fight!!!
         */
        void clearUsersDb() {
            try {
                initDb.ClearUsersDb();
                assert(true);
            } catch (Exception e) {
                assert(false);
            }
        }

        @DisplayName("Get Permiso Entity")
        @org.junit.jupiter.api.Test
        /**
         * @deprecated Antes de montar el UserServices servía para testar las cosicas.
         * Cualquier code-destroyer puede añadir aquí lo que le plazca, pero no está en el contexto adecuado.
         */
        void GetPermiso() {
            try {
                PermisoDAO p = new PermisoDAO();
                Optional<PermisosDTO> permiso = p.get(p.getAll().get(0).get_internalId());
                assert(true);
            } catch (Exception e) {
                assert(false);
            }
        }
    }


    @org.junit.jupiter.api.Nested
    @DisplayName("TESTS SERVICES")
    /**
     * Estos métodos son los que usaran los de arriba (los de la capa de presentación), así que deben funcionar todos y cada uno
     * porque si no irán a por mí!!!!
     */
    class ServicesMetodos {
        @DisplayName("AltaUser")
        @org.junit.jupiter.api.Test
        /**
         * @see #AltaUser()
         */
        void AltaUser() {
            try {
                //Me invento un user y lo doy de alta
                UsuarioDTO user = new UsuarioDTO("mail@mail.com",true,"123456", null);
                serv.AltaUser(user);
                assert(true);
            } catch (Exception e) {
                assert(false);
            }
        }
        @DisplayName("ModificaUser")
        @org.junit.jupiter.api.Test
        /**
         * @see #ModificaUser
         */
        void ModificaUser(){
            try{
                //Me invento un user
                UsuarioDTO user = new UsuarioDTO("mail7@mail.com",true,"123456", null);
                //Lo doy de alta
                serv.AltaUser(user);
                user = serv.getUserByEmail("mail7@mail.com");
                //Le cambio el passs
                user.setPass("modificado7");
                serv.ModificaUser(user);
                String passModified = serv.getUserByEmail("mail7@mail.com").getPassHash();
                String passHash = Utilidades.getMd5("modificado7");
                //Compruebo que el hash es correcto
                assert(passModified.equals(passHash));
            }
            catch (Exception e)
            {
                assert(false);
            }
        }
        @DisplayName("getAll")
        @org.junit.jupiter.api.Test
        /**
         * @see #getAll
         */
        void getAll(){
            List<UsuarioDTO> users = serv.getAll();
            assert(users.iterator().hasNext() == true);
        }
        @DisplayName("getUserById")
        @org.junit.jupiter.api.Test
        /**
         * @see #getUserById
         */
        void getUserById(){
            List<UsuarioDTO> users = serv.getAll();
            //Pillo el primer user, para que exista, esta db mete los ids internos como le sale de los huevos -> :-
            UsuarioDTO user = serv.getUserById(users.get(0).getId());
            assert(user != null);
        }
        @DisplayName("countAllUsers")
        @org.junit.jupiter.api.Test
        /**
         * @see #countAllUsers()
         */
        void countAllUsers(){
            assert(serv.countAllUsers() > 0);
        }

        @DisplayName("getAllUsersPaged")
        @org.junit.jupiter.api.Test
        /**
         * @see #getAllUsersPaged()
         */
        void getAllUsersPaged(){
            assert(serv.getAllUsersPaged(NodosEntities.Nodos.USUARIO.name(), 1, 0, IOrdenCampos.ordenCampo.asc).iterator().hasNext());
        }

        @DisplayName("borrarUsuario")
        @org.junit.jupiter.api.Test
        /**
         * @see #borrarUsuario
         */
        void borrarUsuario(){
            UsuarioDTO user = new UsuarioDTO("mail@mail.com",true,"123456", null);
            serv.AltaUser(user);
            //Creo user y me aseguro de que existe.
            user = serv.getUserByEmail("mail@mail.com");
            assert(serv.borrarUsuario(user));
        }
        @DisplayName("getAllRoles")
        @org.junit.jupiter.api.Test
        /**
         * @see #getAllRoles()
         */
        void getAllRoles(){
            assert(serv.getAllRoles().iterator().hasNext() == true);
        }
        @DisplayName("getRolById")
        @org.junit.jupiter.api.Test
        /**
         * @see #getRolById()
         */
        void getRolById(){
            assert(serv.getRolById(serv.getAllRoles().get(0).get_internalId()) != null);
        }
        @DisplayName("getAllRolesByCriterio")
        @org.junit.jupiter.api.Test
        /**
         * @see #getAllRolesByCriterio()
         */
        void getAllRolesByCriterio(){
            assert(serv.getAllRolesByCriterio("Nombre", "Administrador").iterator().hasNext());
        }
        @DisplayName("countAllRoles")
        @org.junit.jupiter.api.Test
        /**
         * @see #countAllRoles()
         */
        void countAllRoles(){
            assert(serv.countAllRoles() > 0);
        }
        @DisplayName("getAllPermisos")
        @org.junit.jupiter.api.Test
        /**
         * @see #getAllPermisos()
         */
        void getAllPermisos(){
            assert(serv.getAllPermisos().iterator().hasNext());
        }
        @DisplayName("getPermisoById")
        @org.junit.jupiter.api.Test
        /**
         * @see #getPermisoById()
         */
        void getPermisoById(){
            assert(serv.getPermisoById(serv.getAllPermisos().get(0).get_internalId()) != null);
        }
        @DisplayName("getAllPermisosByCriterio")
        @org.junit.jupiter.api.Test
        /**
         * @see #getAllPermisosByCriterio()
         */
        void getAllPermisosByCriterio(){
            assert(serv.getAllPermisosByCriterio("Nombre",serv.getAllPermisos().get(0).getNombre()) != null);
        }
        @DisplayName("countAllPermisos")
        @org.junit.jupiter.api.Test
        /**
         * @see #countAllPermisos()
         */
        void countAllPermisos(){
            assert(serv.countAllPermisos() > 0);
        }
        @DisplayName("getAllPermisosPaged")
        @org.junit.jupiter.api.Test
        /**
         * @see #getAllPermisosPaged()
         */
        void getAllPermisosPaged(){
            assert(serv.getAllPermisosPaged(NodosEntities.Nodos.PERMISO.name(), 1, 0, IOrdenCampos.ordenCampo.asc).iterator().hasNext());
        }
        @DisplayName("addPermisoToRol")
        @org.junit.jupiter.api.Test
        /**
         * @see #addPermisoToRol()
         */
        void addPermisoToRol(){
            RolesDTO rol = serv.getAllRoles().get(0);
            PermisosDTO perm = serv.getAllPermisos().get(0);
            //Pillo el primer permiso, para que exista, esta db mete los ids internos como le sale de los huevos -> :-
            serv.addPermisoToRol(rol,perm);
            //Si peta será un assertfalse en toda regla
        }
        @DisplayName("removePermiso")
        @org.junit.jupiter.api.Test
        /**
         * @see #removePermiso()
         */
        void removePermiso(){
            RolesDTO rol = serv.getAllRoles().get(0);
            PermisosDTO perm = serv.getAllPermisos().get(0);
            //Pillo el primer permiso, para que exista, esta db mete los ids internos como le sale de los huevos -> :-
            serv.removePermiso(rol,perm);
            //Si peta será un assertfalse en toda regla
        }
        @DisplayName("addRolToUser")
        @org.junit.jupiter.api.Test
        /**
         * @see #addRolToUser()
         */
        void addRolToUser(){
            RolesDTO rol = serv.getAllRoles().get(0);
            UsuarioDTO user = serv.getAll().get(0);
            //Si pones la T de ToUser delante de Rol, sale la palabra TRol. Caramba!
            serv.addRolToUser(user,rol);
            //Si peta será un assertfalse en toda regla. Qué espabilen!!!
        }
        @DisplayName("removeRolToUser")
        @org.junit.jupiter.api.Test
        /**
         * @see #removeRolToUser()
         */
        void removeRolToUser(){
            RolesDTO rol = serv.getAllRoles().get(0);
            UsuarioDTO user = serv.getAll().get(0);
            //Aquí se ve que controlo, meto relaciones, las quito, si quieres las vuelvo a poner... Semos los mejores.
            serv.removeRolToUser(user,rol);
            //Si peta será un assertfalse en toda regla
        }
    }

    @org.junit.jupiter.api.AfterAll
    /**
     * Si no quieres que te llene tus databases de neo4j con cosas raras, descomenta este método y tan contentos...
     */
    static void clearUsersDb() {

        //new SetUpServices().ClearUsersDb();
    }


    /**
     * THAT'S ALL FOLKS
     */
}