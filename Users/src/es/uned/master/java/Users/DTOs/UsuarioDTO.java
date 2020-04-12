/**
 * DTO para los Usuarios, con muchas diferencias a las de Permisos y roles
 *
 * @author  Peter Fight (el negro de la capa de datos -roja y con una S fosforita-)
 * @version 0.007
 * @return sweet peace
 * @see <a href="https://www.youtube.com/watch?v=grQgC-mK2_g&list=PLvrWACVuHe6rImJAkWvF03xoGjwZkfYYo&index=2">SIXTO ROCKS!!!!</a>
 *
 */


package es.uned.master.java.Users.DTOs;

import es.uned.master.java.Utils.Utilidades;

import java.util.List;

public class UsuarioDTO implements IDTO{
    int Id;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    String Email;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    Boolean EmailConfirmado;

    public Boolean getEmailConfirmado() {
        return EmailConfirmado;
    }

    public void setEmailConfirmado(Boolean emailConfirmado) {
        EmailConfirmado = emailConfirmado;
    }

    public String getPassHash() {
        return PassHash;
    }

    public void setPass(String passHash) {
        PassHash = passHash;
    }

    public List<RolesDTO> getRoles() {
        return roles;
    }

    public void setRol(List<RolesDTO> roles) {
        this.roles = roles;
    }


    String PassHash;
    List<RolesDTO> roles;

    public UsuarioDTO(int Id, String Email, boolean EmailConfirmado, String PassHash, List<RolesDTO> roles)
    {
        this.Id = Id;
        this.Email = Email;
        this.EmailConfirmado = EmailConfirmado;
        this.PassHash = PassHash;
        this.roles = roles;
    }
    public UsuarioDTO(String Email, boolean EmailConfirmado, String PassHash, List<RolesDTO> roles)
    {
        this.Email = Email;
        this.EmailConfirmado = EmailConfirmado;
        this.PassHash = PassHash;
        this.roles = roles;
    }

    /**
     * Compruebo si el Email y el pass no están vacíos y si el email es un email válido, si no
     * devuelve false para que explote.
     * @return
     */
    @Override
    public boolean esValido() {
        boolean esValido = true;
        esValido = !Utilidades.cadenaNulaEmpty(this.Email)
                && !Utilidades.cadenaNulaEmpty(this.PassHash)
                    &&Utilidades.esMailValido(this.Email);

        return esValido;
    }
}
