package br.edu.tasima.aw.talg.instaritter.client.service;

import br.edu.tasima.aw.talg.instaritter.client.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestClientService {


    @Value("${instaritter.service.url}")
    private String GET_BASE_API_URL;

    private final String GET_PHOTO_API = "photos";

    private final RestTemplate restTemplate;

    @Autowired
    public RestClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get all photos
     *
     * @return a list
     */
    public List<Photo> findAllPhotos() {
        return Arrays.stream(restTemplate.getForObject(GET_BASE_API_URL + GET_PHOTO_API, Photo[].class)).collect(Collectors.toList());
    }

    /**
     * Get a photo by id
     *
     * @param id of photo
     * @return a photo
     */
    public Photo findPhotoById(String id) {
        return restTemplate.getForObject(GET_BASE_API_URL + GET_PHOTO_API + "/" + id, Photo.class);
    }

    /**
     * Save a new photo
     *
     * @param photo photo to be saved by server
     */
    public void savePhoto(Photo photo) {

        HttpHeaders requestHeaders = new HttpHeaders();
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(mediaTypeList);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(photo, requestHeaders);

        restTemplate.exchange(GET_BASE_API_URL + GET_PHOTO_API, HttpMethod.PUT, requestEntity, Photo.class);
    }
}