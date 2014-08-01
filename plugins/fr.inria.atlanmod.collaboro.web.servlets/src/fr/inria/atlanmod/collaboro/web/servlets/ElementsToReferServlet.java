package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.history.User;

@SuppressWarnings("serial")
@WebServlet("/availableElementsToRefer")
public class ElementsToReferServlet extends AbstractCollaboroServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		addResponseOptions(response);
		HttpSession session = request.getSession(false);
		if(session == null) 
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		else
		{
			User historyUser = (User) session.getAttribute("user");
			String dsl = (String) session.getAttribute("dsl");
			if(historyUser == null || dsl == null)
			{
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			else
			{
				EPackage metamodelPackage = CollaboroBackendFactory.getBackend(dsl).getEcoreModel();
				EList<EClassifier> classifiers=metamodelPackage.getEClassifiers();
				ArrayList<String> metamodelElements=new ArrayList<String>();
				for (EClassifier eClassifier : classifiers)
				{
					metamodelElements.add(eClassifier.getName());
					if(eClassifier instanceof EClass)
					{
						EClass eClass=(EClass)eClassifier;
						for(EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures())
						{
							metamodelElements.add(eClassifier.getName()+"."+eStructuralFeature.getName());
						}
					}
					
				}
				Gson gson=new Gson();
				String result=gson.toJson(metamodelElements);
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.print(result); 
				System.out.println("Result:"+result);
				
			}
		}
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
	}

}
