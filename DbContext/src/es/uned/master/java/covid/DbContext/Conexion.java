/**
 * MEGA ULTRA CLASE DE CONEXIÓN A NEO4J!!!
 *
 * Esta clase conecta con la db neo4j.
 * La idea es encapsular la capa de acceso directo a datos: ejecutar comandos y devolver consultas. SÓLO ESO
 *
 * Esta clase se limita a:
 *     Ejecuta un comando
 *     Ejecuta múltiples comandos
 *     Devuelve ID insertado
 *     Devuelve JSON
 *     Devuelve la respuesta y apáñate si quiere JSON o lo que quieras
 *
 * @author Peter Fight
 * @version 0.007
 * @see <a href="https://neo4j.com">Neo4j (que no es neo 4 Java, es otra cosa)</a>
 */



package es.uned.master.java.covid.DbContext;


import com.fasterxml.jackson.databind.ObjectMapper;
import es.uned.master.java.covid.Excepciones.AbstractExcepciones;
import es.uned.master.java.covid.Excepciones.GenericException;
import org.neo4j.driver.*;

import javax.swing.table.DefaultTableModel;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Conexion implements IConexion {
    //Esta variable es el driver de neo4j, 4j no es 4 java, eh!...
    private Driver _driver;


    /**
     * Los datos de conexión a la db pueden variar entre máquinas. Para evitar la pesadilla de
     * tratar de lanzar un proyecto y tener que deducir que si no arranca correctamente se debe
     * a que la db está mal configurada, este gentil developer ha metido un archivo de propiedades.
     * De este modo, si la aplicación no arranca, puede apelar impunemente a la torpeza del arranker,
     * que los hay mu torpes.
     *
     * @param estaProp estaProp es la propiedad de la que se quiere obtener el valor
     * @throws GenericException en caso que el archivo de props no exista... Catapuuuumba!!! Valencian's-style.
     * @return el valor buscado (es la caña)
     */
    private String getProp(String estaProp) throws GenericException {
        Properties prop = new Properties();
        //el nombre del archivo de propiedades
        String propFileName = "es/uned/master/java/covid/DbContext/conexion.properties";
        //Leo el stream del fichero
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        //Si el fichero existe, lo cargo, si no, hastalueg.
        try {
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new GenericException(AbstractExcepciones.TipoExcepcion.ERROR_ARCHIVO);
            }
        }
        catch (Exception e)
        {
            throw new GenericException(AbstractExcepciones.TipoExcepcion.ERROR_ARCHIVO);
        }
        //Devuelvo la prop buscada
        return prop.getProperty(estaProp);
    }

    /**
     * CONSTRUCTOR DE CLASE
     * Cargo el driver con su instanciación
     * @throws GenericException Si al llamar al archivo de propiedades algo falla, genericException
     * se produce una reacción parecida a la de mezclar mal ácidos explosivos.
     */
    public Conexion() {
        try {
            this._driver = GraphDatabase.driver( //Hay que importar la libraría reactive-streams-1.0.3.jar, si no casca.
                    this.getProp("uri"),
                    AuthTokens.basic(
                            this.getProp("user"),
                            this.getProp("pass")
                    )
            );
        }
        catch (Exception e)
        {
            throw new GenericException(AbstractExcepciones.TipoExcepcion.ERROR_DB);
        }
    }

    /**
     * Habrá que cerrar la conexión, vamos, digo yo...
     * @throws Exception es override, si explota que explote lo que tenga que explotar, la culpa es de neo4j.
     */
    @Override
    public void close() throws Exception {
        this._driver.close();
    }

    /**
     * Método que ejecuta un comando contra la base de datos
     * @param comando el comando a ejecutar
     * @return Verdadero si el proceso va guay, si algo se rompe lanza la excepción propia.
     * @throws GenericException En caso de que falle la ejecución del comando
     */
    @Override
    public boolean ejecutarComando(String comando, Map<String, Object> parametros) throws GenericException
    {
        try (Session session = _driver.session()) {
            String resultado = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    String result = tx.run(comando, parametros).toString();
                    return result;
                }
            });
            //No ha explotado, devuelvo true
            return true;
        }
    }

    /**
     * INSERTA UN REGISTRO Y DEVUELVE SU ID
     * @param comando
     * @param parametros
     * @return
     * @throws GenericException
     */
    @Override
    public Integer ejecutarComandoInsercion(String comando, Map<String, Object> parametros) throws GenericException
    {
        try (Session session = _driver.session()) {
            Result r = session.run(comando, parametros);
            //No ha explotado, devuelvo true
            return r.single().get(0).asInt();
        }
    }


    /**
     * EJECUTA VARIOS COMANDOS A LA VEZ
     * @param comandos
     * @param parametros
     * @return
     * @throws GenericException
     */
    @Override
    public boolean ejecutarComandos(List<String> comandos, Map<String, Object> parametros) throws GenericException
    {
        try (Session session = _driver.session()) {
            comandos.forEach(q -> {
                String resultado = session.writeTransaction(new TransactionWork<String>() {
                    @Override
                    public String execute(Transaction tx) {
                        String result = tx.run(q, parametros).toString();
                        return result;
                    }
                });
            });
            //No ha explotado, devuelvo true
            return true;
        }
        catch (Exception e)
        {
            throw new GenericException(AbstractExcepciones.TipoExcepcion.ERROR_DB);
        }
    }




    /**
     * Método que lanza una consulta a la base de datos
     * @param consulta los parámetros de la consulta
     * @return el Json en caso de que vaya bien. Si algo se rompe lanza una excepción.
     * @throws GenericException En caso de que falle la ejecución del comando
     */
    @Override
    public String ejecutarConsulta(String consulta, Map<String, Object> parametros) throws GenericException
    {
        try (Session session = _driver.session()) {
            Result result = session.run(consulta, parametros);
            ObjectMapper mapper = new ObjectMapper();
            //Object o = result.single().get("NODO");
            //String resultado = mapper.writeValueAsString(o.toString());
            DefaultTableModel dtm = new DefaultTableModel(0,0);
            while(result.hasNext()) {
                Record row = (Record) result.next();
                Map<String, Object> map = (Map<String, Object>) row.values().get(0).asMap();
                String idInterno = (String) row.values().get(0).keys().iterator().next();
                String idInterno2 = (String) row.values().get(0).toString();
                return mapper.writeValueAsString(map);
            }
            return mapper.writeValueAsString(result.next().toString());
        }
        catch (Exception e)
        {
            throw new GenericException(AbstractExcepciones.TipoExcepcion.ERROR_DB);
        }
    }

    /**
     * PERMITE LANZAR UNA BÚSQUEDA
     * @param consulta
     * @param parametros
     * @return
     * @throws GenericException
     */
    @Override
    public ArrayList<Record> Search(String consulta, Map<String, Object> parametros) throws GenericException
    {
        try (Session session = _driver.session()) {
            Result result = session.run(consulta, parametros);

            ArrayList<Record> respuesta = new ArrayList<Record>();

            while (result.hasNext())
            {
                respuesta.add((Record)result.next());
            }

            return respuesta;
        }
        catch (Exception e)
        {
            throw new GenericException(AbstractExcepciones.TipoExcepcion.ERROR_DB);
        }
    }
}