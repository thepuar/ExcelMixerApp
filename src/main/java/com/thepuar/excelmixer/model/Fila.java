package com.thepuar.excelmixer.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Fila {

    private String nombreDeArchivo;
    private String numeroDeExperimento;
    private String medicion;
    private LocalDateTime fecha;
    private Double horaSolar;
    private String tipologiaPlaca;
    private Double SMP6;
    private Double RT1;
    private String nomenclatura;
    private Double tension;
    private String tipoDeMaterial;
    private String anguloDeInclinacion;
    private Double intensidad;
    private Double tamanyoDeParticula;
    private Double pesoParticula;
    private Double potencia;
    private Double tAmbiente;
    private Double tCamaraTermigrafica;
    private Double hora;
    private Double analisisCompDeT;
    private Double superficieDeLaMuestra;
    private Double inclinacionSolar;
}
