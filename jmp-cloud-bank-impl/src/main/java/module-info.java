module com.modules.api.impl {
    requires com.modules.api;
    requires com.modules.jmp.dto;
    exports com.modules.api.impl;
    // provides com.modules.api.Bank with com.modules.api.impl.CentralBank; but I am injecting the repo in the Bank
}