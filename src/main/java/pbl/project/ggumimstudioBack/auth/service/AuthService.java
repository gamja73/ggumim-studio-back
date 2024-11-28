package pbl.project.ggumimstudioBack.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.auth.dto.SignUpRequestDto;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommonUtil commonUtil;

    public Boolean signUpUser(SignUpRequestDto requestDto)
    {
        // 데이터 포멧 체크
        if (requestDto.getId() == null || requestDto.getId().isBlank() || !commonUtil.isValidUserId(requestDto.getId()))
        {
            throw new CustomException(CustomErrorCode.USERID_FORMAT_ERR);
        }

        if (requestDto.getCallPhone() == null || requestDto.getCallPhone().isBlank() || !commonUtil.isValidPhoneNumber(requestDto.getCallPhone()))
        {
            throw new CustomException(CustomErrorCode.CALL_PHONE_FORMAT_ERR);
        }

        if (requestDto.getEmail() == null || requestDto.getEmail().isBlank() || !commonUtil.isValidEmail(requestDto.getEmail()))
        {
            throw new CustomException(CustomErrorCode.EMAIL_FORMAT_ERR);
        }

        // 데이터 중복 체크
        if (userRepository.existsByUserId(requestDto.getId()))
        {
            throw new CustomException(CustomErrorCode.USERID_CANNOT_DUPLICATE_ERR);
        }

        if (userRepository.existsByCallPhone(requestDto.getCallPhone()))
        {
            throw new CustomException(CustomErrorCode.CALL_PHONE_CANNOT_DUPLICATE_ERR);
        }

        if (userRepository.existsByEmail(requestDto.getEmail()))
        {
            throw new CustomException(CustomErrorCode.EMAIL_CANNOT_DUPLICATE_ERR);
        }

        // 회원 엔티티 생성 후 DB 저장
        userRepository.save(requestDto.buildUser(passwordEncoder));

        return true;
    }
}
