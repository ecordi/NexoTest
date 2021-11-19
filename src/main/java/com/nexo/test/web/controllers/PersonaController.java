package com.nexo.test.web.controllers;

import com.nexo.test.domain.Persona.PersonaModel;
import com.nexo.test.domain.Persona.PersonaService;
import com.nexo.test.domain.utils.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("")
    public ResponseEntity<List<PersonaModel>> findAll() {
        return ResponseEntity.ok(personaService.findAll());
    }

    @PostMapping
    public ResponseEntity<PersonaModel> create(@RequestBody PersonaModel persona) {
        return ResponseEntity.ok(personaService.create(persona));
    }

    @PutMapping
    public ResponseEntity<PersonaModel> update(@RequestBody PersonaModel persona) {
        if (!personaService.findByDni(persona.getDni()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personaService.update(persona));
    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity delete(@PathVariable Long dni) {
        if (!personaService.findByDni(dni).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        personaService.delete(dni);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{dni}")
    public ResponseEntity<PersonaModel> findByDni(@PathVariable(name = "dni") Long dni) throws Exception {
        if (!personaService.findByDni(dni).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personaService.findByDni(dni).get());
    }

    @GetMapping("{nombre}")
    public ResponseEntity<List<PersonaModel>> findByNombre(@PathVariable(name = "nombre") String nombre) {
        if (!personaService.findByNombre(nombre).isEmpty()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personaService.findByNombre(nombre));
    }

    @GetMapping("{edad}")
    public ResponseEntity<List<PersonaModel>> findByEdad(@PathVariable(name="edad") int edad) {
        return ResponseEntity.ok(personaService.findByEdad(edad));
    }

    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormat = new SimpleDateFormat();
        String currentDateandTime = dateFormat.format(new Date());
        String header = "Content-Disposition";
        String headerValue = "attachment; filename= personas_" + currentDateandTime + ".csv";
        response.setHeader(header, headerValue);
        List<PersonaModel> list = personaService.findAll();
        ExcelExporter excelExporter = new ExcelExporter(list);
        excelExporter.exportData(response);
    }
}