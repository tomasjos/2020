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
import es.uned.master.java.Users.DTOs.PermisosDTO;
import es.uned.master.java.Users.DTOs.RolesDTO;
import es.uned.master.java.Users.Schema.NodosEntities;
import es.uned.master.java.Users.Schema.Relaciones;
import es.uned.master.java.Utils.IOrdenCampos;
import es.uned.master.java.covid.DbContext.Conexion;
import es.uned.master.java.covid.DbContext.Dao;
import org.neo4j.driver.Record;
import org.neo4j.driver.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RolDAO implements Dao {
    /**
     * DEVUELVE EL ROL
     * @param id El identificador interno
     */
    @Override
    public Optional<RolesDTO> get(long id) {
        GetSelectSingle consulta = new GetSelectSingle(id, NodosEntities.Nodos.ROL.name());
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());


        String nombre;
        int orden;
        int internalID;

        Optional<RolesDTO> respuesta = null;

        //Aquí sólo esparamos un resultado (le pasamos un id)
        Record res = result.get(0);

        Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();

        nombre = map.values().iterator().next().toString();
        orden = NodosEntities.Roles.valueOf(nombre).idx;

        String cadenaConInternalId = res.values().get(0).toString();

        cadenaConInternalId = cadenaConInternalId.replaceAll(".*<","");
        cadenaConInternalId = cadenaConInternalId.replace(">","");
        internalID = Integer.parseInt(cadenaConInternalId);
        respuesta = Optional.of(new RolesDTO(nombre,orden,internalID, new PermisoDAO().getAllByRolId(internalID)));

        return respuesta;
    }
    /**
     * DEVUELVE TODOS LOS ROLES
     * @return EN DTOS
     */
    @Override
    public List<RolesDTO> getAll() {
        GetSelectAll consulta = new GetSelectAll(NodosEntities.Nodos.ROL.name());
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        String nombre;
        int orden;
        int internalID;

        List<RolesDTO> respuesta = new ArrayList<RolesDTO>();

        for(Record res:result) {
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();

            nombre = map.values().iterator().next().toString();
            orden = NodosEntities.Roles.valueOf(nombre).idx;

            String cadenaConInternalId = res.values().get(0).toString();

            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<", "");
            cadenaConInternalId = cadenaConInternalId.replace(">", "");
            internalID = Integer.parseInt(cadenaConInternalId);
            respuesta.add(new RolesDTO(nombre, orden, internalID, new PermisoDAO().getAllByRolId(internalID)));

        }
        return respuesta;
    }

    /**
     * Devuelve todos los roles que pertenecen a un usuario
     * @param UsuarioId el idInterno
     * @return
     */
    public List<RolesDTO> getAllByUsuarioId(int UsuarioId) {
        GetSelectAllRelated consulta = new GetSelectAllRelated(NodosEntities.Nodos.USUARIO.name(),
                UsuarioId, Relaciones.Tipos.Tiene_el_rol_de.name(), NodosEntities.Nodos.ROL.name());
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        String nombre;
        int orden;
        int internalID;

        List<RolesDTO> respuesta = new ArrayList<RolesDTO>();

        for(Record res:result) {
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();

            nombre = map.values().iterator().next().toString();
            orden = NodosEntities.Roles.valueOf(nombre).idx;

            String cadenaConInternalId = res.values().get(0).toString();

            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<", "");
            cadenaConInternalId = cadenaConInternalId.replace(">", "");
            internalID = Integer.parseInt(cadenaConInternalId);
            respuesta.add(new RolesDTO(nombre, orden, internalID, new PermisoDAO().getAllByRolId((int)internalID)));

        }
        return respuesta;
    }

    /**
     * Busca roles en base a un criterio
     * @param campo el campo (en este caso el ENum.name() de ROL
     * @param valorCampo el valor a buscar
     * @return lo que pides
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<RolesDTO> getAllByCriterio(String campo, Object valorCampo) {
        GetSelectAllByCriterio consulta = new GetSelectAllByCriterio(NodosEntities.Nodos.ROL.name(), campo, valorCampo);
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        String nombre;
        int orden;
        int internalID;

        List<RolesDTO> respuesta = new ArrayList<RolesDTO>();

        for(Record res:result) {
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();

            nombre = map.values().iterator().next().toString();
            orden = NodosEntities.Roles.valueOf(nombre).idx;

            String cadenaConInternalId = res.values().get(0).toString();

            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<", "");
            cadenaConInternalId = cadenaConInternalId.replace(">", "");
            internalID = Integer.parseInt(cadenaConInternalId);
            respuesta.add(new RolesDTO(nombre, orden, internalID, new PermisoDAO().getAllByRolId(internalID)));

        }
        return respuesta;
    }

    /**
     * @return el total de registros en un entero
     */
    @Override
    public int countAll() {
        GetCountAll consulta = new GetCountAll(NodosEntities.Nodos.ROL.name());
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        Record res = result.get(0);

        int total = res.values().get(0).asInt();

        return total;
    }


    /**
     * Roles paginados
     * @param entidad el Enum.name() de Rol
     * @param ResultadosPorPagina ¿En serio no lo sabes?
     * @param Pagina la página actual
     * @param ordenCampo Asc/Desc del enum
     * @return lo que pides
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    @Override
    public List<RolesDTO> getAllPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo  ordenCampo) {
        GetSelectAllPaged consulta = new GetSelectAllPaged(NodosEntities.Nodos.ROL.name(), ResultadosPorPagina, Pagina, ordenCampo);
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        String nombre;
        int orden;
        int internalID;

        List<RolesDTO> respuesta = new ArrayList<RolesDTO>();

        for(Record res:result) {
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();

            nombre = map.values().iterator().next().toString();
            orden = NodosEntities.Roles.valueOf(nombre).idx;

            String cadenaConInternalId = res.values().get(0).toString();

            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<", "");
            cadenaConInternalId = cadenaConInternalId.replace(">", "");
            internalID = Integer.parseInt(cadenaConInternalId);
            respuesta.add(new RolesDTO(nombre, orden, internalID, new PermisoDAO().getAllByRolId(internalID)));

        }
        return respuesta;


    }
    /**
     * Guarda un nuevo rol, en principio este método no debería usarse nunca, para preservar la integridad
     * con las interfaces del Schema
     * @param o debe poder ser casteable a RolesDTO
     */
    @Override
    public void save(Object o) {
        RolesDTO rol = (RolesDTO)o;
        SaveSimpleEntity consulta = new SaveSimpleEntity(NodosEntities.Nodos.ROL, rol.getNombre());
        boolean ejecutado = new Conexion().ejecutarComando(
                consulta.getConsulta(), consulta.getParametros()
        );
    }
    /**
     * Actualiza un rol. Se le pasa el nuevo rol, debe haber correspondencia entre los ids internos
     * @param o Casteable a RolesDTO
     */
    @Override
    public void update(Object o) {

        RolesDTO rol = (RolesDTO)o;
        UpdateSimpleEntityNombre consulta = new UpdateSimpleEntityNombre(
                (Integer)rol.get_internalId(), NodosEntities.Nodos.ROL.name(), rol.getNombre());
        boolean ejecutado = new Conexion().ejecutarComando(
                consulta.getConsulta(),
                consulta.getParametros()
        );
    }

    /**
     * Borra un Rol en base al id Interno del RolesDTO que se pasa como objeto
     * @param o casteable a RolesDTO
     */
    @Override
    public void delete(Object o) {

        RolesDTO rol = (RolesDTO)o;
        Delete consulta = new Delete(
                (Integer)rol.get_internalId(),
                NodosEntities.Nodos.ROL.name());
        boolean ejecutado = new Conexion().ejecutarComando(
                consulta.getConsulta(),
                consulta.getParametros()
        );
    }

    /**
     * Añade una relación de permiso. Este Rol tiene este nuevo permiso
     * @param rol
     * @param permiso
     * @return
     */
    public boolean AddPermiso(RolesDTO rol, PermisosDTO permiso)
    {
        CrearRelacion consulta = new CrearRelacion(new Pair<Integer, NodosEntities.Nodos>() {
            @Override
            public Integer key() {
                return rol.get_internalId();
            }

            @Override
            public NodosEntities.Nodos value() {
                return NodosEntities.Nodos.ROL;
            }
        }, new Pair<Integer, NodosEntities.Nodos>() {
            @Override
            public Integer key() {
                return permiso.get_internalId();
            }

            @Override
            public NodosEntities.Nodos value() {
                return NodosEntities.Nodos.PERMISO;
            }
        }, Relaciones.Tipos.Tiene_permiso_para.name());
        return new Conexion().ejecutarComando(
                consulta.getConsulta(),
                consulta.getParametros()
        );
    }

    /**
     * Elimina una relación de Rol -> permiso
     * @param rol
     * @param permiso
     * @return verdadero si esta Ok
     */

    public boolean RemovePermiso(RolesDTO rol, PermisosDTO permiso)
    {
        EliminarRelacion consulta = new EliminarRelacion(new Pair<Integer, NodosEntities.Nodos>() {
            @Override
            public Integer key() {
                return rol.get_internalId();
            }

            @Override
            public NodosEntities.Nodos value() {
                return NodosEntities.Nodos.ROL;
            }
        }, new Pair<Integer, NodosEntities.Nodos>() {
            @Override
            public Integer key() {
                return permiso.get_internalId();
            }

            @Override
            public NodosEntities.Nodos value() {
                return NodosEntities.Nodos.PERMISO;
            }
        }, Relaciones.Tipos.Tiene_permiso_para.name());
        return new Conexion().ejecutarComando(
                consulta.getConsulta(),
                consulta.getParametros()
        );
    }
}
