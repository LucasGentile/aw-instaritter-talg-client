package br.edu.tasima.aw.talg.instaritter.client.service;

import br.edu.tasima.aw.talg.instaritter.client.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestClientService {

    private final String GET_BASE_URL = "http://localhost:8080/api/photos";

    private final RestTemplate restTemplate;

    @Autowired
    public RestClientService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    /**
     * Get all photos
     * @return a list
     */
    public List<Photo> findAllPhotos(){
        return Arrays.stream(restTemplate.getForObject(GET_BASE_URL,Photo[].class)).collect(Collectors.toList());
    }

    /**
     * Get a photo by id
     * @param id of photo
     * @return a photo
     */
    public Photo findPhotoById(String id){
        return restTemplate.getForObject(GET_BASE_URL + "/" + id, Photo.class);
    }
}