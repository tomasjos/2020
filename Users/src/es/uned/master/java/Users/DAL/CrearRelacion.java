/**
 * CONSULTA PARA CREAR UNA NUEVA RELACIÓN ENTRE NODOS
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.DAL;

import es.uned.master.java.Users.Schema.NodosEntities;
import org.neo4j.driver.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class CrearRelacion {
    String consulta = "MATCH ($nombreEntidadOrigen:$nombreEntidadOrigen)," +
            "($nombreEntidadDestino:$nombreEntidadDestino) where Id($nombreEntidadOrigen)" +
            " = $idEntidadOrigen and Id($nombreEntidadDestino) = $idEntidadDestino CREATE " +
            " ($nombreEntidadOrigen)-[rel:$etiqueta] " +
            "-> ($nombreEntidadDestino) ";
    public CrearRelacion(Pair<Integer, NodosEntities.Nodos> idEntidadOrigen,
                         Pair<Integer, NodosEntities.Nodos> idEntidadDestino,
                         String etiqueta) {
        parametros = new HashMap<>();
        parametros.put("idEntidadOrigen", idEntidadOrigen.key());
        //parametros.put("nombreEntidadOrigen", idEntidadOrigen.value());
        //Tócate un pie, los selectores no pueden ponerse como parámetros. Cuidadín.
        consulta = consulta.replaceAll("\\$nombreEntidadOrigen", idEntidadOrigen.value().name());
        parametros.put("idEntidadDestino", idEntidadDestino.key());
        //parametros.put("nombreEntidadDestino", idEntidadDestino.value());
        //Tócate un pie, los selectores no pueden ponerse como parámetros. Cuidadín.
        consulta = consulta.replaceAll("\\$nombreEntidadDestino", idEntidadDestino.value().name());
        //parametros.put("etiqueta", etiqueta);
        //Tócate un pie, los selectores no pueden ponerse como parámetros. Cuidadín.
        consulta = consulta.replaceAll("\\$etiqueta", etiqueta);
    }
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
}
