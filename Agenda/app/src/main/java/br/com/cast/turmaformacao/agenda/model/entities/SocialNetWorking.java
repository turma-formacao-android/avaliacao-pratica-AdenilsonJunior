package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adenilson on 01/10/2015.
 */
public class SocialNetWorking  implements Parcelable{

    private Long id;
    private Long contact_id;
    private String link;

    public SocialNetWorking(){
        super();
    }
    public SocialNetWorking(Long id, Long contact_id, String link) {
        this.id = id;
        this.contact_id = contact_id;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialNetWorking that = (SocialNetWorking) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (contact_id != null ? !contact_id.equals(that.contact_id) : that.contact_id != null)
            return false;
        return !(link != null ? !link.equals(that.link) : that.link != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (contact_id != null ? contact_id.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SocialNetWorking{" +
                "id=" + id +
                ", contact_id=" + contact_id +
                ", link='" + link + '\'' +
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
        dest.writeString(this.link);
    }

    protected SocialNetWorking(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.contact_id = (Long) in.readValue(Long.class.getClassLoader());
        this.link = in.readString();
    }

    public static final Creator<SocialNetWorking> CREATOR = new Creator<SocialNetWorking>() {
        public SocialNetWorking createFromParcel(Parcel source) {
            return new SocialNetWorking(source);
        }

        public SocialNetWorking[] newArray(int size) {
            return new SocialNetWorking[size];
        }
    };
}
