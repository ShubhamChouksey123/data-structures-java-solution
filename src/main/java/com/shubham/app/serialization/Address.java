package com.shubham.app.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Address implements Externalizable {

    private int addressId;
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String country;
    private String postalCode;

    public Address() {
    }

    public Address(int addressId, String addressLine1, String addressLine2, String state, String country,
            String postalCode) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" + "addressId=" + addressId + ", addressLine1='" + addressLine1 + '\'' + ", addressLine2='"
                + addressLine2 + '\'' + ", state='" + state + '\'' + ", country='" + country + '\'' + ", postalCode='"
                + postalCode + '\'' + '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(addressId);
        out.writeObject(addressLine1);
        out.writeObject(addressLine2);
        out.writeObject(state);
        // out.writeObject(country);
        // out.writeObject(postalCode);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.addressId = in.readInt();
        this.addressLine1 = (String) in.readObject();
        this.addressLine2 = (String) in.readObject();
        this.state = (String) in.readObject();
        // this.country = (String) in.readObject();
        // this.postalCode = (String) in.readObject();
    }
}
