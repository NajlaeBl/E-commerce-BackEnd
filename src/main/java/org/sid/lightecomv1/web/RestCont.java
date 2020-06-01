package org.sid.lightecomv1.web;

import org.sid.lightecomv1.dao.ProductRepository;
import org.sid.lightecomv1.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



@RestController
public class RestCont {
    private ProductRepository productRepository;

    public RestCont(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @GetMapping("/test")
    public String Test() {
    	return "Test";
    }
    
   
    @PostMapping("/Test")
    public String TestPost() {
    	System.out.println("Test");
    	return "Test";
    }
    
    @GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
    	System.out.println("get pictrue");
        Product p=productRepository.findById(id).get();
        
        byte[] file =Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()));
        System.out.println(file); 
        return file;
    }
  
    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(@RequestParam(value = "image") MultipartFile file, @PathVariable Long id) throws Exception{
    	System.out.println("uplaod Image");
    	System.out.println(file);
        Product p=productRepository.findById(id).get();
        p.setPhotoName(id+".png");
        System.out.println("write file");
        if (!file.isEmpty()) {
        	BufferedOutputStream bos = null;
			try {
				byte[] fileBytes = file.getBytes();
				// location to save the file
				System.out.print(file.getOriginalFilename());
				String fileName = System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName();
				System.out.println(fileName);
				File f = new File(fileName);
				System.out.println(f.getFreeSpace());

				f.getParentFile().mkdirs();
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bos = new BufferedOutputStream(new FileOutputStream(f));
				bos.write(fileBytes);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
						System.out.println(bos.toString().getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
        }else {
        	System.out.println("file is empty");
        }
        //Files.write(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()),file.getBytes());
        System.out.println("save data");
        productRepository.save(p);
        System.out.println("nice job");
        //return true;
    }

}
