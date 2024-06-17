package com.romaneekang.project.model.dto;

import com.romaneekang.project.model.form.GoodsForm;
import com.romaneekang.project.util.KqUtil;
import com.romaneekang.project.util.Pkipair;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ApiWangYinPaymentDto {
    /**
     * 人民币网关账号，该账号为11位人民币网关商户编号+01,该参数必填。<br>
     * 快钱分给商户的11位商户编号。传值为商户号加01
     */
    String merchantAcctId = "1001214035601";
    /**
     * 编码方式，1代表 UTF-8; 2 代表 GBK; 3代表 GB2312 默认为1,该参数必填。
     */
    String inputCharset = "1";
    /**
     * 接收支付结果的页面地址，该参数一般置为空即可。
     */
    String pageUrl = "";
    /**
     * 服务器接收支付结果的后台地址，该参数务必填写，不能为空。<br>
     * 需要是绝对地址，与pageUrl不能同时为空，快钱将支付结果发送到bgUrl对应的地址，并且获取商户按照约定格式输出的地址，显示页面给用户
     */
    String bgUrl = "https://12a86bb9e19893.lhr.life/shop/receive/notify";
    //网关版本，固定值：v2.0,该参数必填。
    String version = "v2.0";
    //语言种类，1代表中文显示，2代表英文显示。默认为1,该参数必填。
    String language = "1";
    //签名类型,该值为4，代表PKI加密方式,该参数必填。
    String signType = "4";
    //支付人姓名,可以为空。
    String payerName = "张三";
    //支付人联系类型，1 代表电子邮件方式；2 代表手机联系方式。可以为空。
    String payerContactType = "2";
    //支付人联系方式，与payerContactType设置对应，payerContactType为1，则填写邮箱地址；payerContactType为2，则填写手机号码。可以为空。
    String payerContact = "123456@qq.com";
    //指定付款人，可以为空
    String payerIdType = "3";
    //付款人标识，可以为空
    String payerId = "KQ33151000";
    //付款人IP，可以为空
    String payerIP = "192.168.1.1";
    //商家的终端ip，支持Ipv4和Ipv6
    String terminalIp = "192.168.1.1";
    //网络交易平台简称，英文或中文字符串,除微信支付宝支付外其他交易方式必传
    String tdpformName = "强盛集团";
    //商户订单号，以下采用时间来定义订单号，商户可以根据自己订单号的定义规则来定义该值，不能为空。
    String orderId;
    //订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试。该参数必填。快钱接受的是一个Long类型的数字，传入参数必须去掉结尾的.00
    String orderAmount;
    //订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101，不能为空。
    String orderTime;
    //快钱时间戳，格式：yyyyMMddHHmmss，如：20071117020101， 可以为空
    String orderTimestamp;
    //商品名称，不可为空。
    String productName;
    //商品数量，不可为空。
    String productNum;
    //商品代码，可以为空。
    String productId;
    //商品描述，可以为空。
    String productDesc;
    //扩展字段1，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
    String ext1 = "";
    //扩展自段2，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
    String ext2 = "";
    //支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10-1或10-2，必填。
    String payType = "00";
    //银行代码，如果payType为00，该值可以为空；如果payType为10-1或10-2，该值必须填写，具体请参考银行列表。
    String bankId = "";
    //同一订单禁止重复提交标志，实物购物车填1，虚拟产品用0。1代表只能提交一次，0代表在支付不成功情况下可以再提交。可为空。
    String redoFlag = "0";
    //快钱合作伙伴的帐户号，即商户编号，可为空。
    String pid = "";
    //00:代表前台提交01：代表后台提交，为空默认为前台提交。
    String submitType = "";
    //订单超时时间(秒)，可为空。
    String orderTimeOut = "";
    //附加信息类型	固定值为NB2（分账时使用）
    String extDataType = "";
    //附加信息（分账时使用）
    String extDataContent = "";//
    // 分期
    String period = "";
    // signMsg 签名字符串 不可空，生成加密签名串
    String signMsgVal = "";

    public ApiWangYinPaymentDto(GoodsForm goodsForm) {
        this.payerContact = goodsForm.getPayerConcat();
        this.payerId = goodsForm.getPayerId();
        this.orderId = goodsForm.getOrderId();
        this.orderAmount = new BigDecimal(goodsForm.getOrderMoney()).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString();
        this.orderTime = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        this.orderTimestamp = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        this.productName = goodsForm.getProductName();
        this.productDesc = goodsForm.getProductName();
        this.productNum = goodsForm.getProductNum();
        this.productId = goodsForm.getProductId();
        this.signMsgVal = createSign();
    }
    // 创建签名
    private String createSign() {
        // signMsg 签名字符串 不可空，生成加密签名串
        String signMsgVal = "";
        signMsgVal = KqUtil.appendParam(signMsgVal, "inputCharset", inputCharset);
        signMsgVal = KqUtil.appendParam(signMsgVal, "pageUrl", pageUrl);
        signMsgVal = KqUtil.appendParam(signMsgVal, "bgUrl", bgUrl);
        signMsgVal = KqUtil.appendParam(signMsgVal, "version", version);
        signMsgVal = KqUtil.appendParam(signMsgVal, "language", language);
        signMsgVal = KqUtil.appendParam(signMsgVal, "signType", signType);
        signMsgVal = KqUtil.appendParam(signMsgVal, "merchantAcctId",merchantAcctId);
        signMsgVal = KqUtil.appendParam(signMsgVal, "payerName", payerName);
        signMsgVal = KqUtil.appendParam(signMsgVal, "payerContactType",payerContactType);
        signMsgVal = KqUtil.appendParam(signMsgVal, "payerContact", payerContact);
        signMsgVal = KqUtil.appendParam(signMsgVal, "payerIdType", payerIdType);
        signMsgVal = KqUtil.appendParam(signMsgVal, "payerId", payerId);
        signMsgVal = KqUtil.appendParam(signMsgVal, "payerIP", payerIP);
        signMsgVal = KqUtil.appendParam(signMsgVal, "orderId", orderId);
        signMsgVal = KqUtil.appendParam(signMsgVal, "orderAmount", orderAmount);
        signMsgVal = KqUtil.appendParam(signMsgVal, "orderTime", orderTime);
        signMsgVal = KqUtil.appendParam(signMsgVal, "orderTimestamp", orderTimestamp);
        signMsgVal = KqUtil.appendParam(signMsgVal, "productName", productName);
        signMsgVal = KqUtil.appendParam(signMsgVal, "productNum", productNum);
        signMsgVal = KqUtil.appendParam(signMsgVal, "productId", productId);
        signMsgVal = KqUtil.appendParam(signMsgVal, "productDesc", productDesc);
        signMsgVal = KqUtil.appendParam(signMsgVal, "ext1", ext1);
        signMsgVal = KqUtil.appendParam(signMsgVal, "ext2", ext2);
        signMsgVal = KqUtil.appendParam(signMsgVal, "payType", payType);
        signMsgVal = KqUtil.appendParam(signMsgVal, "bankId", bankId);
        signMsgVal = KqUtil.appendParam(signMsgVal, "redoFlag", redoFlag);
        signMsgVal = KqUtil.appendParam(signMsgVal, "pid", pid);
        signMsgVal = KqUtil.appendParam(signMsgVal, "submitType", submitType);
        signMsgVal = KqUtil.appendParam(signMsgVal, "orderTimeOut", orderTimeOut);
        signMsgVal = KqUtil.appendParam(signMsgVal, "period", period);
        signMsgVal = KqUtil.appendParam(signMsgVal, "extDataType", extDataType);
        signMsgVal = KqUtil.appendParam(signMsgVal, "extDataContent", extDataContent);
        Pkipair pki = new Pkipair();
        return pki.signMsg(signMsgVal);
    }
}
