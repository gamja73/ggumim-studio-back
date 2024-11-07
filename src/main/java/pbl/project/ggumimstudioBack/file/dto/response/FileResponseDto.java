package pbl.project.ggumimstudioBack.file.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.file.entity.File;

@Data
public class FileResponseDto
{
    private String fileName;
    private String originUrl;
    private String webpUrl;

    public FileResponseDto(File file)
    {
        this.fileName = file.getFileName();
        this.originUrl = file.getOriginURL();
        this.webpUrl = file.getWebpURL();
    }

    public FileResponseDto(String fileName, String originUrl, String webpUrl)
    {
        this.fileName = fileName;
        this.originUrl = originUrl;
        this.webpUrl = webpUrl;
    }

    public File toEntity()
    {
        return File.builder()
                .userUID(null)
                .adminUID(null)
                .fileName(this.fileName)
                .originURL(this.originUrl)
                .webpURL(this.webpUrl)
                .build();
    }
}
