package shopd.services;

import shopd.DAO.GoodsDAO;
import shopd.DAO.GoodsDAOImpl;
import shopd.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    private GoodsDAO goodsDAO;

    @Autowired
    public void setGoodsDao(GoodsDAOImpl goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    public GoodsServiceImpl() {
    }

    @Override
    @Transactional
    public List<Goods> allGoods() {
        return goodsDAO.allGoods();
    }

    @Transactional
    public void save(Goods goods){
        goodsDAO.save(goods);
    }

    @Transactional
    public void delete(Goods goods) {
        goodsDAO.delete(goods);
    }

    @Transactional
    public void update(Goods goods){
        goodsDAO.update(goods);
    }

    @Transactional
    public Goods find(int id) {
        return goodsDAO.findById(id);
    }

    public boolean check(Goods goods, Map<Goods, Integer> basketMap) {

        for (Map.Entry item: basketMap.entrySet()) {
            Goods g = (Goods) item.getKey();
            if (goods.getName().equals(g.getName()))
                return true;
        }
        return false;
    }
    
    public Map<Goods, Integer> restatement(Map<Goods, Integer> basketMap, List<Integer> quantity){
        Map<Goods, Integer> tmp = new LinkedHashMap<>();
        int i = 0;
        for (Map.Entry item: basketMap.entrySet()) {
            tmp.put((Goods) item.getKey(), quantity.get(i));
            i++;
        }
        return tmp;
    }
    
    public List<Goods> basketDelete(Goods goods, List<Goods> basketGoods){
        List<Goods> tmp = new ArrayList<>();
        for (Goods g: basketGoods) {
            if (!g.getName().equals(goods.getName()))
                tmp.add(g);
        }
        return tmp;
    }
}
