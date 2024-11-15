package com.ezen.spring.handler;

import com.ezen.spring.domain.FileVO;
import groovy.util.logging.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Component
public class FileHandler {

    private String UP_DIR = "D:\\_myProject\\_java\\_fileUpload\\";


    public List<FileVO> uploadFiles(MultipartFile[] files) {
        List<FileVO> flist = new ArrayList<>();
        LocalDate date = LocalDate.now();
        // 2024-11-15  => 2024\\11\\15
        String today = date.toString().replace("-", File.separator);
        //  D:\_myProject\_java\_fileUpload\2024\11\15
        File folders = new File(UP_DIR, today);

        // mkdir = 1개의 폴더만 // mkdirs = 여러개
        if(!folders.exists()){
            folders.mkdirs();
        }

        // FileVO 생성
        for(MultipartFile file : files){

            FileVO fvo = new FileVO();
            fvo.setSaveDir(today);
            fvo.setFileSize(file.getSize());

            // file.name => 경로를 포함하는 경우도 있음. /test/test.txt
            String originalFileName = file.getOriginalFilename();
            String onlyFileName = originalFileName.substring(
                    originalFileName.lastIndexOf(File.separator)+1);
            fvo.setFileName(onlyFileName);

            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            fvo.setUuid(uuidStr);

            String fileName = uuidStr+"_"+onlyFileName;
            File storeFile = new File(folders,fileName);

            try{
                file.transferTo(storeFile);
                // 파일 타입 : 그림파일만 썸네일 생성
                if(isImageFile(storeFile)){
                    fvo.setFileType(1);
                    File thumbnail = new File(folders, uuidStr+"_th_"+onlyFileName);
                    Thumbnails.of(storeFile).size(100,100).toFile(thumbnail);
                } else {
                    fvo.setFileType(0);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            flist.add(fvo);
        }

        return flist;
    }
    private boolean isImageFile(File file) throws IOException {
        String mimeType = new Tika().detect(file);
        return mimeType.startsWith("image");
    }
}
