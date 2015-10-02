package br.com.cast.turmaformacao.agenda.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class Email implements Parcelable {

    private Long id;
    private Long contact_id;
    private String email;

    public Email(){
        super();
    }

    public Email(Long id, Long contact_id, String email) {
        this.id = id;
        this.contact_id = contact_id;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email1 = (Email) o;

        if (id != null ? !id.equals(email1.id) : email1.id != null) return false;
        if (contact_id != null ? !contact_id.equals(email1.contact_id) : email1.contact_id != null)
            return false;
        return !(email != null ? !email.equals(email1.email) : email1.email != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (contact_id != null ? contact_id.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", contact_id=" + contact_id +
                ", email='" + email + '\'' +
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
        dest.writeString(this.email);
    }

    protected Email(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.contact_id = (Long) in.readValue(Long.class.getClassLoader());
        this.email = in.readString();
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
