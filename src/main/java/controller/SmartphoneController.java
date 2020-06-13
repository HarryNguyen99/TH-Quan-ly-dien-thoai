package controller;

import model.Smartphone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.SmartphoneService;

@RestController
@RequestMapping("/smartphones")
public class SmartphoneController {

    @Autowired
    private SmartphoneService smartphoneService;

    @GetMapping("/lists")
    public ResponseEntity<Iterable<Smartphone>> showList(){
        Iterable<Smartphone> smartphonesList = smartphoneService.findAll();
        return new ResponseEntity<>(smartphonesList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ModelAndView allPhonePage (){
        ModelAndView modelAndView =new ModelAndView("all-phones");
        modelAndView.addObject("allPhones", smartphoneService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createSmartphone(){
        ModelAndView modelAndView = new ModelAndView("new-phone");
        modelAndView.addObject("sPhone",new Smartphone());
        return modelAndView;
    }

    @PostMapping("/creates")
    public ResponseEntity<Smartphone> createPhone(@RequestBody Smartphone smartphone){
        smartphoneService.save(smartphone);
        return new ResponseEntity<>(smartphone, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Smartphone> deletePhone(@PathVariable Long id){
       Smartphone smartphone = smartphoneService.findById(id);
       if (smartphone != null){
           smartphoneService.remove(id);
           return new ResponseEntity<>(HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST,
//        produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Smartphone editPhone(@PathVariable Long id, @RequestBody Smartphone smartphone){
//        smartphone.setId(id);
//        return smartphoneService.save(smartphone);
//    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Smartphone> editPhones(@PathVariable Long id, @RequestBody Smartphone smartphone){
        Smartphone smartphone1 = smartphoneService.findById(id);
        if (smartphone1 != null){
            smartphone.setId(id);
            smartphoneService.save(smartphone);
            return new ResponseEntity<>(smartphone, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPhone(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("edit-phone");
        Smartphone smartphone = smartphoneService.findById(id);
        modelAndView.addObject("sPhone",smartphone);
        return modelAndView;
    }
}
