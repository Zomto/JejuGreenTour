package com.jejugreentour.jgt.util;

import com.jejugreentour.jgt.csCenter.vo.InqImgVO;
import com.jejugreentour.jgt.csCenter.vo.ResImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadUtillRes {

    // 파일 첨부 기능(단일 파일 업로드)
    public static ResImgVO uploadFile(MultipartFile img) {
        ResImgVO resImgVO = null;

        // 첨부파일을 선택했다면
        if (!img.isEmpty()) {
            resImgVO = new ResImgVO();
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
                File file = new File(ConstantVariable.UPLOAD_PATH_RES + attachedFileName);
                img.transferTo(file);

                resImgVO.setOriginFileName(originFileName);
                resImgVO.setAttachedFileName(attachedFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return resImgVO;


    }


    // 다중 파일 업로드
    public static List<ResImgVO> multiFileUpload(MultipartFile[] imgs){
        List<ResImgVO> resImgList = new ArrayList<>();

        for(MultipartFile img : imgs){
            ResImgVO vo = uploadFile(img);


            if(vo != null){
                resImgList.add(vo);
            }
        }
        return resImgList;
    }
}
