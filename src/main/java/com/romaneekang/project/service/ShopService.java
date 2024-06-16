package com.romaneekang.project.service;

import com.romaneekang.project.model.dto.ApiWangYinPaymentDto;
import com.romaneekang.project.model.form.GoodsForm;
import com.romaneekang.project.model.form.NoticeForm;

public interface ShopService {
    ApiWangYinPaymentDto createOrderGetParam(GoodsForm goodsForm);

    /**
     * 处理异步通知
     * @param noticeForm 快钱传回来的参数
     * @return  处理结果
     */
    String handleNotify(NoticeForm noticeForm);
}
