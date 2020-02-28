package server;

import java.util.ArrayList;
import java.util.Iterator;

import utils.SimpleCSVReader;


public class Dataset implements IDatasetManager {

    private Register register;
    private RegisterManager registerManager;
    private SimpleCSVReader csvReader;
    private RegisterFactory registerFactory;



    //Constructor
    public Dataset() {
        registerFactory = new RegisterFactory();
        this.registerManager = registerFactory.createRegisterManager();
        this.csvReader = registerFactory.createCSV();

    }

    /* Implemented methods from IDataManager */

    @Override
    public int registerDataset(String datasetName, String canonicalPath) {

        if (datasetName == null) {
            System.out.println("you should give a name pall.");
        }

        //check if there is a dataset with this name in data
        if (registerManager.containsName(datasetName)) {
            System.out.println("Contains the name");
            return -1;
        } else {
            register = registerFactory.createRegister();
            register.setName(datasetName);
            register.setPath(canonicalPath);
            register.setData(csvReader.load(canonicalPath));
            registerManager.addRegister(register);
            return 0;
        }

    }

    @Override
    public String[] retrieveDataset(String datasetName, ArrayList<String[]> dataRe) {
        ArrayList<String[]> datas;
        String[] header;
        //checking if there is a dataset.
        if (registerManager.containsName(datasetName)) {
            register = registerManager.getRegister(datasetName);
            header = register.getHeader();
            datas = register.getData();

            Iterator<String[]> it = datas.iterator();
            it.next();
            while (it.hasNext()) {
                String[] row = it.next();
                dataRe.add(row);
            }
            return header;
        } else {
            System.out.println("There is no dataset with this name");
            return null;
        }
    }


    @Override
    public int filterDataset(String originalDatasetName, String newDatasetName, String filterColumnName,
                             String filterValue) {
        int pos = 0;
        ArrayList<String[]> myData;
        ArrayList<String[]> newDataset = new ArrayList<String[]>();
        String[] header;

        if (!registerManager.containsName(originalDatasetName)) {
            System.out.println("There is no dataset with this name");
            return -1;
        }

        register = registerManager.getRegister(originalDatasetName);
        myData = register.getData();
        header = register.getHeader();
        pos = register.getPosition(filterColumnName);
        if (pos == -1) {
            System.out.println("wrong position");
            return -1;
        }
        /** Read all dataset, if attribute = value then put it in a new Dataset*/
        newDataset.add(header);
        for (String[] rows : myData) {
            if (rows[pos].equals(filterValue)) {
                newDataset.add(rows);
            }
        }
        // new Register.
        Register newRegister = new Register();
        newRegister.setName(newDatasetName);
        newRegister.setData(newDataset);
        newRegister.setPath(register.getPath());

        //adding newRegister to database
        registerManager.addRegister(newRegister);
        return 0;
    }

    @Override
    public ArrayList<String[]> getDatasetProjection(String datasetName, ArrayList<String> attributeNames) {
        String xAxis;
        String yAxis;
        ArrayList<String[]> projectedData = null;

        // take attributes.
        xAxis = attributeNames.get(0);
        yAxis = attributeNames.get(1);
        register = registerManager.getRegister(datasetName);
        projectedData = register.getProjectedData(xAxis, yAxis);


        return projectedData;
    }

    } // End of: Dataset





