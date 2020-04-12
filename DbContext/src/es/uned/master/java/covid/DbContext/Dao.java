/**
 * INTERFAZ PARA QUE TODOS LOS DAOS DERIVADOS IMPLEMENTEN, AL MENOS, ESTOS MÉTODOS
 *
 * @author Peter Fight
 * @version 0.007
 * @see <a href="https://neo4j.com">Neo4j (que no es neo 4 Java, es otra cosa)</a>
 */


package es.uned.master.java.covid.DbContext;

import es.uned.master.java.Utils.IOrdenCampos;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(long id);

    List<T> getAll();

    List<T> getAllByCriterio(String campo, Object valorCampo);

    int countAll();

    List<T> getAllPaged(String entidad, int ResultadosPorPagina, int Pagina, IOrdenCampos.ordenCampo ordenCampo);

    void save(T t);

    void update(T t);

    void delete(T t);
}
