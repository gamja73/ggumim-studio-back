package pbl.project.ggumimstudioBack.file.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.common.util.JwtUtil;
import pbl.project.ggumimstudioBack.file.dto.response.FileResponseDto;
import pbl.project.ggumimstudioBack.file.entity.File;
import pbl.project.ggumimstudioBack.file.repository.FileRepository;

@Service
@AllArgsConstructor
public class AdminFileService
{
    private final R2Service r2Service;
    private final FileRepository fileRepository;
    private final JwtUtil jwtUtil;

    /**
     * 파일 조회
     * @param fileUID - 조회 할 파일의 UID
     */
    public FileResponseDto findFile(Long fileUID)
    {
        File responseFile = fileRepository.findById(fileUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.FILE_NOT_FOUND_ERR));

        return new FileResponseDto(responseFile);
    }

    /**
     * 파일 업로드
     * @param file - 업로드 할 파일
     */
    public FileResponseDto uploadFile(MultipartFile file, HttpServletRequest request)
    {
        // 파일이 비어 있는지 확인
        if (file.isEmpty())
        {
            throw new CustomException(CustomErrorCode.FILE_IS_NULL_ERR);
        }
//        jwtUtil.getUidFromToken(request);

        FileResponseDto dto = r2Service.uploadImage(file);

        fileRepository.save(dto.toEntity());

        return dto;
    }

}
