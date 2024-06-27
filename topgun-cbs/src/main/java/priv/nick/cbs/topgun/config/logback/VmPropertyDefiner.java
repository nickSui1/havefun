package priv.nick.cbs.topgun.config.logback;

import ch.qos.logback.core.PropertyDefinerBase;

import java.lang.management.ManagementFactory;

public class VmPropertyDefiner extends PropertyDefinerBase {
    public VmPropertyDefiner() {}
    @Override
    public String getPropertyValue() {
        return ManagementFactory.getRuntimeMXBean().getName();
    }
}
