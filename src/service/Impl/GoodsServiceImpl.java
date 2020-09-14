package service.Impl;

import dao.GoodsDao;
import model.Goods;
import service.GoodsService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao=new GoodsDao();

    @Override
    public Map<String, Object> getScrollGood() {
        Map<String,Object> scrollGood=null;
        try {
            scrollGood=goodsDao.getScrollGood();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scrollGood;
    }

    /**
     *
     * @param recommendType 首页要推荐的类型
     * @return 该类型产品的List集合
     */
    @Override
    public List<Map<String, Object>> getGoodsList(int recommendType) {
        List<Map<String, Object>> goodsList=null;
        try {
            goodsList=goodsDao.getGoodsList(recommendType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) {
        List<Goods> goods=null;

        try {
            goods=goodsDao.selectGoodsByTypeID(typeID,pageNumber,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public int getCountOfGoodsByTypeID(int typeID) {
        return 0;
    }

    @Override
    public List<Goods> selectGoodsByRecommend(int type, int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public int getRecommendCountOfGoodsByTypeID(int type) {
        return 0;
    }

    @Override
    public Goods getGoodsById(int id) {
        Goods good=null;
        try {
            good=goodsDao.getGoodsById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return good;
    }

    @Override
    public int getSearchCount(String keyword) {
        return 0;
    }

    @Override
    public List<Goods> selectSearchGoods(String keyword, int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public boolean isScroll(Goods g) {
        return false;
    }

    @Override
    public boolean isHot(Goods g) {
        return false;
    }

    @Override
    public boolean isNew(Goods g) {
        return false;
    }

    @Override
    public boolean isRecommend(Goods g, int type) {

        return false;
    }

    @Override
    public void addRecommend(int id, int type) {
        try {
            goodsDao.addRecommend( id, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeRecommend(int id, int type) {
        try {
            goodsDao.removeRecommend( id, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Goods g) {
        try {
            goodsDao.insert(g);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Goods g) {
        try {
            goodsDao.update(g);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            goodsDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
