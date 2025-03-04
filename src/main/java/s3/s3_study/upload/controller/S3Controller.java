package s3.s3_study.upload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import s3.s3_study.upload.controller.request.S3UploadRequest;
import s3.s3_study.upload.controller.response.S3FileUploadResponse;
import s3.s3_study.upload.service.S3Service;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
@Slf4j
public class S3Controller {

    private final S3Service s3Service;

    /**
     * Presigned URL 생성
     */
    @GetMapping
    public S3FileUploadResponse generatePresignedUrl(
            @ModelAttribute S3UploadRequest request) {

        // Presigned URL 생성
        S3FileUploadResponse response = s3Service.generatePresignedUrl(request.getFileName(), request.getContentType());

        return response;
    }

    /**
     * 파일 삭제 API
     *
     * @param fileName 삭제할 파일의 전체 경로 ex) image/8be43abc-455f-4c4a-9458-87f6a20d3008-son.jpeg
     */
    @DeleteMapping("/delete")
    public void deleteFile(@RequestParam String fileName) {
        s3Service.deleteFile(fileName);
    }
}
