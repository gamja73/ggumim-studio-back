package pbl.project.ggumimstudioBack.user.repository;

import pbl.project.ggumimstudioBack.user.entity.User;

public interface UserRepositoryCustom
{
    User findByUserID(String userID);
}
