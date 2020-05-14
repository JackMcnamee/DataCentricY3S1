package com.shops;

import java.util.ArrayList;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
	
	String mongoDB = "storeHeadOfficeDB";
	String mongoCollection = "storeHeadOffice";
	
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	public MongoDAO() throws Exception {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}
	
	// load all offices in mongoDB
	public ArrayList<Office> loadOffices() throws MongoException {
	   Gson gson = new Gson();
	   ArrayList<Office> officesList = new ArrayList<Office>();
	   
	   FindIterable<Document> offices = collection.find(); // find all offices in collection
	   
	   for (Document d : offices) {
		   Office office = gson.fromJson(d.toJson(), Office.class);
		   officesList.add(office);
	   }		
	   
	   for(Office o: officesList) {
		   System.out.println(o);
	   }
	
	   return officesList;
	}
	
	// add office to mongoDB
	public void addOffice(Office office) throws MongoException{
	   System.out.println("In addOffice()");
	   Document document = new Document();
	   
	   document.put("_id", office.get_id());
	   document.put("location", office.getLocation());
	   
	   collection.insertOne(document); // add document to collection
	}
}
