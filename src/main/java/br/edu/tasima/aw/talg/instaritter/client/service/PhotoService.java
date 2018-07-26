package br.edu.tasima.aw.talg.instaritter.client.service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
