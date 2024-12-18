package pbl.project.ggumimstudioBack.user.repository;

import pbl.project.ggumimstudioBack.user.entity.User;

public interface UserRepositoryCustom
{
    User findByUserID(String userID);

    // 아이디 있는지 확인
    Boolean existsByUserId(String userId);

    // 전화번호 있는지 확인
    Boolean existsByCallPhone(String callPhone);

    // 이메일 있는지 확인
    Boolean existsByEmail(String email);
}
