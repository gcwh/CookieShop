package service;

import model.Goods;
import model.Page;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    Map<String, Object> getScrollGood();

    List<Map<String, Object>> getGoodsList(int recommendType);

    List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize);

    Page getGoodsRecommendPage(int type, int pageNumber);

    Goods getGoodsById(int id);

    void addRecommend(int id, int type);

    public void removeRecommend(int id, int type);

    void insert(Goods g);

    void update(Goods g);

    void delete(int id);

    Page getSearchGoodsPage(String keyword, int pageNumber);

    Page selectPageByTypeId(int typeID, int pageNumber);

}
