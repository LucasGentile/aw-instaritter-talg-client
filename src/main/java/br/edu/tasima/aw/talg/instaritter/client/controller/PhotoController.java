package br.edu.tasima.aw.talg.instaritter.client.controller;

import br.edu.tasima.aw.talg.instaritter.client.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PhotoController {

    //define a service constant
    private final RestClientService service;

    //Argument contructor of controller, pass in the service
    //and Autowire it
    @Autowired
    public PhotoController(RestClientService service){
        this.service = service;
    }

    /**
     * Get all photos
     * @param model to bind photos to view
     * @return the photos.html page
     */
    @GetMapping("photos")
    public String getAll(Model model){
        model.addAttribute("photos", service.findAllPhotos());
        return "photos";
    }

    /**
     * Get a photo by id
     * @param id of photo
     * @param model binding a photo to view
     * @return a photo page
     */
    @RequestMapping("/photo/{id}")
    public String getById(@PathVariable String id, Model model){
        model.addAttribute("photo", service.findPhotoById(id));
        return "showphoto";
    }
}