package davimoraes2017322.rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;

@RestController
public class RequestController {

    RequestController(){}

    @CrossOrigin
    @RequestMapping("/call")
    public String call() {

        return RequestHandler.caller();
    }

    @CrossOrigin
    @PostMapping("/create")
    public String create(@RequestBody Map<String, String> body) {

        return RequestHandler.creator(body);
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public String update(@PathVariable String id, @RequestBody Map<String, String> body) {

        return RequestHandler.updateChecker(id, body);
    }

    @CrossOrigin
    @GetMapping("/search/{id}")
    public String search(@PathVariable String id) {

        return RequestHandler.searcher(id);
    }

    @CrossOrigin
    @GetMapping("/position/{id}")
    public String showPosition(@PathVariable String id) {

        return RequestHandler.positionChecker(id);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {

        return RequestHandler.deleteId(id);
    }

    @CrossOrigin
    @DeleteMapping("/delete/amount/{value}")
    public String deleteAmount(@PathVariable String value) {

        return RequestHandler.deleteGroup(value);
    }

}

