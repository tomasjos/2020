/**
 * CONSULTA PARA ELIMINAR TODOS LOS NODOS DE UNA ENTIDAD
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.DAL;

import es.uned.master.java.Users.Schema.NodosEntities;

import java.util.HashMap;
import java.util.Map;

public class DeleteAll {
    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    Map<String,Object> parametros;

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    String consulta = "MATCH ($nodo:$nodo) " +
            "OPTIONAL MATCH ($nodo)-[r]-() DELETE $nodo,r ";
    public DeleteAll(NodosEntities.Nodos nodo){
        parametros = new HashMap<>();
        //Tócate un pie, los selectores no pueden ponerse como parámetros. Cuidadín.
        //parametros.put("nodo", nodo.name());
        consulta = consulta.replaceAll("\\$nodo", nodo.name());
    }
}
