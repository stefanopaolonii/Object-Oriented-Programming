package abbigliamento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Abbigliamento {
	private Map<String,Modello> modelliMap= new HashMap<>();
	private Map<String,Colore> coloriMap= new HashMap<>();
	private Map<String,Materiale> materialiMap= new HashMap<>();
	private Map<String,Capo> capiMap= new HashMap<>();
	private Map<String,Collezione> collezioniMap= new HashMap<>();
	public void leggiFile(String fileName){
		try{
			BufferedReader reader= new BufferedReader(new FileReader(fileName));
			String line;
			while((line=reader.readLine())!=0){
				String[] lineparts=line.split(";");
			switch (lineparts[0]) {
				case "MOD":{
					modelliMap.put(lineparts[1], new Modello(lineparts[1], Double.parseDouble(lineparts[2]), Double.parseDouble(lineparts[3])));
					break;
				}
				case "COL":{
					if(lineparts.length<2){
						coloriMap.put(lineparts[1], new Colore(lineparts[1]));
					}else{
						Collezione newCollezione= new Collezione(lineparts[1]);
						for(int i=2;i<lineparts.length;i++) newCollezione.add(capiMap.get(lineparts[i]));
					}
					break;
				}
				case "MAT":{
					materialiMap.put(lineparts[1], new Materiale(lineparts[1], Double.parseDouble(lineparts[2])));
					break;
				}
				case "CAP":{
					capiMap.put(lineparts[1], new Capo(lineparts[1], modelliMap.get(lineparts[2]), materialiMap.get(lineparts[3]), coloriMap.get(lineparts[4])));
					break;
				}
				default:
					break;
			}
		}

		}catch(IOException e){
			e.getLocalizedMessage();
		}
	}

	public Modello getModello(String name){
		return modelliMap.get(name);
	}

	public Colore getColore(String name){
		return coloriMap.get(name);
	}

	public Materiale getMateriale(String name){
		return materialiMap.get(name);
	}

	public Capo getCapo(String name){
		return capiMap.get(name);
	}

	public Collezione getCollezione(String name){
		return collezioniMap.get(name).getCapoList();
	}

}
