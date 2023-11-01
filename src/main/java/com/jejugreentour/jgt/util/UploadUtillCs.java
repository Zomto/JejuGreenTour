package com.jejugreentour.jgt.util;

import com.jejugreentour.jgt.accom.vo.MainAccomImgVO;
import com.jejugreentour.jgt.csCenter.vo.InqImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadUtillCs {

    // 파일 첨부 기능(단일 파일 업로드)
    public static InqImgVO uploadFile(MultipartFile img) {
        InqImgVO inqImgVO = null;

        // 첨부파일을 선택했다면
        if (!img.isEmpty()) {
            inqImgVO = new InqImgVO();
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
                File file = new File(ConstantVariable.UPLOAD_PATH_CS + attachedFileName);
                img.transferTo(file);

                inqImgVO.setOriginFileName(originFileName);
                inqImgVO.setAttachedFileName(attachedFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return inqImgVO;


    }


    // 다중 파일 업로드
    public static List<InqImgVO> multiFileUpload(MultipartFile[] imgs){
        List<InqImgVO> inqImgList = new ArrayList<>();

        for(MultipartFile img : imgs){
            InqImgVO vo = uploadFile(img);


            if(vo != null){
                inqImgList.add(vo);
            }
        }
        return inqImgList;
    }
}
