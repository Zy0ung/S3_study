package s3.s3_study.upload.controller;

import static s3.s3_study.global.response.ResponseStatus.SUCCESS;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import s3.s3_study.global.response.ResponseDto;
import s3.s3_study.upload.dto.request.S3UploadRequest;
import s3.s3_study.upload.dto.response.S3FileUploadResponse;
import s3.s3_study.upload.service.AwsS3Service;
import s3.s3_study.upload.service.S3Service;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
@Slf4j
public class S3Controller {

    // minio S3 service
    private final S3Service s3Service;
    // aws S3 service
    private final AwsS3Service awsS3Service;

    /**
     * Presigned URL 생성
     */
    @GetMapping
    public ResponseEntity<ResponseDto<S3FileUploadResponse>> generatePresignedUrl(
            @ModelAttribute S3UploadRequest request) {

        // minio Presigned URL 생성
        // S3FileUploadResponse response = s3Service.generatePresignedUrl(request.getFileName(), request.getContentType());

        // AWS Presigned URL 생성
        S3FileUploadResponse response = awsS3Service.generatePresignedUrl(request.getFileName(),
                request.getContentType());
        return ResponseEntity.ok(new ResponseDto<>(SUCCESS.name(), "PresignedUrl 생성 성공", response));
    }

    /**
     * 파일 삭제 API
     *
     * @param fileName 삭제할 파일의 전체 경로 ex) image/8be43abc-455f-4c4a-9458-87f6a20d3008-son.jpeg
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto<Void>> deleteFile(@RequestParam String fileName) {
        //  minio S3 삭제
        //  s3Service.deleteFile(fileName);

        // AWS S3 삭제
        awsS3Service.deleteFile(fileName);

        return ResponseEntity.ok(new ResponseDto<>(SUCCESS.name(), "S3 파일 삭제 성공", null));
    }
}
