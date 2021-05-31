/*
 * Vendors
 *
 * Version information
 *
 * Date 30/05/2021
 *
 * Author: Kevin Page-Wood and L00162107
 *
 * Copyright notice
 */
package revolut;

import java.util.ArrayList;

public class Vendors {

    private String name;
    ArrayList<String> affiliateVendors = new ArrayList<String>();

    //instance of a vendor
    public Vendors(String name) {
        affiliateVendors.add("Tesco");
        affiliateVendors.add("Penny's");
        affiliateVendors.add("SuperValu");

        this.name = name;

    }

    //check if the vendor is a valid vendor
    public boolean isVendorAnAffiliate(String name) {

        return affiliateVendors.contains(name);
    }
}
