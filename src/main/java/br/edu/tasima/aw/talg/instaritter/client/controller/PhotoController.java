package br.edu.tasima.aw.talg.instaritter.client.controller;

import br.edu.tasima.aw.talg.instaritter.client.model.Photo;
import br.edu.tasima.aw.talg.instaritter.client.service.PhotoService;
import br.edu.tasima.aw.talg.instaritter.client.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class PhotoController {

    private final RestClientService service;
    private final PhotoService photoService;

    @Autowired
    public PhotoController(RestClientService service, PhotoService photoService){
        this.service = service;
        this.photoService = photoService;
    }

    /**
     * Get all photos
     * @param model to bind photos to view
     * @return the photos.html page
     */
    @GetMapping("/")
    public String showPhotoForm(Model model) {
        List<Photo> photoList = service.findAllPhotos();
        model.addAttribute("photoList", photoList);
        return "photoForm";
    }

    @PostMapping("/uploadPhoto")
    public String handlePhotoUpload(@RequestParam("photoFile") MultipartFile photoFile,
                                      @RequestParam("description") String description,
                                      RedirectAttributes redirectAttributes) {

        try {
            byte[] photoFileByteArray = photoService.validateImageFile(photoFile);
            //TODO Create save enpoint on server side
//            service.save(photoFileByteArray, description);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + photoFile.getOriginalFilename() + "!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message",
                    "Error while uploading " + photoFile.getOriginalFilename() + "!");
        }

        return "redirect:/";
    }
}