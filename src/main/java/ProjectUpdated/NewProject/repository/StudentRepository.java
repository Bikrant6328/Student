package ProjectUpdated.NewProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ProjectUpdated.NewProject.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

      Student findByUsername(String name);

	    Optional<Student> findById(Long studentId);
      
//      @Query(value = "SELECT * FROM Student_Table WHERE id = :studentid", nativeQuery = true)
//      Student findById(@Param("studentid") Long studentid);

    	
    

}
