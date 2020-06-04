package shopd.DAO;
import shopd.model.Order;

import java.util.List;

public interface OrdersDAO {
    List<Order> allOrders();
    void save(Order order);
    void delete(Order order);
    void update(Order order);
    Order findById(int id);
}
