package com.numberia.OrcamentosAPI.Models;

import com.numberia.OrcamentosAPI.Roles.UsuarioRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuarios")
public class UsuarioModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    @NotNull
    @NotEmpty
    @Size(min=3, max = 100)
    private String nome;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    @NotNull
    @NotEmpty
    @Size(min=3, max = 150)
    private String email;

    @Column(name = "senha", nullable = false, length = 50)
    @NotNull
    @NotEmpty
    @Size(min=8, max = 255)
    private String senha;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsuarioRole role;


    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)

    private List<RecursoModel> recursos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private List<ClienteModel> clientes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private List<ObraModel> obras;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<RecursoModel> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoModel> recursos) {
        this.recursos = recursos;
    }

    public List<ClienteModel> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteModel> clientes) {
        this.clientes = clientes;
    }

    public List<ObraModel> getObras() {
        return obras;
    }

    public void setObras(List<ObraModel> obras) {
        this.obras = obras;
    }

    public UsuarioRole getRole() {
        return role;
    }

    public void setRole(UsuarioRole role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role== UsuarioRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
