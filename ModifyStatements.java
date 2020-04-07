

package com.ontohealth.acuity;

import java.util.ArrayList;
import java.util.List;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.iterator.ExtendedIterator;
import openllet.jena.PelletReasonerFactory;



public class ModifyStatements {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
        
		
        
        
    	   	    	 
	  //test data
		
	  		//String patientsName= "Patient_Doug";
	  				//int ptAge=82;
	  				//int iabc=16;
	  				//int iimpact=10;
	  				//int isupervision=10;
	  				//int ifeeding=10;
	  				//int itoilet=10;
	  				//int imobility=10;
	  				
	  		 //setObject(patientsName, ptAge, iabc, iimpact, isupervision, ifeeding, itoilet, imobility);

    	
    	    	
      
		}
	
	//inferred data arrays
	  List<String> nurses=new ArrayList<String>();
	  List<String> nursesAndQualifications=new ArrayList<String>();
	  List<String> riskArray=new ArrayList<String>();
	  List<String> suggestionArray=new ArrayList<String>();
	  List<String> alliedArray=new ArrayList<String>();
	  List<String> unitArray=new ArrayList<String>();
	  String patient="";
	  List<String> severity=new ArrayList<String>();
	  int ptTotal;
	  
	 //asserted data properties
	  String aZero = "hasABCValue";
	  String aOne = "hasImpactOfSymptomsValue";
	  String aTwo = "hasSupervisionValue";
	  String aThree = "hasFeedingValue";
	  String aFour = "hasHygieneToiletingValue";
	  String aFive = "hasMobilityValue";
	  String aSix = "hasAge";
	
	 //Inferred data properties
	  String iZero = "hasTotalScore";
	  String iOne = "hasPossibleRisk";
	  String iTwo = "hasAlliedHealth";
	  String iThree = "hasShiftNurse";
	  String iFour = "hasQualification";
	  String iFive = "hasSuggestion";
	  String iSix = "hasPossibleUnit";
	  
	  
	public void setObject(OntModel model1, String patientsName, int age, int abc, int impact, int supervision, int feeding, int toilet, int mobility) { 
		
		double newTotal=(abc+impact+supervision+feeding+toilet+mobility);
		double acTotal=((newTotal/96)*100);
		ptTotal=(int) acTotal;	
		
		//arrays used when changing to users data
		 List<String> thePropertyBeingChangedArray = new ArrayList<String>();			
		 List<Integer> theNewLiteralsArray = new ArrayList<Integer>();
		 List<Statement> statementsToRemoveArray = new ArrayList<Statement>();
		 patient=patientsName;
		 
		theNewLiteralsArray.clear();
		statementsToRemoveArray.clear();
		thePropertyBeingChangedArray.clear();
		nurses.clear();
		nursesAndQualifications.clear();
		riskArray.clear();
		suggestionArray.clear();
		alliedArray.clear();
		unitArray.clear();
		severity.clear();
		Resource subject=null;
		 Property predicate=null;
		 
		 //ontology name spaces
		 String ns = "http://philshields.altervista.org/owl/unit.owl#";
			String hq="http://example.org/";
			
						
		//incoming integer array 			
    	theNewLiteralsArray.add(0,abc);
    	theNewLiteralsArray.add(1,impact);
    	theNewLiteralsArray.add(2,supervision);
    	theNewLiteralsArray.add(3,feeding);
    	theNewLiteralsArray.add(4,toilet);
    	theNewLiteralsArray.add(5,mobility);
    	theNewLiteralsArray.add(6,age);
    
		
		//store the properties to be deleted then reinstated in an array
		thePropertyBeingChangedArray.add(0,hq+aZero);
		thePropertyBeingChangedArray.add(1,hq+aOne);
		thePropertyBeingChangedArray.add(2,hq+aTwo);
		thePropertyBeingChangedArray.add(3,hq+aThree);
		thePropertyBeingChangedArray.add(4,hq+aFour);
		thePropertyBeingChangedArray.add(5,hq+aFive);
		thePropertyBeingChangedArray.add(6,ns+aSix);
		 
		
		//start deleting stuff to make way for the new
		
		 StmtIterator iter = model1.listStatements();
		 
		 while (iter.hasNext()) 
       {
            Statement stmt  = iter.nextStatement(); 
            
             subject = stmt.getSubject();
             predicate = stmt.getPredicate();  
              
             
             //put statements belonging to the properties for this patient in an array for deletion
             for (int i = 0; i < thePropertyBeingChangedArray.size(); i++) {
            if(subject.toString().equals(ns+patientsName)&& predicate.toString().equals(thePropertyBeingChangedArray.get(i))) {
             
            	
            	statementsToRemoveArray.add(stmt);
            	
            	          	 
            }          	
        
             }
            }
		 
		 Resource s = ResourceFactory.createResource(ns+patientsName);     
		 
     //the part that actually does the removal and addition   
   for (int i = 0; i < statementsToRemoveArray.size(); i++) {
		
	 model1.remove(statementsToRemoveArray.get(i));
	 Property p = ResourceFactory.createProperty(thePropertyBeingChangedArray.get(i));
	 Literal o = model1.createTypedLiteral(theNewLiteralsArray.get(i));
	 Statement newStatement = ResourceFactory.createStatement(s, p, o );
	 model1.add(newStatement);	 
   } 
   
   //model1 is the change model but it has to be made into model which allows inference can take place
   OntModel model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
   model.addSubModel(model1);
   
   age=0; abc=0; impact=0; supervision=0; feeding=0; toilet=0; mobility=0;  
   Individual ind = model.getIndividual(ns+patientsName);
	
	
	//asserted data properties used for testing 
	//OntProperty abcValue = model.getOntProperty(hq+aZero);
	//OntProperty aimpact = model.getOntProperty(hq+aOne);
	//OntProperty asupervision = model.getOntProperty(hq+aTwo);
	//OntProperty afeeding = model.getOntProperty(hq+aThree);
	//OntProperty atoilet = model.getOntProperty(hq+aFour);
	//OntProperty amobility = model.getOntProperty(hq+aFive);
	//OntProperty aage = model.getOntProperty(ns+aSix);
	
	
	//inferred data properties   	    	
	//OntProperty total = model.getOntProperty(ns+iZero);  	
	OntProperty risk = model.getOntProperty(ns+iOne);
	OntProperty allied = model.getOntProperty(ns+iTwo);
	OntProperty nurse = model.getOntProperty(ns+iThree);
	OntProperty qualification = model.getOntProperty(ns+iFour);
	OntProperty suggestion = model.getOntProperty(ns+iFive);
	OntProperty unit = model.getOntProperty(ns+iSix);
	OntProperty acu = model.getOntProperty(ns+"hasAcuityLevel");
	
	//print asserted data properties    for testing	
	//System.out.println("Properties for "+ind.getLocalName().toString());
	//System.out.println( abcValue.getLocalName()+"= "+ind.getPropertyValue(abcValue).asLiteral().getInt());
	//System.out.println( aimpact.getLocalName()+"= "+ind.getPropertyValue(aimpact).asLiteral().getInt());
	//System.out.println( asupervision.getLocalName()+"= "+ind.getPropertyValue(asupervision).asLiteral().getInt());
	//System.out.println( afeeding.getLocalName()+"= "+ind.getPropertyValue(afeeding).asLiteral().getInt());
	//System.out.println( atoilet.getLocalName()+"= "+ind.getPropertyValue(atoilet).asLiteral().getInt());
	//System.out.println(amobility.getLocalName()+"= "+ind.getPropertyValue(amobility).asLiteral().getInt());
	//System.out.println( aage.getLocalName()+"= "+ind.getPropertyValue(aage).asLiteral().getInt());
		
	  		
	//put the new inferred properties into arrays so they can be eventually printed out in the jsp
	//ptTotal=ind.getPropertyValue(total).asLiteral().getInt();
   //list classes that the patient has acuity in
	for (ExtendedIterator<Resource> i = ind.listRDFTypes(true); i.hasNext(); ) {
	    Resource cls = (Resource) i.next();
	   // System.out.println( cls.getLocalName() );
	    severity.add(cls.getLocalName());
	}
   
	    	
	for (StmtIterator j = ind.listProperties(risk); j.hasNext(); ) {
       Statement sr = j.next();
       //System.out.println( "    " + s.getPredicate().getLocalName() + " -> " );
      // System.out.println( "A possible risk... " + sr.getLiteral().getLexicalForm());
       riskArray.add( "A possible risk... " + sr.getLiteral().getLexicalForm());
   }
	
			
			//an example of getting a data property
		for (StmtIterator j = ind.listProperties(suggestion); j.hasNext(); ) {
           Statement ss = j.next();
           //System.out.println( "    " + s.getPredicate().getLocalName() + " -> " );
          // System.out.println( "A possible suggestion... " + ss.getLiteral().getLexicalForm());
           suggestionArray.add( "A possible suggestion... " + ss.getLiteral().getLexicalForm());
       }
		
		for (StmtIterator j = ind.listProperties(unit); j.hasNext(); ) {
           Statement su = j.next();
           //System.out.println( "    " + s.getPredicate().getLocalName() + " -> " );
          // System.out.println( "A possible unit... " + su.getLiteral().getLexicalForm());
           unitArray.add("A possible unit... " + su.getLiteral().getLexicalForm());
       }
		
		for (StmtIterator j = ind.listProperties(acu); j.hasNext(); ) {
	           Statement abs = j.next();
	           //System.out.println( "    " + s.getPredicate().getLocalName() + " -> " );
	          // System.out.println( "Acuity level... " + abs.getLiteral().getLexicalForm());
	           //unitArray.add("A possible unit... " + su.getLiteral().getLexicalForm());
	       }
		
		// an example of getting a class's individual 
		for (StmtIterator j = ind.listProperties(allied); j.hasNext(); ) {
			
           Statement sa = j.next();
           //System.out.println( "    " + s.getPredicate().getLocalName() + " -> " );
          //System.out.println( "Possible allied health... " + ((Resource) sa.getObject()).getLocalName());
          alliedArray.add("Possible allied health... " + ((Resource) sa.getObject()).getLocalName());
       }
		
				
	//put multiple possible nurses in array that may fit the qualification criteria
		for (StmtIterator j = ind.listProperties(nurse); j.hasNext(); ) {
			int x=0;
			Statement sn = j.next();
			nurses.add(x,((Resource) sn.getObject()).getLocalName());
           //System.out.println( "    " + s.getPredicate().getLocalName() + " -> " );
          // System.out.println( "A possible nurse... " + ((Resource) sn.getObject()).getLocalName());
           
          // System.out.println("a nurse from individual= "+nurses.get(x));
           
           
         //System.out.println("a nurse from individual= "+nurses.get(x));
         //System.out.println("Some nurse "+model.getIndividual(ns+nurses.get(x)).listProperties(qualification));
           Individual ind1 = model.getIndividual(ns+nurses.get(x));
           //System.out.println("a nurse from individual= "+ind1);
           
           //find the actual nurses that have the qualifications
           for (StmtIterator ji = ind1.listProperties(qualification); ji.hasNext(); ) {
               Statement si = ji.next();
               //System.out.println( "    " + s.getPredicate().getLocalName() + " -> " );
               //System.out.println( nurses.get(x)+" has an appropriate qualification... " + si.getLiteral().getLexicalForm());
              nursesAndQualifications.add(nurses.get(x)+" has an appropriate qualification... " + si.getLiteral().getLexicalForm());
           }
           //for (int i = 0; i < nursesAndQualifications.size(); i++) {
        	   //System.out.println("nursesAndQualifications array= "+nursesAndQualifications.get(i));
           //}
		}
		
   	
		 } 
    
}

