package pbl.project.ggumimstudioBack.auth.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomJwtPayload
{
    private Long userUID;
    private String userId;
}