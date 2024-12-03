package pbl.project.ggumimstudioBack.file.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.file.dto.response.FileResponseDto;
import pbl.project.ggumimstudioBack.file.service.AdminFileService;
import pbl.project.ggumimstudioBack.file.service.FileService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admin/file")
public class AdminFileController
{
    private final AdminFileService adminFileService;

    @PostMapping("")
    public CommonApiResponse<FileResponseDto> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request)
    {
        FileResponseDto responseDto = adminFileService.uploadFile(file, request);
        return CommonApiResponse.OK(responseDto);
    }

    @GetMapping("/{fileUID}")
    public CommonApiResponse<FileResponseDto> findFile(@PathVariable Long fileUID)
    {
        FileResponseDto responseDto = adminFileService.findFile(fileUID);
        return CommonApiResponse.OK(responseDto);
    }
}
