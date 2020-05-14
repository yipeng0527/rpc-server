package com.yp.provider.v2;

import com.yp.api.v2.IPaymentService;

/**
 * @author ex-yipeng
 * @version Id: PaymentServiceImpl.java, v 0.1 2020/5/14 11:29 ex-yipeng Exp $
 */
@RpcService(IPaymentService.class)
public class PaymentServiceImpl implements IPaymentService {
    @Override
    public void doPay() {
        System.out.println("执行doPay方法");
    }
}
