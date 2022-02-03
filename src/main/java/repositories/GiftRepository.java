package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.Gift;

public interface GiftRepository extends JpaRepository<Gift,Long>{

}
