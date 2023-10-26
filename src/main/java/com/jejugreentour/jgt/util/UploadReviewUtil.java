package com.jejugreentour.jgt.util;


import com.jejugreentour.jgt.buy.vo.ReviewImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadReviewUtil {
    // 파일 첨부 기능(단일 파일 업로드)


    //파일 첨부 가능(단일 파일 업로드)
    static public ReviewImgVO uploadFile(MultipartFile img) {
        ReviewImgVO imgVO = null;
        if (!img.isEmpty()) {
            imgVO = new ReviewImgVO();
            String originFileName = img.getOriginalFilename();
            String uuid = UUID.randomUUID().toString(); //랜덤한 이름

            int dotIndex = originFileName.lastIndexOf(".");
            String extension = originFileName.substring(dotIndex);//확장자명
            String attachedFileName = uuid + extension;
            try {
                File file = new File(ConstantVariable.UPLOAD_REVIEW_PATH + attachedFileName);
                img.transferTo(file);
                imgVO.setOriginFileName(originFileName);
                imgVO.setAttachedFileName(attachedFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return imgVO;
    }
    //다중 파일 업로드
    static public List<ReviewImgVO> multiFileUpload(MultipartFile[] imgs){
        List<ReviewImgVO> imglist =new ArrayList<>();
        for(MultipartFile img : imgs){
            ReviewImgVO vo=uploadFile(img);
            if(vo!=null) {
                imglist.add(vo);
            }
        }
        return imglist;
    }
}
