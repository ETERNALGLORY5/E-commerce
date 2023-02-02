package huffle.puff.wand.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import huffle.puff.wand.exception.BadApiRequest;
import huffle.puff.wand.services.FileService;

@Service
public class FileServiceImpl implements FileService{

  
  private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    
  
  @Override
  public String uploadFile(MultipartFile file, String path) throws IOException {
     
        
		String originalFileName = file.getOriginalFilename();
        logger.info("Filename : {}", originalFileName);
        String filename = UUID.randomUUID().toString();
      
        @SuppressWarnings("null")
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithExtension = filename+extension;
        String fullPathWithFileName = path+File.separator+fileNameWithExtension;

        
        logger.info("Full image path {}", fullPathWithFileName);
if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg"))
{
      //if everything is okay then save the file

      File folder = new File(path);
      if(!folder.exists())
      {
        //create the folder
        folder.mkdirs();
      }

      //upload
      Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
      return fileNameWithExtension;
}
else
{
  throw  new BadApiRequest("File with this "+ extension+ "not allowed");
}
        
       
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        
      String fullPath = path + File.separator+name;

      InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
    
}
