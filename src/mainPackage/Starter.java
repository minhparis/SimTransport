package mainPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.SwingUtilities;

import Environment.*;
import Environment.Paths.Path;
import Environment.Points.InterestPoint;
import Environment.Points.Point;
import Environment.Points.PreEntryPoint;
import Graphics.Panel;
import Graphics.Window;
import Individuals.*;
import Util.Import;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class Starter extends jade.core.Agent {

	public Starter() {
	}
	
	// Lancer dans Run Configurations, Main class : jade.Boot, Program arguments : -local-host 127.0.0.1 -agents "starter:mainPackage.Starter"
	
	/////////////////////////////////// PARAMETRES ///////////////////////////////////
	
	public static int simulationTime = 50; // en s pour une journée complète
	public static int stepLength = 15; // en s (maximum 15s recommandé, sinon arrondis violents)
	public static int startHour = 10; // heure de début de la simulation
	
	// On fait tourner un certain nombre d'agents Person, mais ces agents peuvent 
	// représenter plusieurs personnes réelles pour alléger la simulation. 
	// Ceci intervient dans l'encombrement des routes.
	public static int nbPersons = 10;
	public static int realUsersPerPerson = 100;
	
	// Heures génération aléatoire des schedules :
	public static int centerBeginTime = 10; 
	public static double sigmaBeginTime = 4;
	public static int centerEndTime = 18; 
	public static double sigmaEndTime = 4;

	// Affichage
	public static boolean showSimulation = true;
	public static int windowSize = 700; // taille de la fenêtre en pixels (représente 10km)
	public static int verbose = 1; 
	// 0 : rien
	// 1 : Lancement des personnes et départs/arrivées
	// 2 : 1 + détail des trajets
	
	/////////////////////////////////// MODELE DE DECISION ///////////////////////////////////

	// Simulation sur plusieurs jours
	public static int totalDays = 2;
	
	private static double carFactorInit = 0.5;
	
	// carFactor est compris entre 0 et 1 et décrit la probabilité de choisir la voiture
	// en fonction des résultats de la journée précédente
	public static double carFactor(){
		if (currentDay==0) {
			return carFactorInit;
		} else {
			return 0;
		}
	}
	
	// Ces valeurs correpondent aux résultats de la journée précédente
	public static int timeUsedCars = 0;
	public static int timeUsedPublicTransport = 0;
	private static int nbPeopleCar = 0;
	private static int nbPeoplePublicTransport = 0;
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static Window f;
	public static Panel pan;
	public static int simSize = 10000; // taille en metres de la simulation
	public static Environment env;
	
	private static int currentDay = 0;
	
	private Clock clock;
	private ArrayList<Person> personsSauv;
	private ArrayList<Person> currentPersons;
	
	// On utilise un AID public pour l'envoi des messages de nouvelle journée
	public static AID aidStarter; 

	// Dans le setup du starter on crée et lance tous les agents désirés
	public void setup() {
		
		aidStarter = this.getAID();
		
		// IMPORT DES POINTS DEPUIS LES FICHIERS CSV
		
		Import im = null;
		try {
			im = new Import();
		} catch (IOException e) {
			System.out.println("FAIL");
		}
		
		ArrayList<Point> points = im.getAllPoints();
		ArrayList<Path> carPaths = im.getCarPaths();
		ArrayList<Path> userPaths = im.getUserPaths();
		
		ArrayList<PreEntryPoint> pointsPreEntree = im.getPointsPreEntree();
		ArrayList<InterestPoint> pointsInteret = im.getPointsInteret();
		
		// CREATION DE L'ENVIRONNEMENT A PARTIR DES OBJETS

		env = new Environment(points, carPaths, userPaths);

		personsSauv = new ArrayList<Person>();

		// Création des personnes (le choix de leur mode de transport et leur lancement sera
		// effectué au début de chaque jour )
		for (int i = 0; i < nbPersons; i++){
			Person newPerson = Person.rand_AllerRetour(pointsPreEntree, pointsInteret, env);
			personsSauv.add(newPerson);
		}
		
		// Lancer le comportement des journées
		this.addBehaviour(new Days());
		
		// Lancer le premier jour
		ACLMessage m = new ACLMessage(2); // 2 pour un nouveau jour
		m.addReceiver(this.getAID());
		this.send(m);

		
		
		
		

	}
	
	public boolean noMoreDays(){
		return currentDay == totalDays;
	}
	
	public void incCurrentDay(){
		currentDay++;
	}

	public ArrayList<Person> getPersonsSauv() {
		return personsSauv;
	}
	
	public void incNbPeopleCar() {
		nbPeopleCar++;
	}

	public void incNbPeoplePublicTransport() {
		nbPeoplePublicTransport++;
	}
	
	public void reset() {
		nbPeopleCar = 0;
		nbPeoplePublicTransport = 0;
		timeUsedCars = 0;
		timeUsedPublicTransport = 0;
		int offset = (currentDay-1)*nbPersons;
		for (int i = 0; i < personsSauv.size(); i++) {
			try {
				this.getContainerController().getAgent("person" + (i + 1 + offset)).kill();
			} catch (StaleProxyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ControllerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			this.getContainerController().getAgent("clock"+currentDay).kill();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int getCurrentDay() {
		return currentDay;
	}

	public void setCurrentPersons(ArrayList<Person> currentPersons) {
		this.currentPersons = currentPersons;
	}
	
	
	
	
}