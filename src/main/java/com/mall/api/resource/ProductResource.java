package com.mall.api.resource;

import com.mall.api.utils.StringUtil;
import com.mall.api.request.ProductRequest;
import com.mall.api.response.Response;
import com.mall.api.response.Responses;

import com.mall.domain.model.ProductModel;
import com.mall.domain.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "products")
public class ProductResource {
    private final Logger logger = LoggerFactory.getLogger(ProductResource.class);

    @Resource
    private ProductService productService;

    /**
     * 添加产品
     * @param productRequest
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "")
    public Response addProduct(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("添加产品失败, 验证错误!");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        } else {
            ProductModel productModel = new ProductModel();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            productModel.setCreateTime(currentTime);
            productModel.setUpdateTiem(currentTime);
            productModel.setLink(productRequest.getLink());
            productModel.setName(productRequest.getName());
            productModel.setUserId(productRequest.getUserId());

            int productId = productService.addProduct(productModel);
            if (productId <= 0) {
                return Responses.errorResponse("添加产品失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("productId", productId);
            response.setData(data);
            return response;
        }
    }

    @GetMapping(value = "")
    public Response productList(
            @RequestParam(value = "user_id", defaultValue = "") String userId,
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "10") String size
    ) {
        if (userId.isEmpty()) {
            return Responses.errorResponse("userId is empty()");
        }

        Long pageValue = StringUtil.stringToLong(page);
        Byte sizeValue = StringUtil.stringToByte(size);
        List<ProductModel> products = productService.listProduct(StringUtil.stringToInt(userId),
                pageValue * sizeValue, sizeValue);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("products", products);
        data.put("size", products.size());
        response.setData(data);
        return response;
    }

    @DeleteMapping("/{id}")
    public Response deleteProduct(@PathVariable("id") String id) {
        logger.info("invoke deleteProduct{}, id={id}", id);
        Long productId = StringUtil.stringToLong(id);
        Long deleteId = productService.deleteProduct(productId);
        if (deleteId < 0) {
            return Responses.errorResponse("delete product failure");
        }

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        response.setData(data);
        return response;

    }


}
