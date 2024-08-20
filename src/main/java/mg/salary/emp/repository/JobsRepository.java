package mg.salary.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.salary.emp.model.Jobs;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {

}
