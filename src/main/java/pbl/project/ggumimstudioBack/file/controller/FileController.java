package pbl.project.ggumimstudioBack.file.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.file.dto.response.FileResponseDto;
import pbl.project.ggumimstudioBack.file.service.FileService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController
{
    private final FileService fileService;

    @PostMapping("")
    public CommonApiResponse<FileResponseDto> uploadFile(@RequestParam("file") MultipartFile file)
    {
        FileResponseDto responseDto = fileService.uploadFile(file);
        return CommonApiResponse.OK(responseDto);
    }

    @GetMapping("/{fileUID}")
    public CommonApiResponse<FileResponseDto> findFile(@PathVariable Long fileUID)
    {
        FileResponseDto responseDto = fileService.findFile(fileUID);
        return CommonApiResponse.OK(responseDto);
    }
}
