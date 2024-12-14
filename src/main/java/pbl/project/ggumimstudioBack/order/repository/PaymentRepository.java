package pbl.project.ggumimstudioBack.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pbl.project.ggumimstudioBack.order.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryCustom
{

}
