package com.backjps.backjps.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backjps.backjps.Model.Image;

@Repository("ImageRepository")
public interface ImageRepository extends CrudRepository<Image, Integer> {
    
}
