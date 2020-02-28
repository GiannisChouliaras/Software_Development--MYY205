package server;


import java.util.ArrayList;
import java.util.HashMap;

public class DatasetManagerFactory {

	public DatasetManagerFactory() {
		;
	}

	public IDatasetManager create(String className) {
		switch(className) {
			case "DatasetManager": return new Dataset();
			default: return null;
		}
	}
}
