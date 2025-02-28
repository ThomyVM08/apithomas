package com.narvasoft.apirest.controllers;

import com.narvasoft.apirest.models.Mascotas;
import com.narvasoft.apirest.service.MascotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/mascotas")
public class MascotasController {
    @Autowired
    private MascotasService mascService;//principio de Inversión de Dependencias (IoD)

    //Create a new user
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Mascotas user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascService.save(user));
    }

    //Get a user
    @GetMapping("/{id}")
    public ResponseEntity<?> readOne(@PathVariable(value = "id") Long id) {
        Optional<Mascotas> oMasc = mascService.findById(id);
        //si no se encuentra el usuario(el id) se retorna un not found
        if (!oMasc.isPresent()) {
            return ResponseEntity.notFound().build();
        }//de locontrario se retorna el usuario
        return ResponseEntity.ok(oMasc);
    }

    //Update an user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Mascotas userDetails, @PathVariable(value = "id") Long id) {
        Optional<Mascotas> masc = mascService.findById(id);
        if (!masc.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        //BeanUtils.copyProperties(userDetails, user.get());
        masc.get().setNombre(userDetails.getNombre());
        masc.get().setRaza(userDetails.getRaza());
        masc.get().setEdad(userDetails.getEdad());
        masc.get().setEmail(userDetails.getEmail());
        masc.get().setTelefono(userDetails.getTelefono());
        return ResponseEntity.status(HttpStatus.CREATED).body(mascService.save(masc.get()));
    }

    //Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (!mascService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        mascService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //Get all users
    @GetMapping
    public List<Mascotas> readAll() {
        List<Mascotas> Masc = StreamSupport//<--hereda de Object y me trae los stream
                .stream(mascService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return Masc;
    }
}
