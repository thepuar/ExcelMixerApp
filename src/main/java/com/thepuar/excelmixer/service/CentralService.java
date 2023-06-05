package com.thepuar.excelmixer.service;

import com.thepuar.excelmixer.model.LibroMedicion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CentralService {

    private final ReadExcelService readExcelService;

    private final WriteExcelService writeExcelService;

    @PostConstruct
    public void createTableFromFiles(){
        List<LibroMedicion> mediciones = readExcelService.getLibroMedicionFromFolder();
        writeExcelService.writeExcel(mediciones);

    }
}
