/*******************************************************************************
 * Copyright (c) 2014 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Juan David Villa Calle - (juan-david.villa_calle@inria.fr)
 *******************************************************************************/
package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import fr.inria.atlanmod.collaboro.web.backend.Controller;
import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Version;
import fr.inria.atlanmod.collaboro.history.VersionHistory;
import fr.inria.atlanmod.collaboro.history.Vote;


/**
 * Service to deserialize a .history model into json format
 * 
 * @author Juan David Villa Calle (juan-david.villa_calle@inria.fr)
 *
 */
@WebServlet(description = "Exposes the versions", urlPatterns = { "/version" })
public class VersionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//History history;
	Controller controller;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VersionsServlet() {
        super();
    }

    private String createProposalsResponseJson(VersionHistory versionHistory)
    {
    	String proposalsJson="[";
    	
    	EList<Version> versions=versionHistory.getVersions();
    	if(versions!=null && versions.size()>0)
    	{
    		//TODO The request should have as a parameter the version that the user wants.
    		Version firstVersion=versions.get(versions.size()-1);
    		EList<Proposal> proposals=firstVersion.getProposals();
    		if(proposals!=null && proposals.size()>0)
    		{
    			for (Proposal proposal : proposals) 
    			{
					
					String proposalJson=createCollaborationJson(proposal);
					proposalsJson=proposalsJson.concat(proposalJson).concat(",");
				}
    			
    			proposalsJson=proposalsJson.substring(0,proposalsJson.length()-1);
    			
    		}
    		else
    		{	
    			proposalsJson=proposalsJson.concat("{\"label\": \"No proposals yet\"}");
    		}
    		
    	}
    	
    	proposalsJson=proposalsJson.concat("]");
    	
    	return proposalsJson;
    }
    
    private String createCollaborationJson(Collaboration collaboration)
    {
    	String collaborationJson="";
    	if(collaboration instanceof Comment)
    	{
    		collaborationJson=createCommentJson((Comment)collaboration);
    	}
    	else
    	{
    		collaborationJson="{"+getCollaborationLabel(collaboration);
 
    		//Proposals and Solutions have comments
    		EList<Comment> collaborationComments=collaboration.getComments();
    		boolean collaborationHasComments=collaborationComments!=null && collaborationComments.size()>0;
    		if(collaborationHasComments)
    		{
    			collaborationHasComments=true;
    			collaborationJson=collaborationJson.concat(", \"children\": [");
    			
    			for (Comment collaborationComment : collaborationComments)
    			{
    				collaborationJson=collaborationJson.concat(createCollaborationJson(collaborationComment).concat(","));
				}
    			
    			collaborationJson=collaborationJson.substring(0,collaborationJson.length()-1);
    			
    			
    			if(collaboration instanceof Proposal)
    			{
    				Proposal proposal=(Proposal)collaboration;
    				EList<Solution> proposalSols=proposal.getSols();
    				
    				//Continue the children of the proposal with the solutions
    				collaborationJson=collaborationJson.concat(",");
    				for (Solution collaborationSolution : proposalSols)
    				{
    					collaborationJson=collaborationJson.concat(createCollaborationJson(collaborationSolution).concat(","));
					}
    				collaborationJson=collaborationJson.substring(0,collaborationJson.length()-1);
    				
    			}
    			collaborationJson=collaborationJson.concat("]");
    		}
    		//Test if is a proposal with no comments but with solutions
    		else if(collaboration instanceof Proposal)
    		{
    			
    			Proposal proposal=(Proposal)collaboration;
    			EList<Solution> proposalSols=proposal.getSols();
    			if(proposalSols!=null && proposalSols.size()>0)
    			{
    				collaborationJson=collaborationJson.concat(", \"children\": [");
    				for (Solution proposalSol : proposalSols)
    				{
						collaborationJson=collaborationJson.concat(createCollaborationJson(proposalSol).concat(","));
					}
    				collaborationJson=collaborationJson.substring(0,collaborationJson.length()-1);
    				collaborationJson=collaborationJson.concat("]");
    			}			
    			
    		}
    		//If the collaboration had no comments it was only created with it's label
    		collaborationJson=collaborationJson.concat("}");
    		
    	}
    	
    	return collaborationJson;
    }
    
    
    private String getCollaborationLabel(Collaboration collaboration)
    {
      
      	String collaborationJson="";
      	String typeOfCollaboration=collaboration.eClass().getName();
      	String collaborationLabel="\"label\": \"" + typeOfCollaboration + " "+collaboration.getId()+" from "+collaboration.getProposedBy().getId()+"\"";
      	collaborationJson=collaborationJson.concat(collaborationLabel);
      	if(collaboration.getRationale()!=null && collaboration.getRationale().length()>0)
      	{
      		collaborationJson=collaborationJson.concat(",");
          	String cleanRationale = collaboration.getRationale().replaceAll("(\\r|\\n|\")", " ");
          	String collaborationData="\"data\": { \"username\":\""+ collaboration.getProposedBy().getId() +"\",\"description\": \""+cleanRationale+"\"";
          	collaborationJson=collaborationJson.concat(collaborationData);
          	
          	EList<Vote> votes=collaboration.getVotes();
        	String usersAgree="";
        	String usersDisagree="";
        	
        	for (Vote vote : votes)
        	{
        		boolean voteAgrees=vote.isAgreement();
        		User user=vote.getUser();
        		
        		String userName="Vote with no user name";
    			if(user!=null)
    				userName=user.getId();
    			
        		if(voteAgrees)
        		{
        			
        			usersAgree=usersAgree.concat(userName).concat(", ");
        		}
        			
        		else
        			usersDisagree=usersDisagree.concat(userName).concat(", ");	
    		}
        	
        	if(usersAgree.length()>0)
    			usersAgree=usersAgree.substring(0,usersAgree.length()-2);
        	else
        		usersAgree="No users agree";
    		if(usersDisagree.length()>0)
    			usersDisagree=usersDisagree.substring(0,usersDisagree.length()-2);
    		else
    			usersDisagree="No users disagree";
    		
    		collaborationJson=collaborationJson.concat(",\"agree\":\""+usersAgree+"\"");
    		collaborationJson=collaborationJson.concat(",\"disagree\":\""+usersDisagree+"\"}");
    		
      	}
      	

      	
      	return collaborationJson;
    }
    
    private String createCommentJson(Comment comment)
    {
    	//String commentJson="{\"label\": \"Comment "+comment.getId()+" from "+comment.getProposedBy().getId()+"\"}";
    	String commentJson="{"+getCollaborationLabel(comment)+"}";
    	
    	return commentJson;
    }
    

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession theSession=request.getSession();
		System.out.println("Session creation time: "+theSession.getCreationTime());
		Enumeration<String> sessionAtributes=theSession.getAttributeNames();
		while(sessionAtributes.hasMoreElements())
		{
			System.out.println("attribute name of the session: "+sessionAtributes.nextElement());
		}
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8001");
        response.setContentType("application/json");
		
        URI uriHistoryModel=URI.createURI(getServletContext().getRealPath("/WEB-INF/model/ModiscoWorkflow.ecore"));
        //URI uriHistoryModel=URI.createFileURI(getServletContext().getRealPath("/WEB-INF/model/ModiscoWorkflow.history"));
        
        controller=new Controller(uriHistoryModel);
       // history=controller.getHistory();
        
        String proposalsJson="";
        
        EList<VersionHistory> versionHistories=controller.getHistory().getHistories();
        
        if(versionHistories!=null && versionHistories.size()>0)
        {
        	VersionHistory versionHistory=versionHistories.get(versionHistories.size()-1);
        	proposalsJson=createProposalsResponseJson(versionHistory);
        }
        
        System.out.println(proposalsJson);
        out.print(proposalsJson);   
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		
		PrintWriter out = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		
		
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e)
		  {
			  
		  }
		
		System.out.println(jb);
		Gson gson = new Gson();
		
		JsonParser parser = new JsonParser();
	    
	    JsonArray array=(JsonArray)parser.parse(jb.toString()).getAsJsonObject().get("collaborations");
	    
	    for (JsonElement jsonElement : array) 
	    {
	    	
	    	JsonCollaborationSimplified jsonCollaboration = gson.fromJson(jsonElement, JsonCollaborationSimplified.class);
	    	String collaborationType=jsonCollaboration.getType();
	    	System.out.println("El tipo de la colaboracion: "+collaborationType);
	    	if(collaborationType.compareTo("Proposal")==0)
	    	{
	    		Proposal newProposal = HistoryFactory.eINSTANCE.createProposal();
	    		EList<User> theUsers=controller.getHistory().getUsers();
	    		for (User user : theUsers) {
	    			if(user.getId().compareToIgnoreCase(jsonCollaboration.getProposedBy())==0)
	    				newProposal.setProposedBy(user);
				}
	    		newProposal.setRationale(jsonCollaboration.getRationale());
	    		controller.createProposal(newProposal);
	    	}
	    	else if(collaborationType.compareTo("Comment")==0)
	    	{
	    		Comment c= HistoryFactory.eINSTANCE.createComment();
	    		c.setRationale(jsonCollaboration.getRationale());
	    		
	    		EList<User> theUsers=controller.getHistory().getUsers();
	    		for (User user : theUsers) {
	    			if(user.getId().compareToIgnoreCase(jsonCollaboration.getProposedBy())==0)
	    				c.setProposedBy(user);
				}
	    		
	    	}
	    	
		}
	    
	    controller.saveHistory();
	    
		response.setContentType("application/json");
		//TODO Change the response to a success or failure alert
		out.print("{\"user\": { \"firstName\" : \"Juan\", \"lastName\" : \"Villa\", \"admin\" : false}}");
		
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		super.doOptions(request, response);
		
	}
	
	private void getNotations()
	{
		
	}
	


}
