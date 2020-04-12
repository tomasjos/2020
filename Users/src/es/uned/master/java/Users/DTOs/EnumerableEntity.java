/**
 * CLASE ABSTRACTA PARA GESTIONAR EN UN ÚNICO LUGAR LOS DTOS DE ENTIDADES ENUMERADAS (roles y Permisos)
 * Tienen un campo nombre e ids. SOn simples y pueden gestionarse de manera unitaria
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.DTOs;

import es.uned.master.java.Utils.Utilidades;
import org.neo4j.driver.internal.shaded.reactor.util.annotation.Nullable;

public abstract class EnumerableEntity implements IDTO{
    /**
     * Constructor
     * @param nombre EL nombre del rol o el permiso
     * @param orden Qué orden tiene
     * @param internalId Qué id interno tiene, puede ser nulo, óbviamente si es nuevo
     */
    public EnumerableEntity(String nombre, int orden, @Nullable Integer internalId)
    {
        this.Nombre = nombre;
        this._i = orden;
        this._internalId = internalId;
    }

    public @Nullable Integer get_internalId() {
        return _internalId;
    }

    public void set_internalId(int _internalId) {
        this._internalId = _internalId;
    }

    @Nullable Integer _internalId;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    String Nombre;

    public Integer get_i() {
        return _i;
    }

    public void set_i(Integer _i) {
        this._i = _i;
    }

    Integer _i;


    EnumerableEntity(int i) {
        this._i = i;
    }


    /**
     * Si Existe nombre, doy por bueno el modelo
     * @return
     */
    @Override
    public boolean esValido() {
        boolean esValido = true;
        esValido = !Utilidades.cadenaNulaEmpty(this.Nombre);
        return esValido;
    }
}
