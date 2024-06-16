package com.romaneekang.project.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName simple_order
 */
@TableName(value ="simple_order")
@Data
public class SimpleOrder implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 订单状态（0生成，1付款完成，2订单超时，3付款失败）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 订单支付金额
     */
    @TableField(value = "pay_money")
    private BigDecimal payMoney;

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 订单备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 订单关联商品的id
     */
    @TableField(value = "goods_repl_id")
    private String goodsReplId;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}