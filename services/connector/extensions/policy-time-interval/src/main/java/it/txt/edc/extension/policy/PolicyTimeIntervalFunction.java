package it.txt.edc.extension.policy;

import org.eclipse.edc.policy.engine.spi.AtomicConstraintFunction;
import org.eclipse.edc.policy.engine.spi.PolicyContext;
import org.eclipse.edc.policy.model.Operator;
import org.eclipse.edc.policy.model.Permission;
import org.eclipse.edc.spi.monitor.Monitor;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import java.time.*; 
import java.time.temporal.*; 


public class PolicyTimeIntervalFunction implements AtomicConstraintFunction<Permission> {
    
    @Inject
    private Monitor monitor;
    
    public PolicyTimeIntervalFunction(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public boolean evaluate(Operator operator, Object rightValue, Permission rule, PolicyContext context){

        monitor.info("Evaluating Policy Time Interval Function");

        var dateinPolicy = OffsetDateTime.parse((String) rightValue);

        ZoneId zoneId = ZoneId.of("Europe/Rome");
        ZonedDateTime currentDateTime = ZonedDateTime.now(zoneId);
        ZonedDateTime truncatedDateTime = currentDateTime.truncatedTo(ChronoUnit.MINUTES);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String formatted = truncatedDateTime.format(fmt);

        OffsetDateTime timestamp = OffsetDateTime.parse(formatted);

        switch (operator) {
            case LT:
                return timestamp.isBefore(dateinPolicy);
            case LEQ:
                return timestamp.isBefore(dateinPolicy) || timestamp.equals(dateinPolicy);
            case GT:
                return timestamp.isAfter(dateinPolicy);
            case GEQ:
                return timestamp.isAfter(dateinPolicy) || timestamp.equals(dateinPolicy);
            case EQ:
                return timestamp.equals(dateinPolicy);
            case NEQ:
                return !(timestamp.equals(dateinPolicy));
            default:
                return false;
        }
    }
}