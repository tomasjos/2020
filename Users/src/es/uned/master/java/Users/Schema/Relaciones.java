/**
 * RELACIONES EMPLEADAS EN EL MÓDULO DE USUARIOS
 * HAY
 *  Usuarios que tienene rol de...
 *  ROLES que tienen permiso para...
 *
 * A la hora de crear la estructura inicial de la base de datos tiro de aquí.
 * A fin de evitar "Cypher injection" conviene que los tipos que se manejan estén cotejados en código, para evitar
 * comportamientos inesperados y consultas con parámetros inseguros (meter como parámetro el nombre de un nodo,
 * podría implicar que el usuario pasa una cadena insegura como nombre de nodo, en lugar de variable simple)
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */

package es.uned.master.java.Users.Schema;

public class Relaciones {
    public enum Tipos{
        Tiene_permiso_para,
        Tiene_el_rol_de
    }
}
