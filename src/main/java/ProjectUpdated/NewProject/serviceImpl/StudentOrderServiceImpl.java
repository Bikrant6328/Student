package ProjectUpdated.NewProject.serviceImpl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.OrderClient;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import ProjectUpdated.NewProject.entity.StudentOrder;
import ProjectUpdated.NewProject.repository.StudentOrderRepo;
import ProjectUpdated.NewProject.service.StudentOrderService;
import jakarta.transaction.Transactional;


@Service
public class StudentOrderServiceImpl implements StudentOrderService {
	
	@Autowired
	private StudentOrderRepo studentOrderRepo;

	@Value("${razorpay.key.id}")
	private String razorPayKeyId;
	@Value("${razorpay.secret.key}")
	private String razorPaySecretkey;
	
	private RazorpayClient client;
	
	@Transactional
	@Override
	public StudentOrder createOrder(StudentOrder orders) throws Exception {
		JSONObject orderRequest  = new JSONObject();
		orderRequest.put("amount", orders.getAmount() * 100);
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt",orders.getEmail());
		
		this.client=new RazorpayClient(razorPayKeyId,razorPaySecretkey);
		
		Order razorPayOrder = client.orders.create(orderRequest);
		
		System.out.println(razorPayOrder);
		orders.setRazorpayOrderId(razorPayOrder.get("id"));
		orders.setOrderstatus(razorPayOrder.get("status"));
		
		studentOrderRepo.save(orders);
		
		return orders;
	}

}
