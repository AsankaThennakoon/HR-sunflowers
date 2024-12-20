package com.example.HR_Sunflowers.employee.controller;

import com.example.HR_Sunflowers.employee.dtos.request.CreateEmployeeDto;
import com.example.HR_Sunflowers.employee.dtos.response.EmployeeGeneralDto;
import com.example.HR_Sunflowers.employee.entity.Employee;
import com.example.HR_Sunflowers.employee.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/employee")

public class EmployeeController {
   // Injected by Spring

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Value("${upload.directory}")
    private String uploadDirectory;


    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<EmployeeGeneralDto> getEmployee(@PathVariable Integer id){


        return employeeService.getEmployee(id);

    }


    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeGeneralDto>> getEmployees(){

        return employeeService.getEmployees();

    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(
            @RequestPart("employee") String employeeJson,
            @RequestPart("image") MultipartFile image) {

        System.out.println("Image Content Type: " + image.getContentType());
        System.out.println("Employee JSON: " + employeeJson);

        try {

            // Parse the JSON string into CreateEmployeeDto
            ObjectMapper objectMapper = new ObjectMapper();
            CreateEmployeeDto createEmployeeDto = objectMapper.readValue(employeeJson, CreateEmployeeDto.class);

            // Save the file
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            String directoryPath = new File("src/main/resources/static/images").getAbsolutePath();
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it does not exist
            }
            String filePath = directoryPath + File.separator + fileName;
            File file = new File(filePath);
            image.transferTo(file);
            System.out.println("Saving file to: " + filePath);

            // Call the service with the uploaded file name
            return employeeService.addEmployee(createEmployeeDto, fileName);
        } catch (IOException e) {
            System.out.println(e.toString());
            return ResponseEntity.status(500).body(null);

        }
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<String> deleteEmployee(@RequestParam Integer id){
        return  employeeService.deleteEmployee(id);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<String> updateEmployee(@RequestParam Integer id,@RequestParam String newName){
        return employeeService.updateEmployee(id,newName);
    }
}
