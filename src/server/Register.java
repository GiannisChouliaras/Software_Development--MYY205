package server;

import java.util.ArrayList;
import java.util.Iterator;

public class Register {

    private String name;
    private String canonicalPath;
    private ArrayList<String[]> data;

    //Constructor
    public Register() {
        ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String canonicalPath) {
        this.canonicalPath = canonicalPath;
    }

    public void setData(ArrayList<String[]> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String[]> getData() {
        return data;
    }

    public String getPath() {
        return canonicalPath;
    }


    public String[] getHeader() {
        String[] header;
        header = data.get(0);
        return header;
    }

    public int getPosition(String attributeFilter) {
        String [] header;
        header = data.get(0);

        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(attributeFilter)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String[]> getProjectedData(String x, String y) {
        int posX = 0;
        int posY = 0;
        ArrayList<String[]> dataClone = new ArrayList<>();

        Iterator<String[]> it = data.iterator();
        while (it.hasNext()) {
            dataClone.add((String[]) it.next().clone());
        }

        //take positions.
        String[] header = dataClone.get(0);
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(x)) {
                posX = i;
            }
            if (header[i].equals(y)) {
                posY = i;
            }
        }


        dataClone.remove(0);
        for (String[] rows : dataClone) {
            rows[0] = rows[posX];
            rows[1] = rows[posY];
        }

        return dataClone;
    }
}