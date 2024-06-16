package com.romaneekang.project.model.form;

import com.romaneekang.project.util.KqUtil;
import com.romaneekang.project.util.Pkipair;
import lombok.Data;

/**
 * 接受快钱异步通知参数的对象
 */
@Data
public class NoticeForm {
    String merchantAcctId;
    String version;
    String language;
    String signType;
    String payType;
    String bankId;
    String orderId;
    String orderTime;
    String orderAmount;
    String bindCard;
    String bindMobile;
    String dealId;
    String bankDealId;
    String dealTime;
    String payAmount;
    String fee;
    String ext1;
    String ext2;
    String payResult;
    String aggregatePay;
    String errCode;
    String signMsg;

    /**
     * 验证签名
     * @return true-验证成功 false-验证失败
     */
    public boolean checkSignMsg() {
        String merchantSignMsgVal = "";
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal,"merchantAcctId", merchantAcctId);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "version",version);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "language",language);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "signType",signType);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "payType",payType);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "bankId",bankId);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "orderId",orderId);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "orderTime",orderTime);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "orderAmount",orderAmount);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "bindCard",bindCard);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "bindMobile",bindMobile);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "dealId",dealId);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "bankDealId",bankDealId);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "dealTime",dealTime);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "payAmount",payAmount);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "fee", fee);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "ext1", ext1);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "ext2", ext2);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "payResult",payResult);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "aggregatePay",aggregatePay);
        merchantSignMsgVal = KqUtil.appendParam(merchantSignMsgVal, "errCode",errCode);
        Pkipair pki = new Pkipair();
        return pki.enCodeByCer(merchantSignMsgVal, signMsg);
    }
}
