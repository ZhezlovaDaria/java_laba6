package shopd.DAO;
import shopd.model.Goods;

import java.util.List;

public interface GoodsDAO {
    List<Goods> allGoods();
    void save(Goods goods);
    void delete(Goods goods);
    void update(Goods goods);
    Goods findById(int id);
}
