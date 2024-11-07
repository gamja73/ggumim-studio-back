package pbl.project.ggumimstudioBack.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pbl.project.ggumimstudioBack.file.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long>, FileRepositoryCustom
{

}
