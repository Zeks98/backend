package com.tda.tda.webApi.controllers;

import com.tda.tda.core.models.FileInfo;
import com.tda.tda.core.services.TdaService;
import com.tda.tda.message.ResponseMessage;
import com.tda.tda.webApi.dto.ExcelFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TdaController {

    @Autowired
    private TdaService tdaService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestBody ExcelFileDTO excelFile) {
        String message = "upload done";
        try {

            tdaService.saveFile(excelFile.getExcelFile());

           // message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            //message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/files")
    public /*ResponseEntity<List<FileInfo>>*/ String getListFiles() {
       /* List<FileInfo> fileInfos = tdaService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(TdaService.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);*/

        return "";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public /*ResponseEntity<Resource>*/ void getFile(@PathVariable String filename) {
        /*Resource file = tdaService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);*/
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
