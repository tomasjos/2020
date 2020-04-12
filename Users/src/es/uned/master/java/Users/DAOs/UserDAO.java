/**
 * DATA TRANSFER OBJECTS PARA INTERACTUAR CON LA DB, DEVUELVE DTOS, DE UN NIVEL DE CAPA SUPERIOR, SON INTERMEDIARIOS
 *
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.DAOs;

import es.uned.master.java.Users.DAL.*;
import es.uned.master.java.Users.DTOs.RolesDTO;
import es.uned.master.java.Users.DTOs.UsuarioDTO;
import es.uned.master.java.Users.Schema.NodosEntities;
import es.uned.master.java.Users.Schema.Relaciones;
import es.uned.master.java.Utils.IOrdenCampos;
import es.uned.master.java.covid.DbContext.Conexion;
import es.uned.master.java.covid.DbContext.Dao;
import es.uned.master.java.covid.Excepciones.AbstractExcepciones;
import es.uned.master.java.covid.Excepciones.GenericException;
import org.neo4j.driver.Record;
import org.neo4j.driver.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDAO implements Dao {
    /**
     * DEVUELVE EL USUARIO
     * @param id El identificador interno
     */
    @Override
    public Optional<UsuarioDTO> get(long id) {
        GetSelectSingle consulta = new GetSelectSingle(id, NodosEntities.Nodos.USUARIO.name());
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());


        String nombre;
        int orden;
        int internalID;

        Optional<UsuarioDTO> respuesta = null;

        //Aquí sólo esparamos un resultado (le pasamos un id)
        Record res = result.get(0);

        String Email;
        boolean EmailConfirmado;
        String PassHash;
        Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();
        Email = map.get("Email").toString();
        EmailConfirmado = Boolean.valueOf(map.get("EmailConfirmado").toString());
        PassHash = map.get("PassHash").toString();

        String cadenaConInternalId = res.values().get(0).toString();
        cadenaConInternalId = cadenaConInternalId.replaceAll(".*<","");
        cadenaConInternalId = cadenaConInternalId.replace(">","");
        internalID = Integer.parseInt(cadenaConInternalId);

        respuesta = Optional.of(new UsuarioDTO(internalID, Email, EmailConfirmado, PassHash, new RolDAO().getAllByUsuarioId(internalID)));

        return respuesta;
    }

    /**
     * DEVUELVE EL USUARIO EN BASE AL EMAIL
     * @param email
     * @return
     */
    public Optional<UsuarioDTO> getUserByEmail(String email) {
        GetByEmail consulta = new GetByEmail(email);
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());


        String nombre;
        int orden;
        int internalID;

        Optional<UsuarioDTO> respuesta = null;

        //Aquí sólo esparamos un resultado (le pasamos un id)
        Record res = result.get(0);

        String Email;
        boolean EmailConfirmado;
        String PassHash;
        Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();
        Email = map.get("Email").toString();
        EmailConfirmado = Boolean.valueOf(map.get("EmailConfirmado").toString());
        PassHash = map.get("PassHash").toString();

        String cadenaConInternalId = res.values().get(0).toString();
        cadenaConInternalId = cadenaConInternalId.replaceAll(".*<","");
        cadenaConInternalId = cadenaConInternalId.replace(">","");
        internalID = Integer.parseInt(cadenaConInternalId);

        respuesta = Optional.of(new UsuarioDTO(internalID, Email, EmailConfirmado, PassHash, new RolDAO().getAllByUsuarioId(internalID)));

        return respuesta;
    }

    /**
     * DEVUELVE TODOS LOS REGISTROS
     * @return
     */
    @Override
    public List<UsuarioDTO> getAll() {
        GetSelectAll consulta = new GetSelectAll(NodosEntities.Nodos.USUARIO.name());
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());


        String nombre;
        int orden;
        int internalID;

        List<UsuarioDTO> respuesta = new ArrayList<UsuarioDTO>();
        for(Record res:result) {
            String Email;
            boolean EmailConfirmado;
            String PassHash;
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();
            Email = map.get("Email").toString();
            EmailConfirmado = Boolean.valueOf(map.get("EmailConfirmado").toString());
            PassHash = map.get("PassHash").toString();

            String cadenaConInternalId = res.values().get(0).toString();
            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<","");
            cadenaConInternalId = cadenaConInternalId.replace(">","");
            internalID = Integer.parseInt(cadenaConInternalId);

            respuesta.add(new UsuarioDTO(internalID, Email, EmailConfirmado, PassHash, new RolDAO().getAllByUsuarioId(internalID)));
        }
        return respuesta;
    }
    /**
     * Busca USUARIOS en base a un criterio
     * @param campo el campo (en este caso el ENum.name() de USUARIO
     * @param valorCampo el valor a buscar
     * @return lo que pides
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    @Override
    public List<UsuarioDTO> getAllByCriterio(String campo, Object valorCampo) {
        GetSelectAllByCriterio consulta = new GetSelectAllByCriterio(NodosEntities.Nodos.USUARIO.name(), campo, valorCampo);
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());


        String nombre;
        int orden;
        int internalID;

        List<UsuarioDTO> respuesta = new ArrayList<UsuarioDTO>();
        for(Record res:result) {
            String Email;
            boolean EmailConfirmado;
            String PassHash;
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();
            Email = map.get("Email").toString();
            EmailConfirmado = Boolean.valueOf(map.get("EmailConfirmado").toString());
            PassHash = map.get("PassHash").toString();

            String cadenaConInternalId = res.values().get(0).toString();
            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<","");
            cadenaConInternalId = cadenaConInternalId.replace(">","");
            internalID = Integer.parseInt(cadenaConInternalId);

            respuesta.add(new UsuarioDTO(internalID, Email, EmailConfirmado, PassHash, new RolDAO().getAllByUsuarioId(internalID)));
        }
        return respuesta;
    }
/**
 * El total de USUARIOS existentes
 * @return lo dicho
 * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
 */
    @Override
    public int countAll() {
        GetCountAll consulta = new GetCountAll(NodosEntities.Nodos.USUARIO.name());
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        Record res = result.get(0);

        int total = res.values().get(0).asInt();

        return total;
    }
    /**
     * USUARIOS paginados
     * @param entidad el Enum.name() de USUARIO
     * @param ResultadosPorPagina ¿En serio no lo sabes?
     * @param Pagina la página actual
     * @param ordenCampo Asc/Desc del enum
     * @return lo que pides
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    @Override
    public List<UsuarioDTO> getAllPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo ordenCampo){
        GetSelectAllPaged consulta = new GetSelectAllPaged(NodosEntities.Nodos.USUARIO.name(), ResultadosPorPagina, Pagina, ordenCampo);
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());


        String nombre;
        int orden;
        int internalID;

        List<UsuarioDTO> respuesta = new ArrayList<UsuarioDTO>();
        for(Record res:result) {
            String Email;
            boolean EmailConfirmado;
            String PassHash;
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();
            Email = map.get("Email").toString();
            EmailConfirmado = Boolean.valueOf(map.get("EmailConfirmado").toString());
            PassHash = map.get("PassHash").toString();

            String cadenaConInternalId = res.values().get(0).toString();
            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<","");
            cadenaConInternalId = cadenaConInternalId.replace(">","");
            internalID = Integer.parseInt(cadenaConInternalId);

            respuesta.add(new UsuarioDTO(internalID, Email, EmailConfirmado, PassHash, new RolDAO().getAllByUsuarioId(internalID)));
        }
        return respuesta;
    }
    /**
     * @param o El userDTO a dar de alta
     * @return devuelve si se ha insertado. En principio si no se inserta debería explotar...
     * @throws es.uned.master.java.covid.Excepciones.GenericException si revienta algo
     */
    @Override
    public void save(Object o) {
        UsuarioDTO userGuarda = (UsuarioDTO)o;
        if(!userGuarda.esValido())
        {
            throw new GenericException(AbstractExcepciones.TipoExcepcion.MODELO_INVALIDO);
        }
        if(!userGuarda.esValido())
        {
            throw new GenericException(AbstractExcepciones.TipoExcepcion.MODELO_INVALIDO);
        }
        SaveUsuario consulta = new SaveUsuario(
                NodosEntities.Nodos.USUARIO,
                userGuarda.getEmail(),
                userGuarda.getEmailConfirmado(),
                userGuarda.getPassHash());
        boolean ejecutado = new Conexion().ejecutarComando(
                consulta.getConsulta(),
                consulta.getParametros()
        );
    }
    /**
     * @param o La clave está en el ID interno. Es el único campo que no se modifica y que sirve para
     *                          identificar a qué usuario le metemos mano
     * @return verdadero si todo va bien. Si no debería explotar.
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo falla.
     */
    @Override
    public void update(Object o) {
        UsuarioDTO userGuarda = (UsuarioDTO)o;
        Optional<UsuarioDTO> existente = this.get((long)userGuarda.getId());
        if(existente == null)
        {
            throw new GenericException(AbstractExcepciones.TipoExcepcion.ERROR_INESPERADO);
        }
        else{
            UsuarioDTO u = existente.get();
            u.setEmail(userGuarda.getEmail());
            u.setEmailConfirmado(userGuarda.getEmailConfirmado());
            u.setPass(userGuarda.getPassHash());
            UpdateUsuario consulta = new UpdateUsuario(u);
            boolean ejecutado = new Conexion().ejecutarComando(
                    consulta.getConsulta(),
                    consulta.getParametros()
            );
        }
    }
    /**
     * Aquí borro un user, a coste 0
     * @param o una COSA con destello Humano
     * @return la COSA
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    @Override
    public void delete(Object o) {
        UsuarioDTO user = (UsuarioDTO)o;
        Delete consulta = new Delete(
                (Integer)user.getId(),
                NodosEntities.Nodos.USUARIO.name());
        boolean ejecutado = new Conexion().ejecutarComando(
                consulta.getConsulta(),
                consulta.getParametros()
        );
    }

    /**
     * Le meto un rol al user que me digas
     * @param user Al que le meto
     * @param rol Al que le doy
     */
    public boolean AddRol(UsuarioDTO user, RolesDTO rol)
    {
        CrearRelacion consulta = new CrearRelacion(new Pair<Integer, NodosEntities.Nodos>() {
            @Override
            public Integer key() {
                return user.getId();
            }

            @Override
            public NodosEntities.Nodos value() {
                return NodosEntities.Nodos.USUARIO;
            }
        }, new Pair<Integer, NodosEntities.Nodos>() {
            @Override
            public Integer key() {
                return rol.get_internalId();
            }

            @Override
            public NodosEntities.Nodos value() {
                return NodosEntities.Nodos.ROL;
            }
        }, Relaciones.Tipos.Tiene_el_rol_de.name());
        return new Conexion().ejecutarComando(
                consulta.getConsulta(),
                consulta.getParametros()
        );
    }

    /**
     * Lo de arriba pero al revés
     * @see #AddRol(UsuarioDTO, RolesDTO)
     * @param user venía de aquí
     * @param rol iba para allí
     */
    public boolean RemoveRol(UsuarioDTO user, RolesDTO rol)
    {
        EliminarRelacion consulta = new EliminarRelacion(new Pair<Integer, NodosEntities.Nodos>() {
            @Override
            public Integer key() {
                return user.getId();
            }

            @Override
            public NodosEntities.Nodos value() {
                return NodosEntities.Nodos.USUARIO;
            }
        }, new Pair<Integer, NodosEntities.Nodos>() {
            @Override
            public Integer key() {
                return rol.get_internalId();
            }

            @Override
            public NodosEntities.Nodos value() {
                return NodosEntities.Nodos.ROL;
            }
        }, Relaciones.Tipos.Tiene_el_rol_de.name());
        return new Conexion().ejecutarComando(
                consulta.getConsulta(),
                consulta.getParametros()
        );
    }
}
