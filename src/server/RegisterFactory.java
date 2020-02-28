package server;

import utils.SimpleCSVReader;

public class RegisterFactory {


    public Register createRegister() {
        return new Register();
    }

    public RegisterManager createRegisterManager() {
        return new RegisterManager();
    }

    public SimpleCSVReader createCSV() {
        return new SimpleCSVReader();
    }
}
