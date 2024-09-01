package it.txt.edc.extension.policy;

import org.eclipse.edc.policy.engine.spi.AtomicConstraintFunction;
import org.eclipse.edc.policy.engine.spi.PolicyContext;
import org.eclipse.edc.policy.model.Operator;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.policy.model.Permission;
import org.eclipse.edc.spi.monitor.Monitor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.eclipse.edc.connector.contract.spi.types.agreement.ContractAgreement;
import org.eclipse.edc.connector.contract.spi.types.negotiation.ContractNegotiation;



public class PolicyNtimeUsageFunction implements AtomicConstraintFunction<Permission> {

    @Inject
    private Monitor monitor;

    public PolicyNtimeUsageFunction(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public boolean evaluate(Operator operator, Object rightValue, Permission rule, PolicyContext context){
      if(!(rightValue instanceof Integer)){
          context.reportProblem("Right-value expected to be Integer");
          return false;
      }

      if(operator != Operator.EQ){
          context.reportProblem("Invalid operator, only LTEQ is allowed");
          return false;
      }

      monitor.info("ASSET ID: "+ context.getContextData(ContractAgreement.class).getAssetId());
      monitor.info("CONSUMER ID: "+ context.getContextData(ContractAgreement.class).getConsumerId());
      monitor.info("CONSUMER ID: "+ context.getContextData(ContractNegotiation.class).getCounterPartyAddress());

      Integer count = getAccessNumber("Null");
      Integer value = count.compareTo(objectToInt(rightValue));
      boolean tmp = false;
      if(value > 0 ){
          return tmp;
      }else if (value < 0) {
          tmp = true;
      }

      return tmp;

    }

    //getAccessNUmber for the specific asset
    public Integer getAccessNumber(String id){
        Integer numOfAccesses = 0;
        
        //retrieve value from edc_contract_agreement table
        try (Connection connection = DriverManager.getConnection(System.getenv("MY_EDC_JDBC_URL"), System.getenv("MY_EDC_JDBC_USER"),System.getenv("MY_EDC_JDBC_PASSWORD"))){  
              String sql = "SELECT COUNT(*) AS 'access_count' FROM edc_contract_agreement WHERE consumer_agent_id = '"+System.getenv() + " AND asset_id = '";
              try(PreparedStatement statement = connection.prepareStatement(sql)){
                  try(ResultSet resultSet = statement.executeQuery()){
                      while (resultSet.next()) {
                          numOfAccesses = resultSet.getInt("access_count");
                          monitor.info("Access count: " + numOfAccesses);
                      }
                  }
              }
      
          } catch (SQLException e) {
              e.printStackTrace();
          } 
          
        return numOfAccesses;
    }

    public static int objectToInt(Object obj) {
      int x = (Integer) obj;
      return x;
    }

    
}




/*
    {
  "@context" : {
    "ids" : "https://w3id.org/idsa/core/",
    "idsc" : "https://w3id.org/idsa/code/"
  },
  "@type" : "ids:Permission",
  "@id" : "https://w3id.org/idsa/autogen/permission/4ad88c11-a00c-4479-94f6-2a68cce005ea",
  "ids:description" : [ {
    "@value" : "n-times-usage",
    "@type" : "http://www.w3.org/2001/XMLSchema#string"
  } ],
  "ids:title" : [ {
    "@value" : "Example Usage Policy",
    "@type" : "http://www.w3.org/2001/XMLSchema#string"
  } ],
  "ids:action" : [ {
    "@id" : "idsc:USE"
  } ],
  "ids:constraint" : [ {
    "@type" : "ids:Constraint",
    "@id" : "https://w3id.org/idsa/autogen/constraint/a5d77dcd-f838-48e9-bdc1-4b219946f8ac",
    "ids:rightOperand" : {
      "@value" : "5",
      "@type" : "http://www.w3.org/2001/XMLSchema#double"
    },
    "ids:leftOperand" : {
      "@id" : "idsc:COUNT"
    },
    "ids:operator" : {
      "@id" : "idsc:LTEQ"
    }
  } ],
  "ids:target": [...]
}
*/