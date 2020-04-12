/**
 * CONSULTA PARA CREAR OBTENER UN USUARIO EN BASE A SU EMAIL
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

public class GetByEmail {
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

    String consulta = "MATCH(NODO:$entidad) WHERE NODO.Email = $mail return NODO";
    public GetByEmail(String mail){
        parametros = new HashMap<>();
        //Tócate un pie, los selectores no pueden ponerse como parámetros. Cuidadín.
        consulta = consulta.replace("$entidad", NodosEntities.Nodos.USUARIO.name());
        parametros.put("mail", mail);
    }
}
