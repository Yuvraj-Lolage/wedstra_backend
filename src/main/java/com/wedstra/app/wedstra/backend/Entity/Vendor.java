package com.wedstra.app.wedstra.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "vendors")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vendor {
   private String id;
   private String vendor_name;
   private String business_name;
   private String business_category;
   private String vendor_aadharCard;
   private String vendor_PAN;
   private String business_PAN;
   private String electricity_bill;
   private List<String> business_photos;
   private String liscence;
   private String gst_number;
   private String terms_and_conditions;
   private String email;
   private String phone_no;
   private String password;
   private String city;

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_category() {
        return business_category;
    }

    public void setBusiness_category(String business_category) {
        this.business_category = business_category;
    }

    public String getVendor_aadharCard() {
        return vendor_aadharCard;
    }

    public void setVendor_aadharCard(String vendor_aadharCard) {
        this.vendor_aadharCard = vendor_aadharCard;
    }

    public String getVendor_PAN() {
        return vendor_PAN;
    }

    public void setVendor_PAN(String vendor_PAN) {
        this.vendor_PAN = vendor_PAN;
    }

    public String getBusiness_PAN() {
        return business_PAN;
    }

    public void setBusiness_PAN(String business_PAN) {
        this.business_PAN = business_PAN;
    }

    public String getElectricity_bill() {
        return electricity_bill;
    }

    public void setElectricity_bill(String electricity_bill) {
        this.electricity_bill = electricity_bill;
    }

    public List<String> getBusiness_photos() {
        return business_photos;
    }

    public void setBusiness_photos(List<String> business_photos) {
        this.business_photos = business_photos;
    }

    public String getLiscence() {
        return liscence;
    }

    public void setLiscence(String liscence) {
        this.liscence = liscence;
    }

    public String getGst_number() {
        return gst_number;
    }

    public void setGst_number(String gst_number) {
        this.gst_number = gst_number;
    }

    public String getTerms_and_conditions() {
        return terms_and_conditions;
    }

    public void setTerms_and_conditions(String terms_and_conditions) {
        this.terms_and_conditions = terms_and_conditions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }
}