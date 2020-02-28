package server;

import java.util.ArrayList;


public class RegisterManager {

    protected  ArrayList<Register> allData = new ArrayList<Register>();

    //Constructor
    public RegisterManager() {
        ;
    }



    public void addRegister(Register register){
        allData.add(register);
    }

    public Register getRegister(String name) {
        for (Register register : allData) {
            if (register.getName().equals(name)) {
                return register;
            }
        }
        return null;
    }

    public ArrayList<String[]> getData(String name) {
        ArrayList<String[]> myData = new ArrayList<String[]>();
        for (Register register : allData) {
            if (register.getName().equals(name)) {
                myData = register.getData();
            }
        }
        return myData;
    }

    public boolean containsName(String name) {
        for (Register register : allData) {
            if (register.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}

