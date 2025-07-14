package ProjectUpdated.NewProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ProjectUpdated.NewProject.entity.StudentOrder;

@Repository
public interface StudentOrderRepo extends JpaRepository<StudentOrder, Integer>{

}
