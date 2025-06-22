package ProjectUpdated.NewProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ProjectUpdated.NewProject.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

	Student findByUsername(String name);

}
