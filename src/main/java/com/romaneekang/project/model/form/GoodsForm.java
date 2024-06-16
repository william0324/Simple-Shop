package com.romaneekang.project.model.form;

import lombok.Data;

/**
 * 来自商品页面的值,接收前端传来的信息
 */
@Data
public class GoodsForm {
    /**
        商品名称
     */
    private String productName;
    /**
     * 商品数量
     */
    private String productNum;
    /**
     * 订单金额
     */
    private String orderMoney;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 用户id
     */
    private String payerId;
    /**
     * 用户联系方式
     */
    private String payerConcat;
    /**
     * 商品代码
     */
    private String productId;
}
