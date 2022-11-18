package ayaz.bro.restoran.controller;

import ayaz.bro.restoran.entity.Cook;
import ayaz.bro.restoran.entity.Dish;
import ayaz.bro.restoran.entity.Product;
import ayaz.bro.restoran.exception.DataNotFoundException;
import ayaz.bro.restoran.service.DishService;
import ayaz.bro.restoran.util.DataErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dish")
public class DishController {
    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping()
    public Dish getDish(@RequestParam(defaultValue = "id") String searchBy, @RequestParam(defaultValue = "1") String value) {
        if(searchBy.equals("id"))
            return dishService.findById(Integer.parseInt(value));
        else {
            return dishService.findByName(value);
        }
    }

    @GetMapping("/dishes")
    public List<Dish> getDishes(@RequestParam(defaultValue = "id") String category) {
        return dishService.findAll(category);
    }

    @GetMapping("/{id}/cook")
    public Cook getCook(@PathVariable int id) {
        return dishService.findById(id).getCooker();
    }

    @GetMapping("/{id}/ingridients")
    public List<Product> getIngridients(@PathVariable int id) {
        return dishService.findById(id).getIngridients();
    }

    @ExceptionHandler
    private ResponseEntity<DataErrorResponse> handleException(DataNotFoundException e) {
        DataErrorResponse response=new DataErrorResponse(
                "Dish doesn't found",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);//NOT FOUND - 404 status
    }
}
