package shopd.DAO;

import shopd.config.HibernateSessionFactoryUtil;
import shopd.model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDAOImpl implements OrdersDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> allOrders() {
        List<Order> orders = (List<Order>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Order").list();
        return orders;
    }

    public void save(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(order);
        tx1.commit();
        session.close();
    }

    public void delete(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(order);
        tx1.commit();
        session.close();
    }

    public void update(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(order);
        tx1.commit();
        session.close();
    }

    public Order findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Order.class, id);
    }
}
