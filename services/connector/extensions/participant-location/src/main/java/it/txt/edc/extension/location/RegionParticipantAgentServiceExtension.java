package it.txt.edc.extension.location;

import org.jetbrains.annotations.NotNull;
import org.eclipse.edc.spi.agent.ParticipantAgentServiceExtension;
import org.eclipse.edc.spi.iam.ClaimToken;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;


public class RegionParticipantAgentServiceExtension implements ParticipantAgentServiceExtension {
    
    @Override
    public @NotNull Map<String, String> attributesFor(ClaimToken token) {
        Map<String, String> attributes = new HashMap<>();

        var result = extractLocationFromClaim(token.getClaims().get("client_id"));

        attributes.put("region", result);

        return attributes;
    }

    private String extractLocationFromClaim(Object object){
        
        String result = "";
        Pattern pattern = Pattern.compile("region:(.*?)$");

        Matcher matcher = pattern.matcher((CharSequence) object);
        if(matcher.find()){
        result = matcher.group();
        } 
        
        var client_id = ((String) object);
        int index = client_id.indexOf("region");
        result = client_id.substring(index + "region:".length());
        return result;
        
    }  
}