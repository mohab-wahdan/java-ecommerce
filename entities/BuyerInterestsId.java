// default package
// Generated Aug 23, 2024, 4:18:42 PM by Hibernate Tools 6.5.2.Final
package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * BuyerInterestsId generated by hbm2java
 */
@Embeddable
public class BuyerInterestsId  implements java.io.Serializable {


     private int id;
     private String interests;

    public BuyerInterestsId() {
    }

    public BuyerInterestsId(int id, String interests) {
       this.id = id;
       this.interests = interests;
    }
   


    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }


    @Column(name="interests", nullable=false, length=50)
    public String getInterests() {
        return this.interests;
    }
    
    public void setInterests(String interests) {
        this.interests = interests;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BuyerInterestsId) ) return false;
		 BuyerInterestsId castOther = ( BuyerInterestsId ) other; 
         
		 return (this.getId()==castOther.getId())
 && ( (this.getInterests()==castOther.getInterests()) || ( this.getInterests()!=null && castOther.getInterests()!=null && this.getInterests().equals(castOther.getInterests()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
        result = 37 * result + this.getId();
        result = 37 * result + ( getInterests() == null ? 0 : this.getInterests().hashCode() );
         return result;
   }   


}


