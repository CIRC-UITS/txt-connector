package it.txt.edc.extension.policy;


import org.eclipse.edc.policy.engine.spi.PolicyEngine;
import org.eclipse.edc.policy.engine.spi.RuleBindingRegistry;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.spi.monitor.Monitor;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;

public class PolicyProhibitAccessExtension implements ServiceExtension{

    @Inject
    private Monitor monitor;

    @Inject
    private RuleBindingRegistry ruleBindingRegistry;

    
    //private PolicyDefinitionService policyDefinitionService;

    @Inject
    private PolicyEngine policyEngine;


    @Override
    public void initialize(ServiceExtensionContext context){
        var prohibitAccessService = new ProhibitAccessService(ruleBindingRegistry, policyEngine);
        prohibitAccessService.register();        
    }


    @Override
    public void start(){
        monitor.info("Started Policy Prohibit Access Extension");
    }



    
}
