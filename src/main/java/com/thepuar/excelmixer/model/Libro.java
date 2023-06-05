package com.thepuar.excelmixer.model;

import lombok.Data;

import java.util.List;

@Data
public class Libro {

    private String nombre;
    private List<Fila> filas;
}
