package com.example.demo.services.impl;

import com.example.demo.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService
{
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException
    {
        //File name
        String name=file.getOriginalFilename();
        //for example abc.png

        //random name generate File
        String randomID= UUID.randomUUID().toString();
        String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));

        //FullPath
        String filePath=path+ File.separator+fileName1;

        //Create Folder if not created
        File f=new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }
        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException
    {
        String fullPath=path+File.separator+filename;
        InputStream is=new FileInputStream(fullPath);
        //db logic to return inputstream
        return is;
    }
}
