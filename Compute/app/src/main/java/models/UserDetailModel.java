package models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/8/2.
 */
public class UserDetailModel implements Parcelable
{

    private int id;

    /**
     * 类别
     * */
    private String type;

    /**
     * 毛重
     * */
    private float grossWeight;

    /**
     * 皮重
     * */
    private float tare;

    /**
     * 单价
     * */
    private float unitPrice;

    /**
     * 金额
     * */
    private float sumMoney;

    protected UserDetailModel(Parcel in)
    {
        id = in.readInt();
        type = in.readString();
        grossWeight = in.readFloat();
        tare = in.readFloat();
        unitPrice = in.readFloat();
        sumMoney = in.readFloat();
    }

    public static final Creator<UserDetailModel> CREATOR = new Creator<UserDetailModel>()
    {
        @Override
        public UserDetailModel createFromParcel(Parcel in)
        {
            return new UserDetailModel(in);
        }

        @Override
        public UserDetailModel[] newArray(int size)
        {
            return new UserDetailModel[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(type);
        dest.writeFloat(grossWeight);
        dest.writeFloat(tare);
        dest.writeFloat(unitPrice);
        dest.writeFloat(sumMoney);
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public float getGrossWeight()
    {
        return grossWeight;
    }

    public void setGrossWeight(float grossWeight)
    {
        this.grossWeight = grossWeight;
    }

    public float getTare()
    {
        return tare;
    }

    public void setTare(float tare)
    {
        this.tare = tare;
    }

    public float getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public float getSumMoney()
    {
        return sumMoney;
    }

    public void setSumMoney(float sumMoney)
    {
        this.sumMoney = sumMoney;
    }
}
