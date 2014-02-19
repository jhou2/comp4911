package ca.bcit.infosys.comp4911.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;
import java.lang.Override;
import ca.bcit.infosys.comp4911.domain.PayLevel;
import javax.persistence.ManyToOne;
import ca.bcit.infosys.comp4911.domain.User;

/**
 * Created by Graeme on 2/12/14.
 */
public class UserPayRateHistory
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Integer id = null;

   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private Date startDate;

   @Column
   private Date endDate;

   @ManyToOne
   private PayLevel payLevel;

   @ManyToOne
   private User user;

   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;

      UserPayRateHistory that = (UserPayRateHistory) o;

      if (!id.equals(that.id))
         return false;

      return true;
   }

   @Override
   public int hashCode()
   {
      return id.hashCode();
   }

   public Integer getId()
   {

      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return version;
   }

   public void setVersion(int version)
   {
      this.version = version;
   }

   public Date getStartDate()
   {
      return startDate;
   }

   public void setStartDate(Date startDate)
   {
      this.startDate = startDate;
   }

   public Date getEndDate()
   {
      return endDate;
   }

   public void setEndDate(Date endDate)
   {
      this.endDate = endDate;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      return result;
   }

   public PayLevel getPayLevel()
   {
      return this.payLevel;
   }

   public void setPayLevel(final PayLevel payLevel)
   {
      this.payLevel = payLevel;
   }

   public User getUser()
   {
      return this.user;
   }

   public void setUser(final User user)
   {
      this.user = user;
   }
}
