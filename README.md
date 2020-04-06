# AcuitySuggester
This project receives patient acuity scores from the user and infers patient resources and risks using an ontology/jena/swrl and a reasoner.
The easiest way to run this project is to put the acuitySuggester.war into your Tomcat9 server and run it. The project was constructed with Java 8.

The web application consists of four main interconnected modules which enable the application to receive and infer data. 
The index page accepts acuity data from the user. Data being, the acuity scores, patient name and age. 
The acuity servlet receives patient acuity data from the index page and objects/SWRL from the ontology. It directs the functional data and ontology to Apache Jena and receives inferred results back from the reasoner. 
The ModifyStatements.java class contains Apache Jena, which is an open source Java framework for building Semantic Web and linked data applications. Its purpose in this project is to accept the functional data and facilitate reasoning of the ontology. Jena facilitates reasoning by converting the ontology into two actionable models, one for collecting data and the other for reasoning. For the purpose of clarity, we called the two models ‘ont’ and ‘inf’ respectively. 
The ‘ont’ model contains the SWRL rules and objects and accepts patient age, name and acuity scores via the servlet from the user. The ‘inf’ model is derived from the ‘ont’ model and is the model reasoned by Pellet. Consequently, the pellet reasoner infers new data, the recommendations, directly from the ‘inf’ model. The reason we used two models is that the reasoner cannot reason directly on the ‘ont’ model. After reasoning is completed inferred data is directed back to the servlet and eventually the user. During the construction of the ontology, the pellet reasoner also validated the ontology and found no logical errors. 

Inferred results are displayed on the acuityResults page. 
