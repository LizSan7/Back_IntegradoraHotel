package mx.edu.utez.Integradora_Hotel.controller.habitacion;

import jakarta.validation.Valid;
import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.service.habitacion.HabitacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/habitacion")

public class HabitacionController {
    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return habitacionService.getAll();
    }

    @PostMapping("/crear/")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody HabitacionDto habitacionDto){
        return  habitacionService.register(habitacionDto.toEntity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return  habitacionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        return habitacionService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody HabitacionDto habitacionDto){
        ResponseEntity<ApiResponse> updateResponse = habitacionService.update(id, habitacionDto.toEntity());
        return updateResponse;
    }
    // En HabitacionController.java
    @PutMapping("/{id}/changestatus")
    public ResponseEntity<ApiResponse> changeRoomStatus(@PathVariable Long id, @RequestBody Map<String, Boolean> status) {
        if (status.containsKey("estatus")) {
            return habitacionService.changeRoomStatus(id, status.get("estatus"));
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "Estatus no proporcionado"), HttpStatus.BAD_REQUEST);
        }
    }

}
