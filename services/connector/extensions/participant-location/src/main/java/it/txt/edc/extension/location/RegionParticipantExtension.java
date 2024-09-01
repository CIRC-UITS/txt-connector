package it.txt.edc.extension.location;

import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;
import org.eclipse.edc.spi.agent.ParticipantAgentService;


public class RegionParticipantExtension implements ServiceExtension{

    private ParticipantAgentService participantAgentService;

    @Override
    public void initialize(ServiceExtensionContext context){
        participantAgentService = context.getService(ParticipantAgentService.class);
        RegionParticipantAgentServiceExtension extension = new RegionParticipantAgentServiceExtension();
        participantAgentService.register(extension);
    }
    
}
