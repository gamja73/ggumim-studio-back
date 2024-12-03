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
        this.originUrl = "https://pub-05783cad7c334e7d80c4d282b349f717.r2.dev" + originUrl.substring(originUrl.indexOf("/origin"));
        this.webpUrl = "https://pub-05783cad7c334e7d80c4d282b349f717.r2.dev" + webpUrl.substring(webpUrl.indexOf("/img"));
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
