package com.wuyutong.controller.viewobject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemVO {
    private Integer id;

    //商品名
    private String title;

    //商品价格
    private BigDecimal price;

    //商品的库存
    private Integer stock;

    //商品的描述
    private String description;

    //商品的销量
    private Integer sales;

    //商品图片
    private String imgUrl;
}
