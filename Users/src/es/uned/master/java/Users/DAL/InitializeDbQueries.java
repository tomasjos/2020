/**
 * CLASE QUE DEVUELVE TODAS LAS CONSULTAS PARA CREAR LAS RELACIONES INICIALES
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */


package es.uned.master.java.Users.DAL;

import es.uned.master.java.Users.Schema.NodosEntities;
import es.uned.master.java.Users.Schema.Relaciones;
import org.neo4j.driver.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InitializeDbQueries {
    private static String _comandoBorrarTablasUsers;
    private static ArrayList<String> _comandosCrearRoles;
    private static ArrayList<String> _comandosCrearPermisos;


    public static Map<String, Object> getComandosCrearRelacionesIniciales(
            Integer idDefaultUser,
            ArrayList<Integer> idsRoles,
            ArrayList<Integer> idsPermisos){
        //HashSet para evitar duplicados
        int minIdRol = Collections.min(idsRoles);//Como el rol Administrador lo inserto primero,
        // y el id es autoincremental, es el menor de los ids.
        Pair<Integer, NodosEntities.Nodos> _Rol = new Pair<Integer, NodosEntities.Nodos>() {
            @Override
            public Integer key() {
                return minIdRol;
            }

            @Override
            public NodosEntities.Nodos value() {
                return NodosEntities.Nodos.ROL;
            }
        };
        HashSet<Pair<Integer, NodosEntities.Nodos>> _Permisos = new HashSet<Pair<Integer, NodosEntities.Nodos>>();

        AtomicInteger contador = new AtomicInteger(0);
        InitializeAuthHelpers.getOrderedPermisosDisponibles()
                .forEach(x -> { _Permisos.add(new Pair<Integer, NodosEntities.Nodos>() {
                    @Override
                    public Integer key() {
                        int contadoPermiso = contador.getAndIncrement();
                        return idsPermisos.get(contadoPermiso);
                    }

                    @Override
                    public NodosEntities.Nodos value() {
                        return NodosEntities.Nodos.PERMISO;
                    }
                });
                });

        Map<String, Object> respuesta = new HashMap<>();
        int Contador = 0;
        for(Pair<Integer, NodosEntities.Nodos> kv : _Permisos)
        {
            CrearRelacion consulta = new CrearRelacion(_Rol, kv,Relaciones.Tipos.Tiene_permiso_para.name());
            respuesta.put(
                    consulta.getConsulta() + "//EvitaClaveDuplicadaConElId:"+Contador++,
                    consulta.getParametros()
            );
        }

        //Añado la relación de Usuario -> rol
        Pair<Integer, NodosEntities.Nodos> _Usuario = new Pair<Integer, NodosEntities.Nodos>() {
            @Override
            public Integer key() {

                return idDefaultUser;
            }

            @Override
            public NodosEntities.Nodos value() {
                return NodosEntities.Nodos.USUARIO;
            }
        };
        CrearRelacion consulta = new CrearRelacion(_Usuario, _Rol, Relaciones.Tipos.Tiene_el_rol_de.name());

        respuesta.put(
                consulta.getConsulta(),
                consulta.getParametros()
        );

        return respuesta;
    };
}
