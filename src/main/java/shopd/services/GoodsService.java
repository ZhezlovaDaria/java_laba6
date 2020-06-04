package shopd.services;

import shopd.model.Goods;
import shopd.model.MyException;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    List<Goods> allGoods();
    void save(Goods goods) throws MyException;
    void delete(Goods goods) throws MyException;
    void update(Goods goods) throws MyException;
    Goods find(int id) throws MyException;
    boolean check(Goods goods, Map<Goods, Integer> basketMap);
    Map<Goods, Integer> restatement(Map<Goods, Integer> basketMap, List<Integer> quantity);
    List<Goods> basketDelete(Goods goods, List<Goods> basketGoods);
}
