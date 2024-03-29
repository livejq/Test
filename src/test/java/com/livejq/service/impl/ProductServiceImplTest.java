package com.livejq.service.impl;

import com.livejq.dao.ProductInfo;
import com.livejq.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
        System.out.println(productInfo.toString());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
        for (ProductInfo p : productInfoList) {
            System.out.println(p.toString());
        }
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request = new PageRequest(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
        List<ProductInfo> productInfoList = productInfoPage.getContent();
        for (ProductInfo p : productInfoList) {
            System.out.println(p.toString());
        }

        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result = productService.save(productInfo);
        System.out.println(result.toString());
        Assert.assertNotNull(result);
    }

//    @Test
//    public void onSale() {
//        ProductInfo result = productService.onSale("123456");
//        Assert.assertEquals(ProductStatusEnum.UP, result.getProductStatusEnum());
//    }
//
//    @Test
//    public void offSale() {
//        ProductInfo result = productService.offSale("123456");
//        Assert.assertEquals(ProductStatusEnum.DOWN, result.getProductStatusEnum());
//    }

}