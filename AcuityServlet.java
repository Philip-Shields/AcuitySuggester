package com.ontohealth.acuity;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;

import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

/**
 * Servlet implementation class AcuityServlet
 */
@WebServlet("/AcuityServlet")
public class AcuityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int patientNumber=0;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	ModifyStatements ms = new ModifyStatements();
    public AcuityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//load the data coming from the jsp forms
		String name=request.getParameter("Patients");
		int age=Integer.parseInt(request.getParameter("hasAge"));
		int abc=Integer.parseInt(request.getParameter("hasABCValue"));
		int impact=Integer.parseInt(request.getParameter("hasImpactOfSymptomsValue"));
		int supervision=Integer.parseInt(request.getParameter("hasSupervisionValue"));
		int feeding=Integer.parseInt(request.getParameter("hasFeedingValue"));
		int toilet=Integer.parseInt(request.getParameter("hasHygieneToiletingValue"));
		int mobility=Integer.parseInt(request.getParameter("hasMobilityValue"));
		
		//load arrays with inferred data coming from the modify statements class 
		List<String> qual = ms.nursesAndQualifications;
		List<String> unit =ms.unitArray;
		List<String> suggestion = ms.suggestionArray;
		List<String> risk = ms.riskArray;
		List<String> allied = ms.alliedArray;
		List<String> severity = ms.severity;
		
		//paths to the ontology and user responses txt
		ServletContext context = getServletContext();
		String fullPath = context.getRealPath("/WEB-INF/classes/acuity.owl");
		String activPath= "/home/phil/activity.txt";
		//String activPath= "C:\\eclipse-workspace\\ic\\WebContent\\WEB-INF\\classes\\activity.txt";
		
try {
			
            //create the change model1 
	OntModel model1 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
				
	InputStream in = FileManager.get().open(fullPath);
	if (in == null) {
        throw new IllegalArgumentException("File: " + fullPath + " not found");
    }
	model1.read(in,"");
				//pass the model ontology and the search word to aQuery method
			ms.setObject(model1, name,age,abc,impact,supervision,feeding,toilet,mobility);

			 

      } catch (Exception e) {
          System.out.println(e.getMessage());    
        }
		
		
			
		
		
		//set the inferred data coming from modify statements and going to the jsp
		response.setContentType("text/html");
		request.setAttribute("nurses", qual);
		request.setAttribute("unit", unit);
		request.setAttribute("suggestion", suggestion);
		request.setAttribute("risk", risk);
		request.setAttribute("allied", allied);
		request.setAttribute("patient", ms.patient);
		request.setAttribute("severity", severity);
		request.setAttribute("ptTotal", ms.ptTotal);
		
		
		
		try {
			
			//set the jsp where the inferred results are going to
			request.getRequestDispatcher("/acuityResults.jsp").forward(request, response);
				//System.out.println(mq.list);
			
		  } catch (ServletException e) {
			}
		
		
		//print user selections the the result inferred data to activity txt file
		/*
		try {
			
			FileWriter writer = new FileWriter(activPath,true);
			BufferedWriter out = new BufferedWriter(writer);	
			
			out.append("-----------Patient input data-----------\n");
			out.append("-----------Patient number= "+ (patientNumber=patientNumber+1)+"\n");
			out.append("Patient= "+name+"\n"+"Age= "+age+"\n"+"ABC= "+abc+"\n"+"Impact= "+impact+"\n"+"Supervision= "+supervision+"\n"+"Feeding= "+feeding+"\n"+"Toilet= "+toilet+"\n"+"Mobility= "+mobility+"\n"+"\n");
			
			out.append("-------------------Inferred data--------------\n" );
			for (int i = 0; i < qual.size(); i++) {
		        out.append(qual.get(i)+"\n");
			}
			
			for (int i = 0; i < unit.size(); i++) {
		        out.append(unit.get(i)+"\n");
			}
			
			for (int i = 0; i < suggestion.size(); i++) {
		        out.append(suggestion.get(i)+"\n");
			}
			
			for (int i = 0; i < risk.size(); i++) {
		        out.append(risk.get(i)+"\n");
			}
			
			for (int i = 0; i < allied.size(); i++) {
		        out.append(allied.get(i)+"\n");
			}
			
			for (int i = 0; i < severity.size(); i++) {
		        out.append(severity.get(i)+"\n");
			}
			out.append("Total= "+ms.ptTotal+"%\n"+"---------------END-----------------"+"\n\n\n");
			out.flush();
			out.close();
			
		}catch (Exception e) {
	          System.out.println(e.getMessage());    
	        }
		
		*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
