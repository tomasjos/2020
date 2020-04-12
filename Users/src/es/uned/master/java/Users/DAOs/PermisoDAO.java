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
import es.uned.master.java.Users.Schema.NodosEntities;
import es.uned.master.java.Users.Schema.Relaciones;
import es.uned.master.java.Utils.IOrdenCampos;
import es.uned.master.java.covid.DbContext.Conexion;
import es.uned.master.java.covid.DbContext.Dao;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PermisoDAO implements Dao {
    /**
     * DEVUELVE EL PERMISO
     * @param id El identificador interno
     */
    @Override
    public Optional<PermisosDTO> get(long id) {
        GetSelectSingle consulta = new GetSelectSingle(id, NodosEntities.Nodos.PERMISO.name());

        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        String nombre;
        int orden;
        int internalID;

        Optional<PermisosDTO> respuesta = null;

        //Aquí sólo esparamos un resultado (le pasamos un id)
        Record res = result.get(0);

        Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();

        nombre = map.values().iterator().next().toString();
        orden = NodosEntities.Permisos.valueOf(nombre).idx;

        String cadenaConInternalId = res.values().get(0).toString();

        cadenaConInternalId = cadenaConInternalId.replaceAll(".*<","");
        cadenaConInternalId = cadenaConInternalId.replace(">","");
        internalID = Integer.parseInt(cadenaConInternalId);
        respuesta = Optional.of(new PermisosDTO(nombre,orden,internalID));

        return respuesta;
    }

    /**
     * DEVUELVE LOS PERMISOS PAGINADOS
     * @param entidad la entidad que será el name del Enum PERMISOS
     * @param ResultadosPorPagina Cuántas cosicas quieres obtener
     * @param Pagina el núm de página a mostrar
     * @param ordenCampo es el Enum de la interfaz (asc: ascendente, desc: de lado sin cafeína
     * @return algo devolverá, digo yo... Amos
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    @Override
    public List<PermisosDTO> getAllPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo  ordenCampo) {
        GetSelectAllPaged consulta = new GetSelectAllPaged(NodosEntities.Nodos.PERMISO.name(), ResultadosPorPagina, Pagina, ordenCampo);
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        String nombre;
        int orden;
        int internalID;

        List<PermisosDTO> respuesta = new ArrayList<PermisosDTO>();

        for(Record res:result) {
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();

            nombre = map.values().iterator().next().toString();
            orden = NodosEntities.Permisos.valueOf(nombre).idx;

            String cadenaConInternalId = res.values().get(0).toString();

            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<", "");
            cadenaConInternalId = cadenaConInternalId.replace(">", "");
            internalID = Integer.parseInt(cadenaConInternalId);
            respuesta.add(new PermisosDTO(nombre, orden, internalID));

        }
        return respuesta;
    }

    /**
     * DEVUELVE TODOS LOS PERMISOS
     * @return EN DTOS
     */
    @Override
    public List<PermisosDTO> getAll() {
        GetSelectAll consulta = new GetSelectAll(NodosEntities.Nodos.PERMISO.name());
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        String nombre;
        int orden;
        int internalID;

        List<PermisosDTO> respuesta = new ArrayList<PermisosDTO>();

        for(Record res:result) {
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();

            nombre = map.values().iterator().next().toString();
            orden = NodosEntities.Permisos.valueOf(nombre).idx;

            String cadenaConInternalId = res.values().get(0).toString();

            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<", "");
            cadenaConInternalId = cadenaConInternalId.replace(">", "");
            internalID = Integer.parseInt(cadenaConInternalId);
            respuesta.add(new PermisosDTO(nombre, orden, internalID));

        }
        return respuesta;
    }


    /**
     * Permisos buscados
     * @param campo el campo a buscar, el Enum.name de Permiso
     * @param valorCampo el valor a buscar
     * @return lo que pides
     * @throws es.uned.master.java.covid.Excepciones.GenericException si algo peta
     */
    public List<PermisosDTO> getAllByCriterio(String campo, Object valorCampo) {
        GetSelectAllByCriterio consulta = new GetSelectAllByCriterio(NodosEntities.Nodos.PERMISO.name(), campo, valorCampo);
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        String nombre;
        int orden;
        int internalID;

        List<PermisosDTO> respuesta = new ArrayList<PermisosDTO>();

        for(Record res:result) {
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();

            nombre = map.values().iterator().next().toString();
            orden = NodosEntities.Permisos.valueOf(nombre).idx;

            String cadenaConInternalId = res.values().get(0).toString();

            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<", "");
            cadenaConInternalId = cadenaConInternalId.replace(">", "");
            internalID = Integer.parseInt(cadenaConInternalId);
            respuesta.add(new PermisosDTO(nombre, orden, internalID));

        }
        return respuesta;
    }

    /**
     * Devuelve los permisos que pertenecen a un rol concreto
     * @param RolId
     * @return
     */
    public List<PermisosDTO> getAllByRolId(int RolId) {
        GetSelectAllRelated consulta = new GetSelectAllRelated(NodosEntities.Nodos.ROL.name(),
                RolId, Relaciones.Tipos.Tiene_permiso_para.name(), NodosEntities.Nodos.PERMISO.name());
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        String nombre;
        int orden;
        int internalID;

        List<PermisosDTO> respuesta = new ArrayList<PermisosDTO>();

        for(Record res:result) {
            Map<String, Object> map = (Map<String, Object>) res.values().get(0).asMap();

            nombre = map.values().iterator().next().toString();
            orden = NodosEntities.Permisos.valueOf(nombre).idx;

            String cadenaConInternalId = res.values().get(0).toString();

            cadenaConInternalId = cadenaConInternalId.replaceAll(".*<", "");
            cadenaConInternalId = cadenaConInternalId.replace(">", "");
            internalID = Integer.parseInt(cadenaConInternalId);
            respuesta.add(new PermisosDTO(nombre, orden, internalID));

        }
        return respuesta;
    }


    /**
     * Devuelve cuantos Permisos hay en total
     * @return
     */
    @Override
    public int countAll() {
        GetCountAll consulta = new GetCountAll(NodosEntities.Nodos.PERMISO.name());
        ArrayList<Record> result = new Conexion().Search(consulta.getConsulta(), consulta.getParametros());

        Record res = result.get(0);

        int total = res.values().get(0).asInt();

        return total;
    }


    /**
     * Guarda un nuevo permiso, en principio este método no debería usarse nunca, para preservar la integridad
     * con las interfaces del Schema
     * @param o debe poder ser casteable a PermisoDTO
     */
    @Override
    public void save(Object o) {
        PermisosDTO permiso = (PermisosDTO)o;
        SaveSimpleEntity consulta = new SaveSimpleEntity(NodosEntities.Nodos.PERMISO, permiso.getNombre());
        boolean ejecutado = new Conexion().ejecutarComando(
                consulta.getConsulta(), consulta.getParametros()
        );
    }

    /**
     * Actualiza un permiso. Se le pasa el nuevo permiso, debe haber correspondencia entre los ids internos
     * @param o Casteable a PermisosDTO
     */
    @Override
    public void update(Object o) {

        PermisosDTO permiso = (PermisosDTO)o;
        UpdateSimpleEntityNombre consulta = new UpdateSimpleEntityNombre(
                (Integer)permiso.get_internalId(), NodosEntities.Nodos.PERMISO.name(), permiso.getNombre());
        boolean ejecutado = new Conexion().ejecutarComando(
                consulta.getConsulta(),
                consulta.getParametros()
        );
    }

    /**
     * Borra un Permiso en base al id Interno del PermisosDTO que se pasa como objeto
     * @param o casteable a PermisosDTO
     */
    @Override
    public void delete(Object o) {
        PermisosDTO permiso = (PermisosDTO)o;
        Delete consulta = new Delete(
                (Integer)permiso.get_internalId(),
                NodosEntities.Nodos.PERMISO.name());
        boolean ejecutado = new Conexion().ejecutarComando(
                consulta.getConsulta(),
                consulta.getParametros()
        );
    }
}
