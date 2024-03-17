package com.tda.tda.webApi.controllers;

import com.tda.tda.core.models.Tda;
import com.tda.tda.core.models.TdaSingle;
import com.tda.tda.core.services.TdaService;
import com.tda.tda.webApi.dto.ExcelFileRequestDTO;
import com.tda.tda.webApi.dto.TdaRequestDTO;
import com.tda.tda.webApi.dto.TdaResponseDTO;
import com.tda.tda.webApi.dto.TdaSingleResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TdaController {

    @Autowired
    private TdaService tdaService;

    @Autowired
    @Qualifier("mapper")
    private ModelMapper mapper;

    public TdaController() {

    }

    @PostMapping("/upload")
    public ArrayList<TdaResponseDTO> uploadFile(@RequestBody ExcelFileRequestDTO excelFile) {
        try {
            var result = tdaService.saveFile(excelFile.getExcelFile());

            var mapped = new ArrayList<TdaResponseDTO>();

            for (var x : result) {
                mapped.add(mapper.map(x, TdaResponseDTO.class));
            }

            return mapped;
        } catch (Exception e) {
            //message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponseDTO(message));
            return null;
        }
    }

    @GetMapping("/files")
    public List<TdaSingleResponseDTO> getFiles() {
        var files = new ArrayList<TdaSingleResponseDTO>();

        try {
            var result = this.tdaService.getAllFiles();
            // map from CORE to DTO model
            for (var x : result) {
                files.add(mapper.map(x, TdaSingleResponseDTO.class));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return files;
    }

    @GetMapping("/files/{id}/{sortedBy}")
    public ArrayList<TdaResponseDTO> getContentById(@PathVariable Long id, @PathVariable String sortedBy) {
        var data = new ArrayList<TdaResponseDTO>();

        try {
            var result = this.tdaService.getContentById(id, sortedBy);

            // map from CORE to DTO model
            for (var x : result) {
                data.add(mapper.map(x, TdaResponseDTO.class));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return data;
    }

    @GetMapping("/filter/{fileId}/{searchTerm}/{sortBy}")
    public ArrayList<TdaResponseDTO> filterBySearchTerm(@PathVariable int fileId, @PathVariable String searchTerm, @PathVariable String sortBy) {
        var data = new ArrayList<TdaResponseDTO>();

        try {
            var files = this.tdaService.getFilteredFilesBySearchTerm(fileId, searchTerm, sortBy);

            // map from CORE to DTO model
            for (var x : files) {
                data.add(mapper.map(x, TdaResponseDTO.class));
            }

            return data;
        } catch (Exception ex) {
        }

        return data;
    }

    @PutMapping("/{id}")
    public TdaResponseDTO updateUser(@PathVariable int id, @RequestBody TdaRequestDTO row) {
        var data = new TdaResponseDTO();

        try {
            var mapToPass = mapper.map(row, Tda.class);
            var file = this.tdaService.updateRow(mapToPass);

            // response
            data = mapper.map(file, TdaResponseDTO.class);

        } catch (Exception ex) {
        }

        return data;
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
