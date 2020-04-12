/**
 * CONSULTA PARA OBTENER NODOS EN BASE A UN CRITERIO
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.DAL;

import java.util.HashMap;
import java.util.Map;

public class GetSelectAllByCriterio {
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

    String consulta = "MATCH(NODO:$entidad) where NODO.$campo = $valorCampo return NODO ";
    public GetSelectAllByCriterio(String entidad, String campo, Object valorCampo){
        parametros = new HashMap<>();
        //Tócate un pie, los selectores no pueden ponerse como parámetros. Cuidadín.
        consulta = consulta.replaceAll("\\$entidad", entidad);
        consulta = consulta.replaceAll("\\$campo", campo);
        //parametros.put("entidad", entidad);
        parametros.put("valorCampo", valorCampo);
    }
}
