package it.txt.edc.extension.policy;

import static org.eclipse.edc.policy.engine.spi.PolicyEngine.ALL_SCOPES;

import org.eclipse.edc.policy.engine.spi.PolicyEngine;
import org.eclipse.edc.policy.model.Prohibition;
import org.eclipse.edc.policy.model.Operator;
import org.eclipse.edc.policy.engine.spi.RuleBindingRegistry;

public class ProhibitAccessService {

    private final RuleBindingRegistry ruleBindingRegistry;
    private final PolicyEngine policyEngine;

    public ProhibitAccessService(RuleBindingRegistry ruleBindingRegistry, PolicyEngine policyEngine){
        this.ruleBindingRegistry = ruleBindingRegistry;
        this.policyEngine = policyEngine;
    }

    //register policy to the connector

    public void register(){
        ruleBindingRegistry.bind("USE", ALL_SCOPES);
        ruleBindingRegistry.bind("prohibit-access", ALL_SCOPES);
        policyEngine.registerFunction(ALL_SCOPES, Prohibition.class, "prohibit-access", 
        (operator, rightValue, rule, context1) -> operator.equals(Operator.EQ) &&
                        rightValue.toString().equals("true"));
    } 
    
}
