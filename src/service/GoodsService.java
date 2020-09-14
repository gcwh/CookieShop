package service;

import model.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    Map<String, Object> getScrollGood();

    List<Map<String, Object>> getGoodsList(int recommendType);

    List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize);

    int getCountOfGoodsByTypeID(int typeID);

    //更改了
    List<Goods> selectGoodsByRecommend(int type, int pageNumber, int pageSize);

    int getRecommendCountOfGoodsByTypeID(int type);

    Goods getGoodsById(int id);

    int getSearchCount(String keyword);

    List<Goods> selectSearchGoods(String keyword, int pageNumber, int pageSize);

    boolean isScroll(Goods g);

    boolean isHot(Goods g);

    boolean isNew(Goods g);

    boolean isRecommend(Goods g, int type);

    void addRecommend(int id, int type);

    void removeRecommend(int id, int type);

    void insert(Goods g);

    void update(Goods g);

    void delete(int id);
}
