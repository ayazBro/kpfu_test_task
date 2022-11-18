package ayaz.bro.restoran.controller;

import ayaz.bro.restoran.entity.Cook;
import ayaz.bro.restoran.entity.Dish;
import ayaz.bro.restoran.exception.DataNotFoundException;
import ayaz.bro.restoran.service.CookService;
import ayaz.bro.restoran.util.DataErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cook")
public class CookController {
    private final CookService cookService;

    @Autowired
    public CookController(CookService cookService) {
        this.cookService = cookService;
    }

    @GetMapping()
    public Cook getCook(@RequestParam(defaultValue = "id") String searchBy, @RequestParam(defaultValue = "1") String value) {
        if(searchBy.equals("id"))
            return cookService.findById(Integer.parseInt(value));
        else {
            return cookService.findByName(value);
        }
    }

    @GetMapping("/cooks")
    public List<Cook> getCooks(@RequestParam(defaultValue = "id") String category) {
        return cookService.findAll(category);
    }

    @GetMapping("/{id}/dishes")
    public List<Dish> getDishes(@PathVariable int id) {
        return cookService.findById(id).getDishes();
    }

    @ExceptionHandler
    private ResponseEntity<DataErrorResponse> handleException(DataNotFoundException e) {
        DataErrorResponse response=new DataErrorResponse(
                "Cook doesn't found",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);//NOT FOUND - 404 status
    }
}