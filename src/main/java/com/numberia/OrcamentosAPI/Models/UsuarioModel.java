package com.numberia.OrcamentosAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuarios")
public class UsuarioModel {
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
    @Size(min=8, max = 50)
    private String senha;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RecursoModel> recursos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ClienteModel> clientes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ObraModel> obras;



}
