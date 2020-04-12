/**
 * CONSULTA PARA OBTENER LOS NODOS DE UNA ENTIDAD PAGINADOS
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */


package es.uned.master.java.Users.DAL;

import es.uned.master.java.Utils.IOrdenCampos;

import java.util.HashMap;
import java.util.Map;

public class GetSelectAllPaged {
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

    IOrdenCampos.ordenCampo orden;
    private String getColetillaOrden()
    {
        if(this.orden != null)
        {
            return " order by Id(NODO) "+ this.orden.name();
        }
        else{
            return "";
        }
    }

    int ResultadosPorPagina;
    int Pagina;

    private String getSkipResultados(){
        if(Pagina > 0)
        {
            return " skip("+(ResultadosPorPagina*Pagina)+")";
        }
        else{
            return "";
        }
    }

    private String getLimitResultados(){
        return " Limit " + ResultadosPorPagina;
    }


    String consulta = "";
    public GetSelectAllPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo orden){
        parametros = new HashMap<>();
        this.ResultadosPorPagina = ResultadosPorPagina;
        this.Pagina = Pagina;
        this.orden = orden;
        consulta = "MATCH(NODO:$entidad) return NODO" + //Si instancio antes la consulta los getColetillaRegimen... cascan
                getColetillaOrden()+
                getSkipResultados()+
                getLimitResultados()
        ;
        //Tócate un pie, los selectores no pueden ponerse como parámetros. Cuidadín.
        consulta = consulta.replaceAll("\\$entidad", entidad);
        //parametros.put("entidad", entidad);
    }
}
