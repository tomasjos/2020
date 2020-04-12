/**
 * ESTRUCTURA DE LA BASE DE DATOS EN RELACIÓN AL MÓDULO DE USERS
 * HAY
 *  USUARIOS
 *  ROLES
 *  PERMISOS
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

public class NodosEntities {
    //Las entidades usadas
    public enum Nodos{
        USUARIO(1),
        PERMISO(2),
        ROL(3);

        Nodos(int i) {
        }
    }

    //De momento sólo contemplo estos dos
    public enum Roles {
        Administrador(1),
        Anonimo(2);

        public int idx;
        Roles(int i) {
            this.idx = i;
        }
    }

    //COntemplo estos, pero pueden ser más o menos.
    public enum Permisos {
        RomperloTodo(1),
        RepararloTodo(2),
        AccesoBack(3),
        AltaUsers(4),
        SubirFiles(5),
        LeerFiles(6),
        ReadEstadisticas(7),
        ReadMapas(8);
        public int idx;
        Permisos(int i) {
            this.idx = i;
        }
    }
}