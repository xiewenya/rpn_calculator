package com.airwallex.rpn.core.identity;

import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.management.ManagementFactory;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content: generate identity base on the process id (pid)
 */
@Slf4j
public class SimpleProcessIdentityGenerator extends StringIdentityGenerator {

    @Override
    @NotNull protected String doGenIdentity() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];

        if (StringUtils.isEmpty(pid)){
            log.error("identity generator error");
            throw new RpnRuntimeException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return pid;
    }
}
