package it.txt.edc.extension.policy;

import org.eclipse.edc.policy.engine.spi.PolicyEngine;
import org.eclipse.edc.policy.engine.spi.RuleBindingRegistry;
import org.eclipse.edc.policy.model.Permission;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.spi.monitor.Monitor;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;

import static org.eclipse.edc.policy.engine.spi.PolicyEngine.ALL_SCOPES;

public class PolicyTimeIntervalExtension implements ServiceExtension{

    private static final String POLICY_EVALUATION_TIME = "timeInterval";
    private Monitor monitor;
    
    @Inject
    private RuleBindingRegistry ruleBindingRegistry;

    @Inject
    private PolicyEngine policyEngine;

    @Override
    public void initialize(ServiceExtensionContext context){
        
        monitor = context.getMonitor();
        monitor.info("Initialized Policy Time Interval");

        ruleBindingRegistry.bind(POLICY_EVALUATION_TIME, ALL_SCOPES);
        var function = new PolicyTimeIntervalFunction(monitor);
        policyEngine.registerFunction(ALL_SCOPES, Permission.class, POLICY_EVALUATION_TIME, function);

    }

    @Override
    public void start() {
        monitor.info("Started Policy Time Interval extension");
    }

    @Override
    public void shutdown(){
        monitor.info("Shutdown Policy Time Interval extension");
    }
}
