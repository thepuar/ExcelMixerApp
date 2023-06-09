package com.thepuar.excelmixer.service;

import com.thepuar.excelmixer.model.LibroMedicion;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReadExcelService {

    @Setter
    @Value("${excelapp.input.folder}")
    private String inputFolder;

    public List<LibroMedicion> getLibroMedicionFromFolder(){
        List<LibroMedicion> librosMedicion = this.getExcels().stream().map(this::getDataFromFile).collect(Collectors.toList());
        return librosMedicion;
    }

    public List<File> getExcels(){
        Iterator<File> iteratorFiles = FileUtils.iterateFiles(new File(inputFolder),new String[]{"xlsx"},true);
        List<File> files = new ArrayList<>();
        while(iteratorFiles.hasNext()){
            files.add(iteratorFiles.next());
        }
        return files;
    }

    public LibroMedicion getDataFromFile(File file)  {
        log.info("Leyendo fichero: "+file.getAbsolutePath());
        LibroMedicion libroMedicion = new LibroMedicion();
        libroMedicion.setNombreDeArchivo(file.getName());

        int numeroFila=0;
        int numeroColumna=0;
        try (Workbook libro = WorkbookFactory.create(file)){

        Sheet hoja = libro.getSheetAt(0);

        //Fila NºExperimento
        Row row = hoja.getRow(numeroFila);
        libroMedicion.setNumeroDeExperimento(this.getDataStringFromCell(row,2));
        libroMedicion.setFecha(this.getDataLocalDateTimeFromCell(row,5));
        libroMedicion.setHoraSolar(this.getDataDoubleFromCell(row,10));

        //File Medicion
        numeroFila=3;
        row = hoja.getRow(numeroFila);
        libroMedicion.setMedicion(this.getDataStringFromCell(row,0));
        libroMedicion.setTipologiaPlaca(this.getDataStringFromCell(row,4));
        libroMedicion.setSMP6(this.getDataDoubleFromCell(row,8));

        //RT1
        numeroFila = 5;
        row = hoja.getRow(numeroFila);
        libroMedicion.setRT1(this.getDataDoubleFromCell(row,8));

        //Nomenclatura
        numeroFila=7;
        row = hoja.getRow(numeroFila);
        libroMedicion.setNomenclatura(this.getDataStringFromCell(row,0));
        libroMedicion.setTension(this.getDataDoubleFromCell(row,8));

        //Tipo material
        numeroFila = 9;
        row = hoja.getRow(numeroFila);
        libroMedicion.setTipoDeMaterial(this.getDataStringFromCell(row,0));
        libroMedicion.setAnguloDeInclinacion(this.getDataStringFromCell(row,4));
        libroMedicion.setIntensidad(this.getDataDoubleFromCell(row,8));

        //Tamanyo particula
        numeroFila = 11;
        row = hoja.getRow(numeroFila);

        libroMedicion.setTamanyoDeParticula(this.getDataDoubleFromCell(row,0));
        libroMedicion.setPesoParticula(this.getDataDoubleFromCell(row,4));
        libroMedicion.setPotencia(this.getDataDoubleFromCell(row,8));

        //Temperatura ambiente
        numeroFila = 13;
        row = hoja.getRow(numeroFila);
        libroMedicion.setTAmbiente(this.getDataDoubleFromCell(row,0));
        libroMedicion.setTCamaraTermigrafica(this.getDataDoubleFromCell(row,4));
        libroMedicion.setHora(this.getDataDoubleFromCell(row,8));

        //Analisis comparativo
        numeroFila = 15;
        row = hoja.getRow(numeroFila);
        libroMedicion.setAnalisisCompDeT(this.getDataDoubleFromCell(row,0));
        libroMedicion.setSuperficieDeLaMuestra(this.getDataDoubleFromCell(row,4));
        libroMedicion.setInclinacionSolar(this.getDataDoubleFromCell(row,8));

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return libroMedicion;

    }

    public String getDataStringFromCell(Row row, int number){
        String value ="";
        try {
            value =  row.getCell(number).getStringCellValue();
        }catch(Exception e){
            char character = 'A';
            character=(char)(character+number+1);
            log.error("String - Error de formato en la celda: "+character+(row.getRowNum()+1));
        }
        return value;
    }

    public Double getDataDoubleFromCell(Row row, int number){
        Double value =-100.0;
        try {
            value =  row.getCell(number).getNumericCellValue();

        }catch(Exception e){

            char character = 'A';
            character=(char)(character+number+1);
            log.error("Double - Error de formato en la celda: "+character+(row.getRowNum()+1)+" - Contenido: "+row.getCell(number).getStringCellValue());
            value = Double.parseDouble(row.getCell(number).getStringCellValue().replace("m","").trim().replace(',','.'));
            log.warn("Lo he transformado al valor "+value);
        }
        return value;
    }

    public LocalDateTime getDataLocalDateTimeFromCell(Row row, int number){
        LocalDateTime value = null;
        try {
            value =  row.getCell(number).getLocalDateTimeCellValue();
        }catch(Exception e){
            char character = 'A';
            character=(char)(character+number+1);
            log.error("Data - Error de formato en la celda: "+character+(row.getRowNum()+1));
        }
        return value;
    }




}
