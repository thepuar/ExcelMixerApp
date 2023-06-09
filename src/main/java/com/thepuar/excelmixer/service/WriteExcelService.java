package com.thepuar.excelmixer.service;

import com.thepuar.excelmixer.model.LibroMedicion;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class WriteExcelService {

    @Value("${excelapp.output}")
    String outputPath;

    public void writeExcel(List<LibroMedicion> libroMedicions){
        File file = new File(outputPath);
       try (Workbook workbook = new XSSFWorkbook()){
           Sheet sheet = workbook.createSheet("Tabla");
           int rowPosition = 0;
           Row row = sheet.createRow(rowPosition++);
           this.createHeader(row);
           for(LibroMedicion medicion : libroMedicions){
               row = sheet.createRow(rowPosition++);
               this.createRow(row, medicion);
           }

        workbook.write(new FileOutputStream(file));
       }catch (IOException  e){

       }
    }
    public void createRow(Row row, LibroMedicion libroMedicion){

        Integer position = 0;
        row.createCell(position++).setCellValue(libroMedicion.getNombreDeArchivo());
        row.createCell(position++).setCellValue(libroMedicion.getNumeroDeExperimento());
        row.createCell(position++).setCellValue(libroMedicion.getMedicion());
        row.createCell(position++).setCellValue(libroMedicion.getFecha());
        jumpOrWrite(row,libroMedicion.getHoraSolar(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getHoraSolar());
        row.createCell(position++).setCellValue(libroMedicion.getTipologiaPlaca());
        jumpOrWrite(row,libroMedicion.getSMP6(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getSMP6());
        jumpOrWrite(row,libroMedicion.getRT1(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getRT1());
        row.createCell(position++).setCellValue(libroMedicion.getNomenclatura());
        jumpOrWrite(row,libroMedicion.getTension(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getTension());
        row.createCell(position++).setCellValue(libroMedicion.getTipoDeMaterial());
        row.createCell(position++).setCellValue(libroMedicion.getAnguloDeInclinacion());
        jumpOrWrite(row,libroMedicion.getIntensidad(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getIntensidad());
        jumpOrWrite(row,libroMedicion.getTamanyoDeParticula(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getTamanyoDeParticula());
        jumpOrWrite(row,libroMedicion.getPesoParticula(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getPesoParticula());
        jumpOrWrite(row,libroMedicion.getPotencia(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getPotencia());
        jumpOrWrite(row,libroMedicion.getTAmbiente(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getTAmbiente());
        jumpOrWrite(row,libroMedicion.getTCamaraTermigrafica(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getTCamaraTermigrafica());
        jumpOrWrite(row,libroMedicion.getHora(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getHora());
        if(libroMedicion.getAnalisisCompDeT()==null){
         row.createCell(position++).setCellValue("");
        }else {
            row.createCell(position++).setCellValue(libroMedicion.getAnalisisCompDeT());
        }
        jumpOrWrite(row,libroMedicion.getSuperficieDeLaMuestra(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getSuperficieDeLaMuestra());
        jumpOrWrite(row,libroMedicion.getInclinacionSolar(),position++);
        //row.createCell(position++).setCellValue(libroMedicion.getInclinacionSolar());

    }

    public void jumpOrWrite(Row row, Double value, Integer position){
        if(value==null){
           // position++;
        }else{
            row.createCell(position++).setCellValue(value);
        }
    }

    public void createHeader(final Row row){
        int position = 0;
        Cell cell = null;
        for(String header: this.getHeaderNames()){
            cell = row.createCell(position++);
            cell.setCellValue(header);
        }
    }

    private List<String> getHeaderNames(){
        return List.of(
                "NOMBRE DE ARCHIVO",
                "NÚMERO DE EXPERIMENTO",
                "MEDICIÓN",
                "FECHA",
                "HORA SOLAR",
                "TIPOLOGÍA PLACA",
                "SMP6",
                "RT1",
                "NOMENCLATURA",
                "TENSIÓN",
                "TIPO DE MATERIAL",
                "ÁNGULO DE INCLINACIÓN",
                "INTENSIDAD",
                "TAMAÑO DE PARTÍCULA",
                "PESO PARTÍCULA(g)",
                "POTENCIA(W)",
                "T AMBIENTE(ºC)",
                "T CÁMARA TERMOGRÁFICA",
                "HORA",
                "ANÁLISIS COMP DE T",
                "SUPERFICIE DE LA MUESTRA",
                "INCLINACION SOLAR"
        );
    }
}
