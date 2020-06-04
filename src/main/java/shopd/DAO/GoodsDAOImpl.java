package shopd.DAO;

import shopd.config.HibernateSessionFactoryUtil;
import shopd.model.Goods;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsDAOImpl implements GoodsDAO{

    @Override
    @SuppressWarnings("unchecked")
    public List<Goods> allGoods() {
        List<Goods> goods = (List<Goods>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Goods").list();
        return goods;
    }

    public Goods findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Goods.class, id);
    }

    public void save(Goods goods) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(goods);
        tx1.commit();
        session.close();
    }
    public void update(Goods goods) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(goods);
        tx1.commit();
        session.close();
    }
    public void delete(Goods goods) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(goods);
        tx1.commit();
        session.close();
    }
}
