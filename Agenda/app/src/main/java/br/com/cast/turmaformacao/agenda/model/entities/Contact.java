package br.com.cast.turmaformacao.agenda.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Contact implements Parcelable{

    private Long id;
    private String name;
    private ArrayList<Telephone> telephones;
    private ArrayList<Email> emails;
    private ArrayList<SocialNetWorking> socialNetworking;
    private Address address;

    public Contact(){
        super();
        address = new Address();
    }

    public Contact(Long id, String name, ArrayList<Telephone> telephones,
                   ArrayList<Email> emails, ArrayList<SocialNetWorking> socialNetworking, Address address) {
        this.id = id;
        this.name = name;
        this.telephones = telephones;
        this.emails = emails;
        this.socialNetworking = socialNetworking;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(ArrayList<Telephone> telephones) {
        this.telephones = telephones;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    public ArrayList<SocialNetWorking> getSocialNetworking() {
        return socialNetworking;
    }

    public void setSocialNetworking(ArrayList<SocialNetWorking> socialNetworking) {
        this.socialNetworking = socialNetworking;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (telephones != null ? !telephones.equals(contact.telephones) : contact.telephones != null)
            return false;
        if (emails != null ? !emails.equals(contact.emails) : contact.emails != null) return false;
        if (socialNetworking != null ? !socialNetworking.equals(contact.socialNetworking) : contact.socialNetworking != null)
            return false;
        return !(address != null ? !address.equals(contact.address) : contact.address != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (telephones != null ? telephones.hashCode() : 0);
        result = 31 * result + (emails != null ? emails.hashCode() : 0);
        result = 31 * result + (socialNetworking != null ? socialNetworking.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephones=" + telephones +
                ", emails=" + emails +
                ", socialNetworking=" + socialNetworking +
                ", address=" + address +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeList(this.telephones);
        dest.writeTypedList(emails);
        dest.writeList(this.socialNetworking);
        dest.writeParcelable(this.address, 0);
    }

    protected Contact(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.telephones = new ArrayList<Telephone>();
        in.readList(this.telephones, List.class.getClassLoader());
        this.emails = in.createTypedArrayList(Email.CREATOR);
        this.socialNetworking = new ArrayList<SocialNetWorking>();
        in.readList(this.socialNetworking, List.class.getClassLoader());
        this.address = in.readParcelable(Address.class.getClassLoader());
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
