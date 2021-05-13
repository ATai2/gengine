package com.atai.gengine.gflow.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SendRejectionMail implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("SendRejectionMail for employee "
                + delegateExecution.getVariable("employee"));
    }
}
