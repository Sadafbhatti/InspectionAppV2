package com.example.inspectionapp;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.List;

public class Checklist {

    public static ArrayList<ListItem> listItem = new ArrayList<>(0);

    Checklist(){
        listItem = new ArrayList<>(49);
        listItem.add( new ListItem("Door latches/power lock operation", "OK"));
        listItem.add( new ListItem("Windshield wiper condition/wiper","REQUIRED"));
        listItem.add( new ListItem("Operation","RECOMMENDED"));
        listItem.add( new ListItem("Parking brake","OK"));
        listItem.add( new ListItem("Brake pedal & master cylinder operation","OK"));
        listItem.add( new ListItem("Seatbelt operation","OK"));
        listItem.add( new ListItem("Instruments, gauges, warning lights","OK"));
        listItem.add( new ListItem("Horn operation","OK"));
        listItem.add( new ListItem("Heating, ventilation, AC system operation","OK"));
        listItem.add( new ListItem("Glass/power window operation","OK"));
        listItem.add( new ListItem("Rearview & side mirror operation","OK"));
        listItem.add( new ListItem("12V outlet(s) operation","OK"));
        listItem.add( new ListItem("Accessory/drive belts","OK"));
        listItem.add( new ListItem("Radiator/coolant system/hoses","OK"));
        listItem.add( new ListItem("Hood latch","OK"));
        listItem.add( new ListItem("Terminals/cables","OK"));
        listItem.add( new ListItem("Engine oil","OK"));
        listItem.add( new ListItem("Coolant","OK"));
        listItem.add( new ListItem("Front differential oil","OK"));
        listItem.add( new ListItem("Rear differential oil","OK"));
        listItem.add( new ListItem("Brake/clutch fluid","OK"));
        listItem.add( new ListItem("Shocks/struts","OK"));
        listItem.add( new ListItem("Suspension & chassis components/bushings/ball joints","OK"));
        listItem.add( new ListItem("Steering system","OK"));
        listItem.add( new ListItem("Axles/CV boots","OK"));
        listItem.add( new ListItem("Prop shaft/U-joint","OK"));
        listItem.add( new ListItem("Engine/Transmission mounts","OK"));
        listItem.add( new ListItem("Front/Rear differentials","OK"));
        listItem.add( new ListItem("Brake hoses/lines","OK"));
        listItem.add( new ListItem("Calipers/sliders","OK"));
        listItem.add( new ListItem("Front wheel bearings","OK"));
        listItem.add( new ListItem("Front pads condition","OK"));
        listItem.add( new ListItem("Front pad/caliper movement","OK"));
        listItem.add( new ListItem("Front tire condition","OK"));
        listItem.add( new ListItem("Rear pads condition","OK"));
        listItem.add( new ListItem("Rear pad/caliper movement","OK"));
        listItem.add( new ListItem("Rear tires condition","OK"));
        listItem.add( new ListItem("Washer fluid","OK"));
        listItem.add( new ListItem("Front rotor condition","OK"));
        listItem.add( new ListItem("Cabin Air Filter","OK"));
        listItem.add( new ListItem("Exterior lighting operation","OK"));
        listItem.add( new ListItem("Engine air filter","OK"));
        listItem.add( new ListItem("Battery test/condition","OK"));
        listItem.add( new ListItem("Exhaust system","OK"));
        listItem.add( new ListItem("Transmission Linkages,clutch hydraulics","OK"));
        listItem.add( new ListItem("Centre diff / viscous coupler binding on turns","OK"));
        listItem.add( new ListItem("Engine/Transmission (Damage/fluid leaks","OK"));
        listItem.add( new ListItem("Engine oil leak","OK"));
        listItem.add( new ListItem("Rear Wheel Bearings","OK"));
    }

        public ArrayList<ListItem> getlistItem() {
        return listItem;
    }

    public void setlistItem(ArrayList<ListItem> listItem) {
        this.listItem = listItem;
    }

    // make several pages with next button instead of one page for all 51 items
//4 pages with 15 item each for easy scrolling for user friendliness
//save every single field with update in db.. 1 table with several rows
//descrption and notes... i need ids. .. objects with id , description title, notes recomendations,
// list of object containing title and status of this item

}
/*public class Store_Manager {
    public ArrayList<Product> productList;
    ArrayList<HistoryProduct> listOfHistory = new ArrayList<>(0);
    public Store_Manager() {
        this.productList = new ArrayList<>(3);
        this.productList.add( new Product("Pante", 20.44, 10));
        this.productList.add( new Product("Shoes", 10.44, 100));
        this.productList.add( new Product("Hats", 5.9, 30));
    }
}
*/