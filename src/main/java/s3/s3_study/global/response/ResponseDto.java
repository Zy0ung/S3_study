package s3.s3_study.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResponseDto<T> {
    private final String status; // SUCCESS 성공, FAIL 실패
    private final String msg;
    private final T data;
}