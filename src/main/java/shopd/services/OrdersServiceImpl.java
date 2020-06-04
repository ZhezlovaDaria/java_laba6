package shopd.services;

import shopd.DAO.OrdersDAO;
import shopd.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.ref.SoftReference;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private OrdersDAO ordersDAO;

    @Autowired
    public void setOrdersDao(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    public OrdersServiceImpl() {
    }

    @Override
    @Transactional
    public List<Order> allOrders() {
        return ordersDAO.allOrders();
    }

    @Override
    @Transactional
    public void save(Order order){
        ordersDAO.save(order);
    }

    @Override
    @Transactional
    public void delete(Order order) {
        ordersDAO.delete(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        ordersDAO.update(order);
    }

    @Override
    @Transactional
    public Order find(int id){
        return ordersDAO.findById(id);
    }

    public String goodsNames(List<String> goods){
        String s = "";
        for (String name: goods) {
            s += name + ", ";
        }
        s = s.substring(0, s.length() - 2);
        return s;
    }

    public String goodsQuantity(List<Integer> value){
        String s = "";
        for (Integer v: value) {
            s += v + ", ";
        }
        s = s.substring(0, s.length() - 2);
        return s;
    }
}
