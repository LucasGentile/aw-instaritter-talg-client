package br.edu.tasima.aw.talg.instaritter.client.service;

import br.edu.tasima.aw.talg.instaritter.client.model.Photo;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

public class PhotoService {
    public byte[] validateImageFile(MultipartFile imageFile) throws IOException {
        String filename = StringUtils.cleanPath(imageFile.getOriginalFilename());
        try {
            if (imageFile.isEmpty()) {
                throw new IOException("System cannot save empty image " + filename);
            }
            return imageFile.getBytes();
        } catch (IOException e) {
            throw new IOException("Failed to process image file " + filename, e);
        }
    }

    public Photo processImageFile(byte[] imageFileByteArray, String description) {

        Photo photo = new Photo();

        photo.setDescription(description);
        photo.setDateTime(LocalDateTime.now());
        photo.setImage(Base64.getEncoder().encodeToString(imageFileByteArray));

        return photo;
    }
}
