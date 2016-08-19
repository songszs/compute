package models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/8/2.
 */
public class UserInfoModel implements Parcelable
{

    private int userId;

    private String userName;

    private String lastEditTime;

    protected UserInfoModel(Parcel in)
    {
        userId = in.readInt();
        userName = in.readString();
        lastEditTime = in.readString();
    }

    public UserInfoModel()
    {

    }

    public static final Creator<UserInfoModel> CREATOR = new Creator<UserInfoModel>()
    {
        @Override
        public UserInfoModel createFromParcel(Parcel in)
        {
            return new UserInfoModel(in);
        }

        @Override
        public UserInfoModel[] newArray(int size)
        {
            return new UserInfoModel[size];
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
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeString(lastEditTime);
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getLastEditTime()
    {
        return lastEditTime;
    }

    public void setLastEditTime(String lastEditTime)
    {
        this.lastEditTime = lastEditTime;
    }
}
