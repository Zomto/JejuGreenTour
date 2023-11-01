package com.jejugreentour.jgt.util;

import com.jejugreentour.jgt.accom.vo.MainAccomImgVO;
import com.jejugreentour.jgt.accom.vo.SubAccomImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadUtil {

    // 파일 첨부 기능(단일 파일 업로드)
    public static MainAccomImgVO uploadFile(MultipartFile img) {
        MainAccomImgVO mainAccomImgVO = null;

        // 첨부파일을 선택했다면
        if (!img.isEmpty()) {
            mainAccomImgVO = new MainAccomImgVO();
            // 첨부파일
            String originFileName = img.getOriginalFilename();
            // 첨부된 파일명
            String uuid = UUID.randomUUID().toString();
            // 가장 빨리 만나는 자바.jpg
            int dotIndex = originFileName.lastIndexOf(".");
            String extension = originFileName.substring(dotIndex);
            String attachedFileName = uuid + extension;

            // 파일 첨부
            try {
                File file = new File(ConstantVariable.UPLOAD_PATH2 + attachedFileName);
                img.transferTo(file);

                mainAccomImgVO.setOriginFileName(originFileName);
                mainAccomImgVO.setAttachedFileName(attachedFileName);
                mainAccomImgVO.setIsMain("Y");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return mainAccomImgVO;


    }


    // 다중 파일 업로드
    public static List<MainAccomImgVO> multiFileUpload(MultipartFile[] imgs){
        List<MainAccomImgVO> mainAccomImgList = new ArrayList<>();

        for(MultipartFile img : imgs){
            MainAccomImgVO vo = uploadFile(img);


            if(vo != null){
                vo.setIsMain("N");
                mainAccomImgList.add(vo);
            }
        }
        return mainAccomImgList;
    }



    // 파일 첨부 기능(단일 파일 업로드)
    public static SubAccomImgVO uploadSubFile(MultipartFile img) {
        SubAccomImgVO subAccomImgVO = null;

        // 첨부파일을 선택했다면
        if (!img.isEmpty()) {
            subAccomImgVO = new SubAccomImgVO();
            // 첨부파일
            String originFileName = img.getOriginalFilename();
            // 첨부된 파일명
            String uuid = UUID.randomUUID().toString();
            // 가장 빨리 만나는 자바.jpg
            int dotIndex = originFileName.lastIndexOf(".");
            String extension = originFileName.substring(dotIndex);
            String attachedFileName = uuid + extension;

            // 파일 첨부
            try {
                File file = new File(ConstantVariable.UPLOAD_PATH2 + attachedFileName);
                img.transferTo(file);

                subAccomImgVO.setOriginFileName(originFileName);
                subAccomImgVO.setAttachedFileName(attachedFileName);
                subAccomImgVO.setIsMain("Y");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return subAccomImgVO;


    }


    // 다중 파일 업로드
    public static List<SubAccomImgVO> multiSubFileUpload(MultipartFile[] imgs){
        List<SubAccomImgVO> subAccomImgList = new ArrayList<>();

        for(MultipartFile img : imgs){
            SubAccomImgVO vo = uploadSubFile(img);


            if(vo != null){
                vo.setIsMain("N");
                subAccomImgList.add(vo);
            }
        }
        return subAccomImgList;
    }
}
