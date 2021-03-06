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

import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.history.User;

@WebServlet("/availableElementsToRefer")
public class ElementsToReferServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 133L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);

		// Checking the user is logged
		if(!isLogged(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		HttpSession session = request.getSession(false);
		User historyUser = (User) session.getAttribute("user");
		String dsl = (String) session.getAttribute("dsl");

		EPackage metamodelPackage = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).getEcoreModel();
		EList<EClassifier> classifiers = metamodelPackage.getEClassifiers();
		ArrayList<String> metamodelElements = new ArrayList<String>();
		for (EClassifier eClassifier : classifiers) {
			metamodelElements.add(eClassifier.getName());
			if(eClassifier instanceof EClass) {
				EClass eClass=(EClass)eClassifier;
				for(EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures()) {
					metamodelElements.add(eClassifier.getName()+"."+eStructuralFeature.getName());
				}
			}
		}

		// Building the response
		Gson gson = new Gson();
		String result = gson.toJson(metamodelElements);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(result); 
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
	}
}
