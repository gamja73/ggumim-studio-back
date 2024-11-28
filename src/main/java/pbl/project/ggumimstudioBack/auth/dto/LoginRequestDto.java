package pbl.project.ggumimstudioBack.auth.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginRequestDto
{
    @NotNull
    @Size(min = 1, message = "id를 입력해주세요.")
    private String id;

    @NotNull
    @Size(min = 1, message = "비밀번호를 입력해주세요.")
    private String password;
}
