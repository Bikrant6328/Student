package ProjectUpdated.NewProject.service;

import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.StudentOrder;

@Service
public interface StudentOrderService {
  StudentOrder createOrder(StudentOrder orders) throws Exception;
}
