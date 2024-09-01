package it.txt.edc.extension.policy;

import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;
import org.eclipse.edc.policy.engine.spi.PolicyEngine;
import org.eclipse.edc.policy.engine.spi.RuleBindingRegistry;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.policy.model.Permission;

import org.eclipse.edc.spi.monitor.Monitor;

import static org.eclipse.edc.policy.engine.spi.PolicyEngine.ALL_SCOPES;


public class PolicyNtimeUsageExtension implements ServiceExtension{

    private Monitor monitor;

    @Inject
    private RuleBindingRegistry ruleBindingRegistry;

    @Inject
    private PolicyEngine policyEngine;

    private static final String N_TIME_POLICY_KEY = "POLICY_N_TIME";

    @Override
    public void initialize(ServiceExtensionContext context) {

        monitor = context.getMonitor();
        monitor.info("Initialized Policy N Times Usage");

        //bind the policy to the scope
        ruleBindingRegistry.bind(N_TIME_POLICY_KEY, "transfer.process");
        //create the function object
        var function = new PolicyNtimeUsageFunction(monitor);
        //bind the function to the scope
        policyEngine.registerFunction(
                "transfer.process", 
                Permission.class, 
                N_TIME_POLICY_KEY, 
                function);
    }

    @Override
    public void start() {
        monitor.info("Started Policy N Times Usage extension");
    }

    @Override
    public void shutdown(){
        monitor.info("Shutdown Policy N Times Usage extension");
    }

}