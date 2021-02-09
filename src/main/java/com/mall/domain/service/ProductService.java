package com.mall.domain.service;

import com.mall.domain.model.ProductModel;

import com.mall.infra.persistence.sql.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    /**
     * 获取用户的产品列表
     * @param userId
     * @param start
     * @param size
     * @return
     */
    public List<ProductModel> listProduct(int userId, Long start, Byte size) {
        return productMapper.queryAgent(userId, start, size);
    }

    /**
     * 添加产品
     * @param productModel
     * @return
     */
    public int addProduct(ProductModel productModel) {
        int productId = -1;
        if (productMapper.insertProduct(productModel)) {
            productId = productModel.getId();
        }
        return productId;
    }

    /**
     * 删除产品
     * @param productId
     * @return
     */
    public Long deleteProduct(Long productId) {
        return productMapper.deleteProduct(productId);
    }
}
