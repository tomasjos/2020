/**
 * INTERFAZ QUE DESCRIBE LAS CONEXIONES A DATOS
 *
 * @author Peter Fight
 * @version 0.007
 * @see <a href="https://neo4j.com">Neo4j (que no es neo 4 Java, es otra cosa)</a>
 */


package es.uned.master.java.covid.DbContext;

import es.uned.master.java.covid.Excepciones.GenericException;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.shaded.reactor.util.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//AutoCloseable para que la conexión se cierre automáticamente
public interface IConexion extends AutoCloseable {
    //Ejecuta un comando
    boolean ejecutarComando(String comando, @Nullable Map<String, Object> parametros);
    //Ejecuta múltiples comandos
    boolean ejecutarComandos(List<String> comandos, @Nullable Map<String, Object> parametros);
    //Devuelve ID insertado
    Integer ejecutarComandoInsercion(String comando, @Nullable Map<String, Object> parametros);
    //Devuelve JSON
    String ejecutarConsulta(String consulta, @Nullable Map<String, Object> parametros) throws GenericException;
    //Devuelve la respuesta y apáñate si quiere JSON o lo que quieras
    ArrayList<Record> Search(String consulta, @Nullable Map<String, Object> parametros);


}
