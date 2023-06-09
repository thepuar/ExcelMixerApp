package com.thepuar.excelmixer.service;

import com.thepuar.excelmixer.model.LibroMedicion;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ExcelMixerServiceTest{

    public static final java.lang.String FOLDER_PATH = "C:\\Users\\xicle\\project\\data\\Carpeta Prueba B";
    ReadExcelService service;

    @BeforeEach
    void setup(){
        service = new ReadExcelService();
    }

    @Test
    void getDataFromFileTest(){
        String path = FOLDER_PATH+"\\EXP_01_PJ(23-03-23)/EXP_01_PJ_MED_00.xlsx";
        File file = new File(path);
        LibroMedicion libroMedicion = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            libroMedicion = service.getDataFromFile(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("EXP_01_PJ_MED_00.xlsx",libroMedicion.getNombreDeArchivo());
        assertEquals("EXP_01_PJ",libroMedicion.getNumeroDeExperimento());
        //LocalDateTime fecha = LocalDateTime.parse("27/03/2023T00:00:00.000",dateTimeFormatter);
        LocalDateTime fecha = LocalDateTime.of(2023,03,27,0,0,0);
        assertEquals(fecha , libroMedicion.getFecha());
        assertEquals(0.0,libroMedicion.getHora());
        assertEquals("PnIn_MED_00.01",libroMedicion.getMedicion());
        assertTrue(libroMedicion.getTipologiaPlaca().contains("Placa solar orientado"));
        assertEquals(0.0,libroMedicion.getSMP6());
        assertEquals(0.0,libroMedicion.getRT1());
        assertEquals("Pn_In_So_Es", libroMedicion.getNomenclatura());
        assertEquals(14.06,libroMedicion.getTension());
        assertEquals("",libroMedicion.getTipoDeMaterial());
        assertEquals("0º",libroMedicion.getAnguloDeInclinacion());
        assertEquals(0.03,libroMedicion.getIntensidad());
        assertEquals(0.0,libroMedicion.getTamanyoDeParticula());
        assertEquals(0.0,libroMedicion.getPesoParticula());
        assertEquals(0.4218,libroMedicion.getPotencia());
        assertEquals(0.0, libroMedicion.getTAmbiente());
        assertEquals(0.0,libroMedicion.getTCamaraTermigrafica());
        assertEquals(0.0, libroMedicion.getHora());
        assertEquals("",libroMedicion.getAnalisisCompDeT());
        assertEquals(0.0,libroMedicion.getSuperficieDeLaMuestra());
        assertEquals(0.0,libroMedicion.getInclinacionSolar());

    }

    @Test
    void getExcelsTest(){
        service.setInputFolder(FOLDER_PATH);
        System.out.println(service.getExcels().size());
        for(File file : service.getExcels()){
            log.info("Nombre fichero: "+file.getAbsolutePath());
        }
    }

    @Test
    void validaExcelsTest(){
        service.setInputFolder(FOLDER_PATH);
        log.info(service.getExcels().size()+"");
        for(File file : service.getExcels()){
            service.getDataFromFile(file);
        }
    }
}
