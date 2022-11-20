package com.mysite.jikpoom.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.jikpoom.beans.vo.AttachFileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Slf4j
@RequestMapping("/upload/")
public class UploadController {
    @GetMapping("uploadForm")
    public void uploadForm() {
        log.info("upload form");
    }

    @PostMapping(value="uploadAjaxAction", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AttachFileVO> uploadAjaxPost(MultipartFile[] uploadFile) {

        log.info("----------------------------");
        log.info("Upoad File Name : ");
        List<AttachFileVO> fileList = new ArrayList<>();

        String uploadFolder = "C:/Users/rlfxo/Desktop/Project/upload";
        String uploadFolderPath = getFolder();

        // yyyy/mm/dd 폴더 만들기
        File uploadPath = new File(uploadFolder, uploadFolderPath);
        if(!uploadPath.exists()) { uploadPath.mkdirs(); }
        log.info("upload path : " + uploadPath);

        for(MultipartFile mf : uploadFile) {
            log.info("Upload File Name : " + mf.getOriginalFilename());
            log.info("Upload File Size : " + mf.getSize());

            AttachFileVO attachFileVO = new AttachFileVO();
            String uploadFileName = mf.getOriginalFilename();

            // UUID
            // 동일한 이름의 파일을 방지하기 위한 방법
            // 네트워크 상 개체들을 식별하기 위해 만들어짐
            // 10의 38제곱 수 중 하나 선택(만억조경해자양구간, 340간)
            UUID uuid = UUID.randomUUID();

            // 파일 이름 조회 시 _ 뒤 내용으로 조회
            uploadFileName = uuid.toString() + "_" + uploadFileName;

            // IE에서는 파일 이름을 포함한 전체 경로가 나옴
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);

            log.info("file name : " + uploadFileName);
            attachFileVO.setFileName(uploadFileName);

            try {
                File saveFile = new File(uploadPath, uploadFileName);
                mf.transferTo(saveFile);
                InputStream in = new FileInputStream(saveFile);

                attachFileVO.setUuid(uuid.toString());
                attachFileVO.setUploadPath(uploadFolderPath);

                if(checkImageType(saveFile)) {
                    attachFileVO.setImage(true);
                    FileOutputStream thumbnail =
                            new FileOutputStream(
                                    new File(uploadPath, "s_"+uploadFileName)
                            );
                    // inputstream을 통해 파일을 생성하고, height, width를 지정한다.
                    // 이미지 파일이면 s_ 로 썸네일 이미지가 한 개 더 생성된다.
                    Thumbnailator.createThumbnail(in, thumbnail, 20, 20);
                    thumbnail.close();
                }
                in.close();
                fileList.add(attachFileVO);
            }
            catch(Exception e) {
                log.error(e.getMessage());
            }
        }
        return fileList;
    }

    @GetMapping("display")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String fileName){
        log.info("fileName : " + fileName);

        File file = new File("C:/upload/" + fileName);
        log.info("file : " + file);

        ResponseEntity<byte []> result = null;

        HttpHeaders header = new HttpHeaders();
        try {
            // 헤더에 적절한 파일 타입을 probeContentType을 통해 포함시킨다.
            // png면 image/png 타입, jpg 파일은 image/jpeg 타입으로 포함시킨다.
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<byte[]>(
                    FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);

        return str.replace("-", "/");
    }

    private boolean checkImageType(File file) {
        try {
            String contentType = Files.probeContentType(file.toPath());
            log.info("Content Type : " + contentType);
            return contentType.startsWith("image");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
