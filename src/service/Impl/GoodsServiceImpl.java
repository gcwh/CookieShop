package service.Impl;

import dao.GoodsDao;
import model.Goods;
import model.Page;
import service.GoodsService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao = new GoodsDao();

    @Override
    public Map<String, Object> getScrollGood() {
        Map<String, Object> scrollGood = null;
        try {
            scrollGood = goodsDao.getScrollGood();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scrollGood;
    }

    /**
     * @param recommendType 首页要推荐的类型
     * @return 该类型产品的List集合
     */
    @Override
    public List<Map<String, Object>> getGoodsList(int recommendType) {
        List<Map<String, Object>> goodsList = null;
        try {
            goodsList = goodsDao.getGoodsList(recommendType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) {
        List<Goods> goods = null;
        try {
            goods = goodsDao.selectGoodsByTypeID(typeID, pageNumber, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public Goods getGoodsById(int id) {
        Goods good = null;
        try {
            good = goodsDao.getGoodsById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return good;
    }

    @Override
    public Page getGoodsRecommendPage(int type, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            totalCount = goodsDao.getRecommendCountOfGoodsByTypeID(type);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = null;
        try {
            list = goodsDao.selectGoodsbyRecommend(type, pageNumber, 8);
            for (Goods g : (List<Goods>) list) {
                g.setScroll(goodsDao.isScroll(g));
                g.setHot(goodsDao.isHot(g));
                g.setNew(goodsDao.isNew(g));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }


    @Override
    public void addRecommend(int id, int type) {
        try {
            goodsDao.addRecommend(id, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeRecommend(int id, int type) {
        try {
            goodsDao.removeRecommend(id, type);
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


    @Override
    public Page getSearchGoodsPage(String keyword, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            totalCount = goodsDao.getSearchCount(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = null;
        try {
            list = goodsDao.selectSearchGoods(keyword, pageNumber, 8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }

    @Override
    public Page selectPageByTypeId(int typeID, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            totalCount = goodsDao.getCountOfGoodsByTypeID(typeID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);

        List list = null;
        try {
            list = goodsDao.selectGoodsByTypeID(typeID, pageNumber, 8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }

}
