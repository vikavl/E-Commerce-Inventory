package vikavl.cloud.computing.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vikavl.cloud.computing.inventory.dto.InventoryChangeDto;
import vikavl.cloud.computing.inventory.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final ProductService service;

    public InventoryController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/changes")
    public ResponseEntity<List<InventoryChangeDto>> changes(@RequestParam int hours) {
        return new ResponseEntity<>(service.getChangesLastHours(hours), HttpStatus.OK);
    }
}
