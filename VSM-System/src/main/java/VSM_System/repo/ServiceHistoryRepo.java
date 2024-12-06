package VSM_System.repo;

import VSM_System.entity.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceHistoryRepo extends JpaRepository<ServiceHistory, String> {
}
