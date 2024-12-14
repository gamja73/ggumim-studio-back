package pbl.project.ggumimstudioBack.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pbl.project.ggumimstudioBack.user.entity.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long>, WishListRepositoryCustom
{

}
