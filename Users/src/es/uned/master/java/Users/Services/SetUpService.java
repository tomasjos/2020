/**
 * LIMPIO LA DB Y LA RELLENO CON DATOS BÁSICOS
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.Services;

import es.uned.master.java.Users.DAL.*;
import es.uned.master.java.Users.DTOs.PermisosDTO;
import es.uned.master.java.Users.DTOs.RolesDTO;
import es.uned.master.java.Users.Schema.NodosEntities;
import es.uned.master.java.covid.DbContext.Conexion;
import es.uned.master.java.covid.Excepciones.AbstractExcepciones;
import es.uned.master.java.covid.Excepciones.GenericException;

import java.util.ArrayList;
import java.util.Map;

public class SetUpService {
    /**
     * INICIO DE LA MAGIA
     * Proceso:
     *  1.- Limpio la db
     *  2.- Meto roles y users
     *  3.- Fin
     */
    public void InitUsersDb(){

        try{

            //Esta variable es más para debuggear que para otra cosa, si funciona una vez, debería funcionar siempre
            //(es un método sin variables)
            Boolean ejecutadoCorrectamente = true;

            //Borro usuarios
            DeleteAll consultaDeleteAll = new DeleteAll(NodosEntities.Nodos.USUARIO);
            ejecutadoCorrectamente = new Conexion().ejecutarComando(
                    consultaDeleteAll.getConsulta(),
                    consultaDeleteAll.getParametros()
            );
            //Borro roles
            consultaDeleteAll = new DeleteAll(NodosEntities.Nodos.ROL);
            ejecutadoCorrectamente = new Conexion().ejecutarComando(
                    consultaDeleteAll.getConsulta(),
                    consultaDeleteAll.getParametros()
            );
            //Borro permisos
            consultaDeleteAll = new DeleteAll(NodosEntities.Nodos.PERMISO);
            ejecutadoCorrectamente = new Conexion().ejecutarComando(
                    consultaDeleteAll.getConsulta(),
                    consultaDeleteAll.getParametros()
            );

            //Meto un user por defecto
            SaveUsuario consulta = new SaveUsuario(NodosEntities.Nodos.USUARIO, "fotutto_maestro@uned.es", true, "123456");
            Integer idDefaultUser = new Conexion().ejecutarComandoInsercion(consulta.getConsulta(), consulta.getParametros());

            //Recorro el Schema de roles y permisos y creo las relaciones necesarias para que el usuario
            //Recién creado tenga el rol de administrador (el que tiene orden 1) y éste, a su vez, todos los permisos.
            ArrayList<Integer> idsRoles = new ArrayList<Integer>();

            for(RolesDTO rol : InitializeAuthHelpers.getOrderedRolesDisponibles())
            {
                SaveSimpleEntity consultaInsert = new SaveSimpleEntity(NodosEntities.Nodos.ROL, rol.getNombre());
                idsRoles.add(new Conexion().ejecutarComandoInsercion(consultaInsert.getConsulta(), consultaInsert.getParametros()));
            }
            ArrayList<Integer> idsPermisos = new ArrayList<Integer>();
            for(PermisosDTO perm : InitializeAuthHelpers.getOrderedPermisosDisponibles())
            {
                SaveSimpleEntity consultaInsert = new SaveSimpleEntity(NodosEntities.Nodos.PERMISO, perm.getNombre());
                idsPermisos.add(new Conexion().ejecutarComandoInsercion(consultaInsert.getConsulta(), consultaInsert.getParametros()));
            }
            Map<String, Object> consultasParametros = InitializeDbQueries.getComandosCrearRelacionesIniciales(
                    idDefaultUser,
                    idsRoles,
                    idsPermisos);
            for (Map.Entry<String,Object> entry :InitializeDbQueries.getComandosCrearRelacionesIniciales(
                    idDefaultUser,
                    idsRoles,
                    idsPermisos).entrySet())
            {
                ejecutadoCorrectamente = new Conexion().ejecutarComando(entry.getKey(), (Map<String, Object>)entry.getValue());
            }
        }
        catch (Exception e)
        {
            //La excepción puede venir, en caso de que haya un error en la consulta, pero
            //también puede venir de la generación del hash MD5 al generar el hash del password!!!
            //En ese caso se lanzaría una NoSuchAlgorithmException.
            //En cualquier caso, NINGUNA DE ESTAS EXCEPCIONES DEBERÍA SALTAR NUNCA
            throw new GenericException(AbstractExcepciones.TipoExcepcion.ERROR_INESPERADO);
        }

    }

    /**
     * Aquí te borro los nodos que maneja el módulo de Users de la db
     */
    public void ClearUsersDb(){

        try{

            Boolean ejecutadoCorrectamente = true;
            DeleteAll consultaDelete = new DeleteAll(NodosEntities.Nodos.USUARIO);
            ejecutadoCorrectamente = new Conexion().ejecutarComando(
                    consultaDelete.getConsulta(),
                    consultaDelete.getParametros()
            );
            consultaDelete = new DeleteAll(NodosEntities.Nodos.ROL);
            ejecutadoCorrectamente = new Conexion().ejecutarComando(
                    consultaDelete.getConsulta(),
                    consultaDelete.getParametros()
            );
            consultaDelete = new DeleteAll(NodosEntities.Nodos.PERMISO);
            ejecutadoCorrectamente = new Conexion().ejecutarComando(
                    consultaDelete.getConsulta(),
                    consultaDelete.getParametros()
            );
        }
        catch (Exception e)
        {
            //La excepción puede venir, en caso de que haya un error en la consulta, pero
            //también puede venir de la generación del hash MD5 al generar el hash del password!!!
            //En ese caso se lanzaría una NoSuchAlgorithmException.
            //En cualquier caso, NINGUNA DE ESTAS EXCEPCIONES DEBERÍA SALTAR NUNCA
            throw new GenericException(AbstractExcepciones.TipoExcepcion.ERROR_INESPERADO);
        }

    }
}
