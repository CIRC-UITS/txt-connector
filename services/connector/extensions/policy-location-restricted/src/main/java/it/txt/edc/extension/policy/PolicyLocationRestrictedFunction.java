package it.txt.edc.extension.policy;

import org.eclipse.edc.policy.engine.spi.AtomicConstraintFunction;
import org.eclipse.edc.policy.engine.spi.PolicyContext;
import org.eclipse.edc.policy.model.Operator;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.policy.model.Permission;
import org.eclipse.edc.spi.agent.ParticipantAgent;
import org.eclipse.edc.spi.monitor.Monitor;

public class PolicyLocationRestrictedFunction implements  AtomicConstraintFunction<Permission>{

    @Inject
    private final Monitor monitor;
    private ParticipantAgent participant;

    public PolicyLocationRestrictedFunction(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public boolean evaluate(Operator operator, Object rightValue, Permission rule, PolicyContext policyContext){

        monitor.info("EVALUATING POLICY LOCATION FUNCTION");

        if(!(rightValue instanceof String)) {
            policyContext.reportProblem("Right-value expected to be String");
            return false;
        }
        if(operator != Operator.EQ){
            policyContext.reportProblem("Invalid operator, only EQ is allowed");
            return false;
        }        

        participant = policyContext.getContextData(ParticipantAgent.class);

        monitor.info("CLAIMS CLAIM "+ policyContext.getContextData(ParticipantAgent.class).getClaims());
        monitor.info("ATTRIBUTES "+ policyContext.getContextData(ParticipantAgent.class).getAttributes());
        monitor.info("IDENTITY "+ policyContext.getContextData(ParticipantAgent.class).getIdentity());

        var participantLocation = extractLocationClaim(participant);
        monitor.info("PARTICIPANT_LOCATION: "+participantLocation);
        switch (operator) {
          case EQ:
              return participantLocation.equals(String.valueOf(rightValue));
          default:
              return false;
        }

    }

    private String extractLocationClaim(ParticipantAgent participant){
        
        String result_location = "";
        result_location = participant.getAttributes().get("region");
        return result_location;
    }       
}



