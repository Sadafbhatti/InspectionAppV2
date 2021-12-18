package com.example.inspectionapp;

import android.app.Application;

public class myApp extends Application {
    private static myApp mInstance;

    private Checklist mychecklist =  new Checklist();
    private NetworkManager myNetworkManager=new NetworkManager();
    private Vehicle myVehicle=new Vehicle();
    private Shop myShop=new Shop();
    private Customer myCustomer=new Customer();
  //  private ListItem myListItem=new ListItem();
  @Override
  public void onCreate() {
      super.onCreate();
      mInstance = this;
  }


    public Checklist getChecklist() {
        return mychecklist;
    }

    public NetworkManager getmyNetworkManager() {
        return myNetworkManager;
    }
    public Vehicle getmyVehicle() {
        return myVehicle;
    }
    public Shop getmyShop() {
        return myShop;
    }
    public Customer getmyCustomer() {
        return myCustomer;
    }
   // public ListItem getListItem() {
    //    return myListItem;
   // }

    public static myApp getInstance() { return mInstance; }

}

