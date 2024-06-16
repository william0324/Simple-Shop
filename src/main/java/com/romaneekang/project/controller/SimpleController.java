package com.romaneekang.project.controller;

import com.romaneekang.project.model.dto.ApiWangYinPaymentDto;
import com.romaneekang.project.model.form.GoodsForm;
import com.romaneekang.project.model.form.NoticeForm;
import com.romaneekang.project.service.ShopService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    @Resource
    private ShopService shopService;

    @GetMapping("/order/new")
    public String createOrder(GoodsForm goodsForm, Model model) {
        ApiWangYinPaymentDto orderGetParamDto = shopService.createOrderGetParam(goodsForm);
        model.addAttribute("pay", orderGetParamDto);
        return "payment";
    }

    // 接受快钱给商家通知支付结果的地址
    @GetMapping("/receive/notify")
    @ResponseBody
    public String receiveNotify(NoticeForm noticeForm) {
        String handleNotify = "";
        try {
            // 验证签名
            boolean flag = noticeForm.checkSignMsg();
            System.out.println("验证签名的结果是" + flag);

            // TODO 验证签名没有完成
            flag = true;
            if (!flag) {
                throw new RuntimeException("签名失败");
            }
            // 查询订单是否存在，处理订单逻辑
            handleNotify = shopService.handleNotify(noticeForm);
        } catch (RuntimeException e) {
            handleNotify = "ORDER_ERROR";
        }
        if (handleNotify.equals("ORDER_ERROR") || handleNotify.equals("ORDER_NONE")) {
            return "<result>0</result><redirecturl>http://localhost:8080/shop/show.html</redirecturl>";
        } else {
            return "<result>1</result><redirecturl>http://localhost:8080/shop/show.html</redirecturl>";
        }
    }

}
