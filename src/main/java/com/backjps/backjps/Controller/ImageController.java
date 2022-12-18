package com.backjps.backjps.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backjps.backjps.Model.Image;
import com.backjps.backjps.Repository.ImageRepository;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    @Qualifier("ImageRepository")
    private ImageRepository repo;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Image image){
        try{
            repo.save(image);
            return new ResponseEntity<Image>(repo.findById(image.getId()).get(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<String>("No se ha podido insertar", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Image image, @PathVariable Integer id) {
       try{
            Image img = repo.findById(id).get();

            if (image.getTitle() != null)
                img.setTitle(image.getTitle());
            if (image.getDescription() != null)
                img.setDescription(image.getDescription());
            if (image.getCategory() != null)
                img.setCategory(image.getCategory());
            if (image.getUrl() != null)
                img.setUrl(image.getUrl());

            repo.save(img);
            return new ResponseEntity<Image>(img, HttpStatus.OK);
       }catch(Exception ex){
            return new ResponseEntity<String>("No se ha podido actualizar", HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        try{
            ArrayList<Image> imgs = new ArrayList<Image>();

            for(Image img : repo.findAll())
                imgs.add(img);

            return new ResponseEntity<ArrayList<Image>>(imgs, HttpStatus.OK);  
        }catch(Exception ex){
            return new ResponseEntity<String>("No se ha encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        try{
            Image img = repo.findById(id).get();
            return new ResponseEntity<Image>(img, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<String>("No se ha encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            Image img = repo.findById(id).get();
            repo.delete(img);
            return new ResponseEntity<Image>(img, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<String>("No se ha podido eliminar", HttpStatus.BAD_REQUEST);
        }
    }
    
}
