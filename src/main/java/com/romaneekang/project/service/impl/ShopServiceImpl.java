package com.romaneekang.project.service.impl;

import com.romaneekang.project.mapper.SimpleOrderMapper;
import com.romaneekang.project.model.dto.ApiWangYinPaymentDto;
import com.romaneekang.project.model.form.GoodsForm;
import com.romaneekang.project.model.form.NoticeForm;
import com.romaneekang.project.pojo.SimpleOrder;
import com.romaneekang.project.service.ShopService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    @Resource
    private SimpleOrderMapper simpleOrderMapper;

    @Override
    public ApiWangYinPaymentDto createOrderGetParam(GoodsForm goodsForm) {
        // 快钱网银支付接口的参数值
        ApiWangYinPaymentDto dto = new ApiWangYinPaymentDto(goodsForm);
        // 生成订单记录
        SimpleOrder simpleOrder = new SimpleOrder();
        simpleOrder.setUid(Integer.parseInt(goodsForm.getPayerId()));
        simpleOrder.setOrderNo(goodsForm.getOrderId());
        simpleOrder.setStatus(0);
        simpleOrder.setPayMoney(new BigDecimal(goodsForm.getOrderMoney()));
        simpleOrder.setPayTime(new Date());
        simpleOrder.setRemark("创建支付订单");
        simpleOrder.setGoodsReplId("0");
        simpleOrderMapper.insert(simpleOrder);
        return dto;
    }

    /**
     * 处理异步通知
     * for update需要在事务控制下使用
     * @param noticeForm 快钱传回来的参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized String handleNotify(NoticeForm noticeForm) {
        // 1.查询订单是否存在
        SimpleOrder simpleOrder = simpleOrderMapper.selectByOrderIdForUpdate(noticeForm.getOrderId());
        if (simpleOrder == null) {
            return "ORDER_NULL";
        }
        // 2.判断订单是否处理过
        if (simpleOrder.getStatus() != 0) {
            return "ORDER_HANDLE";  //订单已经处理过了
        }
        // 3.判断支付结果
        if ("10".equals(noticeForm.getPayResult())) {
            SimpleOrder updateOrder = new SimpleOrder();
            updateOrder.setId(simpleOrder.getId());
            updateOrder.setStatus(1);
            simpleOrderMapper.updateById(updateOrder);
            return "ORDER_PAY_SUCCESS"; //支付成功
        } else {
            SimpleOrder updateOrder = new SimpleOrder();
            updateOrder.setId(simpleOrder.getId());
            updateOrder.setStatus(1);
            simpleOrderMapper.updateById(updateOrder);
            return "ORDER_PAY_FAIL"; //支付失败
        }
    }
}
