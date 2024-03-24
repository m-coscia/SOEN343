package logic;

public class Login {
    private Profile currentUser;
    private DataBase db = DataBase.getDataBase();

    public Login(Profile user) {
        if (db.profileExists(user)) {
            currentUser = user;
        } else {
            System.out.println("The current user does not exist");
        }
    }
    
    public void setCurrentUser(Profile p){
        currentUser = p;
    }

    public Profile getCurrentUser(){
        return currentUser;
    }



}
