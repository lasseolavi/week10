package com.example.week10;

import android.content.Context;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserStorage {
    private ArrayList<User> Users = new ArrayList<>();
    private static UserStorage userStorage = null;
    private static final  String FILENAME = "users.data";

    private UserStorage(){
    }
    public  static UserStorage getInstance(){
        if (userStorage == null){
            userStorage = new UserStorage();
        }
        return userStorage;
    }
    public ArrayList getUsers(){
        return Users;
    }
    public void addUser(User user, Context context){
        Users.add(user);
        try {
            ObjectOutputStream userWriter = new ObjectOutputStream(context.openFileOutput(FILENAME,Context.MODE_PRIVATE));
            userWriter.writeObject(Users);
            userWriter.close();
        } catch (IOException e) {
            System.out.println("Käyttäjien tallentaminen ei onnistunut");
        }
    }

    public void removeUser(int id){
        Users.remove(id);
    }



    public UserStorage loadUsers(Context context) {
        try {
            ObjectInputStream userReader = new ObjectInputStream(context.openFileInput(FILENAME));
            Users = (ArrayList<User>) userReader.readObject();
            userReader.close();
        }catch (FileNotFoundException e){
            System.out.println("Käyttäjien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut");
            e.printStackTrace();
        }
        return null;
    }
}
