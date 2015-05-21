# Storm-archetype
This is a simple project that can be used to generate a mvn archetype for storm projects. This is still work in progress
so its not yet published to mvn central.

# Why use it?
Right now all this archetype does is generate a project structure with a sample wordcount topology and sets up a pom.xml
with storm-core dependency. This will give you a good starting point without having to generate all the boilerplate by hand.

#How to use it?
* git clone https://github.com/Parth-Brahmbhatt/storm-archetype.git
* cd storm-archetype
* mvn clean install (This will install this archetype in your local mvn repo)
* cd (to a location where you want to keep your new shiny storm project)
* mvn archetype:generate -DarchetypeGroupId=org.apache.storm -DarchetypeArtifactId=storm-archetype -DarchetypeVersion=1.0-SNAPSHOT  -DgroupId=(your-group) -DartifactId=(your-artifactId)
* You should now have a storm project under current directory with folder name same as your-artifactId.
* cd (your-artifactId)
* mvn clean compile assembly:single (This will generate a jar with all dependencies)
* storm jar target/(your-artifactId)-1.0-SNAPSHOT-jar-with-dependencies.jar apache.storm.topology.Topology (topology-name). (This assumes you have storm client installed locally)

# What's coming?
* Trident examples.
* Connector examples.
* Sample test cases to test bolts.spouts and topologies.
