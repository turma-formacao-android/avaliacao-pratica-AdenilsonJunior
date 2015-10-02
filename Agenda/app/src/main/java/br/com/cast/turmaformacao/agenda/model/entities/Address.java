package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address implements Parcelable {


    @JsonProperty("cep")
    private Long zipCode;

    @JsonProperty("tipoDeLogradouro")
    private String type;

    @JsonProperty("logradouro")
    private String Street;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("estado")
    private String state;


    public Address() {
        super();
    }

    public Address(Long zipCode, String type,
                   String street, String neighborhood, String city, String state) {
        this.zipCode = zipCode;
        this.type = type;
        Street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null)
            return false;
        if (type != null ? !type.equals(address.type) : address.type != null) return false;
        if (Street != null ? !Street.equals(address.Street) : address.Street != null) return false;
        if (neighborhood != null ? !neighborhood.equals(address.neighborhood) : address.neighborhood != null)
            return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        return !(state != null ? !state.equals(address.state) : address.state != null);

    }

    @Override
    public int hashCode() {
        int result = zipCode != null ? zipCode.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (Street != null ? Street.hashCode() : 0);
        result = 31 * result + (neighborhood != null ? neighborhood.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.zipCode);
        dest.writeString(this.type);
        dest.writeString(this.Street);
        dest.writeString(this.neighborhood);
        dest.writeString(this.city);
        dest.writeString(this.state);
    }

    protected Address(Parcel in) {
        this.zipCode = (Long) in.readValue(Long.class.getClassLoader());
        this.type = in.readString();
        this.Street = in.readString();
        this.neighborhood = in.readString();
        this.city = in.readString();
        this.state = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
