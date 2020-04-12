/**
 * CONSULTA PARA OBTENER UN NODO EN CONCRETO
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

public class GetSelectSingle {
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

    String consulta = "MATCH(NODO:$entidad) WHERE Id(NODO) = $id return NODO";
    public GetSelectSingle(long id, String entidad){
        parametros = new HashMap<>();
        //Tócate un pie, los selectores no pueden ponerse como parámetros. Cuidadín.
        consulta = consulta.replace("$entidad",entidad);
        parametros.put("id", (int)id);
    }
}
