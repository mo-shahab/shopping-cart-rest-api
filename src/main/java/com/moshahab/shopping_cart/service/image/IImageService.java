package com.moshahab.shopping_cart.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.moshahab.shopping_cart.dto.ImageDto;
import com.moshahab.shopping_cart.model.Image;

public interface IImageService {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);
}
