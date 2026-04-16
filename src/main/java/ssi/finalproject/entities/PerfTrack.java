/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssi.finalproject.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "PERFTRACK")
public class PerfTrack {
    @DatabaseField( columnName = "perfTrackId", generatedId = true) private Integer perfTrackId;
    @DatabaseField(columnName = "trackTime") private Date trackTime;
    @DatabaseField(foreign = true, columnName = "pickOrderId") private PickingOrder pickingOrder;
    @DatabaseField(foreign = true, columnName = "userId") private AppUser user;
    @DatabaseField(columnName = "pickCount") private Integer pickCount;
    @DatabaseField(columnName = "duration") private Integer duration;

    public Integer getPerfTrackId() {
        return perfTrackId;
    }

    public void setPerfTrackId(Integer perfTrackId) {
        this.perfTrackId = perfTrackId;
    }

    public Date getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(Date trackTime) {
        this.trackTime = trackTime;
    }

    public PickingOrder getPickingOrder() {
        return pickingOrder;
    }

    public void setPickingOrder(PickingOrder pickingOrder) {
        this.pickingOrder = pickingOrder;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Integer getPickCount() {
        return pickCount;
    }

    public void setPickCount(Integer pickCount) {
        this.pickCount = pickCount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public PerfTrack() {}
}