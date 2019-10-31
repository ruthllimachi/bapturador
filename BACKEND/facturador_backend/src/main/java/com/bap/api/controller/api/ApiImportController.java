package com.bap.api.controller.api;

import com.bap.api.services.api.ApiClienteService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jacqueline
 */
@RestController
@RequestMapping(value = "/apiImport", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiImportController {

    @Autowired
    private ApiClienteService apiClienteService;
    private String fileLocation;
    private Path fileStorageLocation;

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

//    @PostMapping("/import")
//    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
//
//        System.out.println("............................en import............" + reapExcelDataFile);
//
//        List<ApiCliente> listaClientes = new ArrayList<ApiCliente>();
//        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
//        XSSFSheet worksheet = workbook.getSheetAt(0);
//
//        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
//            ApiCliente cliente = new ApiCliente();
//
//            XSSFRow row = worksheet.getRow(i);
//            System.out.println("...codigo ===" + row.getCell(1).getStringCellValue());
//            cliente.setCodigoCliente(row.getCell(1).getStringCellValue());
//            cliente.setNitEmisor((long) row.getCell(3).getNumericCellValue());
//            System.out.println("...NIT EMISOR ==" + cliente.getNitEmisor());
//            listaClientes.add(cliente);
//
//        }
//    }
    

    @PostMapping("/uploadExcelFile/xls")
    public String uploadExcelFile(MultipartFile file) throws IOException {

        System.out.println("........dentro de if ****************" + file);
        System.out.println("........dentro de if ****************" + file.getOriginalFilename());

        if (file != null && file.getOriginalFilename() != null
                && (file.getOriginalFilename().endsWith("xls") || file.getOriginalFilename().endsWith("xlsx"))) {
            InputStream in = file.getInputStream();
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();

            FileOutputStream f = new FileOutputStream(fileLocation);

            int ch = 0;
            while ((ch = in.read()) != -1) {
                f.write(ch);
            }
            f.flush();
            f.close();

        }

        return "excel";
    }
    
    
    @PostMapping("/uploadfile")
    public String uploadfile(@RequestParam("uploadfile") MultipartFile[] submissions, Model model) {
        System.out.println(".......................................................................................");
		try {
                    System.out.println("...file ==="+MultipartFile.class);
//                    apiClienteService.cargarClientes(file);
//			fileServices.store(file);
			model.addAttribute("message", "File uploaded successfully!");
		} catch (Exception e) {
			model.addAttribute("message", "Fail! -> uploaded filename: " + MultipartFile.class);
		}
        return "multipartfile/uploadform.html";
    }

    
  
}
