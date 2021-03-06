package Util;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Environment.*;
import Environment.Paths.*;
import Environment.Points.EntryPoint;
import Environment.Points.InterestPoint;
import Environment.Points.Point;
import Environment.Points.PreEntryPoint;
import mainPackage.Starter;

// Cette classe comporte une méthode d'import par type d'objet.
// Ces imports sont effectués depuis les fichiers .csv du dossier 'objects'.
// Tous les imports et créations d'objets sont effectués à l'instanciation d'un objet de cette classe
// et ils sont récupérables à l'aide des différents getters.

public class Import {

	ArrayList<Point> points = new ArrayList<Point>();
	ArrayList<Path> userPaths = new ArrayList<Path>();
	ArrayList<Path> carPaths = new ArrayList<Path>();
	ArrayList<PreEntryPoint> pointsPreEntree = new ArrayList<PreEntryPoint>();
	ArrayList<EntryPoint> pointsEntree = new ArrayList<EntryPoint>();
	ArrayList<InterestPoint> pointsInteret = new ArrayList<InterestPoint>();

	// Hashmap des points créés à l'importation, pour faciliter la création des chemins (appel des objets correspondants)
	private Map<String, Point> pointsMap = new HashMap<String, Point>();

	public Import() throws IOException {
		pointsPreEntree = getPreEntryPoints();
		points.addAll(pointsPreEntree);
		pointsEntree = getEntryPoints();
		points.addAll(pointsEntree);
		pointsInteret = getInterestPoints();
		points.addAll(pointsInteret);
		points.addAll(getPoints());
		points.addAll(getPointsRER());
		carPaths.addAll(getRoadPaths());
		carPaths.addAll(getHighwayPaths());
		carPaths.addAll(getEntryPaths());
		userPaths.addAll(getFootPaths());
		userPaths.addAll(getEntryPaths());
		userPaths.addAll(getBusPaths());
		userPaths.addAll(getRerPaths());
	};


	// Importe les points d'intérêt
	public ArrayList<InterestPoint> getInterestPoints() throws IOException {
		ArrayList<InterestPoint> points = new ArrayList<InterestPoint>();
		if (Starter.verbose>=1){ System.out.println("IN GETINTERESTPOINTS"); }
		FileReader filer = new FileReader("objects/interestPoints.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/interestPoints.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointID = line[0];
			String pointName = line[1];
			int x = Integer.parseInt(line[2]);
			int y = Integer.parseInt(line[3]);
			int z = Integer.parseInt(line[4]);
			InterestPoint point = new InterestPoint(pointName, x, y, z);
			points.add(point);
			pointsMap.put(pointID, point);
			lines--;
		}

		br.close();

		return points;
	}

	// Importe les points de pré-entrée
		public ArrayList<PreEntryPoint> getPreEntryPoints() throws IOException {
			ArrayList<PreEntryPoint> points = new ArrayList<PreEntryPoint>();
			if (Starter.verbose>=1){ System.out.println("IN GETENTRYPOINTS"); }
			FileReader filer = new FileReader("objects/preEntryPoints.csv");
			// NOMBRE DE LIGNES
			int lines = 0;
			BufferedReader reader = new BufferedReader(filer);
			while(reader.readLine()!=null) lines++;
			reader.close();
			// LECTURE DU FICHIER
			FileReader fr = new FileReader("objects/preEntryPoints.csv");
			BufferedReader br = new BufferedReader(fr);
			br.readLine(); // éliminer la ligne d'entête
			while (lines>1) {
				String[] line = br.readLine().split(",");
				String pointID = line[0];
				String pointName = line[1];
				PreEntryPoint point = new PreEntryPoint(pointName);
				points.add(point);
				pointsMap.put(pointID, point);
				lines--;
			}

			br.close();

			return points;
		}

	// Importe les points d'entrée
	public ArrayList<EntryPoint> getEntryPoints() throws IOException {
		ArrayList<EntryPoint> points = new ArrayList<EntryPoint>();
		if (Starter.verbose>=1){ System.out.println("IN GETENTRYPOINTS"); }
		FileReader filer = new FileReader("objects/entryPoints.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/entryPoints.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointID = line[0];
			String pointName = line[1];
			int x = Integer.parseInt(line[2]);
			int y = Integer.parseInt(line[3]);
			int z = Integer.parseInt(line[4]);
			EntryPoint point = new EntryPoint(pointName, x, y, z);
			points.add(point);
			pointsMap.put(pointID, point);
			lines--;
		}

		br.close();

		return points;
	}

	// Importe les points 'de passage'
	public ArrayList<Point> getPoints() throws IOException {
		ArrayList<Point> points = new ArrayList<Point>();
		if (Starter.verbose>=1){ System.out.println("IN GETPOINTS"); }
		FileReader filer = new FileReader("objects/points.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/points.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointID = line[0];
			String pointName = line[1];
			int x = Integer.parseInt(line[2]);
			int y = Integer.parseInt(line[3]);
			int z = Integer.parseInt(line[4]);
			Point point = new Point(pointName, x, y, z);
			points.add(point);
			pointsMap.put(pointID, point);
			lines--;
		}

		br.close();

		return points;
	}

	// Importe les points de RER
	public ArrayList<Point> getPointsRER() throws IOException {
		ArrayList<Point> points = new ArrayList<Point>();
		if (Starter.verbose>=1){ System.out.println("IN GETPOINTSRER"); }
		FileReader filer = new FileReader("objects/pointsRER.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/pointsRER.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointID = line[0];
			String pointName = line[1];
			int x = Integer.parseInt(line[2]);
			int y = Integer.parseInt(line[3]);
			int z = Integer.parseInt(line[4]);
			Point point = new Point(pointName, x, y, z);
			points.add(point);
			pointsMap.put(pointID, point);
			lines--;
		}

		br.close();

		return points;
	}

	//Importe les chemins d'entrée (abstraits)
	public ArrayList<EntryPath> getEntryPaths() throws IOException {
		ArrayList<EntryPath> paths = new ArrayList<EntryPath>();
		if (Starter.verbose>=1){ System.out.println("IN GETENTRYPATH"); }
		FileReader filer = new FileReader("objects/entryPaths.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/entryPaths.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointAID = line[0];
			String pointBID = line[1];
			EntryPath path = new EntryPath(pointsMap.get(pointAID), pointsMap.get(pointBID));
			EntryPath pathReturn = new EntryPath(pointsMap.get(pointBID), pointsMap.get(pointAID));
			paths.add(path);
			paths.add(pathReturn);
			lines--;
		}

		br.close();

		return paths;
	}
	
	
	// Importe les chemins autoroute
	public ArrayList<HighwayPath> getHighwayPaths() throws IOException {
		ArrayList<HighwayPath> paths = new ArrayList<HighwayPath>();
		if (Starter.verbose>=1){ System.out.println("IN GETHIGHWAYPATH"); }
		FileReader filer = new FileReader("objects/highwayPaths.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/highwayPaths.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointAID = line[0];
			String pointBID = line[1];
			HighwayPath path = new HighwayPath(pointsMap.get(pointAID), pointsMap.get(pointBID));
			HighwayPath pathReturn = new HighwayPath(pointsMap.get(pointBID), pointsMap.get(pointAID));
			paths.add(path);
			paths.add(pathReturn);
			lines--;
		}

		br.close();

		return paths;
	}

	// Importe les chemins route
	public ArrayList<RoadPath> getRoadPaths() throws IOException {
		ArrayList<RoadPath> paths = new ArrayList<RoadPath>();
		if (Starter.verbose>=1){ System.out.println("IN GETROADPATH"); }
		FileReader filer = new FileReader("objects/roadPaths.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/roadPaths.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointAID = line[0];
			String pointBID = line[1];
			RoadPath path = new RoadPath(pointsMap.get(pointAID), pointsMap.get(pointBID));
			RoadPath pathReturn = new RoadPath(pointsMap.get(pointBID), pointsMap.get(pointAID));
			paths.add(path);
			paths.add(pathReturn);
			lines--;
		}

		br.close();

		return paths;
	}

	// Importe les chemins piétons
	public ArrayList<Path> getFootPaths() throws IOException {
		ArrayList<Path> paths = new ArrayList<Path>();
		if (Starter.verbose>=1){ System.out.println("IN GETFOOTPATH"); }
		FileReader filer = new FileReader("objects/footPaths.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/footPaths.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointAID = line[0];
			String pointBID = line[1];
			FootPath path = new FootPath(pointsMap.get(pointAID), pointsMap.get(pointBID));
			//System.out.println(path.toString());
			FootPath pathReturn = new FootPath(pointsMap.get(pointBID), pointsMap.get(pointAID));
			paths.add(path);
			paths.add(pathReturn);
			lines--;
		}

		br.close();


		filer = new FileReader("objects/roadPaths.csv");
		// NOMBRE DE LIGNES
		lines = 0;
		reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		fr = new FileReader("objects/roadPaths.csv");
		br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointAID = line[0];
			String pointBID = line[1];
			FootPath path = new FootPath(pointsMap.get(pointAID), pointsMap.get(pointBID));
			//System.out.println(path.toString());
			FootPath pathReturn = new FootPath(pointsMap.get(pointBID), pointsMap.get(pointAID));
			paths.add(path);
			paths.add(pathReturn);
			lines--;
		}
		/*for (Path p: paths){
			System.out.println(p.toString());
		}*/

		br.close();
		return paths;
	}

	// Importe les chemins RER
	public ArrayList<RerPath> getRerPaths() throws IOException {
		ArrayList<RerPath> paths = new ArrayList<RerPath>();
		if (Starter.verbose>=1){ System.out.println("IN GETRERPATH"); }
		FileReader filer = new FileReader("objects/rerPaths.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/rerPaths.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointAID = line[0];
			String pointBID = line[1];
			int lineID = Integer.parseInt(line[2]);
			RerPath path = new RerPath(pointsMap.get(pointAID), pointsMap.get(pointBID), lineID);
			RerPath pathReturn = new RerPath(pointsMap.get(pointBID), pointsMap.get(pointAID), lineID);
			paths.add(path);
			paths.add(pathReturn);
			lines--;
		}

		br.close();

		return paths;
	}
	
	// Importe les chemins Bus
		public ArrayList<BusPath> getBusPaths() throws IOException {
			ArrayList<BusPath> paths = new ArrayList<BusPath>();
			if (Starter.verbose>=1){ System.out.println("IN GETRERPATH"); }
			FileReader filer = new FileReader("objects/busPaths.csv");
			// NOMBRE DE LIGNES
			int lines = 0;
			BufferedReader reader = new BufferedReader(filer);
			while(reader.readLine()!=null) lines++;
			reader.close();
			// LECTURE DU FICHIER
			FileReader fr = new FileReader("objects/busPaths.csv");
			BufferedReader br = new BufferedReader(fr);
			br.readLine(); // éliminer la ligne d'entête
			while (lines>1) {
				String[] line = br.readLine().split(",");
				String pointAID = line[0];
				String pointBID = line[1];
				int lineID = Integer.parseInt(line[2]);
				BusPath path = new BusPath(pointsMap.get(pointAID), pointsMap.get(pointBID), lineID);
				BusPath pathReturn = new BusPath(pointsMap.get(pointBID), pointsMap.get(pointAID), lineID);
				paths.add(path);
				paths.add(pathReturn);
				lines--;
			}

			br.close();

			return paths;
		}




	// GETTERS DES LISTES D'OBJETS

	public ArrayList<PreEntryPoint> getPointsPreEntree(){
		return pointsPreEntree;
	}

	public ArrayList<InterestPoint> getPointsInteret(){
		return pointsInteret;
	}

	public ArrayList<Point> getAllPoints(){
		return points;
	}

	public ArrayList<Path> getUserPaths(){
		return userPaths;
	}

	public ArrayList<Path> getCarPaths(){
		return carPaths;
	}


}
