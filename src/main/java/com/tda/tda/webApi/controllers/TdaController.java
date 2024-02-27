package com.tda.tda.webApi.controllers;

import com.tda.tda.core.services.TdaService;
import com.tda.tda.infrastructure.sql.entities.TdaEntity;
import com.tda.tda.message.ResponseMessage;
import com.tda.tda.webApi.dto.ExcelFileRequestDTO;
import com.tda.tda.webApi.dto.TdaResponseDTO;
import com.tda.tda.webApi.dto.TdaSingleResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TdaController {

    @Autowired
    private TdaService tdaService;

    private final ModelMapper mapper;

    public TdaController(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestBody ExcelFileRequestDTO excelFile) {
        String message = "upload done";
        try {
            List<TdaEntity> fileList = new ArrayList<>();
            fileList.add(new TdaEntity());

            tdaService.saveFile(excelFile.getExcelFile());

            // message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            //message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public List<TdaSingleResponseDTO> getFiles() {
        var files = new ArrayList<TdaSingleResponseDTO>();

        try {
            var result = this.tdaService.getAllFiles();

            // map from CORE to DTO model

            // fill "files" variable after mapping and return it
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return files;
    }

    @GetMapping
    public TdaResponseDTO getContentById(@PathVariable UUID id) {
        var file = new TdaResponseDTO();
        try {
            var result = this.tdaService.getContentById(id);

            // map from CORE to DTO model

            // fill "file" variable after mapping and return it

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return file;
    }


}
//    private boolean isValidExcelFile(String file) {
//        try {
//            // Decode Base64 string to byte array
//            byte[] decodedBytes = Base64.decodeBase64(file);
//
//            // Write byte array to a temporary file
//            String tempFilePath = "temp.xlsx"; // Temporary file path
//            try (FileOutputStream fos = new FileOutputStream(tempFilePath)) {
//                fos.write(decodedBytes);
//            }
//
//            // Validate the file as an Excel file using Apache POI
//            Workbook workbook = WorkbookFactory.create(new File(tempFilePath));
//            // If no exception is thrown, it indicates that the file is a valid Excel file
//            workbook.close();
//        } catch (Exception ex) {
//            System.out.println(ex);
//            return false;
//        }
//
//        return true;
//    }
//
//}
