package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adenilson on 01/10/2015.
 */
public class Telephone implements Parcelable{

    private Long id;
    private Long contact_id;
    private Long number;

    public Telephone(){
        super();
    }
    public Telephone(Long id, Long contact_id, Long number) {
        this.id = id;
        this.contact_id = contact_id;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContact_id() {
        return contact_id;
    }

    public void setContact_id(Long contact_id) {
        this.contact_id = contact_id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Telephone telephone = (Telephone) o;

        if (id != null ? !id.equals(telephone.id) : telephone.id != null) return false;
        if (contact_id != null ? !contact_id.equals(telephone.contact_id) : telephone.contact_id != null)
            return false;
        return !(number != null ? !number.equals(telephone.number) : telephone.number != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (contact_id != null ? contact_id.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "id=" + id +
                ", contact_id=" + contact_id +
                ", number=" + number +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.contact_id);
        dest.writeValue(this.number);
    }

    protected Telephone(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.contact_id = (Long) in.readValue(Long.class.getClassLoader());
        this.number = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Telephone> CREATOR = new Creator<Telephone>() {
        public Telephone createFromParcel(Parcel source) {
            return new Telephone(source);
        }

        public Telephone[] newArray(int size) {
            return new Telephone[size];
        }
    };
}
