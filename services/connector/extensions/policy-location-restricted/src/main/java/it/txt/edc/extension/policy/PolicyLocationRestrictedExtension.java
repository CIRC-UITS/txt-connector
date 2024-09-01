package it.txt.edc.extension.policy;

import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;

import org.eclipse.edc.policy.engine.spi.PolicyEngine;
import org.eclipse.edc.policy.engine.spi.RuleBindingRegistry;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.policy.model.Permission;
import org.eclipse.edc.spi.monitor.Monitor;
import static org.eclipse.edc.policy.model.OdrlNamespace.ODRL_SCHEMA;
// import static org.eclipse.edc.connector.contract.spi.validation.ContractValidationService.NEGOTIATION_SCOPE;

import static org.eclipse.edc.policy.engine.spi.PolicyEngine.ALL_SCOPES;


public class PolicyLocationRestrictedExtension implements ServiceExtension{

    private Monitor monitor;

    @Inject
    private RuleBindingRegistry ruleBindingRegistry;

    @Inject
    private PolicyEngine policyEngine;

    private static final String LOCATION_RESTRICTED_POLICY_KEY = "region";

    @Override
    public void initialize(ServiceExtensionContext context){

        monitor = context.getMonitor();
        monitor.info("Initialized policy location restricted");

        ruleBindingRegistry.bind("USE", "catalog");
        ruleBindingRegistry.bind(ODRL_SCHEMA + "use", "catalog");
        //bind the policy to the scope
        ruleBindingRegistry.bind(LOCATION_RESTRICTED_POLICY_KEY, "Catalog");
        //create the function object
        var function = new PolicyLocationRestrictedFunction(monitor);
        //bind the function to the scope
        policyEngine.registerFunction
                ("catalog", 
                Permission.class, 
                LOCATION_RESTRICTED_POLICY_KEY, 
                function);
    }

    @Override
    public void start() {
        monitor.info("Started Policy Location Restricted extension");
    }

    @Override
    public void shutdown(){
        monitor.info("Shutdown Policy Location Restricted extension");
    }
}